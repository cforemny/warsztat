package com.mkyong.date;

/**
 * Created by Cyprian on 2017-06-15.
 */
public class CourseDate {

    private String currentDate;

    public CourseDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public CourseDate() {
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }
}
