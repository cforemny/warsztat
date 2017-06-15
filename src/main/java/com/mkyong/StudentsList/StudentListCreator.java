package com.mkyong.StudentsList;

import org.springframework.stereotype.Component;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Cyprian on 2017-06-04.
 */

@Component
public class StudentListCreator {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private Date date;

    public List getStudentListFromTable(String tableName) {

        List<Student> studentList = new ArrayList<Student>();
        try {
            getConnection();
            String query = "select * from " + tableName;
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String imie = resultSet.getString("imie");
                String nazwisko = resultSet.getString("nazwisko");
                String wiek = resultSet.getString("wiek");
                String email = resultSet.getString("email");
                String daneRodzica = resultSet.getString("parentName");
                String telefon = resultSet.getString("telephone");
                String id = resultSet.getString("id");
                studentList.add(new Student(imie, nazwisko, wiek, email, daneRodzica, telefon,id));
            }

        } catch (Exception exception) {
            System.out.println(exception);
        }
        return studentList;
    }

    public void addStudentToList(Student student, String tableName) {

        try {
            getConnection();

            String insertQuery = "INSERT INTO " + tableName  + " (imie, nazwisko, wiek, email, parentName, telephone, id)"
                    + " VALUES " + "('" + student.getName()+ "'," + "'" + student.getLastName()+ "'," + "'" + student.getAge()+ "',"
                    + "'" + student.getEmail()+ "'," + "'" + student.getParentName()+ "'," + "'" + student.getTelephone()+ "',"
                    + "'" + student.getId() + "')";

            statement.execute(insertQuery);

        } catch (Exception exception) {
            System.out.println(exception);

        }
    }

    public void deleteStudent(String studentListId, String tableName){
        try{
            getConnection();
            String query = "DELETE FROM " + tableName + " WHERE " + tableName + ".id="+studentListId;
            statement.execute(query);

        }catch(Exception exception){
            System.out.println(exception);
        }

    }

    public void addNewDate(String tableName, String date){

        try{
            getConnection();
            String query = "ALTER TABLE " + tableName + " ADD " + date +" DATE";
            statement.execute(query);

        }catch(Exception exception){
            System.out.println(exception);
        }
    }

    public List getDateTable(String tableName){

            List<String> dateList = new ArrayList<String>();
            try {
                getConnection();
                String query = "select * from " + tableName;
                resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    String date = resultSet.getString("data");
                    dateList.add(date);
                }

            } catch (Exception exception) {
                System.out.println(exception);
            }
            return dateList;
        }

    private void getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/warsztatyrobotow?useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
        statement = connection.createStatement();
    }
}
