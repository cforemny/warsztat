package com.mkyong.payment.paymentSummary;

import com.mkyong.payment.Summary;
import com.mkyong.utils.NurserySchool;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Cyprian on 2017-07-22.
 */
@Component
public class NurserySchoolSummary extends Summary {

    private ResultSet resultSet;
    private Statement statement;
    private final String REGEX = "-";

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
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String liczbaDzieci = resultSet.getString("liczbadzieci");
                String cena = resultSet.getString("cena");
                payment = payment + Integer.parseInt(liczbaDzieci) * Integer.parseInt(cena);
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

    public List<NurserySchool> getListOfNurserySchoolByMonth(String date){

        List<NurserySchool> nurserySchools = new ArrayList<>();
        try {

            String year = getYearForSummary(date);
            String monthNumber;

            if (date.contains(REGEX)) {
                monthNumber = getActualMonthForSummary(date);
            } else {
                monthNumber = switchMonth(getMonthForSummary(date));

            }

            String query = "select data, liczbadzieci, cena, nazwaprzedszkola from listaprzedszkoli " + " WHERE data LIKE '" + year + "%'" +
                    "AND data LIKE '%-" + monthNumber + "-%'";
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                String nurserySchoolName = resultSet.getString("nazwaprzedszkola");
                String childrens = resultSet.getString("liczbadzieci");
                String lessonDate = resultSet.getString("data");
                String value = resultSet.getString("cena");

                nurserySchools.add(new NurserySchool(Integer.parseInt(childrens),lessonDate,Integer.parseInt(value),nurserySchoolName));
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
        return nurserySchools;
    }
}
