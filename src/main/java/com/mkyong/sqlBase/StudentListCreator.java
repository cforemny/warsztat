package com.mkyong.sqlBase;

import com.mkyong.payment.Summary;
import com.mkyong.payment.paymentSummary.Payment;
import com.mkyong.utils.Date;
import com.mkyong.utils.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Cyprian on 2017-06-04.
 */

@Component
public class StudentListCreator extends Summary {

    @Autowired
    private TableSelector tableSelector;

    public StudentListCreator() throws SQLException, ClassNotFoundException {
    }

    public void addStudentToList(Student student, String tableName, int studentId) {

        String insertQuery = "INSERT INTO " + tableName + " (imie, nazwisko, wiek, email, parentName, telephone, id)"
                + " VALUES " + "('" + student.getImie() + "'," + "'" + student.getNazwisko() + "'," + "'" + student.getWiek() + "',"
                + "'" + student.getEmail() + "'," + "'" + student.getParentName() + "'," + "'" + student.getTelephone() + "',"
                + "'" + studentId + "')";
        getJdbcTemplate().execute(insertQuery);
    }

    public void deleteStudent(String studentListId, String tableName) {

        String deleteQuery = "DELETE FROM " + tableName + " WHERE " + tableName + ".id=" + studentListId;
        getJdbcTemplate().execute(deleteQuery);
    }

    public void addNewDate(String tableName, String date) {

        String queryData = "INSERT INTO " + tableName + " (data) " + " VALUES ('" + date + "')";
        getJdbcTemplate().execute(queryData);
    }

    public boolean addNewPayment(String tableName, Payment payment, String studentId, String date, char typPlatnosci) {

        int dateIndex = 1;
        if (payment.getPaymentCount() == 1) {
            String queryPayment = "INSERT INTO " + "platnosci" + tableName + " (studentId,data,platnosc, typPlatnosci) " + " VALUES (" + Integer.parseInt(studentId) + ",'" + date + "'," + payment.getPlatnosc() +
                    ",'" + typPlatnosci + "')";
            getJdbcTemplate().execute(queryPayment);
        } else {
            List<Date> dateTable = tableSelector.getDateTable("daty" + tableName, true);
            if (dateTable.indexOf(date) + payment.getPaymentCount() > dateTable.size()-1) {
                return false;
            }
            for (Date groupDate : dateTable) {
                if ((groupDate.getData()).equals(date) || (dateIndex > 1 && dateIndex <= payment.getPaymentCount())) {

                    dateIndex++;
                    dateTable.indexOf(groupDate.getData());
                    String queryPayment = "INSERT INTO " + "platnosci" + tableName + " (studentId,data,platnosc, typPlatnosci) " + " VALUES " +
                            "(" + Integer.parseInt(studentId) + ",'" + groupDate.getData() + "'," + payment.getPlatnosc() +
                            ",'" + typPlatnosci + "')";
                    getJdbcTemplate().execute(queryPayment);
                }
            }
        }
        return true;
    }

    public void removePayment(String tableName, String studentId, String date) {

        String queryPayment = "DELETE from " + tableName + " WHERE studentId = " + Integer.parseInt(studentId) + " AND  data = '" + date + "'";
        getJdbcTemplate().execute(queryPayment);
    }

    public void removeDate(String tableName, String date) {

        String queryDate = "DELETE from " + tableName + " WHERE data = '" + date + "'";
        getJdbcTemplate().execute(queryDate);

    }

}
