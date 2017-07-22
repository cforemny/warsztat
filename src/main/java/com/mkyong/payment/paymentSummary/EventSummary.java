package com.mkyong.payment.paymentSummary;

import com.mkyong.payment.Summary;
import com.mkyong.utils.Event;
import com.mkyong.utils.NurserySchool;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Cyprian on 2017-07-22.
 */
@Component
public class EventSummary extends Summary {

    private ResultSet resultSet;

    public EventSummary() throws SQLException, ClassNotFoundException {
    }

    public double getIncomFromEvent(String date){

        double payment = 0;
        try {
            getConnection();

            String year = getYearForSummary(date);
            String month = getMonthForSummary(date);
            String monthNumber = switchMonth(month);

            String query = "select data, cena from eventy " + " WHERE data LIKE '" + year + "%'" +
                    "AND data LIKE '%-" + monthNumber + "-%'";

            resultSet = getConnection().executeQuery(query);
            while (resultSet.next()) {
                String cena = resultSet.getString("cena");
                payment = payment +  Double.parseDouble(cena);
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

    public List<Event> getListOfEventsByMonth(String date){

        List<Event> events = new ArrayList<>();
            try {


            String year = getYearForSummary(date);
            String monthNumber = getActualMonthForSummary(date);


            String query = "select data, rodzajeventu, cena, faktura from eventy " + " WHERE data LIKE '" + year + "%'" +
                    "AND data LIKE '%-" + monthNumber + "-%'";
            resultSet = getConnection().executeQuery(query);
            while (resultSet.next()){
                String eventType = resultSet.getString("rodzajeventu");
                String facture = resultSet.getString("faktura");
                String eventDate = resultSet.getString("data");
                String value = resultSet.getString("cena");

                events.add(new Event(eventDate,eventType,Double.parseDouble(value),facture.charAt(0)));
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
        return events;
    }
}
