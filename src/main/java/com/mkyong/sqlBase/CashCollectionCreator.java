package com.mkyong.sqlBase;

import com.mkyong.payment.Summary;
import com.mkyong.utils.CashCollection;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Cyprian on 2017-07-16.
 */
@Component
public class CashCollectionCreator extends Summary {

    private Statement statement;
    private Connection connection;


    public CashCollectionCreator() throws SQLException, ClassNotFoundException {
    }

    public void insertCashCollectionIntoTable(CashCollection cashCollection) {

        String query = "Insert into odbioryinstruktorow (instruktor, miejsce, kwota, data) values ('" + cashCollection.getInstructor() + "','" +
                cashCollection.getLocation() + "'," + cashCollection.getValue() + ",'" + cashCollection.getDate() + "')";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql:// 144.76.228.149:3306/testowa?useLegacyDatetimeCode=false&serverTimezone=UTC", "cypek", "foremny1a");
            statement = connection.createStatement();
            statement.execute(query);
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
