package com.mkyong.payment.expenseSummary;

import com.mkyong.payment.Summary;
import com.mkyong.utils.Expense;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Cyprian on 2017-07-16.
 */
@Component
public class MonthExpense extends Summary {

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

            resultSet = getConnection().executeQuery(query);
            while (resultSet.next()) {
                String kwota = resultSet.getString("kwota");
                expense = expense + Double.parseDouble(kwota);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return expense;
    }

    public List<Expense> getExpenseListByMonth(String date){

        List<Expense> expenseList = new ArrayList<>();
        try {
            String year = getYearForSummary(date);
            String monthNumber = getActualMonthForSummary(date);

            String query = "select instruktor, opisWydatku, kwota, data from wydatkiinstruktorow " + " WHERE data LIKE '" + year + "%'" +
                    "AND data LIKE '%-" + monthNumber + "-%'";
            resultSet = getConnection().executeQuery(query);
            while (resultSet.next()){
                String instructor = resultSet.getString("instruktor");
                String description = resultSet.getString("opisWydatku");
                String expenseDate = resultSet.getString("data");
                String value = resultSet.getString("kwota");

                expenseList.add(new Expense(description,value,instructor,expenseDate));
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
        return expenseList;
    }


}
