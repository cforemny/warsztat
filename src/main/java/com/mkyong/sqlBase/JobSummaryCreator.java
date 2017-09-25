package com.mkyong.sqlBase;

import com.mkyong.utils.Student;
import com.mkyong.utils.WorkSummary;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Cyprian on 2017-09-25.
 */
@Component
public class JobSummaryCreator {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public Map getInstrucorsList() {
    Map<Integer,WorkSummary> instructorList = new HashMap<Integer,WorkSummary>();

        try {
        getConnection();
        String query = "select * from zestawieniePracy";
        resultSet = statement.executeQuery(query);

        int record = 0;
        while (resultSet.next()) {
            int numer = resultSet.getInt("zestawienieId");
            String data = resultSet.getString("data");
            String miejsce = resultSet.getString("miejsce");
            String cforemny = resultSet.getString("Cyprian");
            String oforemna = resultSet.getString("Ola Foremna");
            String kasiak = resultSet.getString("Katarzyna");
            String jcichon = resultSet.getString("Joanna Cichon");
            String kskotniczny = resultSet.getString("Krzysztof Skotniczny");
            String pszydlo = resultSet.getString("Piotr Szydlo");
            String lkrason = resultSet.getString("Lukasz Krason");
            record++;
            instructorList.put(record,new WorkSummary(numer,data,miejsce,cforemny,oforemna,kasiak,jcichon,kskotniczny,pszydlo,lkrason));

        }

    } catch (Exception exception) {
        System.out.println(exception);
    }
        return instructorList;
}

    private void getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql:// 144.76.228.149:3306/testowa?useLegacyDatetimeCode=false&serverTimezone=UTC", "cypek", "foremny1a");
        statement = connection.createStatement();
    }
}
