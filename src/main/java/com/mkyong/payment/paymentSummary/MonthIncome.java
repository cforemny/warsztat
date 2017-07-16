package com.mkyong.payment.paymentSummary;

import com.mkyong.payment.Summary;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cyprian on 2017-07-11.
 */
@Component
public class MonthIncome extends Summary {

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
}
