package com.example.inpeace.activites;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Database extends SQLiteOpenHelper {

    private static final String DatabaseName = "User";
    public Database(@Nullable Context context) {
        super(context, DatabaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("drop table OurActivity");
        final String Querry = "create table OurActivity( SNo  integer primary key autoincrement, Activity text , Code text )";
        db.execSQL(Querry);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table OurActivity");
        onCreate(db);
    }

    public boolean create_user_table(String tablename){
        SQLiteDatabase database = this.getReadableDatabase();
        final String QuerryTable = "SELECT name as 'Name' FROM sqlite_master WHERE type ='table' AND name = '"+tablename+"';";
        final String Querry = "create table "+ tablename + " ( SNo  integer primary key autoincrement ,Activity text , Time text, TaskCode text)" ;
        Cursor cursor = database.rawQuery(QuerryTable,null);
        if(cursor.getCount() == 0){
            database.execSQL(Querry);
//            database.close();
            return true;
        }else {
//            database.close();
            return false;
        }
    }

//    public boolean insert_value_in_user_activity(String Activity , String user_table){
//        SQLiteDatabase database = this.getReadableDatabase();
//        ContentValues contents =  new ContentValues();
//        contents.put("Activity" , Activity);
//        long result = database.insert(user_table,null,contents);
//        if(result == 1)
//            return false;
//        else
//            return true;
//    }

    public void create_our_activity_table(){
        SQLiteDatabase database = this.getReadableDatabase();
        database.execSQL("drop table OurActivity");
        final String Querry = "create table OurActivity( SNo  integer primary key autoincrement, Activity text , Code text )";
        database.execSQL(Querry);
//        database.close();
    }

    public boolean insert_Value_In_UserActivity(String Activity , String Code , String Time){
        SQLiteDatabase database = this.getReadableDatabase();
        ContentValues content = new ContentValues(3);
        content.put("Activity" , Activity);
        content.put("Time" , Time);
        content.put("TaskCode" , Code);
        long result = database.insert("ABC",null,content);
//        database.close();
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean insert_Value_In_OurActivity(String Activity , String Code){
        SQLiteDatabase database = this.getReadableDatabase();
        ContentValues content = new ContentValues(2);
        content.put("Activity" , Activity);
        content.put("Code" , Code);
        long result = database.insert("OurActivity",null,content);
//        database.close();
        if(result == 1)
            return false;
        else
            return true;
    }

    public Cursor get_data_from_ourActivity(){
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from OurActivity", null);
//       database.close();
        return cursor;
    }


    public int Total_Tasks_OurActivity(){
        String countQuery = "SELECT  * FROM OurActivity ";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
//        cursor.close();
//        db.close();
        return cursor.getCount();
    }

    public String current_time(){
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
    }

    public int user_activity_number(String tablename){
        String countQuery = "SELECT  * FROM " + tablename ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
//        cursor.close();
//        db.close();
        return cursor.getCount();
    }

    public int max_serial_number_of_user_table(String tablename){
        SQLiteDatabase database = this.getReadableDatabase();
        String Querry = "select MAX(SNo) as 'MAX' from " + tablename ;
        Cursor cursor = database.rawQuery(Querry, null);
        cursor.moveToFirst();
//        database.close();
        return cursor.getInt(cursor.getColumnIndex("MAX"));
    }

    public String latest_date_user_activity(String tablename , int maxSNo){
        SQLiteDatabase database = this.getReadableDatabase();
        String Query = "select Time from " + tablename + " where SNo = " + maxSNo ;
        Cursor cursor = database.rawQuery(Query , null);
        cursor.moveToFirst();
//        database.close();
        return cursor.getString(cursor.getColumnIndex("Time"));
    }

    public String time_difference(String storedTime){
        SQLiteDatabase database = this.getReadableDatabase();
        String Query = "SELECT time('" +current_time()+"','-"+ storedTime+ "') as 'Time' ";
        Cursor cursor = database.rawQuery(Query , null);
        cursor.moveToFirst();
//        database.close();
        return cursor.getString(cursor.getColumnIndex("Time"));
    }

    public void empty_user_table(String tablename ){
        SQLiteDatabase database = this.getReadableDatabase();
        String Query = "delete from "+ tablename;
        database.execSQL(Query );
//        database.close();
    }

    public int  row_at_given_time_user_activity(String cur_time ,String tablename){
        SQLiteDatabase database = this.getReadableDatabase();
        String Query = "SELECT * from "+ tablename + " where Time = '"+ cur_time +"'";
        Cursor cursor = database.rawQuery(Query , null);
//        database.close();
        return cursor.getCount();
    }

    public int count_rows_code_user_activity(String task_code , String tablename){
        SQLiteDatabase database = this.getReadableDatabase();
        String Query = "SELECT * from "+ tablename + " where TaskCode = '"+ task_code +"'";
        Cursor cursor = database.rawQuery(Query , null);
//        database.close();
        return cursor.getCount();
    }

    public String random_code_from_ourActivity(){
        SQLiteDatabase database = this.getReadableDatabase();
        String Query = "SELECT Code FROM OurActivity ORDER BY RANDOM() LIMIT 1";
        Cursor cursor = database.rawQuery(Query , null);
        cursor.moveToFirst();
//        database.close();
        return cursor.getString(cursor.getColumnIndex("Code"));
    }

    public String selecting_tasks_from_ourActivity(String task_code){
            SQLiteDatabase database = this.getReadableDatabase();
            String Query = "SELECT Activity FROM OurActivity where Code = '"+ task_code + "'";
            Cursor cursor = database.rawQuery(Query , null);
            cursor.moveToFirst();
//            database.close();
            return cursor.getString(cursor.getColumnIndex("Activity"));
    }



}
