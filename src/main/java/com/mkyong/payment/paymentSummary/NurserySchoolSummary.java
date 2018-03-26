package com.mkyong.payment.paymentSummary;

import com.mkyong.payment.Summary;
import com.mkyong.utils.NurserySchool;
import com.mkyong.utils.NurserySchoolPaymentSummary;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Cyprian on 2017-07-22.
 */
@Component
public class NurserySchoolSummary extends Summary {

    private final String REGEX = "-";

    public NurserySchoolSummary() throws SQLException, ClassNotFoundException {
    }

    public double getPaymentFromNurserySchools(String date) {

        int payment = 0;

        String year = getYearForSummary(date);
        String month = getMonthForSummary(date);
        String monthNumber = switchMonth(month);

        String query = "select data, liczbadzieci, cena from listaprzedszkoli " + " WHERE data LIKE '" + year + "%'" +
                "AND data LIKE '%-" + monthNumber + "-%'";

        List<NurserySchoolPaymentSummary> nurserySchoolListForSummary = getJdbcTemplate().query(query, new BeanPropertyRowMapper(NurserySchoolPaymentSummary.class));

        for (NurserySchoolPaymentSummary nurserySchoolPaymentSummary : nurserySchoolListForSummary) {
            payment = payment + nurserySchoolPaymentSummary.getCena() * nurserySchoolPaymentSummary.getLiczbaDzieci();
        }

        return payment;
    }

    public List<NurserySchool> getListOfNurserySchoolByMonth(String date) {

        String year = getYearForSummary(date);
        String monthNumber;

        if (date.contains(REGEX)) {
            monthNumber = getActualMonthForSummary(date);
        } else {
            monthNumber = switchMonth(getMonthForSummary(date));
        }

        String query = "select data, liczbadzieci, cena, nazwaprzedszkola, czyzaplacono, uwagi from listaprzedszkoli " + " WHERE data LIKE '" + year + "%'" +
                "AND data LIKE '%-" + monthNumber + "-%'";

        List<NurserySchool> nurserySchools = getJdbcTemplate().query(query, new BeanPropertyRowMapper(NurserySchool.class));

        return nurserySchools;
    }
}
