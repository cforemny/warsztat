package com.mkyong.payment.paymentSummary;

import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Cyprian on 2017-07-25.
 */
@Component
public class MonthIncomeForLocations extends MonthIncome {

    private ResultSet resultSet;
    private Statement statement;
    private Connection connection;

    public MonthIncomeForLocations() throws SQLException, ClassNotFoundException {
    }

    public Map<String, Integer> getLocationSummary(String date, String isCash) {

        Map<String, Integer> locationSummary = new TreeMap<>();

        try {

            List<String> paymentTables = super.prepareTableList();
            String year = getYearForSummary(date);
            String month = getMonthForSummary(date);
            String monthNumber = switchMonth(month);

            for (String paymentTable : paymentTables) {
                String query = "select data, platnosc, typPlatnosci from " + paymentTable + " WHERE data LIKE '" + year + "%'" +
                        "AND data LIKE '%-" + monthNumber + "-%'";
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql:// 144.76.228.149:3306/testowa?useLegacyDatetimeCode=false&serverTimezone=UTC", "cypek", "foremny1a");
                statement = connection.createStatement();
                resultSet = statement.executeQuery(query);
                locationSummary.put(paymentTable, getIncomeValue(resultSet, isCash));
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return locationSummary;
    }
}
