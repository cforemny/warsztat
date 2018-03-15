package com.mkyong.payment.paymentSummary;

/**
 * Created by Cyprian on 2017-06-16.
 */
public class Payment {

    private String studentId;
    private String paymentDate;
    private int paymentValue;
    private int paymentId;
    private char paymentType;
    private int paymentCount;

    public Payment(String studentId, String date, int paymentValue, int paymentId, char paymentType, int paymentCount) {
        this.studentId = studentId;
        this.paymentDate = date;
        this.paymentValue = paymentValue;
        this.paymentId = paymentId;
        this.paymentType = paymentType;
        this.paymentCount = paymentCount;
    }

    public Payment(String studentId, String date, int paymentValue, int paymentId, char paymentType) {
        this.studentId = studentId;
        this.paymentDate = date;
        this.paymentValue = paymentValue;
        this.paymentId = paymentId;
        this.paymentType = paymentType;
    }

    public Payment() {

    }

    public Payment(int paymentCount) {
        this.paymentCount = paymentCount;
    }

    public int getPaymentCount() {
        return paymentCount;
    }

    public void setPaymentCount(int paymentCount) {
        this.paymentCount = paymentCount;
    }

    public char getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(char paymentType) {
        this.paymentType = paymentType;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
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
