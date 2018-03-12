package com.mkyong.utils;

/**
 * Created by Cyprian on 2017-07-22.
 */
public class Event {

    private String date;
    private String eventType;
    private double value;
    private char faktura;
    private char isPaid;
    private String comments;


    public Event(String date, String eventType, double value, char faktura, char isPaid, String comments) {
        this.date = date;
        this.eventType = eventType;
        this.value = value;
        this.faktura = faktura;
        this.isPaid = isPaid;
        this.comments = comments;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Event(String date, String eventType, double value, char faktura, char isPaid) {
        this.date = date;
        this.eventType = eventType;
        this.value = value;
        this.faktura = faktura;
        this.isPaid = isPaid;
    }

    public Event() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public char getFaktura() {
        return faktura;
    }

    public void setFaktura(char faktura) {
        this.faktura = faktura;
    }

    public char getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(char isPaid) {
        this.isPaid = isPaid;
    }
}
