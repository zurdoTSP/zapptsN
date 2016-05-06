package com.salvaterra.tablayout;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zurdotsg on 14/02/16.
 */
public class productos extends AppCompatActivity

{

    ListView listView ;
    ListaAdapter adapter;
    private ProductoAdapter peliculaAdapter;
    private String recuperar;
    private int id;
    private ArrayList<Datos> Datos;
    private List<String> solud = new ArrayList<String>();
    private List<Tarea> TAREAS=new ArrayList<Tarea>();
    private String x;
    private int rumanoZ;
    private int pos;
    private List<Integer> myCoords = new ArrayList<Integer>();
    private List<String> pedido = new ArrayList<String>();
    private EditText buscar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.productos);
        // Get ListView object from xml
        Bundle bundle = getIntent().getExtras();
        Datos = new ArrayList<Datos>();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        id=bundle.getInt("direccion");

        buscar=(EditText) findViewById(R.id.editText5);

        // TAREAS.add(new Tarea("Trotar 30 minutos","08:00",R.mipmap.ic_health));
        // TAREAS.add(new Tarea("Estudiar análisis técnico","10:00",R.mipmap.ic_money));
        // TAREAS.add(new Tarea("Comer 4 rebanadas de manzana", "10:30", R.mipmap.ic_health));
        // TAREAS.add(new Tarea("Asistir al taller de programación gráfica", "15:45", R.mipmap.ic_carreer));
        // TAREAS.add(new Tarea("Consignarle a Marta", "18:00", R.mipmap.ic_money));

        listView = (ListView) findViewById(R.id.listView9);
        Datos = new ArrayList<Datos>();
        recuperarCONTACTOS();
        adapter = new ListaAdapter(this, Datos);

        //Relacionando la lista con el adaptador
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            // public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


            // Assign adapter to ListView


            //   pos = i;
            //      q=listView.getItemAtPosition(i).toString();
            //    Toast.makeText(getApplicationContext(), "Ha elegido " +String.valueOf(i) , Toast.LENGTH_SHORT).show();
            //(listView.getItemAtPosition(i).toString());
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                TextView textView = (TextView) view.findViewById(R.id.tituloLista);
                String text = textView.getText().toString();
                entra(text);
                veranoAzulado(view);

            }
        });
        buscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
    public void entra(String alfombra)
    {
        for (int i = 0; i < solud.size(); i++) {
            if (solud.get(i).equals(alfombra)) {
                Toast.makeText(getApplicationContext(), "Entro" + String.valueOf(i), Toast.LENGTH_SHORT).show();
                rumanoZ = i;
            }

        }
    }

    public void veranoAzulado(View v) {
        Intent i = new Intent(this, modProducotos.class);
        Toast.makeText(getApplicationContext(), "Ha elegido " +String.valueOf(myCoords.get(rumanoZ)) , Toast.LENGTH_SHORT).show();
        i.putExtra("direccion", myCoords.get(rumanoZ));
        startActivity(i);
    }

    public void altaFactura() {
        BDcliente admin = new BDcliente(this,
                "administracion", null, 1);
        ContentValues registro = new ContentValues();
        int cont=1;
        String nombre = null;
        SQLiteDatabase bd = admin.getWritableDatabase();

        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss yyyy/MM/d");
        String dateString = sdf.format(date);

        registro.put("name",pedido.get(pos));
        registro.put("id_cliente", id);
        registro.put("precio", myCoords.get(pos));
        bd.insert("producto", null, registro);

        bd.close();
    }
    public void crearpro(View v) {

        Intent q = new Intent(this, CrearProducto.class);
        startActivity(q);

    }
    public void crearBase()
    {
        // lista(_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,name text,precio INTEGER)");
        BDcliente admin = new BDcliente(this,
                "administracion", null, 1);
        ContentValues registro = new ContentValues();
        int cont=1;
        String nombre = null;
        SQLiteDatabase bd = admin.getWritableDatabase();


        registro.put("name", "pescado frito");

        registro.put("cantidad", 10);

        bd.insert("stock", null, registro);
        registro.put("name", "japuta");

        registro.put("cantidad", 15);

        bd.insert("stock", null, registro);

        bd.close();
        SharedPreferences preferencias=getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferencias.edit();
        editor.putString("mail2", "funciona");
        editor.commit();
    }
    public void recuperarCONTACTOS() {
        BDcliente admin = new BDcliente(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        List lista_contactos = new ArrayList();

        String[] valores_recuperar = {"_ID","name","precio"};
        Cursor c = bd.query("lista", valores_recuperar,
                null, null, null, null, null, null);
        Datos d;
        if (c.moveToFirst()) {
            do {
                //TAREAS.add(new Tarea(c.getString(1),String.valueOf(c.getFloat(2)),R.mipmap.ic_health));

                pedido.add(c.getString(0));
                d = new Datos(c.getString(1) + " " + String.valueOf(c.getFloat(2)), R.mipmap.ic_health);
                Datos.add(d);
                //Toast.makeText(getApplicationContext(),c.getString(1) + " " + String.valueOf(c.getFloat(0))  , Toast.LENGTH_SHORT).show();
                myCoords.add(c.getInt(0));
                //pedido.add(c.getString(0));
                solud.add(c.getString(1) + " " + String.valueOf(c.getFloat(2)));
                //  lista_contactos.add(c.getString(1));
                //  myCoords.add(c.getInt(0));

            } while (c.moveToNext());
        }
        bd.close();
        c.close();

    }
    public void productosw() {
        String dato ="datos";
        Intent intent = new Intent(
                productos.this, MainActivity.class);
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

