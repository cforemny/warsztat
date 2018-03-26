package com.mkyong.utils;

/**
 * Created by Cyprian on 2017-07-16.
 */
public class Expense {

    private String opisWydatku;
    private String kwota;
    private String instruktor;
    private String data;
    private char faktura;

    public Expense() {
    }

    public Expense(String opisWydatku, String kwota, String instruktor, String data, char faktura) {
        this.opisWydatku = opisWydatku;
        this.kwota = kwota;
        this.instruktor = instruktor;
        this.data = data;
        this.faktura = faktura;
    }

    public String getOpisWydatku() {
        return opisWydatku;
    }

    public void setOpisWydatku(String opisWydatku) {
        this.opisWydatku = opisWydatku;
    }

    public String getKwota() {
        return kwota;
    }

    public void setKwota(String kwota) {
        this.kwota = kwota;
    }

    public String getInstruktor() {
        return instruktor;
    }

    public void setInstruktor(String instruktor) {
        this.instruktor = instruktor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public char getFaktura() {
        return faktura;
    }

    public void setFaktura(char faktura) {
        this.faktura = faktura;
    }
}
