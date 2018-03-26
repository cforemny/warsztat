package com.mkyong.utils;

/**
 * Created by Cyprian on 2017-07-21.
 */

public class NurserySchool {

    private int liczbaDzieci;
    private String data;
    private int cena;
    private String nazwaPrzedszkola;
    private String czyZaplacono;
    private String uwagi;


    public NurserySchool(int liczbaDzieci, String data, int cena, String nazwaPrzedszkola, String czyZaplacono, String uwagi) {
        this.liczbaDzieci = liczbaDzieci;
        this.data = data;
        this.cena = cena;
        this.nazwaPrzedszkola = nazwaPrzedszkola;
        this.czyZaplacono = czyZaplacono;
        this.uwagi = uwagi;
    }

    public NurserySchool(int liczbaDzieci, String data, int cena, String nazwaPrzedszkola, String czyZaplacono) {
        this.liczbaDzieci = liczbaDzieci;
        this.data = data;
        this.cena = cena;
        this.nazwaPrzedszkola = nazwaPrzedszkola;
        this.czyZaplacono = czyZaplacono;
    }

    public NurserySchool(int numerOfChildren, String data, int cena, String nazwaPrzedszkola) {
        this.liczbaDzieci = numerOfChildren;
        this.data = data;
        this.cena = cena;
        this.nazwaPrzedszkola = nazwaPrzedszkola;
    }

    public NurserySchool() {
    }

    public int getLiczbaDzieci() {
        return liczbaDzieci;
    }

    public void setLiczbaDzieci(int liczbaDzieci) {
        this.liczbaDzieci = liczbaDzieci;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public String getNazwaPrzedszkola() {
        return nazwaPrzedszkola;
    }

    public void setNazwaPrzedszkola(String nazwaPrzedszkola) {
        this.nazwaPrzedszkola = nazwaPrzedszkola;
    }

    public String getCzyZaplacono() {
        return czyZaplacono;
    }

    public void setCzyZaplacono(String czyZaplacono) {
        this.czyZaplacono = czyZaplacono;
    }

    public String getUwagi() {
        return uwagi;
    }

    public void setUwagi(String uwagi) {
        this.uwagi = uwagi;
    }
}
