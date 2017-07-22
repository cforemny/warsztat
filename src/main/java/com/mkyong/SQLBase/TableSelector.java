package com.mkyong.SQLBase;

import com.mkyong.utils.Student;
import com.mkyong.payment.paymentSummary.Payment;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cyprian on 2017-06-23.
 */
@Component
public class TableSelector {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

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
                studentList.add(new Student(imie, nazwisko, wiek, email, daneRodzica, telefon, id));
            }

        } catch (Exception exception) {
            System.out.println(exception);
        }
        return studentList;
    }

    @SuppressWarnings("ThrowablePrintedToSystemOut")
    public List getDateTable(String tableName) {

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

    public List getPaymentList(String tableName) {

        ArrayList<Payment> paymentList = new ArrayList<>();
        try {
            getConnection();
            String query = "select * from " + tableName;
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String studentId = resultSet.getString("studentId");
                String date = resultSet.getString("data");
                String paymentValue = resultSet.getString("platnosc");
                String paymentId = resultSet.getString("obecnoscId");
                String paymentType = resultSet.getString("typPlatnosci");

                paymentList.add(new Payment(studentId, date, Integer.parseInt(paymentValue), Integer.parseInt(paymentId), paymentType.charAt(0)));

            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
        return paymentList;
    }

    public List showTablesFromBase() {

        List<String> tableList = new ArrayList<>();
        try {
            getConnection();
            String query = "show TABLES from cfrobotics";
            resultSet = statement.executeQuery(query);
            resultSet.toString();

            while (resultSet.next()) {
                String tableName = resultSet.getString("Tables_in_cfrobotics");
                tableList.add(tableName);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return tableList;
    }

    private void getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql:// cfrobotics.nazwa.pl:3306/cfrobotics?useLegacyDatetimeCode=false&serverTimezone=UTC", "cfrobotics", "cfRoB0T!C$");
        statement = connection.createStatement();
    }
}
