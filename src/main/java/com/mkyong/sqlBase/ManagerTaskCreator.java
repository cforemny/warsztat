package com.mkyong.sqlBase;

import com.mkyong.payment.Summary;
import com.mkyong.utils.ManagerTask;
import com.mkyong.utils.Note;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * Created by Cyprian on 2017-09-28.
 */
@Component
public class ManagerTaskCreator extends Summary {

    public ManagerTaskCreator() throws SQLException, ClassNotFoundException {
    }

    public List getTasksList() {

        String query = "SELECT * FROM zadaniaManagera";
        List<ManagerTask> taskList = getJdbcTemplate().query(query, new BeanPropertyRowMapper(ManagerTask.class));
        Collections.reverse(taskList);

        return taskList;
    }

    public void confirmTask(ManagerTask managerTask) {

        String query = "UPDATE zadaniaManagera SET uwagi =' " + managerTask.getUwagi() + "', status = 'wykonane' " +
                " WHERE zadanieId = " + managerTask.getZadanieId();
        getJdbcTemplate().execute(query);
    }

    public void addTask(ManagerTask managerTask) {

        String query = "INSERT INTO zadaniaManagera (status, typZadania) VALUES('niewykonane','" + managerTask.getTypZadania() + "')";
        getJdbcTemplate().execute(query);
    }

    public void createNote(Note note) {

        String query = "INSERT INTO  notatki (notatkaId, tresc) VALUES ('" + note.getId() + "','" + note.getContent() + "')";
        getJdbcTemplate().execute(query);
    }

    public List getNoteList() {

        String query = "SELECT * FROM notatki";
        List<Note> noteList = getJdbcTemplate().query(query, new BeanPropertyRowMapper(Note.class));
        noteList = noteList.subList(noteList.size() - 10, noteList.size());
        Collections.reverse(noteList);

        return noteList;
    }
}
