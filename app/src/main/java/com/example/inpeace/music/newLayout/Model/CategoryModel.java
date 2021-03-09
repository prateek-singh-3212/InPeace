package com.example.inpeace.music.newLayout.Model;

public class CategoryModel {

    public CategoryModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public CategoryModel(String category , String title) {
        this.category = category;
        this.title = title;
    }

    private String category ,title;


}

