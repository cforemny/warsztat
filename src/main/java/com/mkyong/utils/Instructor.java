package com.mkyong.utils;

/**
 * Created by Cyprian on 2017-09-27.
 */
public class Instructor {

    private String name;
    private String date;
    private String time;
    private String place;


    public Instructor(String name, String date, String time, String place) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.place = place;
    }

    public Instructor() {
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
