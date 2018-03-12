package com.mkyong.payment.expenseSummary;

import com.mkyong.payment.Summary;
import com.mkyong.utils.Expense;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cyprian on 2017-07-16.
 */
@Component
public class MonthExpense extends Summary {

    private ResultSet resultSet;
    private Statement statement;
    private Connection connection;


    public MonthExpense() throws SQLException, ClassNotFoundException {
    }

    public double getInstructorExpenseForMonth(String date) {

        String year = getYearForSummary(date);
        String month = getMonthForSummary(date);
        date = switchMonth(month);

        String query = "select kwota from wydatkiinstruktorow " + " WHERE data LIKE '" + year + "%'" +
                "AND data LIKE '%-" + date + "-%' ";
        return getValue(query);
    }

    public List<Expense> getExpenseListByDate(String date) {

        List<Expense> expenseList = new ArrayList<>();
        String monthNumber;
        String year = getYearForSummary(date);
        if (date.contains(REGEX)) {
            monthNumber = getActualMonthForSummary(date);
        } else {
            monthNumber = switchMonth(getMonthForSummary(date));

        }
        try {
            String query = "select instruktor, opisWydatku, kwota, data, faktura from wydatkiinstruktorow " + " WHERE data LIKE '" + year + "%'" +
                    "AND data LIKE '%-" + monthNumber + "-%'" + "order by instruktor";
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql:// 144.76.228.149:3306/testowa?useLegacyDatetimeCode=false&serverTimezone=UTC", "cypek", "foremny1a");
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String instructor = resultSet.getString("instruktor");
                String description = resultSet.getString("opisWydatku");
                String expenseDate = resultSet.getString("data");
                String value = resultSet.getString("kwota");
                String facture = resultSet.getString("faktura");

                expenseList.add(new Expense(description, value, instructor, expenseDate, facture.charAt(0)));
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return expenseList;
    }

    public double getNoneBackExpenses(String date) {
        String year = getYearForSummary(date);
        String month = getMonthForSummary(date);
        date = switchMonth(month);
        String query = "select kwota from wydatkiinstruktorow where faktura = 'N' AND " + "data LIKE '" + year + "%'" +
                "AND data LIKE '%-" + date + "-%'" + " UNION ALL select kosztWartosc from kosztystale where faktura = 'N'" + " AND data LIKE '" + year + "%'" +
                "AND data LIKE '%-" + date + "-%'";
        return getValue(query);
    }

    public double getExpensesToPayback(String date) {

        String year = getYearForSummary(date);
        String month = getMonthForSummary(date);
        date = switchMonth(month);
        String query = "select kwota from wydatkiinstruktorow where faktura = 'T' AND " + "data LIKE '" + year + "%'" +
                "AND data LIKE '%-" + date + "-%'" + " UNION ALL select kosztWartosc from kosztystale where faktura = 'T'" + " AND data LIKE '" + year + "%'" +
                "AND data LIKE '%-" + date + "-%'";
        return getValue(query);
    }

    private double getValue(String query) {

        double value = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql:// 144.76.228.149:3306/testowa?useLegacyDatetimeCode=false&serverTimezone=UTC", "cypek", "foremny1a");
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String expense = resultSet.getString("kwota");
                value = value + Double.parseDouble(expense);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

}
