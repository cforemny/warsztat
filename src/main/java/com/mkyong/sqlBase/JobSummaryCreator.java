package com.mkyong.sqlBase;

import com.mkyong.payment.Summary;
import com.mkyong.utils.Instructor;
import com.mkyong.utils.WorkSummary;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Cyprian on 2017-09-25.
 */
@Component
public class JobSummaryCreator extends Summary {


    private final String REGEX = "-";
    private Statement statement = getConnection().createStatement();
    private ResultSet resultSet;

    public JobSummaryCreator() throws SQLException, ClassNotFoundException {
    }

    public Map getInstrucorsList(String date) {
        Map<Integer, WorkSummary> instructorList = new HashMap<Integer, WorkSummary>();

        try {

            getConnection();
            String query = "select * from zestawieniePracy" + " WHERE data LIKE '%" + date + "%' order by data";
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
                String mpiech = resultSet.getString("mpiech");
                String rjanus = resultSet.getString("rjanus");
                String dduda = resultSet.getString("dduda");
                record++;
                instructorList.put(record, new WorkSummary(numer, data, miejsce, cforemny, oforemna, kasiak, jcichon, kskotniczny, pszydlo, lkrason
                        , mpiech, rjanus, dduda));

            }

        } catch (Exception exception) {
            System.out.println(exception);
        }
        return instructorList;
    }

    public WorkSummary getInstructorWorkSummaryByMonth(String date) {

        try {

            getConnection();
            String query = "select SUM(cforemny) as cforemny, SUM(oforemna) as oforemna,  SUM(kasiak) as kasiak, SUM(jcichon) as jcichon, " +
                    "SUM(kskotniczny) as kskotniczny, SUM(pszydlo) as pszydlo,  SUM(lkrason) as lkrason," +
                    " SUM(mpiech) as mpiech, SUM(rjanus) as rjanus, SUM(dduda) as dduda  from zestawieniePracy"
                    + " WHERE data LIKE '%" + date + "%'";
            resultSet = statement.executeQuery(query);
            resultSet.next();
            String cforemny = resultSet.getString("cforemny");
            String oforemna = resultSet.getString("oforemna");
            String kasiak = resultSet.getString("kasiak");
            String jcichon = resultSet.getString("jcichon");
            String kskotniczny = resultSet.getString("kskotniczny");
            String pszydlo = resultSet.getString("pszydlo");
            String lkrason = resultSet.getString("lkrason");
            String mpiech = resultSet.getString("mpiech");
            String rjanus = resultSet.getString("rjanus");
            String dduda = resultSet.getString("dduda");

            WorkSummary workSummary = new WorkSummary(cforemny, oforemna, kasiak, jcichon, kskotniczny, pszydlo, lkrason, mpiech, rjanus, dduda);
            return workSummary;

        } catch (Exception exception) {
            System.out.println(exception);
        }
        return null;
    }


    public void insertWorkingHour(Instructor instructor) {

        try {
            getConnection();

            String selectQuery = "select data, miejsce from zestawieniePracy where data = '" + instructor.getDate() + "' and miejsce ='" +
                    instructor.getPlace() + "'";

            resultSet = statement.executeQuery(selectQuery);

            if (resultSet.next() == false) {
                String query = "INSERT INTO zestawieniePracy (data," + instructor.getName() + ",miejsce) VALUES (" +
                        "'" + instructor.getDate() + "','" + instructor.getTime() + "','" + instructor.getPlace() +
                        "')";
                statement.execute(query);
            } else {
                String query = "UPDATE zestawieniePracy SET " + instructor.getName() + " = '" + instructor.getTime() +
                        "' WHERE data = '" + instructor.getDate() + "' and miejsce ='" + instructor.getPlace() + "'";
                statement.execute(query);
            }

        } catch (Exception exception) {
            System.out.println(exception);
        }
    }


}
