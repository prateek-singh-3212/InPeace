package com.example.inpeace.activites;

public class ActivityModel {

    String Task, Code;

    public ActivityModel() {
    }

    public ActivityModel(String task, String code) {
        Task = task;
        Code = code;
    }

    public String getTask() {
        return Task;
    }

    public void setTask(String task) {
        Task = task;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public ActivityModel(String task) {
        Task = task;
    }
}
