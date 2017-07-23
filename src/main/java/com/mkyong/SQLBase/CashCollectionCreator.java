package com.mkyong.SQLBase;

import com.mkyong.payment.Summary;
import com.mkyong.utils.CashCollection;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Cyprian on 2017-07-16.
 */
@Component
public class CashCollectionCreator extends Summary {

    private Statement statement;

    public CashCollectionCreator() throws SQLException, ClassNotFoundException {
    }

    public void insertCashCollectionIntoTable(CashCollection cashCollection) {

        String query = "Insert into odbioryinstruktorow (instruktor, miejsce, kwota, data) values ('" + cashCollection.getInstructor() + "','" +
                cashCollection.getLocation() + "'," + cashCollection.getValue() + ",'" + cashCollection.getDate() + "')";
        try {


            statement = getConnection().createStatement();
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                getConnection().close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
