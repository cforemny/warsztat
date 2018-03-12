package com.mkyong.utils;

/**
 * Created by Cyprian on 2017-07-21.
 */

public class NurserySchool {

    private int numberOfChildren;
    private String date;
    private int value;
    private String name;
    private String czyZaplacono;
    private String comments;

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public NurserySchool(int numberOfChildren, String date, int value, String name, String czyZaplacono, String comments) {
        this.numberOfChildren = numberOfChildren;
        this.date = date;
        this.value = value;
        this.name = name;
        this.czyZaplacono = czyZaplacono;
        this.comments = comments;
    }

    public NurserySchool(int numberOfChildren, String date, int value, String name, String czyZaplacono) {
        this.numberOfChildren = numberOfChildren;
        this.date = date;
        this.value = value;
        this.name = name;
        this.czyZaplacono = czyZaplacono;
    }

    public NurserySchool(int numerOfChildren, String date, int value, String name) {
        this.numberOfChildren = numerOfChildren;
        this.date = date;
        this.value = value;
        this.name = name;
    }

    public NurserySchool() {
    }

    public String getCzyZaplacono() {
        return czyZaplacono;
    }

    public void setCzyZaplacono(String czyZaplacono) {
        this.czyZaplacono = czyZaplacono;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
