package com.example.inpeace.music;

public class ClickedMusicModel {

    private String Songname ;
    private String SongURL;

    public void changeTEXT(String text){
        text = Songname;
    }

    public ClickedMusicModel() {
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

    public ClickedMusicModel(String songname, String songURL) {
        Songname = songname;
        SongURL = songURL;
    }

}
