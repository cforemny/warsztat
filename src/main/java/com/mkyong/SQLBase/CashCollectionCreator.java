package com.mkyong.SQLBase;

import com.mkyong.payment.Summary;
import com.mkyong.payment.paymentSummary.CashCollection;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * Created by Cyprian on 2017-07-16.
 */
@Component
public class CashCollectionCreator extends Summary {

    public CashCollectionCreator() throws SQLException, ClassNotFoundException {
    }

    public void insertCashCollectionIntoTable(CashCollection cashCollection) {

        String query = "Insert into odbioryinstruktorow (instruktor, miejsce, kwota, data) values ('" + cashCollection.getInstructor() + "','" +
                cashCollection.getLocation() + "'," + cashCollection.getValue() + ",'" + cashCollection.getDate() + "')";
        try {

            getConnection().execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
