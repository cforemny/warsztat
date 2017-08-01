package com.mkyong.utils;

/**
 * Created by Cyprian on 2017-07-16.
 */
public class Expense {

    private String expenseType;
    private String expenseValue;
    private String expenseInstructor;
    private String expenseData;
    private char bill;

    public Expense() {
    }

    public Expense(String expenseType, String expenseValue, String expenseInstructor, String expenseData, char bill) {
        this.expenseType = expenseType;
        this.expenseValue = expenseValue;
        this.expenseInstructor = expenseInstructor;
        this.expenseData = expenseData;
        this.bill = bill;
    }

    public Expense(String expenseType, String expenseValue, String expenseInstructor, String expenseData) {
        this.expenseType = expenseType;
        this.expenseValue = expenseValue;
        this.expenseInstructor = expenseInstructor;
        this.expenseData = expenseData;
    }

    public char getBill() {
        return bill;
    }

    public void setBill(char bill) {
        this.bill = bill;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    public String getExpenseValue() {
        return expenseValue;
    }

    public void setExpenseValue(String expenseValue) {
        this.expenseValue = expenseValue;
    }

    public String getExpenseInstructor() {
        return expenseInstructor;
    }

    public void setExpenseInstructor(String expenseInstructor) {
        this.expenseInstructor = expenseInstructor;
    }

    public String getExpenseData() {
        return expenseData;
    }

    public void setExpenseData(String expenseData) {
        this.expenseData = expenseData;
    }
}
