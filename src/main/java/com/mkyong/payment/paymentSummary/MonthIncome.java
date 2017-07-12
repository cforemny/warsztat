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

    private Statement statement;
    private ResultSet resultSet;


    public double getPaymentFromMonth(String date){

        try {
            getConnection();
            List<String> paymentTables = preapareTableList();
            String year =  getYearForSummary(date);
            String month = getMonthForSummary(date);
            for (String paymentTable : paymentTables) {
                String query = "select data, platnosc, typPlatnosci from " + paymentTable + " WHERE data LIKE '" + year + "%'";
                resultSet = statement.executeQuery(query);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;

    }


    private String getYearForSummary(String date) {
        return date.substring(0, 4);
    }

    private String getMonthForSummary(String date) {
        return date.substring(5, date.length()-1);
    }


    public List preapareTableList() {
        ArrayList<String> paymentTables = new ArrayList<>();
        try {
            getConnection();
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

    private void getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/warsztatyrobotow?useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
        statement = connection.createStatement();
    }
}
