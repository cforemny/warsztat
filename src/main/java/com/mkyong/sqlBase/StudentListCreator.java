package com.mkyong.sqlBase;

import com.mkyong.payment.paymentSummary.Payment;
import com.mkyong.utils.Date;
import com.mkyong.utils.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by Cyprian on 2017-06-04.
 */

@Component
public class StudentListCreator {

    private Connection connection;
    private Statement statement;
    @Autowired
    private TableSelector tableSelector;


    public void addStudentToList(Student student, String tableName, int studentId) {
        try {
            getConnection();
            String insertQuery = "INSERT INTO " + tableName + " (imie, nazwisko, wiek, email, parentName, telephone, id)"
                    + " VALUES " + "('" + student.getName() + "'," + "'" + student.getLastName() + "'," + "'" + student.getAge() + "',"
                    + "'" + student.getEmail() + "'," + "'" + student.getParentName() + "'," + "'" + student.getTelephone() + "',"
                    + "'" + studentId + "')";

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


    public void addNewPayment(String tableName, Payment payment, String studentId, String date, char typPlatnosci) {
        try {
            getConnection();

            if(payment.getPaymentCount() == 1){
                String queryPayment = "INSERT INTO " + tableName + " (studentId,data,platnosc, typPlatnosci) " + " VALUES (" + Integer.parseInt(studentId) + ",'" + date + "'," + payment.getPaymentValue() +
                        ",'" + typPlatnosci + "')";
                statement.execute(queryPayment);
            }else {
                List<Date> dateTable = tableSelector.getDateTable(tableName, true);

            }

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

    public void removePayment(String tableName, String studentId, String date) {
            try {
                getConnection();
                String queryPayment = "DELETE from " + tableName + " WHERE studentId = " + Integer.parseInt(studentId) + " AND  data = '" + date + "'";
                statement.execute(queryPayment);
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



    private void getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql:// 144.76.228.149:3306/testowa?useLegacyDatetimeCode=false&serverTimezone=UTC", "cypek", "foremny1a");
        statement = connection.createStatement();
    }

    public void removePayment(String tableName, String date) {
        try {
            getConnection();
            String queryDate = "DELETE from " + tableName + " WHERE data = '" + date + "'";
            statement.execute(queryDate);
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
}
