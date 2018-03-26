package com.mkyong.sqlBase;

import com.mkyong.payment.Summary;
import com.mkyong.utils.NurserySchool;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * Created by Cyprian on 2017-07-21.
 */
@Component
public class PreSchoolCreator extends Summary {


    public PreSchoolCreator() throws SQLException, ClassNotFoundException {
    }

    public void updateNurseryPayment(String data, String liczbaDzieci) {

        String query = "UPDATE listaprzedszkoli SET czyzaplacono =  'T' WHERE data ='" + data + "' AND liczbadzieci = '" +
                liczbaDzieci + "'";
        getJdbcTemplate().update(query);
    }

    public void insertPreschoolIntoTable(NurserySchool nurserySchool) {

        String query = "INSERT INTO listaprzedszkoli (liczbadzieci, data, cena, nazwaprzedszkola, czyzaplacono ) VALUES(" + nurserySchool.getLiczbaDzieci() +
                ",'" + nurserySchool.getData() + "'," + nurserySchool.getCena() + ",'" + nurserySchool.getNazwaPrzedszkola() + "','N')";
        getJdbcTemplate().update(query);
    }


}
