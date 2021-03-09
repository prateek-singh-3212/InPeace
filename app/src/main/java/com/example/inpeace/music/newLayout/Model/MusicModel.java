package com.example.inpeace.music.newLayout.Model;

public class MusicModel {

//    private String title , url,thumbnail;
//
//    public MusicModel() {
//    }
//
////    public String getTitle() {
////        return title;
////    }
////
////    public void setTitle(String title) {
////        this.title = title;
////    }
//
//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
//
//    public String getThumbnail() {
//        return thumbnail;
//    }
//
//    public void setThumbnail(String thumbnail) {
//        this.thumbnail = thumbnail;
//    }
//
//    public MusicModel(String title, String url, String thumbnail) {
////        this.title = title;
//        this.url = url;
//        this.thumbnail = thumbnail;
//    }

    private String songname,songImage,songURL;

    public String getSongname() {
        return songname;
    }

    public void setSongname(String songname) {
        this.songname = songname;
    }

    public String getSongImage() {
        return songImage;
    }

    public void setSongImage(String songImage) {
        this.songImage = songImage;
    }

    public String getSongURL() {
        return songURL;
    }

    public void setSongURL(String songURL) {
        this.songURL = songURL;
    }

    public MusicModel() {
    }

    public MusicModel(String songname, String songImage, String songURL) {
        this.songname = songname;
        this.songImage = songImage;
        this.songURL = songURL;
    }
}
