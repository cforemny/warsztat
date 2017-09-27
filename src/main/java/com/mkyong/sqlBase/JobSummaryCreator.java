package com.mkyong.sqlBase;

import com.mkyong.controller.InstruktorController;
import com.mkyong.payment.Summary;
import com.mkyong.utils.Instructor;
import com.mkyong.utils.WorkSummary;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Cyprian on 2017-09-25.
 */
@Component
public class JobSummaryCreator extends Summary {


    private Statement statement = getConnection().createStatement();
    private ResultSet resultSet;
    private final String REGEX = "-";

    public JobSummaryCreator() throws SQLException, ClassNotFoundException {
    }

    public Map getInstrucorsList(String date) {
        Map<Integer, WorkSummary> instructorList = new HashMap<Integer, WorkSummary>();

        try {

            String year = getYearForSummary(date);
            String monthNumber;

            if (date.contains(REGEX)) {
                monthNumber = getActualMonthForSummary(date);
            } else {
                monthNumber = switchMonth(getMonthForSummary(date));
            }

            getConnection();
            String query = "select * from zestawieniePracy" + " WHERE data LIKE '" + year + "%'" +
                    "AND data LIKE '%-" + monthNumber + "-%'";
            resultSet = statement.executeQuery(query);

            int record = 0;
            while (resultSet.next()) {
                String numer = resultSet.getString("zestawienieId");
                String data = resultSet.getString("data");
                String miejsce = resultSet.getString("miejsce");
                String cforemny = resultSet.getString("cforemny");
                String oforemna = resultSet.getString("oforemna");
                String kasiak = resultSet.getString("kasiak");
                String jcichon = resultSet.getString("jcichon");
                String kskotniczny = resultSet.getString("kskotniczny");
                String pszydlo = resultSet.getString("pszydlo");
                String lkrason = resultSet.getString("lkrason");
                record++;
                instructorList.put(record, new WorkSummary(numer, data, miejsce, cforemny, oforemna, kasiak, jcichon, kskotniczny, pszydlo, lkrason));

            }

        } catch (Exception exception) {
            System.out.println(exception);
        }
        return instructorList;
    }

    public void insertWorkingHour(Instructor instructor) {

        try {
            getConnection();

            String selectQuery = "select data, miejsce from zestawieniePracy where data = '" + instructor.getDate() +"' and miejsce ='" +
                    instructor.getPlace() + "'";

            resultSet = statement.executeQuery(selectQuery);

            if(resultSet.next() == false) {
                String query = "INSERT INTO zestawieniePracy (data," + instructor.getName() + ",miejsce) VALUES (" +
                        "'" + instructor.getDate() + "','" + instructor.getTime() + "','" + instructor.getPlace() +
                        "')";
                statement.execute(query);
            }
            else
            {
                String query = "UPDATE zestawieniePracy SET " + instructor.getName() + " = '" + instructor.getTime() +
                        "' WHERE data = '" + instructor.getDate() + "' and miejsce ='" +  instructor.getPlace() + "'" ;
                statement.execute(query);
            }

        } catch (Exception exception) {
            System.out.println(exception);
        }
    }




}
