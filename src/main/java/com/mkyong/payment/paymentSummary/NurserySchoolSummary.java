package com.mkyong.payment.paymentSummary;

import com.mkyong.payment.Summary;
import com.mkyong.utils.NurserySchool;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cyprian on 2017-07-22.
 */
@Component
public class NurserySchoolSummary extends Summary {

    private final String REGEX = "-";
    private ResultSet resultSet;
    private Statement statement;
    private Connection connection;

    public NurserySchoolSummary() throws SQLException, ClassNotFoundException {
    }

    public double getPaymentFromNurserySchools(String date) {

        int payment = 0;
        try {

            String year = getYearForSummary(date);
            String month = getMonthForSummary(date);
            String monthNumber = switchMonth(month);

            String query = "select data, liczbadzieci, cena from listaprzedszkoli " + " WHERE data LIKE '" + year + "%'" +
                    "AND data LIKE '%-" + monthNumber + "-%'";
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql:// 144.76.228.149:3306/testowa?useLegacyDatetimeCode=false&serverTimezone=UTC", "cypek", "foremny1a");
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String liczbaDzieci = resultSet.getString("liczbadzieci");
                String cena = resultSet.getString("cena");
                payment = payment + Integer.parseInt(liczbaDzieci) * Integer.parseInt(cena);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return payment;
    }

    public List<NurserySchool> getListOfNurserySchoolByMonth(String date) {

        List<NurserySchool> nurserySchools = new ArrayList<>();
        try {

            String year = getYearForSummary(date);
            String monthNumber;

            if (date.contains(REGEX)) {
                monthNumber = getActualMonthForSummary(date);
            } else {
                monthNumber = switchMonth(getMonthForSummary(date));
            }

            String query = "select data, liczbadzieci, cena, nazwaprzedszkola, czyzaplacono from listaprzedszkoli " + " WHERE data LIKE '" + year + "%'" +
                    "AND data LIKE '%-" + monthNumber + "-%'";
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql:// 144.76.228.149:3306/testowa?useLegacyDatetimeCode=false&serverTimezone=UTC", "cypek", "foremny1a");
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String nurserySchoolName = resultSet.getString("nazwaprzedszkola");
                String childrens = resultSet.getString("liczbadzieci");
                String lessonDate = resultSet.getString("data");
                String value = resultSet.getString("cena");
                String platnosc = resultSet.getString("czyzaplacono");

                nurserySchools.add(new NurserySchool(Integer.parseInt(childrens), lessonDate, Integer.parseInt(value), nurserySchoolName, platnosc));
            }connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nurserySchools;
    }
}
