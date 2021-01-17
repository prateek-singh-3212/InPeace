package com.example.inpeace.motivation;

public class Model {

    String Title,MiniURL,Code;

    public Model() {
    }

    public Model(String title, String miniURL, String code) {
        Title = title;
        MiniURL = miniURL;
        Code = code;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getMiniURL() {
        return MiniURL;
    }

    public void setMiniURL(String miniURL) {
        MiniURL = miniURL;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }
}
