package com.salvaterra.tablayout;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zurdotsg on 12/02/16.
 */
public class comprarealizada extends AppCompatActivity {

    ListView listView ;
    ArrayAdapter adaptador;
    private ProductoAdapter peliculaAdapter;
    private String recuperar;
    private int id;
    private String cad;
    private List<Tarea> TAREAS=new ArrayList<Tarea>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.compra);
        cad="ABORDO\n--------------------------------------------";
        escribirFicheroMemoriaInterna();
        // Get ListView object from xml
    //    TAREAS.add(new Tarea("Trotar 30 minutos","08:00",R.mipmap.ic_health));

        Bundle bundle = getIntent().getExtras();
        id=bundle.getInt("direccion");
        listView = (ListView) findViewById(R.id.listView7);
        recuperarCONTACTOS();
        adaptador = new TareaArrayAdapter(this, TAREAS);
        grabar();
         Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Relacionando la lista con el adaptador
        listView.setAdapter(adaptador);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                // Assign adapter to ListView


                // altaFactura();
            }
        });
    }



    public void recuperarCONTACTOS() {
        BDcliente admin = new BDcliente(this,
                "administracion", null, 1);
        float suma=0;
        String total="TOTAL";
        SQLiteDatabase bd = admin.getWritableDatabase();
        List lista_contactos = new ArrayList();

        String[] valores_recuperar = {"_ID","name","id_cliente","precio"};
        Cursor c = bd.query("producto", valores_recuperar,
                null, null, null, null, null, null);
        if (c.moveToFirst()) {
            do {
                if(c.getInt(2)==id) {
                    TAREAS.add(new Tarea(c.getString(1),String.valueOf(c.getFloat(3)),R.mipmap.ic_health));
                    cad=cad+"\n"+c.getString(1)+"       "+String.valueOf(c.getFloat(3));
                    suma=suma+c.getFloat(3);

                    //  lista_contactos.add(c.getString(1));
                  //  myCoords.add(c.getInt(0));
                }
            } while (c.moveToNext());
        }
        TAREAS.add(new Tarea(total, String.valueOf(suma), R.mipmap.ic_health));
        cad=cad+"\n--------------------------------------------\nTOTAL: "+suma+"€";
        bd.close();
        c.close();

    }
    public void productosw() {
        String dato ="datos";
        Intent intent = new Intent(comprarealizada.this, MainActivity.class);
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
    public void grabar() {
        String nomarchivo ="zurdo.txt";
        String contenido = cad;
        try {
            File tarjeta = Environment.getExternalStorageDirectory();
            Toast.makeText(this,tarjeta.getAbsolutePath(),Toast.LENGTH_LONG).show();
            File file = new File(tarjeta.getAbsolutePath(), nomarchivo);
            OutputStreamWriter osw = new OutputStreamWriter(
                    new FileOutputStream(file));
            osw.write(contenido);
            osw.flush();
            osw.close();
            Toast.makeText(this, "Los datos fueron grabados correctamente",
                    Toast.LENGTH_SHORT).show();

        } catch (IOException ioe) {
            Toast.makeText(this, "No se pudo grabar",
                    Toast.LENGTH_SHORT).show();
        }
    }
    private void escribirFicheroMemoriaInterna()
    {
        try
        {
            OutputStreamWriter fout=
                    new OutputStreamWriter(
                            openFileOutput("factura.txt", Context.MODE_PRIVATE));

            fout.write("Texto de prueba.");
            fout.close();
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al escribir fichero a memoria interna");
        }
    }

}
