package com.mkyong.SQLBase;

/**
 * Created by Cyprian on 2017-06-07.
 */
public class TableCreator {

    private String statement;

    public TableCreator(String statement) {
        this.statement = statement;
    }

    public TableCreator() {
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }
}

