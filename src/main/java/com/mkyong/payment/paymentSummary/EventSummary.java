package com.mkyong.payment.paymentSummary;

import com.mkyong.payment.Summary;
import com.mkyong.utils.Event;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Cyprian on 2017-07-22.
 */
@Component
public class EventSummary extends Summary {

    public EventSummary() throws SQLException, ClassNotFoundException {
    }

    public double getIncomeFromEvent(String date, String isCash) {

        String year = getYearForSummary(date);
        String month = getMonthForSummary(date);
        String monthNumber = switchMonth(month);

        String query = "select sum(cena) as cena from eventy " + " WHERE data LIKE '" + year + "%'" +
                "AND data LIKE '%-" + monthNumber + "-%' and faktura='" + isCash + "'";

        try {
            return Double.parseDouble(getJdbcTemplate().query(query, new ResultSetExtractor<String>() {
                @Override
                public String extractData(ResultSet rs) throws SQLException,
                        DataAccessException {
                    return rs.next() ? rs.getString("cena") : "0";
                }
            }));
        } catch (Exception e) {
            return 0;
        }
    }

    public List<Event> getListOfEventsByMonth(String date) {

        String year = getYearForSummary(date);
        String monthNumber;
        if (date.contains(REGEX)) {
            monthNumber = getActualMonthForSummary(date);
        } else {
            monthNumber = switchMonth(getMonthForSummary(date));
        }
        String query = "select data, rodzajeventu, cena, faktura, czyzaplacono, uwagi from eventy " + " WHERE data LIKE '" + year + "%'" +
                "AND data LIKE '%-" + monthNumber + "-%' order by data";

        List<Event> events = getJdbcTemplate().query(query, new BeanPropertyRowMapper<Event>(Event.class));

        return events;
    }
}
