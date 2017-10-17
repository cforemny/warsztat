package com.mkyong.sqlBase;

import com.mkyong.payment.Summary;
import com.mkyong.utils.ManagerTask;
import com.mkyong.utils.Note;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Cyprian on 2017-09-28.
 */
@Component
public class ManagerTaskCreator extends Summary {


    private Statement statement = getConnection().createStatement();
    private ResultSet resultSet;

    public ManagerTaskCreator() throws SQLException, ClassNotFoundException {
    }

    public List getTasksList() {

        List<ManagerTask> taskList = new ArrayList<ManagerTask>();

        try {
            getConnection();
            String query = "select * from zadaniaManagera";
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                int id = resultSet.getInt("zadanieid");
                String zadanie = resultSet.getString("typZadania");
                String uwagi = resultSet.getString("uwagi");
                String status = resultSet.getString("status");

                taskList.add(new ManagerTask(id, zadanie, uwagi, status));
            }

        } catch (Exception exception) {
            System.out.println(exception);
        }
        Collections.reverse(taskList);
        return taskList;
    }

    public void confirmTask(ManagerTask managerTask) {

        try {
            getConnection();
            String query = "UPDATE zadaniaManagera SET uwagi =' " + managerTask.getComments() + "', status = 'wykonane' " +
                    " WHERE zadanieId = " + managerTask.getId();
            statement.execute(query);

        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public void createNote(Note note) {

        try {
            getConnection();
            String query = "INSERT INTO  notatki (notatkaId, tresc) VALUES ('" + note.getId() + "','" + note.getContent() + "')";
            statement.execute(query);

        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public List getNoteList() {

        List<Note> noteList = new ArrayList<Note>();

        try {
            getConnection();
            String query = "select * from notatki";
            resultSet = statement.executeQuery(query);

            int i = 0;
            while (resultSet.next() || i == 10) {

                int id = resultSet.getInt("notatkaId");
                String tresc = resultSet.getString("tresc");
                noteList.add(new Note(tresc, id));
                i++;
            }

        } catch (Exception exception) {
            System.out.println(exception);
        }
        Collections.reverse(noteList);
        return noteList;
    }
}
