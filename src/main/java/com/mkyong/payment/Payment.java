package com.mkyong.payment;

/**
 * Created by Cyprian on 2017-06-16.
 */
public class Payment {

    private String studentId;
    private String paymentDate;
    private int paymentValue;
    private int paymentId;

    public Payment(String studentId, String date, int paymentValue, int paymentId) {
        this.studentId = studentId;
        this.paymentDate = date;
        this.paymentValue = paymentValue;
        this.paymentId = paymentId;
    }

    public Payment(){

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
