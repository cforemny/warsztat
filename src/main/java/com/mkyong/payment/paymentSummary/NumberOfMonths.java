package com.mkyong.payment.paymentSummary;

import com.mkyong.payment.Summary;
import com.mkyong.sqlBase.TableSelector;
import com.mkyong.utils.Date;
import com.mkyong.utils.TableName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.*;

/**
 * Created by Cyprian on 2017-07-09.
 */

@Component
public class NumberOfMonths extends Summary {

    private static final String REGEX = "-";
    @Autowired
    private TableSelector tableSelector;

    public NumberOfMonths() throws SQLException, ClassNotFoundException {
    }

    public Map prepareButtons() {

        Map<String, Set> monthAndYearMap = new HashMap();
        Set<String> yearSet = prepareYearSetForSite();

        for (String year : yearSet) {
            monthAndYearMap.put(year, getMonthSetForYear(year));
        }
        return monthAndYearMap;
    }

    private List getDataTables() {

        List<TableName> tableList = tableSelector.showTablesFromBase();
        List<String> dataTables = new ArrayList<>();
        for (TableName tableName : tableList) {
            if ((tableName.getTables_in_testowa()).contains("daty"))
                dataTables.add(tableName.getTables_in_testowa());
        }
        return dataTables;
    }

    private List getDataList() {

        List dataList = new ArrayList<String>();
        List<String> dataTables = getDataTables();
        for (String dataTablename : dataTables) {
            String query = "SELECT * FROM " + dataTablename;
            List<Date> dataListForMonth = getJdbcTemplate().query(query, new BeanPropertyRowMapper(Date.class));
            for (Date data : dataListForMonth) {
                dataList.add(data.getData());
            }
        }

        return dataList;
    }

    private Set prepareYearSetForSite() {

        List<String> dataList = getDataList();
        Set<String> yearSet = new TreeSet<String>();

        for (String data : dataList) {
            yearSet.add(cutDataYear(data));
        }

        return yearSet;
    }

    private String cutDataMonth(String data) {

        String[] splittedData = data.split(REGEX);
        return splittedData[1];
    }

    private String cutDataYear(String data) {

        String[] splittedData = data.split(REGEX);
        return splittedData[0];
    }

    private Set getMonthSetForYear(String year) {

        List<String> dataList = getDataList();
        Set<String> monthSet = new TreeSet<>();
        for (String data : dataList) {
            if (data.contains(year)) {
                monthSet.add(switchMonthNumberToName(cutDataMonth(data)));
            }
        }
        return monthSet;
    }

    private String switchMonthNumberToName(String numberOfMonth) {

        switch (numberOfMonth) {
            case "01":
                return "Styczeń";
            case "02":
                return "Luty";
            case "03":
                return "Marzec";
            case "04":
                return "Kwiecień";
            case "05":
                return "Maj";
            case "06":
                return "Czerwiec";
            case "07":
                return "Lipiec";
            case "08":
                return "Sierpień";
            case "09":
                return "Wrzesień";
            case "10":
                return "Październik";
            case "11":
                return "Listopad";
            case "12":
                return "Grudzień";

        }
        return "";
    }


}
