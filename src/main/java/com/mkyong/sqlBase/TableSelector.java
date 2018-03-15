package com.mkyong.sqlBase;

import com.mkyong.payment.paymentSummary.Payment;
import com.mkyong.utils.Student;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    public List getStudentListFromTable(String tableName, boolean checkbox) {

        List<Student> studentList = new ArrayList<Student>();
        List<Student> studentListRemoved = new ArrayList<Student>();
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
        if (!checkbox) {
            return studentList;
        } else {
            for (Student student : studentList) {
                if (!removeStudentFromList(student, tableName) || Integer.parseInt(student.getId()) == studentList.size() ) {
                    studentListRemoved.add(student);
                }
            }
            return studentListRemoved;
        }
    }


    public List getDateTable(String tableName, boolean checkbox) {

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
        if (checkbox && dateList.size() >= 8) {
            List<String> sublist = dateList.subList(dateList.size() - 8, dateList.size());
            return sublist;
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
            String query = "SHOW TABLES FROM testowa";
            resultSet = statement.executeQuery(query);
            resultSet.toString();

            while (resultSet.next()) {
                String tableName = resultSet.getString("Tables_in_testowa");
                tableList.add(tableName);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return tableList;
    }

    public void removeDuplicatesFromPayments(String tableName) {

        List<Payment> paymentList = getPaymentList(tableName);

        for (Payment comperingPayment : paymentList) {
            if (comperingPayment.getPaymentValue() == 0 && comperingPayment.getPaymentType() == 'T') {
                for (Payment comparedPayment : paymentList) {
                    if (comparedPayment.getPaymentValue() > 0 &&
                            comparedPayment.getPaymentDate().equals(comperingPayment.getPaymentDate()) &&
                            comparedPayment.getStudentId().equals(comperingPayment.getStudentId())) {
                        try {
                            getConnection();
                            String query = "DELETE FROM " + tableName + " WHERE studentId = " + comperingPayment.getStudentId() +
                                    " AND data = '" + comperingPayment.getPaymentDate() + "' AND typPlatnosci = 'T'" + " AND platnosc = " +
                                    comperingPayment.getPaymentValue();
                            statement.execute(query);
                        } catch (Exception e) {
                            System.out.println(e);

                        }
                    }
                }
            }
        }
    }

    private void getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql:// 144.76.228.149:3306/testowa?useLegacyDatetimeCode=false&serverTimezone=UTC", "cypek", "foremny1a");
        statement = connection.createStatement();
    }

    private boolean removeStudentFromList(Student student, String tableName) {

        int countPayments = 12;

        List<Payment> paymentList = getPaymentList("platnosci" + tableName);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();
        String currentDate = dtf.format(localDate);
        int paymentDateMontAsInt;
        int currenttDateMontAsInt;

        for (Payment payment : paymentList) {
            if (student.getId().equals(payment.getStudentId())) {
                String paymentDate = payment.getPaymentDate();
                paymentDateMontAsInt = Integer.parseInt(paymentDate.substring(5, 7));
                currenttDateMontAsInt = Integer.parseInt(currentDate.substring(5, 7));

                if (paymentDateMontAsInt == currenttDateMontAsInt ||
                        paymentDateMontAsInt == currenttDateMontAsInt - 1 ||
                        paymentDateMontAsInt == currenttDateMontAsInt - 2 ||
                        paymentDateMontAsInt == currenttDateMontAsInt + 1) {
                    countPayments--;
                }
            }
        }
        if (countPayments == 12) {
            return true;
        } else
            return false;
    }
}
