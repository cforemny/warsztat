package com.mkyong.utils;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cyprian on 2017-12-09.
 */
@Component
public class Debt {

    private double value;
    private String comment;
    private String date;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public Debt(double value, String comment, String date) {
        this.value = value;
        this.comment = comment;
        this.date = date;
    }

    public Debt() {
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double payedValue(){

        double payedValue = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql:// 144.76.228.149:3306/testowa?useLegacyDatetimeCode=false&serverTimezone=UTC", "cypek", "foremny1a");
            statement = connection.createStatement();
            String selectQuery = "select wartosc, komentarz, data from splaty";

            resultSet = statement.executeQuery(selectQuery);
            while (resultSet.next()){
                String value = resultSet.getString("wartosc");
                payedValue = Double.parseDouble(value) + payedValue;
            }
        } catch (Exception e) {
        }
        return 71540 - payedValue;

    }

    public List<Debt> getDebtList() {
        List<Debt> debts = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql:// 144.76.228.149:3306/testowa?useLegacyDatetimeCode=false&serverTimezone=UTC", "cypek", "foremny1a");
            statement = connection.createStatement();
            String selectQuery = "select wartosc, komentarz, data from splaty";

            resultSet = statement.executeQuery(selectQuery);
            while (resultSet.next()){
                String value = resultSet.getString("wartosc");
                String comment = resultSet.getString("komentarz");
                String date = resultSet.getString("data");
                debts.add(new Debt(Double.parseDouble(value),comment,date));
            }
        } catch (Exception e) {
        }
        return debts;
    }
}
