package com.example.cc2tdi202;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDatabase extends SQLiteOpenHelper {

    public static String DB_NAME="enterprises.db";
    public static String TABLE_NAME="ENTREPRISE";
    public static String COL1="ID";
    public static String COL2="Raison_Sociale";
    public static String COL3="adresse";
    public static String COL4="Capitale";

    public MyDatabase(Context c) {
        super( c, DB_NAME, null, 1 );
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table " + TABLE_NAME + "("+COL1+" integer primary key autoincrement,"+COL2+" TEXT,"+COL3+" TEXT,"+COL4+" double)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql= "DROP TABLE " + TABLE_NAME;
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

}
