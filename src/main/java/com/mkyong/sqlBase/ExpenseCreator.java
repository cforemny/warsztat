package com.mkyong.sqlBase;

import com.mkyong.payment.Summary;
import com.mkyong.utils.Expense;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * Created by Cyprian on 2017-07-16.
 */
@Component
public class ExpenseCreator extends Summary {

    public ExpenseCreator() throws SQLException, ClassNotFoundException {
    }

    public void insertExpenseToTable(Expense expense) {

        String query = "INSERT INTO wydatkiinstruktorow (instruktor, opisWydatku,kwota,data,faktura) VALUES ('" + expense.getInstruktor() + "','" +
                expense.getOpisWydatku() + "'," + expense.getKwota() + ",'" + expense.getData() + "','" + expense.getFaktura() + "')";

        getJdbcTemplate().update(query);
    }
}
