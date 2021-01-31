package com.example.inpeace.music.newLayout.Model;

public class MusicModel {

    private String Songname ;
    private String SongURL;
    private String SongImage;

    public MusicModel() {
    }

    public MusicModel(String songname, String songURL, String songImage) {
        Songname = songname;
        SongURL = songURL;
        SongImage = songImage;
    }

    public String getSongname() {
        return Songname;
    }

    public void setSongname(String songname) {
        Songname = songname;
    }

    public String getSongURL() {
        return SongURL;
    }

    public void setSongURL(String songURL) {
        SongURL = songURL;
    }

    public String getSongImage() {
        return SongImage;
    }

    public void setSongImage(String songImage) {
        SongImage = songImage;
    }
}
