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

    public static long AddEntreprise(SQLiteDatabase db, Entreprise e){
        ContentValues ct = new ContentValues();
        ct.put(COL2,e.getRaisonSociale());
        ct.put(COL3,e.getAdresse());
        ct.put(COL4,e.getCapitale());
        return db.insert(TABLE_NAME,null,ct);
    }

    public static long UpdateEntreprise(SQLiteDatabase db, Entreprise e){
        ContentValues ct = new ContentValues();
        ct.put(COL2,e.getRaisonSociale());
        ct.put(COL3,e.getAdresse());
        ct.put(COL4,e.getCapitale());
        return db.update(TABLE_NAME,ct,"id="+e.getId(),null);
    }

    public static long DeleteEntreprise(SQLiteDatabase db, int id){
        return db.delete(TABLE_NAME,"id="+id,null);
    }

    public static ArrayList<Entreprise> getAllEntreprise(SQLiteDatabase db){
        ArrayList<Entreprise> entr = new ArrayList<>();

        Cursor cur = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);

        while(cur.moveToNext()){
            Entreprise e = new Entreprise();
            e.setId(cur.getInt(0));
            e.setRaisonSociale(cur.getString(1));
            e.setAdresse(cur.getString(2));
            e.setCapitale(cur.getDouble(3));
            entr.add(e);
        }

        return entr;
    }

    public static Entreprise getOneEntreprise(SQLiteDatabase db, int id){
        Entreprise e = null;

        Cursor cur = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE id = " + id,null);

        if(cur.moveToNext()){
            e = new Entreprise();
            e.setId(cur.getInt(0));
            e.setRaisonSociale(cur.getString(1));
            e.setAdresse(cur.getString(2));
            e.setCapitale(cur.getDouble(3));
        }

        return e;
    }

}
