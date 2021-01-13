package com.example.inpeace.activites;

public class ActivityModel {

    String Task, SNo;

    public ActivityModel() {
    }

    public String getTask() {
        return Task;
    }

    public void setTask(String task) {
        Task = task;
    }

    public String getSNo() {
        return SNo;
    }

    public void setSNo(String SNo) {
        this.SNo = SNo;
    }

    public ActivityModel(String task, String SNo) {
        Task = task;
        this.SNo = SNo;
    }
}
