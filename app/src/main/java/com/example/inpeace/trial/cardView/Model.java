package com.example.inpeace.trial.cardView;

public class Model {
    private String name;
    private String age;

    public void changeText(String text){
        name = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Model() {
    }

    public Model(String name, String age) {
        this.name = name;
        this.age = age;
    }
}
