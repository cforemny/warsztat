package com.mkyong.payment.paymentSummary;

import com.mkyong.utils.TableName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Cyprian on 2017-07-25.
 */
@Component
public class MonthIncomeForLocations extends MonthIncome {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public MonthIncomeForLocations() throws SQLException, ClassNotFoundException {
    }

    public Map<String, Integer> getLocationSummary(String date, String isCash) {

        Map<String, Integer> locationSummary = new TreeMap<>();

        List<TableName> paymentTables = super.prepareTableList();
        String year = getYearForSummary(date);
        String month = getMonthForSummary(date);
        String monthNumber = switchMonth(month);

        for (TableName paymentTable : paymentTables) {
            String query = "select sum(platnosc) as platnosc from " + paymentTable.getTables_in_testowa() + " WHERE data LIKE '" + year + "%'" +
                    "AND data LIKE '%-" + monthNumber + "-%' AND typPlatnosci = '" + isCash + "'";
            try {
                int paymentSum = Integer.parseInt(jdbcTemplate.query(query, new ResultSetExtractor<String>() {
                    @Override
                    public String extractData(ResultSet rs) throws SQLException,
                            DataAccessException {
                        return rs.next() ? rs.getString("platnosc") : "0";
                    }
                }));
                locationSummary.put(paymentTable.getTables_in_testowa(), paymentSum);
            } catch (Exception e) {
                locationSummary.put(paymentTable.getTables_in_testowa(), 0);
            }
        }
        return locationSummary;
    }
}
