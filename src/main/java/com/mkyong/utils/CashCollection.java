package com.mkyong.utils;

/**
 * Created by Cyprian on 2017-07-16.
 */
public class CashCollection {

    private String instruktor;
    private String kwota;
    private String data;
    private String miejsce;


    public CashCollection() {
    }

    public CashCollection(String instruktor, String kwota, String data, String miejsce) {
        this.instruktor = instruktor;
        this.kwota = kwota;
        this.data = data;
        this.miejsce = miejsce;
    }

    public String getInstruktor() {
        return instruktor;
    }

    public void setInstruktor(String instruktor) {
        this.instruktor = instruktor;
    }

    public String getKwota() {
        return kwota;
    }

    public void setKwota(String kwota) {
        this.kwota = kwota;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMiejsce() {
        return miejsce;
    }

    public void setMiejsce(String miejsce) {
        this.miejsce = miejsce;
    }
}
