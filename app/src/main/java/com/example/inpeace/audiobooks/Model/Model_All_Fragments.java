package com.example.inpeace.audiobooks.Model;

public class Model_All_Fragments {

    private String thumbnail,bookname;

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public Model_All_Fragments() {
    }

    public Model_All_Fragments(String thumbnail, String bookname) {
        this.thumbnail = thumbnail;
        this.bookname = bookname;
    }
}
