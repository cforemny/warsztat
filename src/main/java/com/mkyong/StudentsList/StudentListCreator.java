package com.mkyong.StudentsList;

import com.mkyong.payment.Payment;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cyprian on 2017-06-04.
 */

@Component
public class StudentListCreator {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;


    public void addStudentToList(Student student, String tableName) {

        try {
            getConnection();

            String insertQuery = "INSERT INTO " + tableName + " (imie, nazwisko, wiek, email, parentName, telephone, id)"
                    + " VALUES " + "('" + student.getName() + "'," + "'" + student.getLastName() + "'," + "'" + student.getAge() + "',"
                    + "'" + student.getEmail() + "'," + "'" + student.getParentName() + "'," + "'" + student.getTelephone() + "',"
                    + "'" + student.getId() + "')";

            statement.execute(insertQuery);

        } catch (Exception exception) {
            System.out.println(exception);

        }
    }

    public void deleteStudent(String studentListId, String tableName) {
        try {
            getConnection();
            String query = "DELETE FROM " + tableName + " WHERE " + tableName + ".id=" + studentListId;
            statement.execute(query);

        } catch (Exception exception) {
            System.out.println(exception);
        }

    }

    public void addNewDate(String tableName, String date) {

        try {
            getConnection();
            String queryData = "INSERT INTO " + tableName + " (data) " + " VALUES ('" + date + "')";
            statement.execute(queryData);
            String queryPayment = "INSERT INTO platnosci " + " (studentId,data,platnosc) " + " VALUES (0,'" + date + "',0)";
            statement.execute(queryPayment);


        } catch (Exception exception) {
            System.out.println(exception);
        }
    }


    public void addNewPayment(String tableName, int paymentValue, String studentId, String date) {


        try {
            getConnection();
            String queryPayment = "INSERT INTO " + tableName + " (studentId,data,platnosc) " + " VALUES (" + Integer.parseInt(studentId) + ",'" + date + "'," + paymentValue + ")";
            statement.execute(queryPayment);


        } catch (Exception exception) {
            System.out.println(exception);
        }

    }


    private void getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/warsztatyrobotow?useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
        statement = connection.createStatement();
    }
}
