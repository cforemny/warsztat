package com.mkyong.utils;

/**
 * Created by Cyprian on 2017-07-22.
 */
public class Event {

    private String data;
    private String rodzajEventu;
    private double cena;
    private char faktura;
    private char czyZaplacono;
    private String uwagi;


    public Event(String data, String rodzajEventu, double cena, char faktura, char czyZaplacono) {
        this.data = data;
        this.rodzajEventu = rodzajEventu;
        this.cena = cena;
        this.faktura = faktura;
        this.czyZaplacono = czyZaplacono;
    }

    public Event(String data, String rodzajEventu, double cena, char faktura, char czyZaplacono, String uwagi) {
        this.data = data;
        this.rodzajEventu = rodzajEventu;
        this.cena = cena;
        this.faktura = faktura;
        this.czyZaplacono = czyZaplacono;
        this.uwagi = uwagi;
    }


    public Event() {
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getRodzajEventu() {
        return rodzajEventu;
    }

    public void setRodzajEventu(String rodzajEventu) {
        this.rodzajEventu = rodzajEventu;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public char getFaktura() {
        return faktura;
    }

    public void setFaktura(char faktura) {
        this.faktura = faktura;
    }

    public char getCzyZaplacono() {
        return czyZaplacono;
    }

    public void setCzyZaplacono(char czyZaplacono) {
        this.czyZaplacono = czyZaplacono;
    }

    public String getUwagi() {
        return uwagi;
    }

    public void setUwagi(String uwagi) {
        this.uwagi = uwagi;
    }


}
