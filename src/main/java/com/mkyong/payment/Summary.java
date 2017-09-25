package com.mkyong.payment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Cyprian on 2017-07-16.
 */
public class Summary {

    protected final String REGEX = "-";
    private Connection connection;


    public Summary() throws SQLException, ClassNotFoundException {
    }

    public String getYearForSummary(String date) {
        return date.substring(0, 4);
    }

    public String getMonthForSummary(String date) {
        return date.substring(4, date.length());
    }

    public String getActualMonthForSummary(String date) {
        return date.substring(5, 7);
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql:// 144.76.228.149:3306/testowa?useLegacyDatetimeCode=false&serverTimezone=UTC", "cypek", "foremny1a");
        return connection;
    }


    public String switchMonth(String month) {
        switch (month) {
            case "Styczeń":
                return "01";
            case "Luty":
                return "02";
            case "Marzec":
                return "03";
            case "Kwiecień":
                return "04";
            case "Maj":
                return "5";
            case "Czerwiec":
                return "06";
            case "Lipiec":
                return "07";
            case "Sierpień":
                return "08";
            case "Wrzesień":
                return "09";
            case "Październik":
                return "10";
            case "Listopad":
                return "11";
            case "Grudzień":
                return "12";
        }
        return "Zly miesiac";
    }
}
