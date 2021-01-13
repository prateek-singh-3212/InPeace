package com.example.inpeace.music;

import java.util.Scanner;

public class ModelMusic {
    private String Songname ;
    private String SongURL;
    private String SongImage;

    public ModelMusic(String songname, String songURL, String songImage) {
        Songname = songname;
        SongURL = songURL;
        SongImage = songImage;
    }


    public ModelMusic() {
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
