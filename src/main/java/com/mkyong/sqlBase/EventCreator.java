package com.mkyong.sqlBase;

import com.mkyong.utils.Event;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Cyprian on 2017-07-22.
 */
@Component
public class EventCreator {

    private Connection connection;
    private Statement statement;


    public EventCreator() throws SQLException, ClassNotFoundException {
    }

    public void insertNewEventToTable(Event event) {

        String query = "INSERT INTO eventy (data, rodzajeventu,cena,faktura) VALUES ('" + event.getDate() + "','" +
                event.getEventType() + "'," + event.getValue() + ",'" + event.getFaktura() + "')";
        execute(query);
    }

    public void updateEventPayment(String data, String cena) {
        String query = "UPDATE eventy SET czyzaplacono =  'T' WHERE data ='" + data + "' AND cena = '" +
                cena + "'";
        execute(query);
    }

    private void execute(String query) {
        try {
            getConnection();
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void getConnection() throws ClassNotFoundException, SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql:// 144.76.228.149:3306/testowa?useLegacyDatetimeCode=false&serverTimezone=UTC", "cypek", "foremny1a");
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
