package com.example.inpeace.music.newLayout;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLDatabase extends SQLiteOpenHelper {

    public static final String databaseName =  "UserMusic";

    public SQLDatabase(@Nullable Context context) {
        super(context, databaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String Querry = "create table Music ( songName text , songURL text , songImageURL text)";
        db.execSQL(Querry);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
