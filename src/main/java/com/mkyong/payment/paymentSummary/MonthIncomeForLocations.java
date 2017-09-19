package com.mkyong.payment.paymentSummary;

import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
                statement = getConnection().createStatement();
                resultSet = statement.executeQuery(query);

                locationSummary.put(paymentTable, getIncomeValue(resultSet, isCash));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                getConnection().close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return locationSummary;
    }
}
