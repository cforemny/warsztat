package com.mkyong.sqlBase;

import com.mkyong.payment.Summary;
import com.mkyong.utils.Event;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * Created by Cyprian on 2017-07-22.
 */
@Component
public class EventCreator extends Summary {


    public EventCreator() throws SQLException, ClassNotFoundException {
    }


    public void insertNewEventToTable(Event event) {

        String query = "INSERT INTO eventy (data, rodzajeventu,cena,faktura) VALUES ('" + event.getData() + "','" +
                event.getRodzajEventu() + "'," + event.getCena() + ",'" + event.getFaktura() + "')";
        getJdbcTemplate().update(query);
    }

    public void updateEventPayment(String data, String cena) {
        String query = "UPDATE eventy SET czyzaplacono =  'T' WHERE data ='" + data + "' AND cena = '" +
                cena + "'";
        getJdbcTemplate().update(query);
    }


}
