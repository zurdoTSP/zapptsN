package com.salvaterra.tablayout;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zurdotsg on 9/02/16.
 */
public class listaFacturas extends AppCompatActivity {

    private TextView webView1;
    private int dato;
    ListView lv;
    private int guardar;
    private int id;
    private List<Integer> myCoords = new ArrayList<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.facturas);

        webView1 = (TextView) findViewById(R.id.textView3);

        Bundle bundle = getIntent().getExtras();
        dato=bundle.getInt("direccion");
        webView1.setText("has elegido:"+dato);
        lv = (ListView) findViewById(R.id.listView5);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, recuperarCONTACTOS());
        lv.setAdapter(adapter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                /// Obtiene el valor de la casilla elegida
                String itemSeleccionado = adapterView.getItemAtPosition(i).toString();
                // muestra un mensaje
                id=myCoords.get(i);
                Toast.makeText(getApplicationContext(), "Ha elegido " + myCoords.get(i), Toast.LENGTH_SHORT).show();
                ver(adapterView);
            }
        });
    }
    public void ver(View v) {
        Intent i = new Intent(this, listacompra.class);
        i.putExtra("direccion",id);
        startActivity(i);
    }
    public void altaFactura(View v) {
        BDcliente admin = new BDcliente(this,
                "administracion", null, 1);
        ContentValues registro = new ContentValues();
        int cont=1;
        String nombre = null;
        SQLiteDatabase bd = admin.getWritableDatabase();

        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss yyyy/MM/d");
        String dateString = sdf.format(date);

        registro.put("name", dateString);
        registro.put("id_cliente", dato);
        bd.insert("passs", null, registro);
        Toast.makeText(this, "Se cargaron los datos de la cuenta",
                Toast.LENGTH_SHORT).show();

        bd.close();
        Intent i=new Intent(this,listaFacturas.class);
        i.putExtra("direccion", dato);
        startActivity(i);
    }
    public List recuperarCONTACTOS() {
        BDcliente admin = new BDcliente(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        List lista_contactos = new ArrayList();

        String[] valores_recuperar = {"_ID","name","id_cliente"};
        Cursor c = bd.query("passs", valores_recuperar,
                null, null, null, null, null, null);
        if (c.moveToFirst()) {
            do {
                if(c.getInt(2)==dato) {
                    lista_contactos.add(c.getString(1));
                    myCoords.add(c.getInt(0));
                }
            } while (c.moveToNext());
        }
        bd.close();
        c.close();
        return lista_contactos;
    }


    ///////////////////////////////

    public void alta(View v,int datoq) {
        BDcliente admin = new BDcliente(this,
                "administracion", null, 1);
        ContentValues registro = new ContentValues();
        int cont=1;
        String nombre = null;
        SQLiteDatabase bd = admin.getWritableDatabase();

        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss yyyy/MM/d");
        String dateString = sdf.format(date);

        registro.put("_ID", 1);
        registro.put("id_cliente", datoq);
        bd.insert("prueba", null, registro);
        Toast.makeText(this, "Se cargaron los datos de la cuenta",
                Toast.LENGTH_SHORT).show();

        bd.close();

    }
    public void borrarCONTACTO(int id) {
        BDcliente  admin= new BDcliente(this,
                "administracion", null, 1);
        ContentValues registro = new ContentValues();
        int cont=1;
        String nombre = null;
        SQLiteDatabase bd = admin.getWritableDatabase();

        bd.delete("prueba", "_ID=" + "1", null);
        bd.close();
    }


    public void productosw() {
        String dato ="datos";
        Intent intent = new Intent(listaFacturas.this, MainActivity.class);
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

}
