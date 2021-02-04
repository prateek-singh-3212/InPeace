package com.example.inpeace.audiobooks.Model;

public class Model_All_Fragments {

    private String thumbnail;
    private String title;
    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Model_All_Fragments() {
    }

    public Model_All_Fragments(String thumbnail, String title , String category) {
        this.thumbnail = thumbnail;
        this.title = title;
        this.category = category;
    }
}
