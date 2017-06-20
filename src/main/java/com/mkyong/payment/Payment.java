package com.mkyong.payment;

/**
 * Created by Cyprian on 2017-06-16.
 */
public class Payment {

    private int studentId;
    private String date;
    private int paymentValue;
    private int paymentId;

    public Payment(int studentId, String date, int paymentValue, int paymentId) {
        this.studentId = studentId;
        this.date = date;
        this.paymentValue = paymentValue;
        this.paymentId = paymentId;
    }

    public Payment(){

    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPaymentValue() {
        return paymentValue;
    }

    public void setPaymentValue(int paymentValue) {
        this.paymentValue = paymentValue;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }
}
