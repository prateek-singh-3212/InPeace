package com.example.inpeace.motivation.newLayout.Model;

public class VideoModel {

    private String thumbnail,title,video;

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

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public VideoModel(String thumbnail, String title, String video) {
        this.thumbnail = thumbnail;
        this.title = title;
        this.video = video;
    }

    public VideoModel() {
    }
}
