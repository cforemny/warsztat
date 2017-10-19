package com.mkyong.payment.paymentSummary;

import com.mkyong.payment.Summary;
import com.mkyong.utils.CashCollection;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cyprian on 2017-07-22.
 */
@Component
public class CashSummary extends Summary {

    private ResultSet resultSet;
    private Statement statement;
    private Connection connection;

    public CashSummary() throws SQLException, ClassNotFoundException {
    }

    public List<CashCollection> getListOfCashCollectionByMonth(String date) {

        List<CashCollection> cashList = new ArrayList<>();
        try {

            String query = "select data, instruktor, kwota, miejsce from odbioryinstruktorow " + " WHERE data LIKE '" + getYearForSummary(date) + "%'" +
                    "AND data LIKE '%-" + getActualMonthForSummary(date) + "-%' order by data";
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql:// 144.76.228.149:3306/testowa?useLegacyDatetimeCode=false&serverTimezone=UTC", "cypek", "foremny1a");
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String location = resultSet.getString("miejsce");
                String instructor = resultSet.getString("instruktor");
                String collectionDate = resultSet.getString("data");
                String value = resultSet.getString("kwota");

                cashList.add(new CashCollection(instructor, value, collectionDate, location));
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cashList;
    }
}
