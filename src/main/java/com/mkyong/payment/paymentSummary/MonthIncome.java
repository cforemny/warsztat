package com.mkyong.payment.paymentSummary;

import com.mkyong.payment.Summary;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.*;

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

    public Map getCashPerInstrutor(String date){
        Map<String, Double> instructorsCash = new HashMap<>();

        try {
            String year = getYearForSummary(date);
            String month = getMonthForSummary(date);
            String monthNumber = switchMonth(month);
            getConnection();
            String query = "SELECT SUM(kwota), instruktor FROM odbioryinstruktorow " + "WHERE data LIKE '" + year + "%'" +
            "AND data LIKE '%-" + monthNumber + "-%'"  +  "GROUP BY instruktor";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                String instructor = resultSet.getString("instruktor");
                String value = resultSet.getString("SUM(kwota)");
                instructorsCash.put(instructor, Double.parseDouble(value));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return instructorsCash;
    }
    
    public int getPaymentFromLocations(String date, String isCash) {

        int payment = 0;
        try {
            List<String> paymentTables = preapareTableList();
            String year = getYearForSummary(date);
            String month = getMonthForSummary(date);
            String monthNumber = switchMonth(month);
            for (String paymentTable : paymentTables) {
                String query = "select data, platnosc, typPlatnosci from " + paymentTable + " WHERE data LIKE '" + year + "%'" +
                        "AND data LIKE '%-" + monthNumber + "-%'";
                resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    String paymentType = resultSet.getString("typPlatnosci");
                    String paymentValue = resultSet.getString("platnosc");
                    if (paymentType.equals(isCash)) {
                        payment = payment + Integer.parseInt(paymentValue);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return payment;
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
