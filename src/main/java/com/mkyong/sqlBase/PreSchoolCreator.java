package com.mkyong.sqlBase;

import com.mkyong.payment.Summary;
import com.mkyong.utils.NurserySchool;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Cyprian on 2017-07-21.
 */
@Component
public class PreSchoolCreator extends Summary {

    private Statement statement;
    private Connection connection;

    public PreSchoolCreator() throws SQLException, ClassNotFoundException {
    }

    public void updateNurseryPayment(String data, String liczbaDzieci) {

        try {
            String query = "UPDATE listaprzedszkoli SET czyzaplacono =  'T' WHERE data ='" + data + "' AND liczbadzieci = '" +
                    liczbaDzieci + "'";
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql:// 144.76.228.149:3306/testowa?useLegacyDatetimeCode=false&serverTimezone=UTC", "cypek", "foremny1a");
            statement = connection.createStatement();
            statement.execute(query);
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    public void insertPreschoolIntoTable(NurserySchool nurserySchool) {

        try {
            String query = "INSERT INTO listaprzedszkoli (liczbadzieci, data, cena, nazwaprzedszkola, czyzaplacono ) VALUES(" + nurserySchool.getNumberOfChildren() +
                    ",'" + nurserySchool.getDate() + "'," + nurserySchool.getValue() + ",'" + nurserySchool.getName() + "','N')";
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
