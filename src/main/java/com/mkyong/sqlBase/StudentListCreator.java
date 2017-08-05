package com.mkyong.sqlBase;

import com.mkyong.utils.Student;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Cyprian on 2017-06-04.
 */

@Component
public class StudentListCreator {

    private Connection connection;
    private Statement statement;

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

        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteStudent(String studentListId, String tableName) {
        try {
            getConnection();
            String query = "DELETE FROM " + tableName + " WHERE " + tableName + ".id=" + studentListId;
            statement.execute(query);

        } catch (Exception exception) {
            System.out.println(exception);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public void addNewPayment(String tableName, double paymentValue, String studentId, String date, char typPlatnosci) {


        try {
            getConnection();
            String queryPayment = "INSERT INTO " + tableName + " (studentId,data,platnosc, typPlatnosci) " + " VALUES (" + Integer.parseInt(studentId) + ",'" + date + "'," + paymentValue +
                    ",'" + typPlatnosci + "')";
            statement.execute(queryPayment);


        } catch (Exception exception) {
            System.out.println(exception);
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void removePayment(String tableName, String studentId, String date) {

        try {
            getConnection();
            String queryPayment = "DELETE from " + tableName + " WHERE studentId = " + Integer.parseInt(studentId) + " AND  data = '" + date+"'";
            statement.execute(queryPayment);


        } catch (Exception exception) {
            System.out.println(exception);
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


    private void getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql:// cfrobotics.nazwa.pl:3306/cfrobotics?useLegacyDatetimeCode=false&serverTimezone=UTC", "cfrobotics", "cfRoB0T!C$");
        statement = connection.createStatement();
    }
}
