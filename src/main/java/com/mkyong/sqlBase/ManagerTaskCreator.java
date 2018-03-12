package com.mkyong.sqlBase;

import com.mkyong.payment.Summary;
import com.mkyong.utils.ManagerTask;
import com.mkyong.utils.Note;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Cyprian on 2017-09-28.
 */
@Component
public class ManagerTaskCreator extends Summary {


    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public ManagerTaskCreator() throws SQLException, ClassNotFoundException {
    }

    public List getTasksList() {

        List<ManagerTask> taskList = new ArrayList<ManagerTask>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql:// 144.76.228.149:3306/testowa?useLegacyDatetimeCode=false&serverTimezone=UTC", "cypek", "foremny1a");
            statement = connection.createStatement();
            String query = "SELECT * FROM zadaniaManagera";
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                int id = resultSet.getInt("zadanieid");
                String zadanie = resultSet.getString("typZadania");
                String uwagi = resultSet.getString("uwagi");
                String status = resultSet.getString("status");

                taskList.add(new ManagerTask(id, zadanie, uwagi, status));
            }
            connection.close();
        } catch (Exception exception) {
            System.out.println(exception);
        }
        Collections.reverse(taskList);
        return taskList;
    }

    public void confirmTask(ManagerTask managerTask) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql:// 144.76.228.149:3306/testowa?useLegacyDatetimeCode=false&serverTimezone=UTC", "cypek", "foremny1a");
            statement = connection.createStatement();
            String query = "UPDATE zadaniaManagera SET uwagi =' " + managerTask.getComments() + "', status = 'wykonane' " +
                    " WHERE zadanieId = " + managerTask.getId();
            statement.execute(query);
            connection.close();
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public void addTask(ManagerTask managerTask) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql:// 144.76.228.149:3306/testowa?useLegacyDatetimeCode=false&serverTimezone=UTC", "cypek", "foremny1a");
            statement = connection.createStatement();
            String query = "INSERT INTO zadaniaManagera (status, typZadania) VALUES('niewykonane','" + managerTask.getTask() + "')";
            statement.execute(query);
            connection.close();
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public void createNote(Note note) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql:// 144.76.228.149:3306/testowa?useLegacyDatetimeCode=false&serverTimezone=UTC", "cypek", "foremny1a");
            statement = connection.createStatement();
            String query = "INSERT INTO  notatki (notatkaId, tresc) VALUES ('" + note.getId() + "','" + note.getContent() + "')";
            statement.execute(query);
            connection.close();
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public List getNoteList() {

        List<Note> noteList = new ArrayList<Note>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql:// 144.76.228.149:3306/testowa?useLegacyDatetimeCode=false&serverTimezone=UTC", "cypek", "foremny1a");
            statement = connection.createStatement();
            String query = "SELECT * FROM notatki";
            resultSet = statement.executeQuery(query);

            int i = 0;
            while (resultSet.next() || i == 10) {

                int id = resultSet.getInt("notatkaId");
                String tresc = resultSet.getString("tresc");
                noteList.add(new Note(tresc, id));
                i++;
            }
            connection.close();
        } catch (Exception exception) {
            System.out.println(exception);
        }
        Collections.reverse(noteList);
        return noteList;
    }
}
