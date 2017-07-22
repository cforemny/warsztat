package com.mkyong.SQLBase;

import com.mkyong.utils.NurserySchool;
import com.mkyong.payment.Summary;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * Created by Cyprian on 2017-07-21.
 */
@Component
public class PreSchoolCreator extends Summary {


    public PreSchoolCreator() throws SQLException, ClassNotFoundException {
    }

    public void insertPreschoolIntoTable(NurserySchool nurserySchool) {

        try {
            String query = "INSERT INTO listaprzedszkoli (liczbadzieci, data, cena, nazwaprzedszkola) VALUES(" + nurserySchool.getNumberOfChildren() +
                    ",'" + nurserySchool.getDate() + "'," + nurserySchool.getValue() + ",'" + nurserySchool.getName() + "')";
            getConnection().execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                getConnection().close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
