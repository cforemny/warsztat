package com.mkyong.payment.paymentSummary;

import com.mkyong.SQLBase.TableSelector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.*;

/**
 * Created by Cyprian on 2017-07-09.
 */
@Component
public class MonthSummary {

    @Autowired
    TableSelector tableSelector;
    private Statement statement;
    private ResultSet resultSet;
    private Connection connection;
    private static final String REGEX = "-";

    public List getDataTables() {

        List<String> tableList = tableSelector.showTablesFromBase();
        List<String> dataTables = new ArrayList<>();
        for (String tableName : tableList) {
            if (tableName.contains("daty"))
                dataTables.add(tableName);
        }
        return dataTables;
    }

    public List getDataList() {

        List dataList = new ArrayList<String>();
        try {
            getConnection();
            List<String> dataTables = getDataTables();

            for (String dataTablename : dataTables) {
                String query = "SELECT * FROM " + dataTablename;
                resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    String data = resultSet.getString("data");
                    dataList.add(data);
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return dataList;
    }

    public Set prepareYearSetForSite() {

        List<String> dataList = getDataList();
        Set<String> yearSet = new TreeSet<String>();

        for (String data : dataList) {
            yearSet.add(cutDataYear(data));
        }

        return yearSet;
    }

    public Map prepareButtons() {

        Map<String, Set> monthAndYearMap = new HashMap();
        Set<String> yearSet = prepareYearSetForSite();

        for (String year : yearSet) {
            monthAndYearMap.put(year, getMonthSetForYear(year));
        }
        return monthAndYearMap;
    }


    private void getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/warsztatyrobotow?useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
        statement = connection.createStatement();
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
                return "Styczen";
            case "02":
                return "Luty";
            case "03":
                return "Marzec";
            case "04":
                return "Kwiecien";
            case "05":
                return "Maj";
            case "06":
                return "Czerwiec";
            case "07":
                return "Lipiec";
            case "08":
                return "Sierpień";
        }
        return "";
    }


}
