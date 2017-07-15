package com.mkyong.payment.paymentSummary;

import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cyprian on 2017-07-11.
 */
@Component
public class MonthIncome {

    private Connection connection;
    private Statement statement = getConnection();
    private ResultSet resultSet;

    public MonthIncome() throws SQLException, ClassNotFoundException {
    }


    public int getCashFromLocations(String date) {

        int cashPayment = 0;
        try {
            List<String> paymentTables = preapareTableList();
            String year = getYearForSummary(date);
            String month = getMonthForSummary(date);
            date = switchMonth(month);
            for (String paymentTable : paymentTables) {
                String query = "select data, platnosc, typPlatnosci from " + paymentTable + " WHERE data LIKE '" + year + "%'" +
                        "AND data LIKE '%-" + date + "-%'";
                resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    String paymentType = resultSet.getString("typPlatnosci");
                    String paymentValue = resultSet.getString("platnosc");
                    if (paymentType.equals("T")) {
                        cashPayment = cashPayment + Integer.parseInt(paymentValue);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cashPayment;
    }

    public int getRemittamceFromLocations(String date) {
        int remittancePayment = 0;
        try {

            List<String> paymentTables = preapareTableList();
            String year = getYearForSummary(date);
            String month = getMonthForSummary(date);

            date = switchMonth(month);
            for (String paymentTable : paymentTables) {
                String query = "select data, platnosc, typPlatnosci from " + paymentTable + " WHERE data LIKE '" + year + "%'" +
                        "AND data LIKE '%-" + date + "-%'";
                resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    String paymentType = resultSet.getString("typPlatnosci");
                    String paymentValue = resultSet.getString("platnosc");
                    if (paymentType.equals("N")) {
                        remittancePayment = remittancePayment + +Integer.parseInt(paymentValue);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return remittancePayment;
    }

    private String getYearForSummary(String date) {
        return date.substring(0, 4);
    }

    private String getMonthForSummary(String date) {
        return date.substring(4, date.length());
    }

    private List preapareTableList() {
        ArrayList<String> paymentTables = new ArrayList<>();
        try {

            String query = "show tables from warsztatyrobotow like '%platnosci%' ";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String tableName = resultSet.getString("Tables_in_warsztatyrobotow (%platnosci%)");
                paymentTables.add(tableName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paymentTables;
    }

    private Statement getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/warsztatyrobotow?useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
        return connection.createStatement();
    }

    private String switchMonth(String month) {

        switch (month) {
            case "Styczeń":
                return "01";
            case "Luty":
                return "02";
            case "Marzec":
                return "03";
            case "Kwiecień":
                return "04";
            case "Maj":
                return "5";
            case "Czerwiec":
                return "06";
            case "Lipiec":
                return "07";
            case "Sierpień":
                return "08";
            case "Wrzesień":
                return "09";
            case "Październik":
                return "10";
            case "Listopad":
                return "11";
            case "Grudzień":
                return "12";
        }
        return "Zly miesiac";
    }
}
