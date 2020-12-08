package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    public DBHandler(Context context){
        super(context,"Userdata.db",null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create Table Userdetails(name Text primary key, contact TEXT, dob TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("drop Table if exists Userdetails");

    }

    public Boolean insertData(String name,String contact,String dob){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("Name",name);
        contentValues.put("Contact",contact);
        contentValues.put("dob",dob);

        long results = sqLiteDatabase.insert("Userdetails",null,contentValues);

        if (results == -1){
            return false;
        }else{
            return true;
        }
    }
    public Boolean updateData(String name, String contact,String dob){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("contact",contact);
        contentValues.put("dob",dob);

        Cursor cursor = sqLiteDatabase.rawQuery("Select * from Userdetails where name = ?",new String[]{name});

        if (cursor.getCount()>0){
            long resuls = sqLiteDatabase.update("Userdetails",contentValues,"name = ?",new String[]{name});

            if(resuls == -1){
                return false;
            }else {
                return true;
            }
            }else {
                return true;
            }
        }


        public Boolean deleteData(String name){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("Select * from Userdetails where name= ?",new String[]{name});

        if (cursor.getCount()>0){
            long results = sqLiteDatabase.delete("Userdetails","name= ?",new String[]{name});

            if (results == -1){
                return false;
            }else {
                return true;}

            }else {
            return false;
        }
    }
    public Cursor getData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("Select * from Userdetails",null);

        return cursor;
    }
}

