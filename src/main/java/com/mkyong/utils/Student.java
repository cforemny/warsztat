package com.mkyong.utils;

/**
 * Created by Cyprian on 2017-06-04.
 */
public class Student {

    private String imie;
    private String nazwisko;
    private String wiek;
    private String email;
    private String parentName;
    private String telephone;
    private String id;

    public Student(String imie, String nazwisko, String wiek, String email, String parentName, String telephone, String id) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.wiek = wiek;
        this.email = email;
        this.parentName = parentName;
        this.telephone = telephone;
        this.id = id;
    }

    public Student() {

    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getWiek() {
        return wiek;
    }

    public void setWiek(String wiek) {
        this.wiek = wiek;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
