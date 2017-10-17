package com.mkyong.utils;

/**
 * Created by Cyprian on 2017-09-25.
 */
public class WorkSummary {

    private String id;
    private String data;
    private String miejsce;
    private String cforemny;
    private String oforemna;
    private String jcichon;
    private String kskotniczny;
    private String lkrason;
    private String pszydlo;
    private String kasiak;
    private String mpiech;
    private String rjanus;
    private String dduda;

    public WorkSummary() {
    }

    public WorkSummary(String data, String miejsce) {
        this.data = data;
        this.miejsce = miejsce;
    }


    public WorkSummary(String cforemny, String oforemna, String kasiak, String jcichon, String kskotniczny, String pszydlo, String lkrason,
                       String mpiech, String rjanus, String dduda) {
        this.cforemny = cforemny;
        this.oforemna = oforemna;
        this.kasiak = kasiak;
        this.jcichon = jcichon;
        this.kskotniczny = kskotniczny;
        this.lkrason = lkrason;
        this.pszydlo = pszydlo;
        this.mpiech = mpiech;
        this.rjanus = rjanus;
        this.dduda = dduda;
    }

    public WorkSummary(String id, String data, String miejsce, String cforemny, String oforemna, String kasiak, String jcichon, String kskotniczny, String pszydlo, String lkrason, String mpiech, String rjanus, String dduda) {
        this.id = id;
        this.data = data;
        this.miejsce = miejsce;
        this.cforemny = cforemny;
        this.oforemna = oforemna;
        this.jcichon = jcichon;
        this.kskotniczny = kskotniczny;
        this.lkrason = lkrason;
        this.pszydlo = pszydlo;
        this.kasiak = kasiak;
        this.mpiech = mpiech;
        this.rjanus = rjanus;
        this.dduda = dduda;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCforemny() {
        return cforemny;
    }

    public void setCforemny(String cforemny) {
        this.cforemny = cforemny;
    }

    public String getOforemna() {
        return oforemna;
    }

    public void setOforemna(String oforemna) {
        this.oforemna = oforemna;
    }

    public String getJcichon() {
        return jcichon;
    }

    public void setJcichon(String jcichon) {
        this.jcichon = jcichon;
    }

    public String getKskotniczny() {
        return kskotniczny;
    }

    public void setKskotniczny(String kskotniczny) {
        this.kskotniczny = kskotniczny;
    }

    public String getLkrason() {
        return lkrason;
    }

    public void setLkrason(String lkrason) {
        this.lkrason = lkrason;
    }

    public String getPszydlo() {
        return pszydlo;
    }

    public void setPszydlo(String pszydlo) {
        this.pszydlo = pszydlo;
    }

    public String getKasiak() {
        return kasiak;
    }

    public void setKasiak(String kasiak) {
        this.kasiak = kasiak;
    }

    public String getMpiech() {
        return mpiech;
    }

    public void setMpiech(String mpiech) {
        this.mpiech = mpiech;
    }

    public String getRjanus() {
        return rjanus;
    }

    public void setRjanus(String rjanus) {
        this.rjanus = rjanus;
    }

    public String getDduda() {
        return dduda;
    }

    public void setDduda(String dduda) {
        this.dduda = dduda;
    }
}
