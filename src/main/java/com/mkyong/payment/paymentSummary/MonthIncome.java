package com.mkyong.payment.paymentSummary;

import com.mkyong.payment.Summary;
import com.mkyong.utils.CashCollection;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Cyprian on 2017-07-11.
 */

@Component
public class MonthIncome extends Summary {


    private ResultSet resultSet;
    private Statement statement;

    public MonthIncome() throws SQLException, ClassNotFoundException {
    }

    public List<CashCollection> getCashPerInstructor(String date) {
        List<CashCollection> instructorsCash = new LinkedList<>();

        try {
            String year = getYearForSummary(date);
            String month = getMonthForSummary(date);
            String monthNumber = switchMonth(month);

            String query = "SELECT kwota, instruktor, data, miejsce FROM odbioryinstruktorow " + "WHERE data LIKE '" + year + "%'" +
                    "AND data LIKE '%-" + monthNumber + "-%'" + "order by instruktor";
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String instructor = resultSet.getString("instruktor");
                String value = resultSet.getString("kwota");
                String location = resultSet.getString("miejsce");
                String collectionDate = resultSet.getString("data");
                instructorsCash.add(new CashCollection(instructor, value, collectionDate, location));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                getConnection().close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instructorsCash;
    }

    public double getCashByDate(String date) {

        double cash = 0;
        try {
            String year = getYearForSummary(date);
            String month = getMonthForSummary(date);
            String monthNumber = switchMonth(month);

            String query = "SELECT kwota FROM odbioryinstruktorow " + "WHERE data LIKE '" + year + "%'" +
                    "AND data LIKE '%-" + monthNumber + "-%'";
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String cashValue = resultSet.getString("kwota");
                cash = cash + Double.parseDouble(cashValue);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                getConnection().close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return cash;
    }

    public int getPaymentFromLocations(String date, String isCash) {

        int payment = 0;
        try {

            List<String> paymentTables = prepareTableList();
            String year = getYearForSummary(date);
            String month = getMonthForSummary(date);
            String monthNumber = switchMonth(month);
            for (String paymentTable : paymentTables) {
                String query = "select data, platnosc, typPlatnosci from " + paymentTable + " WHERE data LIKE '" + year + "%'" +
                        "AND data LIKE '%-" + monthNumber + "-%'";
                statement = getConnection().createStatement();
                resultSet = statement.executeQuery(query);

                payment = payment + getIncomeValue(resultSet, isCash);
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
        return payment;
    }

    public List prepareTableList() {
        ArrayList<String> paymentTables = new ArrayList<>();
        try {

            String query = "show tables from testowa like '%platnosci%' ";
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String tableName = resultSet.getString("Tables_in_testowa (%platnosci%)");
                paymentTables.add(tableName);
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
        return paymentTables;
    }

    public int getIncomeValue(ResultSet resultSet, String isCash) {
        int payment = 0;

        try {
            while (resultSet.next()) {
                String paymentType = resultSet.getString("typPlatnosci");
                String paymentValue = resultSet.getString("platnosc");
                if (paymentType.equals(isCash)) {
                    payment = payment + Integer.parseInt(paymentValue);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payment;
    }

}
