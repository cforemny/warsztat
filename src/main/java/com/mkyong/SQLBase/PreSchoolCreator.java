package com.mkyong.SQLBase;

import com.mkyong.StudentsList.NurserySchool;
import com.mkyong.payment.Summary;
import org.springframework.stereotype.Component;

import javax.swing.plaf.nimbus.State;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Cyprian on 2017-07-21.
 */
@Component
public class PreSchoolCreator extends Summary {

    private Statement statement =  getConnection();;


    public PreSchoolCreator() throws SQLException, ClassNotFoundException {
    }

    public void insertPreschoolIntoTable(NurserySchool nurserySchool){

        try {
            String query = "INSERT INTO listaprzedszkoli (liczbadzieci, data, cena, nazwaprzedszkola) VALUES(" + nurserySchool.getNumberOfChildren() +
                    ",'" + nurserySchool.getDate() + "'," + nurserySchool.getValue() + ",'" + nurserySchool.getName() +"')" ;
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





}
