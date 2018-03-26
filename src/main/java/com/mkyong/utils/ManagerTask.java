package com.mkyong.utils;

/**
 * Created by Cyprian on 2017-09-28.
 */
public class ManagerTask {

    private int zadanieId;
    private String typZadania;
    private String uwagi;
    private String status;

    public ManagerTask(int zadanieId, String typZadania, String uwagi, String status) {
        this.zadanieId = zadanieId;
        this.typZadania = typZadania;
        this.uwagi = uwagi;
        this.status = status;
    }

    public ManagerTask() {
    }

    public int getZadanieId() {
        return zadanieId;
    }

    public void setZadanieId(int zadanieId) {
        this.zadanieId = zadanieId;
    }

    public String getTypZadania() {
        return typZadania;
    }

    public void setTypZadania(String typZadania) {
        this.typZadania = typZadania;
    }

    public String getUwagi() {
        return uwagi;
    }

    public void setUwagi(String uwagi) {
        this.uwagi = uwagi;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
