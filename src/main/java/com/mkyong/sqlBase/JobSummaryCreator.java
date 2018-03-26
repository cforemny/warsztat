package com.mkyong.sqlBase;

import com.mkyong.payment.Summary;
import com.mkyong.utils.Instructor;
import com.mkyong.utils.WorkSummary;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Cyprian on 2017-09-25.
 */
@Component
public class JobSummaryCreator extends Summary {

    private final String REGEX = "-";

    public JobSummaryCreator() throws SQLException, ClassNotFoundException {
    }

    public Map getInstrucorsList(String date) {
        Map<Integer, WorkSummary> instructorMap = new HashMap<Integer, WorkSummary>();

        String query = "select * from zestawieniePracy" + " WHERE data LIKE '%" + date + "%' order by data";
        List<WorkSummary> instructorList = getJdbcTemplate().query(query, new BeanPropertyRowMapper(WorkSummary.class));

        int record = 0;
        for (WorkSummary workSummary : instructorList) {
            record++;
            instructorMap.put(record, workSummary);
        }
        return instructorMap;
    }

    public WorkSummary getInstructorWorkSummaryByMonth(String date) {

        String query = "select SUM(cforemny) as cforemny, SUM(oforemna) as oforemna,  SUM(kasiak) as kasiak, SUM(jcichon) as jcichon, " +
                "SUM(kskotniczny) as kskotniczny, SUM(pszydlo) as pszydlo,  SUM(lkrason) as lkrason," +
                " SUM(mpiech) as mpiech, SUM(rjanus) as rjanus, SUM(dduda) as dduda  from zestawieniePracy"
                + " WHERE data LIKE '%" + date + "%'";
        Object workingHourSummary = getJdbcTemplate().queryForObject(query, new BeanPropertyRowMapper(WorkSummary.class));

        WorkSummary workSummary = (WorkSummary) workingHourSummary;

        return workSummary;
    }


    public void insertWorkingHour(Instructor instructor) {

        String selectQuery = "select data, miejsce from zestawieniePracy where data = '" + instructor.getDate() + "' and miejsce ='" +
                instructor.getPlace() + "'";

        List workSummary = getJdbcTemplate().query(selectQuery, new BeanPropertyRowMapper(WorkSummary.class));

        if (workSummary.size() == 0) {
            String query = "INSERT INTO zestawieniePracy (data," + instructor.getName() + ",miejsce) VALUES (" +
                    "'" + instructor.getDate() + "','" + instructor.getTime() + "','" + instructor.getPlace() +
                    "')";
            getJdbcTemplate().execute(query);
        } else {
            String query = "UPDATE zestawieniePracy SET " + instructor.getName() + " = '" + instructor.getTime() +
                    "' WHERE data = '" + instructor.getDate() + "' and miejsce ='" + instructor.getPlace() + "'";
            getJdbcTemplate().execute(query);
        }
    }
}
