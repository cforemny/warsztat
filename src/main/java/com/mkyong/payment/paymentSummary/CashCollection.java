package com.mkyong.payment.paymentSummary;

/**
 * Created by Cyprian on 2017-07-16.
 */
public class CashCollection {

    private String instructor;
    private String value;
    private String date;
    private String location;


    public CashCollection() {
    }

    public CashCollection(String instructor, String value, String date, String location) {
        this.instructor = instructor;
        this.value = value;
        this.date = date;
        this.location = location;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
