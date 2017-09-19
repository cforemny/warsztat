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

    public void updateEventPayment(Event event) {
        String query = "UPDATE eventy SET czyzaplacono =  'T' WHERE data ='" + event.getDate() + "' AND cena = '" +
                event.getValue() + "'";
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
            connection = DriverManager.getConnection("jdbc:mysql:// cfrobotics.nazwa.pl:3306/cfrobotics?useLegacyDatetimeCode=false&serverTimezone=UTC", "cfrobotics", "cfRoB0T!C$");
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
