package com.mkyong.SQLBase;

import com.mkyong.payment.expenseSummary.Expense;
import org.springframework.stereotype.Component;

import java.sql.*;

/**
 * Created by Cyprian on 2017-07-16.
 */
@Component
public class ExpenseCreator {

    private Connection connection;
    private Statement statement = getConnection();


    public ExpenseCreator() throws SQLException, ClassNotFoundException {
    }

    public void insertExpenseToTable(Expense expense){

        String query = "INSERT INTO wydatkiinstruktorow (instruktor, opisWydatku,kwota,data) VALUES ('" + expense.getExpenseInstructor() + "','" +
                expense.getExpenseType() + "'," + expense.getExpenseValue() + ",'" + expense.getExpenseData() + "')";
        try {
            statement.execute(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private Statement getConnection() throws ClassNotFoundException, SQLException {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/warsztatyrobotow?useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");

        }catch(Exception e){
            e.printStackTrace();
        }
        return connection.createStatement();
    }

}
