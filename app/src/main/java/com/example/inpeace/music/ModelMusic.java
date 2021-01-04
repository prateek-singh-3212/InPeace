package com.example.inpeace.music;

import java.util.Scanner;

public class ModelMusic {
    private String Songname ;
    private String SongURL;

//    public void changeTEXT(String text){
//        text = Songname;
//    }

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

    public ModelMusic(String songname, String songURL) {
        Songname = songname;
        SongURL = songURL;
    }

}
