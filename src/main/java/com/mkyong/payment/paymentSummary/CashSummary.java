package com.mkyong.payment.paymentSummary;

import com.mkyong.payment.Summary;
import com.mkyong.utils.CashCollection;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cyprian on 2017-07-22.
 */
@Component
public class CashSummary extends Summary {

    private ResultSet resultSet;
    private Statement statement;

    public CashSummary() throws SQLException, ClassNotFoundException {
    }

    public List<CashCollection> getListOfCashCollectionByMonth(String date) {

        List<CashCollection> cashList = new ArrayList<>();
        try {

            String query = "select data, instruktor, kwota, miejsce from odbioryinstruktorow " + " WHERE data LIKE '" + getYearForSummary(date) + "%'" +
                    "AND data LIKE '%-" + getActualMonthForSummary(date) + "-%'";
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String location = resultSet.getString("miejsce");
                String instructor = resultSet.getString("instruktor");
                String collectionDate = resultSet.getString("data");
                String value = resultSet.getString("kwota");

                cashList.add(new CashCollection(instructor, value, collectionDate, location));
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
        return cashList;
    }
}
