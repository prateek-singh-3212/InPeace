package com.example.inpeace.audiobooks.Model;

import java.util.ArrayList;
import java.util.List;

public class Model_Details {


    String bookName,Bookcategory,imgUrl;

    List<String> data = new ArrayList<>();

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public Model_Details(String bookName, String bookcategory, String imgUrl) {
        this.bookName = bookName;
        Bookcategory = bookcategory;
        this.imgUrl = imgUrl;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookcategory() {
        return Bookcategory;
    }

    public void setBookcategory(String bookcategory) {
        Bookcategory = bookcategory;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Model_Details() {
    }
}
