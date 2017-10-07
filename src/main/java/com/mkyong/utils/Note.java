package com.mkyong.utils;

/**
 * Created by Cyprian on 2017-10-07.
 */
public class Note {

    private String content;
    private int id;

    public Note(String content, int id) {
        this.content = content;
        this.id = id;
    }

    public Note() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
