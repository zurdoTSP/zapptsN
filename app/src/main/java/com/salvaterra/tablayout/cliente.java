package com.salvaterra.tablayout;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zurdotsg on 7/02/16.
 */
public class cliente extends AppCompatActivity {
    Button btnCambioActivity;
    ListView lv;
    private int d;
   private List<Integer> myCoords = new ArrayList<Integer>();
    private  ArrayList<String> listo = new ArrayList<String>();
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cliente);
        btnCambioActivity = (Button) findViewById(R.id.button5);
        lv = (ListView) findViewById(R.id.listView4);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, recuperarCONTACTOS());
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
                setSupportActionBar(toolbar);
                /// Obtiene el valor de la casilla elegida
                String itemSeleccionado = adapterView.getItemAtPosition(i).toString();
                // muestra un mensaje
                d=myCoords.get(i);
                Toast.makeText(getApplicationContext(), "Ha elegido " + myCoords.get(i), Toast.LENGTH_SHORT).show();
                ver(adapterView);
            }
        });
     }
    public void ver(View v) {
        Intent i=new Intent(this,listaFacturas.class);
        i.putExtra("direccion",d);
        startActivity(i);
    }
    public void lanzarcrear(View v) {
        String dato="dato";
        Intent intent = new Intent(cliente.this, crearCliente.class);

        startActivity(intent);
    }


    public List recuperarCONTACTOS() {
        BDcliente admin = new BDcliente(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        List lista_contactos = new ArrayList();

        String[] valores_recuperar = {"name","_ID"};
        Cursor c = bd.query("pass", valores_recuperar,

                null, null, null, null, null, null);
        if (c.moveToFirst()) {
            do {
                lista_contactos.add(c.getString(0));
                myCoords.add(c.getInt(1));
            } while (c.moveToNext());
        }
            bd.close();
            c.close();
            return lista_contactos;
        }
    public void productosw() {
        String dato ="datos";
        Intent intent = new Intent(cliente.this, MainActivity.class);
        intent.putExtra("DATO", dato);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add) {
            productosw();
            return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public void cerrar(View view) {
        finish();
    }
}