package com.mkyong.SQLBase;

import com.mkyong.payment.expenseSummary.Expense;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Cyprian on 2017-07-16.
 */
@Component
public class ExpenseCreator {

    private Connection connection;
    private Statement statement;


    public ExpenseCreator() throws SQLException, ClassNotFoundException {
    }

    public void insertExpenseToTable(Expense expense) {

        String query = "INSERT INTO wydatkiinstruktorow (instruktor, opisWydatku,kwota,data) VALUES ('" + expense.getExpenseInstructor() + "','" +
                expense.getExpenseType() + "'," + expense.getExpenseValue() + ",'" + expense.getExpenseData() + "')";
        try {
            getConnection();
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getConnection() throws ClassNotFoundException, SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql:// cfrobotics.nazwa.pl:3306/cfrobotics?useLegacyDatetimeCode=false&serverTimezone=UTC", "cfrobotics", "cfRoB0T!C$");
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
