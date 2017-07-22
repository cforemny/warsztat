package com.mkyong.utils;

/**
 * Created by Cyprian on 2017-07-22.
 */
public class Event {

    private String date;
    private String eventType;
    private double value;
    private char faktura;

    public Event(String date, String eventType, double value, char faktura) {
        this.date = date;
        this.eventType = eventType;
        this.value = value;
        this.faktura = faktura;
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
}
