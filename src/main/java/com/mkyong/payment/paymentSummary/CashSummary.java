package com.mkyong.payment.paymentSummary;

import com.mkyong.payment.Summary;
import com.mkyong.utils.CashCollection;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Cyprian on 2017-07-22.
 */
@Component
public class CashSummary extends Summary {

    public CashSummary() throws SQLException, ClassNotFoundException {
    }

    public List<CashCollection> getListOfCashCollectionByMonth(String date) {


        String query = "select data, instruktor, kwota, miejsce from odbioryinstruktorow " + " WHERE data LIKE '" + getYearForSummary(date) + "%'" +
                "AND data LIKE '%-" + getActualMonthForSummary(date) + "-%' order by data";
        List<CashCollection> cashList = getJdbcTemplate().query(query, new BeanPropertyRowMapper<>(CashCollection.class));

        return cashList;
    }
}
