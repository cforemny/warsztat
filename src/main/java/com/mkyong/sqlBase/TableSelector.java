package com.mkyong.sqlBase;

import com.mkyong.payment.Summary;
import com.mkyong.payment.paymentSummary.Payment;
import com.mkyong.utils.Date;
import com.mkyong.utils.Student;
import com.mkyong.utils.TableName;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cyprian on 2017-06-23.
 */
@Component
public class TableSelector extends Summary {

    public TableSelector() throws SQLException, ClassNotFoundException {
    }

    public List getStudentListFromTable(String tableName, boolean checkbox) {

        List<Student> studentListRemoved = new ArrayList<Student>();

        String query = "select * from " + tableName;
        List<Student> studentList = getJdbcTemplate().query(query, new BeanPropertyRowMapper(Student.class));

        if (!checkbox) {
            return studentList;
        } else {
            for (Student student : studentList) {
                if (!removeStudentFromList(student, tableName) || Integer.parseInt(student.getId()) == studentList.size()) {
                    studentListRemoved.add(student);
                }
            }
            return studentListRemoved;
        }
    }

    public List getDateTable(String tableName, boolean checkbox) {

        String query = "select * from " + tableName;
        List<Date> dateList = getJdbcTemplate().query(query, new BeanPropertyRowMapper(Date.class));

        if (checkbox && dateList.size() >= 8) {
            List<Date> sublist = dateList.subList(dateList.size() - 8, dateList.size());
            return sublist;
        }
        return dateList;
    }

    public List getPaymentList(String tableName) {

        String query = "select * from " + tableName;
        List<Payment> paymentList = getJdbcTemplate().query(query, new BeanPropertyRowMapper<>(Payment.class));

        return paymentList;
    }

    public List showTablesFromBase() {

        String query = "SHOW TABLES FROM testowa WHERE tables_in_testowa LIKE 'daty%'";
        List<TableName> tableList = getJdbcTemplate().query(query, new BeanPropertyRowMapper(TableName.class));

        return tableList;
    }

    public void removeDuplicatesFromPayments(String tableName) {

        List<Payment> paymentList = getPaymentList(tableName);

        for (Payment comperingPayment : paymentList) {
            if (comperingPayment.getPlatnosc() == 0 && comperingPayment.getTypPlatnosci() == 'T') {
                for (Payment comparedPayment : paymentList) {
                    if (comparedPayment.getPlatnosc() > 0 &&
                            comparedPayment.getData().equals(comperingPayment.getData()) &&
                            comparedPayment.getStudentId().equals(comperingPayment.getStudentId())) {

                        String query = "DELETE FROM " + tableName + " WHERE studentId = " + comperingPayment.getStudentId() +
                                " AND data = '" + comperingPayment.getData() + "' AND typPlatnosci = 'T'" + " AND platnosc = " +
                                comperingPayment.getPlatnosc();
                        getJdbcTemplate().execute(query);
                    }
                }
            }
        }
    }

    private boolean removeStudentFromList(Student student, String tableName) {

        int countPayments = 12;

        List<Payment> paymentList = getPaymentList("platnosci" + tableName);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();
        String currentDate = dtf.format(localDate);
        int paymentDateMontAsInt;
        int currenttDateMontAsInt;

        for (Payment payment : paymentList) {
            if (student.getId().equals(payment.getStudentId())) {
                String paymentDate = payment.getData();
                paymentDateMontAsInt = Integer.parseInt(paymentDate.substring(5, 7));
                currenttDateMontAsInt = Integer.parseInt(currentDate.substring(5, 7));

                if (paymentDateMontAsInt == currenttDateMontAsInt ||
                        paymentDateMontAsInt == currenttDateMontAsInt - 1 ||
                        paymentDateMontAsInt == currenttDateMontAsInt - 2 ||
                        paymentDateMontAsInt == currenttDateMontAsInt + 1) {
                    countPayments--;
                }
            }
        }
        if (countPayments == 12) {
            return true;
        } else
            return false;
    }
}
