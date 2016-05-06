package com.salvaterra.tablayout;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zurdotsg on 5/02/16.
 */
public class BDcliente extends SQLiteOpenHelper {
    public BDcliente(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table pass(_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,name text)");
        db.execSQL("create table passs(_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,name text,id_cliente INTEGER)");
        db.execSQL("create table lista(_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,name text,precio INTEGER)");
        db.execSQL("create table producto(_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,name text,id_cliente INTEGER,precio INTEGER)");
        db.execSQL("create table  stock(_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,name text,cantidad INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
