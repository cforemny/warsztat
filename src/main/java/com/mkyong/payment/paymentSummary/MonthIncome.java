package com.mkyong.payment.paymentSummary;

import com.mkyong.payment.Summary;
import com.mkyong.utils.CashCollection;
import org.springframework.stereotype.Component;

import java.sql.*;
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
    private Connection connection;

    public MonthIncome() throws SQLException, ClassNotFoundException {
    }

    public double incomeFromNowySacz(String date) {

        String year = getYearForSummary(date);
        String month = getMonthForSummary(date);
        String monthNumber = switchMonth(month);

        String query = "select wartosc from zyskNowySacz where data like ('%" + year + "%')" +
                " AND data LIKE ('%-" + monthNumber + "-%')";
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql:// 144.76.228.149:3306/testowa?useLegacyDatetimeCode=false&serverTimezone=UTC", "cypek", "foremny1a");
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            resultSet.next();
            double value = resultSet.getDouble("wartosc");
            connection.close();
            return value;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<CashCollection> getCashPerInstructor(String date) {
        List<CashCollection> instructorsCash = new LinkedList<>();

        try {
            String year = getYearForSummary(date);
            String month = getMonthForSummary(date);
            String monthNumber = switchMonth(month);

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql:// 144.76.228.149:3306/testowa?useLegacyDatetimeCode=false&serverTimezone=UTC", "cypek", "foremny1a");

            String query = "SELECT SUM(kwota) as kwota, instruktor, data, miejsce FROM odbioryinstruktorow " + "WHERE data LIKE '" + year + "%'" +
                    "AND data LIKE '%-" + monthNumber + "-%'" + "GROUP by instruktor order by instruktor ";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String instructor = resultSet.getString("instruktor");
                String value = resultSet.getString("kwota");
                String location = resultSet.getString("miejsce");
                String collectionDate = resultSet.getString("data");
                instructorsCash.add(new CashCollection(instructor, value, collectionDate, location));
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
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

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql:// 144.76.228.149:3306/testowa?useLegacyDatetimeCode=false&serverTimezone=UTC", "cypek", "foremny1a");

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String cashValue = resultSet.getString("kwota");
                cash = cash + Double.parseDouble(cashValue);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
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

                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql:// 144.76.228.149:3306/testowa?useLegacyDatetimeCode=false&serverTimezone=UTC", "cypek", "foremny1a");
                statement = connection.createStatement();
                resultSet = statement.executeQuery(query);

                payment = payment + getIncomeValue(resultSet, isCash);
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return payment;
    }

    public List prepareTableList() {
        ArrayList<String> paymentTables = new ArrayList<>();
        try {

            String query = "SHOW TABLES FROM testowa LIKE '%platnosci%' ";
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql:// 144.76.228.149:3306/testowa?useLegacyDatetimeCode=false&serverTimezone=UTC", "cypek", "foremny1a");
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String tableName = resultSet.getString("Tables_in_testowa (%platnosci%)");
                paymentTables.add(tableName);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
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
