package com.mkyong.payment.paymentSummary;

/**
 * Created by Cyprian on 2017-06-16.
 */
public class Payment {

    private String studentId;
    private String data;
    private int platnosc;
    private int obecnoscId;
    private char typPlatnosci;
    private int paymentCount;

    public Payment(String studentId, String data, int platnosc, int obecnoscId, char typPlatnosci, int paymentCount) {
        this.studentId = studentId;
        this.data = data;
        this.platnosc = platnosc;
        this.obecnoscId = obecnoscId;
        this.typPlatnosci = typPlatnosci;
        this.paymentCount = paymentCount;
    }

    public Payment(String studentId, String data, int platnosc, int obecnoscId, char typPlatnosci) {
        this.studentId = studentId;
        this.data = data;
        this.platnosc = platnosc;
        this.obecnoscId = obecnoscId;
        this.typPlatnosci = typPlatnosci;
    }

    public Payment() {

    }

    public Payment(int paymentCount) {
        this.paymentCount = paymentCount;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getPlatnosc() {
        return platnosc;
    }

    public void setPlatnosc(int platnosc) {
        this.platnosc = platnosc;
    }

    public int getObecnoscId() {
        return obecnoscId;
    }

    public void setObecnoscId(int obecnoscId) {
        this.obecnoscId = obecnoscId;
    }

    public char getTypPlatnosci() {
        return typPlatnosci;
    }

    public void setTypPlatnosci(char typPlatnosci) {
        this.typPlatnosci = typPlatnosci;
    }

    public int getPaymentCount() {
        return paymentCount;
    }

    public void setPaymentCount(int paymentCount) {
        this.paymentCount = paymentCount;
    }
}
