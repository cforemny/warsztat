package com.mkyong.utils;

import com.mkyong.payment.Summary;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Cyprian on 2017-12-09.
 */
@Component
public class Debt extends Summary {

    private double wartosc;
    private String komentarz;
    private String data;


    public Debt(double wartosc, String komentarz, String data) throws SQLException, ClassNotFoundException {
        super();
        this.wartosc = wartosc;
        this.komentarz = komentarz;
        this.data = data;
    }

    public Debt() throws SQLException, ClassNotFoundException {
        super();
    }

    public double getWartosc() {
        return wartosc;
    }

    public void setWartosc(double wartosc) {
        this.wartosc = wartosc;
    }

    public String getKomentarz() {
        return komentarz;
    }

    public void setKomentarz(String komentarz) {
        this.komentarz = komentarz;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double payedValue() {

        double payedValue = 0;
        String selectQuery = "SELECT wartosc, komentarz, data FROM splaty";
        List<Debt> debts = getJdbcTemplate().query(selectQuery, new BeanPropertyRowMapper<>(Debt.class));
        for (Debt debt : debts) {
            payedValue = debt.getWartosc() + payedValue;
        }

        return 71540 - payedValue;
    }

    public List<Debt> getDebtList() {

        String selectQuery = "SELECT wartosc, komentarz, data FROM splaty";
        List<Debt> debts = getJdbcTemplate().query(selectQuery, new BeanPropertyRowMapper<>(Debt.class));

        return debts;
    }
}
