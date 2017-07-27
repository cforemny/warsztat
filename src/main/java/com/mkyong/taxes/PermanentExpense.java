package com.mkyong.taxes;

import com.mkyong.payment.Summary;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Cyprian on 2017-07-25.
 */
@Component
public class PermanentExpense extends Summary {

    private String name;
    private double value;
    private String date;
    private char bill;
    private ResultSet resultSet;
    private Statement statement;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public char getBill() {
        return bill;
    }

    public void setBill(char bill) {
        this.bill = bill;
    }

    public PermanentExpense() throws SQLException, ClassNotFoundException {
    }

    public PermanentExpense(String name, double value, String date, char bill) throws SQLException, ClassNotFoundException {
        this.name = name;
        this.value = value;
        this.date = date;
        this.bill = bill;
    }

    public PermanentExpense(String name, double value) throws SQLException, ClassNotFoundException {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public List<PermanentExpense> getPermanentExpense(String date) {

        List<PermanentExpense> permanentExpenses = new LinkedList<>();
        try {
            String year = getYearForSummary(date);
            String month = getMonthForSummary(date);
            String monthNumber = switchMonth(month);

            String query = "select * from kosztystale " + " WHERE data LIKE '" + year + "%'" +
                    "AND data LIKE '%-" + monthNumber + "-%' OR data = 0000-00-00";
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String name = resultSet.getString("kosztOpis");
                String value = resultSet.getString("kosztWartosc");
                permanentExpenses.add(new PermanentExpense(name, Double.parseDouble(value)));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return permanentExpenses;
    }

    public double getPermanenetExpenseSummary(String date) {
        String year = getYearForSummary(date);
        String month = getMonthForSummary(date);
        String monthNumber = switchMonth(month);
        double expensesSummary = 0;
        String query = "select * from kosztystale " + " WHERE data LIKE '" + year + "%'" +
                "AND data LIKE '%-" + monthNumber + "-%' OR data = 0000-00-00";
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                String value = resultSet.getString("kosztWartosc");
                expensesSummary = expensesSummary + Double.parseDouble(value);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                getConnection().close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return expensesSummary;
    }

    public void insertPermanentExpense(PermanentExpense permanentExpense) {

        String query = "insert into kosztystale (kosztOpis, kosztWartosc, data, faktura) values('" + permanentExpense.getName() + "'," + permanentExpense.getValue() +
                ",'" + permanentExpense.getDate() + "','" + permanentExpense.getBill() + "')";
        try {
            statement = getConnection().createStatement();
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                getConnection().close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


}
