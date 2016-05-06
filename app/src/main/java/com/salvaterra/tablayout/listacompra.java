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
 * Created by zurdotsg on 12/02/16.
 */
public class listacompra extends AppCompatActivity {
    private  ArrayList<Datos> Datos;
    ListView listView ;
    ListaAdapter adapter;
    private ProductoAdapter peliculaAdapter;
    private String recuperar;
    private int id;
    private List<Tarea> TAREAS=new ArrayList<Tarea>();
    private String x;
    private String pedire;
    private int pos;
    private List<Float> myCoords = new ArrayList<Float>();
    private List<Integer> idn = new ArrayList<Integer>();
    private List<String> pedido = new ArrayList<String>();
    private List<String> solud = new ArrayList<String>();
    private EditText buscar;
    private String q;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.listacompra);
        // Get ListView object from xml
        Bundle bundle = getIntent().getExtras();
        id=bundle.getInt("direccion");
        SharedPreferences prefe=getSharedPreferences("datos", Context.MODE_PRIVATE);
        x=prefe.getString("mail","");
        Datos = new ArrayList<Datos>();
        if(x.equals(""))
        {
            crearBase();
        }
        recuperarCONTACTOS();
       // TAREAS.add(new Tarea("Trotar 30 minutos","08:00",R.mipmap.ic_health));
        // TAREAS.add(new Tarea("Estudiar análisis técnico","10:00",R.mipmap.ic_money));
        // TAREAS.add(new Tarea("Comer 4 rebanadas de manzana", "10:30", R.mipmap.ic_health));
        // TAREAS.add(new Tarea("Asistir al taller de programación gráfica", "15:45", R.mipmap.ic_carreer));
        // TAREAS.add(new Tarea("Consignarle a Marta", "18:00", R.mipmap.ic_money));

        listView = (ListView) findViewById(R.id.listView6);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        buscar= (EditText) findViewById(R.id.editText3);
        //Relacionando la lista con el adaptador
        adapter = new ListaAdapter(this, Datos);
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
                altaFactura(text);
                Toast.makeText(getApplicationContext(), "Ha elegido " +text + String.valueOf(solud.size()) , Toast.LENGTH_SHORT).show();
                 // perreoGuarro();
            }});
           //     perreoGuarro();


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

    public void veranoAzul(View v) {
        Intent i = new Intent(this, comprarealizada.class);
        i.putExtra("direccion",id);
        startActivity(i);
    }

    public void altaFactura(String alfombra) {
        int encontrar=0;
        boolean x=false;
        BDcliente admin = new BDcliente(this,
                "administracion", null, 1);
        ContentValues registro = new ContentValues();
        int cont=1;
        String nombre = null;
        SQLiteDatabase bd = admin.getWritableDatabase();
        for(int i=0;i<solud.size();i++)
        {
            if(solud.get(i).equals(alfombra))
            {
                pos=i;
               // Toast.makeText(getApplicationContext(), "ENTREEEEE "  + String.valueOf(i) , Toast.LENGTH_SHORT).show();
                encontrar=i;
                x=perreoGuarro();
            }

        }
        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss yyyy/MM/d");
        String dateString = sdf.format(date);
        if(x==true) {
            registro.put("name", pedido.get(encontrar));
            registro.put("id_cliente", id);
            registro.put("precio", myCoords.get(encontrar));
            bd.insert("producto", null, registro);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "No queda stock de este producto" , Toast.LENGTH_SHORT).show();
        }

        bd.close();
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
    registro.put("name", "Salmon noruego ahumado");
    registro.put("precio", 3.25);
    bd.insert("lista", null, registro);
    registro.put("name", "Lomitos Salmón Salar");
    registro.put("precio", 5.95);
    bd.insert("lista", null, registro);
    registro.put("name", "Emperador");
    registro.put("precio", 15.95);
    bd.insert("lista", null, registro);
    registro.put("name", "Emperador cortado");
    registro.put("precio", 13.95);
    bd.insert("lista", null, registro);
    registro.put("name", "Lomos de atún rojo");
    registro.put("precio", 14.21);
    bd.insert("lista", null, registro);
    registro.put("name", "Filete de palometa");
    registro.put("precio", 9.95);
    bd.insert("lista", null, registro);
    registro.put("name", "Lubina eviscerada");
    registro.put("precio", 3.95);
    bd.insert("lista", null, registro);
    registro.put("name", "Blquerón limpio");
    registro.put("precio", 4.25);
    bd.insert("lista", null, registro);
    registro.put("name", "Rodajas de merluza");
    registro.put("precio", 11.20);
    bd.insert("lista", null, registro);
    registro.put("name", "Solomillos de merluza");
    registro.put("precio", 10.13);
    bd.insert("lista", null, registro);
    registro.put("name", "Medallones de merluza");
    registro.put("precio", 10.5);
    bd.insert("lista", null, registro);
    registro.put("name", "Filetes de merluza");
    registro.put("precio", 9.68);
    bd.insert("lista", null, registro);
    registro.put("name", "Pescadilla sin cabeza");
    registro.put("precio", 5.45);
    bd.insert("lista", null, registro);
    registro.put("name", "Lenguado 100% Limpio");
    registro.put("precio", 4.35);
    bd.insert("lista", null, registro);
    registro.put("name", "Filete de Lenguado");
    registro.put("precio", 3.59);
    bd.insert("lista", null, registro);
    registro.put("name", "Filetes de Tilapia");
    registro.put("precio", 8.90);
    bd.insert("lista", null, registro);
    registro.put("name", "Filetes de Pangasius");
    registro.put("precio", 5.70);
    bd.insert("lista", null, registro);
    registro.put("name", "Cola de rape nacional");
    registro.put("precio", 19.50);
    bd.insert("lista", null, registro);
    registro.put("name", "Cola de rape mediano limpio");
    registro.put("precio", 8.75);
    bd.insert("lista", null, registro);
    registro.put("name", "Lomos de bacalao gris");
    registro.put("precio", 8.74);
    bd.insert("lista", null, registro);
    registro.put("name", "Filetes de Bacaladilla");
    registro.put("precio", 3.75);
    bd.insert("lista", null, registro);
    registro.put("name", "Solomillos grandes de Bacalao blanco");
    registro.put("precio", 13.39);
    bd.insert("lista", null, registro);
    registro.put("name", "Trozos de Bacalao");
    registro.put("precio", 3.90);
    bd.insert("lista", null, registro);
    registro.put("name", "Preparado para paella");
    registro.put("precio", 4.90);
    bd.insert("lista", null, registro);
    registro.put("name", "Preparado para sopa marinera");
    registro.put("precio", 3.75);
    bd.insert("lista", null, registro);
    registro.put("name", "Frituras de pescado");
    registro.put("precio", 10.95);
    bd.insert("lista", null, registro);
    registro.put("name", "Sepia limpia");
    registro.put("precio", 9.95);
    bd.insert("lista", null, registro);
    registro.put("name", "Sepia limpia troceada");
    registro.put("precio", 6.95);
    bd.insert("lista", null, registro);
    registro.put("name", "Pata de pulpo cocidas");
    registro.put("precio", 8.95);
    bd.insert("lista", null, registro);
    registro.put("name", "Sepia limpia natural");
    registro.put("precio", 11.95);
    bd.insert("lista", null, registro);
    registro.put("name", "Puntilla de calamar limpia sin pluma");
    registro.put("precio", 8.95);
    bd.insert("lista", null, registro);
    registro.put("name", "Anillas limpias");
    registro.put("precio", 5.95);
    bd.insert("lista", null, registro);
    registro.put("name", "Chipirón limpio y troceado");
    registro.put("precio", 8.50);
    bd.insert("lista", null, registro);
    registro.put("name", "Calamar fino entero");
    registro.put("precio", 19.75);
    bd.insert("lista", null, registro);
    registro.put("name", "Calamar fino");
    registro.put("precio", 7.95);
    bd.insert("lista", null, registro);
    registro.put("name", "Bogavante crudo");
    registro.put("precio", 11.75);
    bd.insert("lista", null, registro);
    registro.put("name", "Gambones grandes");
    registro.put("precio", 13.95);
    bd.insert("lista", null, registro);
    registro.put("name", "Cigalas grandes");
    registro.put("precio", 13.50);
    bd.insert("lista", null, registro);
    registro.put("name", "Rojas brillantes grandes");
    registro.put("precio", 59.95);
    bd.insert("lista", null, registro);
    registro.put("name", "Bocas de buey de mar cocidas y precortadas");
    registro.put("precio", 17.50);
    bd.insert("lista", null, registro);
    registro.put("name", "Gambas rayadas medianas");
    registro.put("precio", 23.50);
    bd.insert("lista", null, registro);
    registro.put("name", "Langostinos grandes cocidas");
    registro.put("precio", 13.95);
    bd.insert("lista", null, registro);
    registro.put("name", "Gambas saladas");
    registro.put("precio", 7.95);
    bd.insert("lista", null, registro);
    registro.put("name", "Gambas rojas peladas gigantes");
    registro.put("precio", 17.50);
    bd.insert("lista", null, registro);
    registro.put("name", "Gambas rojas peladas grandes");
    registro.put("precio", 4.95);
    bd.insert("lista", null, registro);
    registro.put("name", "Mejillones cocidos sin concha grande");
    registro.put("precio", 2.70);
    bd.insert("lista", null, registro);
    registro.put("name", "Mejillones en su propio jugo");
    registro.put("precio", 4.90);
    bd.insert("lista", null, registro);
    registro.put("name", "Caracoles precocidos");
    registro.put("precio", 9.95);
    bd.insert("lista", null, registro);
    registro.put("name", "Palitos de mar abordo");
    registro.put("precio", 7.90);
    bd.insert("lista", null, registro);
    registro.put("name", "Sucedaneo de angulas abordo");
    registro.put("precio", 3.65);
    bd.insert("lista", null, registro);
    registro.put("name", "Muslitos de mar abordo");
    registro.put("precio", 9.90);
    bd.insert("lista", null, registro);
    registro.put("name", "Salteado campestre");
    registro.put("precio", 1.95);
    bd.insert("lista", null, registro);
    registro.put("name", "Salteado de verduras braseadas");
    registro.put("precio", 2.00);
    bd.insert("lista", null, registro);
    registro.put("name", "Salteado de patatas");
    registro.put("precio", 1.95);
    bd.insert("lista", null, registro);
    registro.put("name", "Salteado mixto de setas");
    registro.put("precio", 3.25);
    bd.insert("lista", null, registro);
    registro.put("name", "Arroz 6 delicias");
    registro.put("precio", 5.90);
    bd.insert("lista", null, registro);
    registro.put("name", "Salteado de habilas ajetes y bacon");
    registro.put("precio", 3.50);
    bd.insert("lista", null, registro);
    registro.put("name", "Revuelto de esparragos ajos y gambas");
    registro.put("precio", 3.50);
    bd.insert("lista", null, registro);
    registro.put("name", "Salpicon de marisco");
    registro.put("precio", 9.95);
    bd.insert("lista", null, registro);
    registro.put("name", "Especial cóctel y ensalada");
    registro.put("precio", 3.75);
    bd.insert("lista", null, registro);
    registro.put("name", "Ensalada de cangrejo");
    registro.put("precio", 3.00);
    bd.insert("lista", null, registro);
    registro.put("name", "Ensalada con pollo");
    registro.put("precio", 3.00);
    bd.insert("lista", null, registro);
    registro.put("name", "Alcachofa troceada");
    registro.put("precio", 2.50);
    bd.insert("lista", null, registro);
    registro.put("name", "Alubia blanca precocida");
    registro.put("precio", 1.25);
    bd.insert("lista", null, registro);
    registro.put("name", "Garbanzos");
    registro.put("precio", 1.30);
    bd.insert("lista", null, registro);
    registro.put("name", "Cebolla limpia y troceada");
    registro.put("precio", 1.95);
    bd.insert("lista", null, registro);
    registro.put("name", "Ajo picado");
    registro.put("precio", 2.50);
    bd.insert("lista", null, registro);
    registro.put("name", "Guisante Baby");
    registro.put("precio", 3.30);
    bd.insert("lista", null, registro);
    registro.put("name", "Hojas de espinacas");
    registro.put("precio", 2.65);
    bd.insert("lista", null, registro);
    registro.put("name", "Zanahorias baby");
    registro.put("precio", 1.10);
    bd.insert("lista", null, registro);
    registro.put("name", "Puntas de espárrago verde extra");
    registro.put("precio", 3.25);
    bd.insert("lista", null, registro);
    registro.put("name", "Floretes de brócoli");
    registro.put("precio", 2.35);
    bd.insert("lista", null, registro);
    registro.put("name", "Habitas superbaby nacionales");
    registro.put("precio", 10.95);
    bd.insert("lista", null, registro);
    registro.put("name", "Cardo troceado");
    registro.put("precio", 1.95);
    bd.insert("lista", null, registro);
    registro.put("name", "Ensaladilla rusa");
    registro.put("precio", 2.30);
    bd.insert("lista", null, registro);
    registro.put("name", "Judía extrafina entera");
    registro.put("precio", 2.95);
    bd.insert("lista", null, registro);
    registro.put("name", "Menestra de la huerta navarra");
    registro.put("precio", 3.00);
    bd.insert("lista", null, registro);
    registro.put("name", "Judías verdes planas");
    registro.put("precio", 2.60);
    bd.insert("lista", null, registro);
    registro.put("name", "Menestra brocozan");
    registro.put("precio", 2.45);
    bd.insert("lista", null, registro);
    registro.put("name", "Pisto de verduras");
    registro.put("precio", 3.00);
    bd.insert("lista", null, registro);
    registro.put("name", "Garrofón precocido");
    registro.put("precio", 1.35);
    bd.insert("lista", null, registro);
    registro.put("name", "Verduras para paella");
    registro.put("precio", 3.10);
    bd.insert("lista", null, registro);
    registro.put("name", "Ajos tiernos troceados");
    registro.put("precio", 2.00);
    bd.insert("lista", null, registro);
    registro.put("name", "Hojaldre de salmón y espinacas");
    registro.put("precio", 12.60);
    bd.insert("lista", null, registro);
    registro.put("name", "Cangrejo relleno");
    registro.put("precio", 2.75);
    bd.insert("lista", null, registro);
    registro.put("name", "Filete de lenguado relleno de mousse de gambas");
    registro.put("precio", 16.00);
    bd.insert("lista", null, registro);
    registro.put("name", "Bacalao a la vizcaina");
    registro.put("precio", 4.95);
    bd.insert("lista", null, registro);
    registro.put("name", "Lubina a las finas hierbas");
    registro.put("precio", 5.75);
    bd.insert("lista", null, registro);
    registro.put("name", "Salmón a las finas hierbas");
    registro.put("precio", 5.95);
    bd.insert("lista", null, registro);
    registro.put("name", "Salmón a la barbacoa");
    registro.put("precio", 5.95);
    bd.insert("lista", null, registro);
    registro.put("name", "Costillar entero barbacoa");
    registro.put("precio", 9.75);
    bd.insert("lista", null, registro);
    registro.put("name", "Carrillada de cerdo con salsa de champiñones");
    registro.put("precio", 6.50);
    bd.insert("lista", null, registro);
    registro.put("name", "Carrillada de ternera con salsa boletus");
    registro.put("precio", 7.90);
    bd.insert("lista", null, registro);
    registro.put("name", "Jarrete de cordero con salsa");
    registro.put("precio", 7.90);
    bd.insert("lista", null, registro);
    registro.put("name", "Canelones de espinacas");
    registro.put("precio", 2.95);
    bd.insert("lista", null, registro);
    registro.put("name", "Mussaka");
    registro.put("precio", 2.95);
    bd.insert("lista", null, registro);
    registro.put("name", "Canalones de carne");
    registro.put("precio", 4.00);
    bd.insert("lista", null, registro);
    registro.put("name", "Lasaña vegetal");
    registro.put("precio", 2.95);
    bd.insert("lista", null, registro);
    registro.put("name", "Lasaña carne con bechamel");
    registro.put("precio", 4.00);
    bd.insert("lista", null, registro);
    registro.put("name", "Lasaña de atún");
    registro.put("precio", 2.95);
    bd.insert("lista", null, registro);
    registro.put("name", "Pizza base fina y crujiente");
    registro.put("precio", 3.10);
    bd.insert("lista", null, registro);
    registro.put("name", "Tostas");
    registro.put("precio", 3.50);
    bd.insert("lista", null, registro);
    registro.put("name", "Mini pizzas");
    registro.put("precio", 3.00);
    bd.insert("lista", null, registro);
    registro.put("name", "Hamburgesa con verduras");
    registro.put("precio", 2.00);
    bd.insert("lista", null, registro);
    registro.put("name", "Hamburgesa de ternera mixta");
    registro.put("precio", 2.00);
    bd.insert("lista", null, registro);
    registro.put("name", "Chuleta de palo de cordero");
    registro.put("precio", 6.75);
    bd.insert("lista", null, registro);
    registro.put("name", "Hamburguesa de ternera extra");
    registro.put("precio", 2.00);
    bd.insert("lista", null, registro);
    registro.put("name", "Cordero lechal");
    registro.put("precio", 19.95);
    bd.insert("lista", null, registro);
    registro.put("name", "Carrillada de cerdo");
    registro.put("precio", 4.50);
    bd.insert("lista", null, registro);
    registro.put("name", "Albóndigas de carne caseras");
    registro.put("precio", 2.95);
    bd.insert("lista", null, registro);
    registro.put("name", "Rabo de cerdo cocido");
    registro.put("precio", 4.50);
    bd.insert("lista", null, registro);
    registro.put("name", "Careta de cerdo troceada");
    registro.put("precio", 3.95);
    bd.insert("lista", null, registro);
    registro.put("name", "Solomillo de cerdo");
    registro.put("precio", 8.50);
    bd.insert("lista", null, registro);
    registro.put("name", "Longaniza de magro");
    registro.put("precio", 2.00);
    bd.insert("lista", null, registro);
    registro.put("name", "Chorizos");
    registro.put("precio", 2.00);
    bd.insert("lista", null, registro);
    registro.put("name", "Morcillas de cebolla");
    registro.put("precio", 2.00);
    bd.insert("lista", null, registro);
    registro.put("name", "Pechuga de pollo");
    registro.put("precio", 8.50);
    bd.insert("lista", null, registro);
    registro.put("name", "Alitas de pollo");
    registro.put("precio", 3.95);
    bd.insert("lista", null, registro);
    registro.put("name", "Jamoncitos de pollo");
    registro.put("precio", 3.40);
    bd.insert("lista", null, registro);
    registro.put("name", "Alitas de pollo adobadas");
    registro.put("precio", 5.90);
    bd.insert("lista", null, registro);
    registro.put("name", "Mini redondo de jámon y queso");
    registro.put("precio", 2.65);
    bd.insert("lista", null, registro);
    registro.put("name", "Pelotas para cocido");
    registro.put("precio", 3.25);
    bd.insert("lista", null, registro);
    registro.put("name", "Redondo de pollo a las finas huerbas");
    registro.put("precio", 11.00);
    bd.insert("lista", null, registro);
    registro.put("name", "Arreglo puchero con ternera");
    registro.put("precio", 5.75);
    bd.insert("lista", null, registro);
    registro.put("name", "Redondo de pavo");
    registro.put("precio", 5.95);
    bd.insert("lista", null, registro);
    registro.put("name", "Lomito de cerdo a la jardinera");
    registro.put("precio", 11.50);
    bd.insert("lista", null, registro);
    registro.put("name", "Figuritas de merluza rebozadas");
    registro.put("precio", 2.50);
    bd.insert("lista", null, registro);
    registro.put("name", "Varitas de merluza");
    registro.put("precio", 2.75);
    bd.insert("lista", null, registro);
    registro.put("name", "Filete de merluza rebozada crunchi");
    registro.put("precio", 5.50);
    bd.insert("lista", null, registro);
    registro.put("name", "Calamar chipirón enharinado");
    registro.put("precio", 3.95);
    bd.insert("lista", null, registro);
    registro.put("name", "Tortillas de camarón");
    registro.put("precio", 3.25);
    bd.insert("lista", null, registro);
    registro.put("name", "Rabas de calamar rebozadas");
    registro.put("precio", 7.50);
    bd.insert("lista", null, registro);
    registro.put("name", "San jacobo");
    registro.put("precio", 8.65);
    bd.insert("lista", null, registro);
    registro.put("name", "Libritos de pollo con salsa boloñesa y queso");
    registro.put("precio", 11.50);
    bd.insert("lista", null, registro);
    registro.put("name", "Solomillos de pollo rebozado");
    registro.put("precio", 3.99);
    bd.insert("lista", null, registro);
    registro.put("name", "Nuggets de pollo");
    registro.put("precio", 9.65);
    bd.insert("lista", null, registro);
    registro.put("name", "Triangulos de queso brie");
    registro.put("precio", 4.50);
    bd.insert("lista", null, registro);
    registro.put("name", "Medallones de queso de cabra");
    registro.put("precio", 3.90);
    bd.insert("lista", null, registro);
    registro.put("name", "Mini camembert");
    registro.put("precio", 4.50);
    bd.insert("lista", null, registro);
    registro.put("name", "Queso fresco tempura");
    registro.put("precio", 3.95);
    bd.insert("lista", null, registro);
    registro.put("name", "Mini empanadillas de atún");
    registro.put("precio", 1.80);
    bd.insert("lista", null, registro);
    registro.put("name", "Empanadillas de atún");
    registro.put("precio", 1.80);
    bd.insert("lista", null, registro);
    registro.put("name", "Patatas bravas");
    registro.put("precio", 2.60);
    bd.insert("lista", null, registro);
    registro.put("name", "Patatas Corte Grueso");
    registro.put("precio", 1.90);
    bd.insert("lista", null, registro);
    registro.put("name", "Patatas gajos (Crujientes)");
    registro.put("precio", 3.10);
    bd.insert("lista", null, registro);
    registro.put("name", "Rollitos Primavera Grandes");
    registro.put("precio", 2.20);
    bd.insert("lista", null, registro);
    registro.put("name", "Salsa Agridulce");
    registro.put("precio", 0.90);
    bd.insert("lista", null, registro);
    registro.put("name", "Dátiles con Bacon y Almendra");
    registro.put("precio", 5.00);
    bd.insert("lista", null, registro);
    registro.put("name", "Colas de gamba Rebozadas");
    registro.put("precio", 5.00);
    bd.insert("lista", null, registro);
    registro.put("name", "Baguetina Plus Express");
    registro.put("precio", 2.40);
    bd.insert("lista", null, registro);
    registro.put("name", "Masa de Hojaldre");
    registro.put("precio", 2.00);
    bd.insert("lista", null, registro);
    registro.put("name", "Surtido de Panecillos Precocidos");
    registro.put("precio", 3.75);
    bd.insert("lista", null, registro);
    registro.put("name", "Trenza de Nueces y pasas");
    registro.put("precio", 9.95);
    bd.insert("lista", null, registro);
    registro.put("name", "Churros Lazo");
    registro.put("precio", 2.00);
    bd.insert("lista", null, registro);
    registro.put("name", "Frivolidades Variadas");
    registro.put("precio", 3.50);
    bd.insert("lista", null, registro);
    registro.put("name", "Trenza de Frutos del Bosque");
    registro.put("precio", 2.00);
    bd.insert("lista", null, registro);
    registro.put("name", "Galletas para Helado de Corte");
    registro.put("precio", 0.70);
    bd.insert("lista", null, registro);
    registro.put("name", "Corte Tradicional Turrón");
    registro.put("precio", 2.90);
    bd.insert("lista", null, registro);
    registro.put("name", "Corte Tradicional Nata y Café");
    registro.put("precio", 2.90);
    bd.insert("lista", null, registro);
    registro.put("name", "Helado de Leche Merengada con Canela");
    registro.put("precio", 3.50);
    bd.insert("lista", null, registro);
    registro.put("name", "Helado de Turrón de Jijona con Trocitos de Turrón");
    registro.put("precio", 3.50);
    bd.insert("lista", null, registro);
    registro.put("name", "Sandwich de Nata");
    registro.put("precio", 2.00);
    bd.insert("lista", null, registro);
    registro.put("name", "Sandwich de Vainilla y Chocolate");
    registro.put("precio", 3.60);
    bd.insert("lista", null, registro);
    registro.put("name", "Surtido Mini Bombones");
    registro.put("precio", 2.95);
    bd.insert("lista", null, registro);
    registro.put("name", "Mini Conos de Vainilla y Chocolate");
    registro.put("precio", 3.25);
    bd.insert("lista", null, registro);
    registro.put("name", "Tarta Laminada de Nata y Chocolate");
    registro.put("precio", 3.00);
    bd.insert("lista", null, registro);
    registro.put("name", "Castañas de Nata");
    registro.put("precio", 2.70);
    bd.insert("lista", null, registro);
    registro.put("name", "Tarta al Whisky");
    registro.put("precio", 4.50);
    bd.insert("lista", null, registro);
    registro.put("name", "Surtido Helados Artesanos");
    registro.put("precio", 7.00);
    bd.insert("lista", null, registro);
    registro.put("name", "Coulant de Chocolate (Receta mejorada)");
    registro.put("precio", 2.95);
    bd.insert("lista", null, registro);
    registro.put("name", "Creps Artesanas de Chocolate (Grandes)");
    registro.put("precio", 2.50);
    bd.insert("lista", null, registro);
    registro.put("name", "Tarta de la Abuela");
    registro.put("precio", 22.00);
    bd.insert("lista", null, registro);
    registro.put("name", "Tarta Muerte por Chocolate");
    registro.put("precio", 32.00);
    bd.insert("lista", null, registro);
    registro.put("name", "Tarta de Queso con Arándanos");
    registro.put("precio", 8.95);
    bd.insert("lista", null, registro);
    registro.put("name", "Profiteroles de Nata");
    registro.put("precio", 1.75);
    bd.insert("lista", null, registro);
    registro.put("name", "Surtido Pastelitos");
    registro.put("precio", 10.00);
    bd.insert("lista", null, registro);
    registro.put("name", "Puntilla de Calamar Limpia y Sin Pluma");
    registro.put("precio", 8.95);
    bd.insert("lista", null, registro);


    registro.put("name", "Cochifrito");
    registro.put("precio", 6.50);
    bd.insert("lista", null, registro);




    registro.put("name", "Chuletillas de toston");
    registro.put("precio", 7.50);
    bd.insert("lista", null, registro);







    bd.close();
    SharedPreferences preferencias=getSharedPreferences("datos", Context.MODE_PRIVATE);
    SharedPreferences.Editor editor=preferencias.edit();
    editor.putString("mail", "funciona");
    editor.commit();
}
    public void recuperarCONTACTOS() {
        BDcliente admin = new BDcliente(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        List lista_contactos = new ArrayList();
        Datos d;
        String[] valores_recuperar = {"_ID","name","precio"};
        Cursor c = bd.query("lista", valores_recuperar,
                null, null, null, null, null, null);
        if (c.moveToFirst()) {
            do {
                 //   TAREAS.add(new Tarea(c.getString(1),String.valueOf(c.getFloat(2)),R.mipmap.ic_health));
                d = new Datos(c.getString(1)+" "+String.valueOf(c.getFloat(2)),R.mipmap.ic_health);
                Datos.add(d);
                    solud.add(c.getString(1)+" "+String.valueOf(c.getFloat(2)));
                    myCoords.add(c.getFloat(2));
                    pedido.add(c.getString(1));
                    idn.add(c.getInt(0));
                    //  lista_contactos.add(c.getString(1));
                    //  myCoords.add(c.getInt(0));

            } while (c.moveToNext());
        }
        bd.close();
        c.close();

    }
    public boolean perreoGuarro() {
        BDcliente admin = new BDcliente(this,
                "administracion", null, 1);
        int idelio;
        SQLiteDatabase bd = admin.getWritableDatabase();
        String[] valores_recuperar = {"_ID", "name", "cantidad"};
        Cursor c = bd.query("stock", valores_recuperar, "_ID=" + idn.get(pos),
                null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        idelio = c.getInt(2);
        ContentValues valores = new ContentValues();
        if (idelio == 0) {
            return false;

        } else {
            valores.put("cantidad", idelio - 1);
            bd.update("stock", valores, "_ID=" + idn.get(pos), null);

            bd.close();
            c.close();
            return true;
        }
    }

    public void productosw() {
        String dato ="datos";
        Intent intent = new Intent(listacompra.this, MainActivity.class);
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
