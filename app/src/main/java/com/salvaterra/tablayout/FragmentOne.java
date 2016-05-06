package com.salvaterra.tablayout;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class FragmentOne extends Fragment  {
    ListView listView ;
    private ArrayList<Productos> producto;
    private ProductoAdapter peliculaAdapter;
    private String recuperar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_page, container, false);
        // Get ListView object from xml
        final ListView listView = (ListView)view.findViewById(R.id.listView3);


        listView.setAdapter(peliculaAdapter);





        // Assign adapter to ListView
        listView.setAdapter(peliculaAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int posicion, long id) {
                altaFactura(getView());
                Toast notificacion = Toast.makeText(getContext(),String.valueOf(posicion), Toast.LENGTH_SHORT);
                notificacion.show();
            }
        });
        return view;

    }


    public String recuperarCONTACTO(int id) {
        BDcliente  admin= new BDcliente(getContext(),
                "administracion", null, 1);
        ContentValues registro = new ContentValues();
        int cont=1;
        String nombre = null;
        SQLiteDatabase bd = admin.getWritableDatabase();
        String[] valores_recuperar = {"_ID", "id_cliente"};
        Cursor c = bd.query("prueba", valores_recuperar, "_ID=" + "1",
                null, null, null, null, null);
        if(c != null) {
            c.moveToFirst();
        }
       recuperar= String.valueOf(c.getInt(1));

        bd.close();
        c.close();
        return "hola";
    }
    public void altaFactura(View v) {
        BDcliente admin = new BDcliente(getContext(),
                "administracion", null, 1);
        ContentValues registro = new ContentValues();
        int cont=1;
        String nombre = null;
        SQLiteDatabase bd = admin.getWritableDatabase();

        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss yyyy/MM/d");
        String dateString = sdf.format(date);

        registro.put("name", "producto");
        registro.put("id_cliente", recuperar);
        registro.put("precio", 10);
        bd.insert("producto", null, registro);

        bd.close();
    }


}
