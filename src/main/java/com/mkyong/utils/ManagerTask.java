package com.mkyong.utils;

/**
 * Created by Cyprian on 2017-09-28.
 */
public class ManagerTask {

    private int id;
    private String task;
    private String comments;
    private String status;

    public ManagerTask(int id, String task, String comments, String status) {
        this.id = id;
        this.task = task;
        this.comments = comments;
        this.status = status;
    }

    public ManagerTask() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
