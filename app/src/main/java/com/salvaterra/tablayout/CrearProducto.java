package com.salvaterra.tablayout;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zurdotsg on 29/04/16.
 */
public class CrearProducto extends Activity {
        private EditText a,b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.nuevo_producto);
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        a= (EditText)findViewById(R.id.editText6);
        b= (EditText)findViewById(R.id.editText7);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_info_base, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.home) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void crearnuevosd(View v) {
        // lista(_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,name text,precio INTEGER)");
        BDcliente admin = new BDcliente(this,
                "administracion", null, 1);
        ContentValues registro = new ContentValues();
        ContentValues registra = new ContentValues();



        SQLiteDatabase bd = admin.getWritableDatabase();



        registro.put("name", String.valueOf(a.getText()));
        registro.put("precio", Float.parseFloat(String.valueOf(b.getText())));
        bd.insert("lista", null, registro);

        registra.put("name", String.valueOf(a.getText()));
        registra.put("cantidad", 0);
        bd.insert("stock", null, registra);
        Intent q = new Intent(this, productos.class);
        startActivity(q);




        bd.close();



    }

}


