package com.mkyong.payment.expenseSummary;

import com.mkyong.payment.Summary;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by Cyprian on 2017-07-16.
 */
@Component
public class MonthExpense extends Summary {


    private Statement statement = getConnection();
    private ResultSet resultSet;

    public MonthExpense() throws SQLException, ClassNotFoundException {
    }

    public double getInstructorExpenseForMonth(String date) {

        String year = getYearForSummary(date);
        String month = getMonthForSummary(date);
        date = switchMonth(month);
        double expense = 0;
        String query = "select kwota from wydatkiinstruktorow " + " WHERE data LIKE '" + year + "%'" +
                "AND data LIKE '%-" + date + "-%'";
        try {
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String kwota = resultSet.getString("kwota");
                expense = expense + Double.parseDouble(kwota);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return expense;
    }


}
