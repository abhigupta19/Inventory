package com.sar.user.inventory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public  static final String DATABASE_NAME="Inventory.db";
    public  static final String TABLE_NAME="data";
    public  static final String col1="INNVOICE";
    public  static final String col2="NAME";
    public  static final String col3="COMPANY";
    public  static final String col4="QUENTITY";



    public DatabaseHelper(@Nullable Context context) {
        super(context,DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (INNVOICE TEXT PRIMARY KEY ,NAME TEXT,COMPANY TEXT,QUENTITY TEXT)");


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }
    public  boolean insert(String innvoice,String name,String company,String quantity)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(col1,innvoice);
        contentValues.put(col2,name);
        contentValues.put(col3,company);
        contentValues.put(col4,quantity);
        Log.i("yuuuu",name);

        long a=sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        Log.i("yuuuuuu",String.valueOf(a));
        if(a==-1)
            return false;
        else
            return true;

    }
    public Cursor showdata(){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT *FROM "+TABLE_NAME,null);
        return cursor;
    }
    public Cursor delete(String innvoice)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM data WHERE INNVOICE="+innvoice,null);
        return cursor;
    }
}
