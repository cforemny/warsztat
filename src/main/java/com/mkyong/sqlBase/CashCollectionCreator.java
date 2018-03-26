package com.mkyong.sqlBase;

import com.mkyong.payment.Summary;
import com.mkyong.utils.CashCollection;
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

        String query = "Insert into odbioryinstruktorow (instruktor, miejsce, kwota, data) values ('" + cashCollection.getInstruktor() + "','" +
                cashCollection.getMiejsce() + "'," + cashCollection.getKwota() + ",'" + cashCollection.getData() + "')";
        getJdbcTemplate().update(query);
    }
}
