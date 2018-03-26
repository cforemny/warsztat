package com.mkyong.payment.expenseSummary;

import com.mkyong.payment.Summary;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Cyprian on 2017-07-25.
 */
@Component
public class PermanentExpense extends Summary {

    private String kosztOpis;
    private double kosztWartosc;
    private String data;
    private char faktura;


    public PermanentExpense() throws SQLException, ClassNotFoundException {
    }

    public PermanentExpense(String kosztOpis, double kosztWartosc, String data, char faktura) throws SQLException, ClassNotFoundException {
        this.kosztOpis = kosztOpis;
        this.kosztWartosc = kosztWartosc;
        this.data = data;
        this.faktura = faktura;
    }

    public String getKosztOpis() {
        return kosztOpis;
    }

    public void setKosztOpis(String kosztOpis) {
        this.kosztOpis = kosztOpis;
    }

    public double getKosztWartosc() {
        return kosztWartosc;
    }

    public void setKosztWartosc(double kosztWartosc) {
        this.kosztWartosc = kosztWartosc;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public char getFaktura() {
        return faktura;
    }

    public void setFaktura(char faktura) {
        this.faktura = faktura;
    }

    public List<PermanentExpense> getPermanentExpense(String date) {


        String year = getYearForSummary(date);
        String month = getMonthForSummary(date);
        String monthNumber = switchMonth(month);

        String query = "select * from kosztystale " + " WHERE data LIKE '" + year + "%'" +
                "AND data LIKE '%-" + monthNumber + "-%' OR data = 0000-00-00";

        List<PermanentExpense> permanentExpenses = getJdbcTemplate().query(query, new BeanPropertyRowMapper<PermanentExpense>(PermanentExpense.class));

        return permanentExpenses;
    }

    public double getPermanenetExpenseSummary(String date) {

        String year = getYearForSummary(date);
        String month = getMonthForSummary(date);
        String monthNumber = switchMonth(month);

        String query = "select sum(kosztWartosc) as kosztWartosc from kosztystale " + " WHERE data LIKE '" + year + "%'" +
                "AND data LIKE '%-" + monthNumber + "-%' OR data = 0000-00-00";

        return Double.parseDouble(getJdbcTemplate().query(query, new ResultSetExtractor<String>() {
            @Override
            public String extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                return rs.next() ? rs.getString("kosztWartosc") : "0";
            }
        }));
    }

    public void insertPermanentExpense(PermanentExpense permanentExpense) {

        String query = "insert into kosztystale (kosztOpis, kosztWartosc, data, faktura) values('" + permanentExpense.getKosztOpis() + "'," + permanentExpense.getKosztWartosc() +
                ",'" + permanentExpense.getData() + "','" + permanentExpense.getFaktura() + "')";

        getJdbcTemplate().execute(query);
    }
}
