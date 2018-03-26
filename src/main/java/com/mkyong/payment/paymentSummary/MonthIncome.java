package com.mkyong.payment.paymentSummary;

import com.mkyong.payment.Summary;
import com.mkyong.utils.CashCollection;
import com.mkyong.utils.TableName;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Cyprian on 2017-07-11.
 */

@Component
public class MonthIncome extends Summary {

    public MonthIncome() throws SQLException, ClassNotFoundException {
    }

    public double incomeFromNowySacz(String date) {

        String year = getYearForSummary(date);
        String month = getMonthForSummary(date);
        String monthNumber = switchMonth(month);

        String query = "select wartosc from zyskNowySacz where data like ('%" + year + "%')" +
                " AND data LIKE ('%-" + monthNumber + "-%')";

        return Double.parseDouble(getJdbcTemplate().query(query, new ResultSetExtractor<String>() {
            @Override
            public String extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                return rs.next() ? rs.getString("wartosc") : "0";
            }
        }));
    }

    public List<CashCollection> getCashPerInstructor(String date) {

        String year = getYearForSummary(date);
        String month = getMonthForSummary(date);
        String monthNumber = switchMonth(month);

        String query = "SELECT SUM(kwota) as kwota, instruktor, data, miejsce FROM odbioryinstruktorow " + "WHERE data LIKE '" + year + "%'" +
                "AND data LIKE '%-" + monthNumber + "-%'" + "GROUP by instruktor order by instruktor ";

        List<CashCollection> instructorsCash = getJdbcTemplate().query(query, new BeanPropertyRowMapper(CashCollection.class));

        return instructorsCash;
    }

    public double getCashByDate(String date) {

        String year = getYearForSummary(date);
        String month = getMonthForSummary(date);
        String monthNumber = switchMonth(month);

        String query = "SELECT sum(kwota) as kwota FROM odbioryinstruktorow " + "WHERE data LIKE '" + year + "%'" +
                "AND data LIKE '%-" + monthNumber + "-%'";

        return Double.parseDouble(getJdbcTemplate().query(query, new ResultSetExtractor<String>() {
            @Override
            public String extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                return rs.next() ? rs.getString("kwota") : "0";
            }
        }));
    }

    public int getPaymentFromLocations(String date, String isCash) {

        int payment = 0;


        List<TableName> paymentTables = prepareTableList();
        String year = getYearForSummary(date);
        String month = getMonthForSummary(date);
        String monthNumber = switchMonth(month);


        for (TableName paymentTable : paymentTables) {
            String query = "select sum(platnosc) as platnosc from " + paymentTable.getTables_in_testowa() + " WHERE data LIKE '" + year + "%'" +
                    "AND data LIKE '%-" + monthNumber + "-%' AND typPlatnosci = '" + isCash + "'";

            try {
                int paymentSum = Integer.parseInt(getJdbcTemplate().query(query, new ResultSetExtractor<String>() {
                    @Override
                    public String extractData(ResultSet rs) throws SQLException,
                            DataAccessException {
                        return rs.next() ? rs.getString("platnosc") : "0";
                    }
                }));
                payment = payment + paymentSum;
            } catch (Exception e) {
                payment = payment + 0;
            }
        }
        return payment;
    }

    public List prepareTableList() {

        String query = "SHOW TABLES FROM testowa where tables_in_testowa like '%platnosci%' ";
        List<TableName> paymentTables = getJdbcTemplate().query(query, new BeanPropertyRowMapper(TableName.class));

        return paymentTables;
    }

    public int getIncomeValue(ResultSet resultSet, String isCash) {
        int payment = 0;

        try {
            while (resultSet.next()) {
                String paymentType = resultSet.getString("typPlatnosci");
                String paymentValue = resultSet.getString("platnosc");
                if (paymentType.equals(isCash)) {
                    payment = payment + Integer.parseInt(paymentValue);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payment;
    }

}
