package com.mkyong.payment.expenseSummary;

import com.mkyong.payment.Summary;
import com.mkyong.utils.Expense;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by Cyprian on 2017-07-16.
 */
@Component
public class MonthExpense extends Summary {

    public MonthExpense() throws SQLException, ClassNotFoundException {
    }

    public double getInstructorExpenseForMonth(String date) {

        String year = getYearForSummary(date);
        String month = getMonthForSummary(date);
        date = switchMonth(month);

        String query = "select sum(kwota) as kwota from wydatkiinstruktorow " + " WHERE data LIKE '" + year + "%'" +
                "AND data LIKE '%-" + date + "-%' ";

        return Double.parseDouble(getJdbcTemplate().query(query, new ResultSetExtractor<String>() {
            @Override
            public String extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                return rs.next() ? rs.getString("kwota") : "0";
            }
        }));
    }

    public List<Expense> getExpenseListByDate(String date) {

        String monthNumber;
        String year = getYearForSummary(date);

        if (date.contains(REGEX)) {
            monthNumber = getActualMonthForSummary(date);
        } else {
            monthNumber = switchMonth(getMonthForSummary(date));
        }

        String query = "select instruktor, opisWydatku, kwota, data, faktura from wydatkiinstruktorow " + " WHERE data LIKE '" + year + "%'" +
                "AND data LIKE '%-" + monthNumber + "-%'" + "order by instruktor";
        List<Expense> expenseList = getJdbcTemplate().query(query, new BeanPropertyRowMapper(Expense.class));

        return expenseList;
    }


    public double getExpenses(String date, String type) {

        String queryInstructor = "select sum(kwota) as kwota from wydatkiinstruktorow where faktura = '" + type + "' AND data LIKE '" + getYearForSummary(date) + "%'" +
                "AND data LIKE '%-" + switchMonth(getMonthForSummary(date)) + "-%'";
        String queryAdministrator = "select sum(kosztWartosc) as kosztWartosc from kosztystale where faktura = '" + type + "' AND data LIKE '" + getYearForSummary(date) + "%'" +
                "AND data LIKE '%-" + switchMonth(getMonthForSummary(date)) + "-%'";

        List<Map<String, Object>> mapsInstructor = getJdbcTemplate().queryForList(queryInstructor);
        List<Map<String, Object>> mapsAdministrator = getJdbcTemplate().queryForList(queryAdministrator);

        Double instructorExpense = (Double) mapsInstructor.get(0).get("kwota");
        Double administratoExpense = (Double) mapsAdministrator.get(0).get("kosztWartosc");

        return instructorExpense + administratoExpense;
    }


}
