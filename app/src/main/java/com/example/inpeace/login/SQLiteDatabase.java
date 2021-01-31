package com.example.inpeace.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteDatabase extends SQLiteOpenHelper {

    private static final String databaseName = "UserDataLogin";

    public SQLiteDatabase(@Nullable Context context) {
        super(context, databaseName, null, 1);
    }

    @Override
    public void onCreate(android.database.sqlite.SQLiteDatabase db) {
        final String Querry = "create table loginStatus ( UserId text , LoginStatus boolean , LastLogInTime datetime default current_timestamp)";
        db.execSQL(Querry);
    }

    @Override
    public void onUpgrade(android.database.sqlite.SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists loginStatus");
        onCreate(db);
    }

    public String insertValueInLoginTable(String userId , String loginStatus){
        android.database.sqlite.SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("UserId",userId);
        values.put("LoginStatus",loginStatus);
        long result = database.insert("loginStatus",null,values);
        if(result == -1)
            return "InsertSuccessful";
        else
            return "InsertUnsuccessful";
    }

    public boolean checkUserWithCurrentId(String user_id){
        android.database.sqlite.SQLiteDatabase database = getReadableDatabase();
        String Querry = "SELECT COUNT(UserID) as 'UserId' FROM loginStatus where UserId = '" + user_id +"' AND LoginStatus = 'true'";
        Cursor cursor = database.rawQuery(Querry,null);
        cursor.moveToFirst();
        int num = cursor.getInt(cursor.getColumnIndex("UserId"));
        if(num >=1){
            return false;
        }else {
            return true;
        }
    }

    public boolean checkUserIsSignedIn(){
        android.database.sqlite.SQLiteDatabase database = this.getReadableDatabase();
        String Querry = "SELECT COUNT(UserID) as 'UserId' FROM loginStatus where LoginStatus = 'true'";
        Cursor cursor = database.rawQuery(Querry,null);
        cursor.moveToFirst();
        int in = cursor.getInt(cursor.getColumnIndex("UserId"));
        if(in == 1 ){
//            String Querry1 = "SELECT UserID as 'UserId' FROM loginStatus where LoginStatus = 'true'";
//            Cursor cursor1 = database.rawQuery(Querry1,null);
            cursor.moveToFirst();
            return true;
        }else {
            return false;
        }
    }

    public void logOut_user(String userId){
        android.database.sqlite.SQLiteDatabase database = getWritableDatabase();
        String Querry ="Update loginStatus set LoginStatus = 'false' where UserId = '"+ userId+"'";
        database.execSQL(Querry);
    }
}
