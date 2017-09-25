package com.mkyong.payment.paymentSummary;

import com.mkyong.sqlBase.TableSelector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.*;

/**
 * Created by Cyprian on 2017-07-09.
 */

@Component
public class NumberOfMonths {

    private static final String REGEX = "-";
    @Autowired
    private TableSelector tableSelector;
    private Statement statement;
    private ResultSet resultSet;
    private Connection connection;

    public Map prepareButtons() {

        Map<String, Set> monthAndYearMap = new HashMap();
        Set<String> yearSet = prepareYearSetForSite();

        for (String year : yearSet) {
            monthAndYearMap.put(year, getMonthSetForYear(year));
        }
        return monthAndYearMap;
    }

    private List getDataTables() {

        List<String> tableList = tableSelector.showTablesFromBase();
        List<String> dataTables = new ArrayList<>();
        for (String tableName : tableList) {
            if (tableName.contains("daty"))
                dataTables.add(tableName);
        }
        return dataTables;
    }

    private List getDataList() {

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
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
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

    private void getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql:// 144.76.228.149:3306/testowa?useLegacyDatetimeCode=false&serverTimezone=UTC", "cypek", "foremny1a");
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
