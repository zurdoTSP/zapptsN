package com.salvaterra.tablayout;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zurdotsg on 5/02/16.
 */
public class BDfactura extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    public BDfactura(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table passs(_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,name text,id_cliente INTEGER)");
    }

    @Override

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + "passs");
        db.execSQL("drop table if exists " + "pass");
        onCreate(db);
    }
}
