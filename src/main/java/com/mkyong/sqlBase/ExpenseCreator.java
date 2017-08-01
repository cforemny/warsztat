package com.mkyong.sqlBase;

import com.mkyong.utils.Expense;
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

        String query = "INSERT INTO wydatkiinstruktorow (instruktor, opisWydatku,kwota,data,faktura) VALUES ('" + expense.getExpenseInstructor() + "','" +
                expense.getExpenseType() + "'," + expense.getExpenseValue() + ",'" + expense.getExpenseData() + "','" + expense.getBill() + "')";
        try {
            getConnection();
            statement.execute(query);
            statement.close();
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
