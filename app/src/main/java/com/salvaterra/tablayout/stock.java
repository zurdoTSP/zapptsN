package com.salvaterra.tablayout;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
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

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zurdotsg on 14/02/16.
 */
public class stock extends AppCompatActivity

{
    private EditText busqueda;
    private Toolbar toolbar;
    ListView listView;
    private ArrayList<Datos> Datos;
    ListaAdapter adapter;

    private ProductoAdapter peliculaAdapter;
    private int rumanoZ;
    private String recuperar;
    private int id;
    private List<Tarea> TAREAS = new ArrayList<Tarea>();
    private String x;
    private int pos;
    private List<Integer> myCoords = new ArrayList<Integer>();
    private List<String> pedido = new ArrayList<String>();
    private List<String> solud = new ArrayList<String>();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.stock);
        // Get ListView object from xml
        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("direccion");
        SharedPreferences prefe = getSharedPreferences("datos", Context.MODE_PRIVATE);
        x = prefe.getString("mail2", "");

        toolbar = (Toolbar) findViewById(R.id.toolbar); // Conectamos el Layout al objeto Toolbar

        // Configuración de la barra de herramientas (toolbar ) como la Barra de acciones (ActionBar)
        // con la llamada de setSupportActionBar ()
        setSupportActionBar(toolbar);
        if (x.equals("")) {
            crearBase();
        }

        Datos = new ArrayList<Datos>();

        recuperarCONTACTOS();
        // TAREAS.add(new Tarea("Trotar 30 minutos","08:00",R.mipmap.ic_health));
        // TAREAS.add(new Tarea("Estudiar análisis técnico","10:00",R.mipmap.ic_money));
        // TAREAS.add(new Tarea("Comer 4 rebanadas de manzana", "10:30", R.mipmap.ic_health));
        // TAREAS.add(new Tarea("Asistir al taller de programación gráfica", "15:45", R.mipmap.ic_carreer));
        // TAREAS.add(new Tarea("Consignarle a Marta", "18:00", R.mipmap.ic_money));

        listView = (ListView) findViewById(R.id.listView8);
        adapter = new ListaAdapter(this, Datos);
        busqueda = (EditText) findViewById(R.id.editText4);
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

            }});
        busqueda.addTextChangedListener(new TextWatcher() {
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

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }
public void entra(String alfombra)
{
    for (int i = 0; i < solud.size(); i++) {
        if (solud.get(i).equals(alfombra)) {
            Toast.makeText(getApplicationContext(), "Entro" +String.valueOf(i) , Toast.LENGTH_SHORT).show();
            rumanoZ = i;
       }

    }
}
    public void veranoAzulado(View v) {
        Intent i = new Intent(this, modStocks.class);
        Toast.makeText(getApplicationContext(), "Ha elegido " +String.valueOf(myCoords.get(rumanoZ)) , Toast.LENGTH_SHORT).show();
        i.putExtra("direccion", myCoords.get(rumanoZ));
        startActivity(i);
    }

    public void altaFactura() {
        BDcliente admin = new BDcliente(this,
                "administracion", null, 1);
        ContentValues registro = new ContentValues();
        int cont = 1;
        String nombre = null;
        SQLiteDatabase bd = admin.getWritableDatabase();

        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss yyyy/MM/d");
        String dateString = sdf.format(date);

        registro.put("name", pedido.get(pos));
        registro.put("id_cliente", id);
        registro.put("precio", myCoords.get(pos));
        bd.insert("producto", null, registro);

        bd.close();
    }

    public void crearBase() {
        // lista(_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,name text,precio INTEGER)");
        BDcliente admin = new BDcliente(this,
                "administracion", null, 1);
        ContentValues registro = new ContentValues();
        int cont = 1;
        String nombre = null;
        SQLiteDatabase bd = admin.getWritableDatabase();

        registro.put("name", "Salmon noruego ahumado");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Lomitos Salmón Salar");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Emperador");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Emperador cortado");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Lomos de atún rojo");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Filete de palometa");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Lubina eviscerada");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Blquerón limpio");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Rodajas de merluza");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Solomillos de merluza");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Medallones de merluza");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Filetes de merluza");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Pescadilla sin cabeza");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Lenguado 100% Limpio");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Filete de Lenguado");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Filetes de Tilapia");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Filetes de Pangasius");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Cola de rape nacional");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Cola de rape mediano limpio");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Lomos de bacalao gris");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Filetes de Bacaladilla");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Solomillos grandes de Bacalao blanco");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Trozos de Bacalao");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Preparado para paella");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Preparado para sopa marinera");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Frituras de pescado");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Sepia limpia");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Sepia limpia troceada");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Pata de pulpo cocidas");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Sepia limpia natural");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Puntilla de calamar limpia sin pluma");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Anillas limpias");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Chipirón limpio y troceado");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Calamar fino entero");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Calamar fino");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Bogavante crudo");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Gambones grandes");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Cigalas grandes");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Rojas brillantes grandes");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Bocas de buey de mar cocidas y precortadas");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Gambas rayadas medianas");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Langostinos grandes cocidas");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Gambas saladas");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Gambas rojas peladas gigantes");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Gambas rojas peladas grandes");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Mejillones cocidos sin concha grande");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Mejillones en su propio jugo");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Caracoles precocidos");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Palitos de mar abordo");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Sucedaneo de angulas abordo");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Muslitos de mar abordo");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Salteado campestre");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Salteado de verduras braseadas");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Salteado de patatas");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Salteado mixto de setas");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Arroz 6 delicias");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Salteado de habilas ajetes y bacon");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Revuelto de esparragos ajos y gambas");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Salpicon de marisco");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Especial cóctel y ensalada");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Ensalada de cangrejo");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Ensalada con pollo");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Alcachofa troceada");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Alubia blanca precocida");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Garbanzos");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Cebolla limpia y troceada");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Ajo picado");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Guisante Baby");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Hojas de espinacas");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Zanahorias baby");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Puntas de espárrago verde extra");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Floretes de brócoli");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Habitas superbaby nacionales");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Cardo troceado");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Ensaladilla rusa");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Judía extrafina entera");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Menestra de la huerta navarra");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Judías verdes planas");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Menestra brocozan");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Pisto de verduras");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Garrofón precocido");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Verduras para paella");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Ajos tiernos troceados");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Hojaldre de salmón y espinacas");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Cangrejo relleno");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Filete de lenguado relleno de mousse de gambas");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Bacalao a la vizcaina");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Lubina a las finas hierbas");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Salmón a las finas hierbas");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Salmón a la barbacoa");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Costillar entero barbacoa");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Carrillada de cerdo con salsa de champiñones");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Carrillada de ternera con salsa boletus");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Jarrete de cordero con salsa");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Canelones de espinacas");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Mussaka");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Canalones de carne");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Lasaña vegetal");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Lasaña carne con bechamel");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Lasaña de atún");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Pizza base fina y crujiente");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Tostas");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Mini pizzas");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Hamburgesa con verduras");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Hamburgesa de ternera mixta");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Chuleta de palo de cordero");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Hamburguesa de ternera extra");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Cordero lechal");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Carrillada de cerdo");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Albóndigas de carne caseras");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Rabo de cerdo cocido");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Careta de cerdo troceada");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Solomillo de cerdo");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Longaniza de magro");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Chorizos");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Morcillas de cebolla");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Pechuga de pollo");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Alitas de pollo");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Jamoncitos de pollo");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Alitas de pollo adobadas");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Mini redondo de jámon y queso");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Pelotas para cocido");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Redondo de pollo a las finas huerbas");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Arreglo puchero con ternera");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Redondo de pavo");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Lomito de cerdo a la jardinera");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Figuritas de merluza rebozadas");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Varitas de merluza");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Filete de merluza rebozada crunchi");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Calamar chipirón enharinado");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Tortillas de camarón");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Rabas de calamar rebozadas");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "San jacobo");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Libritos de pollo con salsa boloñesa y queso");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Solomillos de pollo rebozado");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Nuggets de pollo");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Triangulos de queso brie");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Medallones de queso de cabra");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Mini camembert");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Queso fresco tempura");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Mini empanadillas de atún");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Empanadillas de atún");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Patatas bravas");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Patatas Corte Grueso");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Patatas gajos (Crujientes)");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Rollitos Primavera Grandes");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Salsa Agridulce");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Dátiles con Bacon y Almendra");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Colas de gamba Rebozadas");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Baguetina Plus Express");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Masa de Hojaldre");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Surtido de Panecillos Precocidos");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Trenza de Nueces y pasas");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Churros Lazo");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Frivolidades Variadas");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Trenza de Frutos del Bosque");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Galletas para Helado de Corte");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Corte Tradicional Turrón");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Corte Tradicional Nata y Café");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Helado de Leche Merengada con Canela");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Helado de Turrón de Jijona con Trocitos de Turrón");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Sandwich de Nata");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Sandwich de Vainilla y Chocolate");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Surtido Mini Bombones");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Mini Conos de Vainilla y Chocolate");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Tarta Laminada de Nata y Chocolate");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Castañas de Nata");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Tarta al Whisky");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Surtido Helados Artesanos");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Coulant de Chocolate (Receta mejorada)");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Creps Artesanas de Chocolate (Grandes)");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Tarta de la Abuela");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Tarta Muerte por Chocolate");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Tarta de Queso con Arándanos");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Profiteroles de Nata");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Surtido Pastelitos");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);
        registro.put("name", "Puntilla de Calamar Limpia y Sin Pluma");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);



        registro.put("name", "Cochifrito");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);







        registro.put("name", "Chuletillas de toston");
        registro.put("cantidad", 0);
        bd.insert("stock", null, registro);





        bd.close();
        SharedPreferences preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putString("mail2", "funciona");
        editor.commit();
    }

    public void recuperarCONTACTOS() {
        BDcliente admin = new BDcliente(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        List lista_contactos = new ArrayList();

        String[] valores_recuperar = {"_ID", "name", "cantidad"};
        Cursor c = bd.query("stock", valores_recuperar,
                null, null, null, null, null, null);
        Datos d;
        if (c.moveToFirst()) {
            do {
                //TAREAS.add(new Tarea(c.getString(1), String.valueOf(c.getFloat(2)), R.mipmap.ic_health));
                d = new Datos(c.getString(1) + " " + String.valueOf(c.getInt(2)), R.mipmap.ic_health);
                Datos.add(d);
                myCoords.add(c.getInt(0));
                pedido.add(c.getString(0));
                solud.add(c.getString(1) + " " + String.valueOf(c.getInt(2)));
                //  lista_contactos.add(c.getString(1));
                //  myCoords.add(c.getInt(0));

            } while (c.moveToNext());
        }
        bd.close();
        c.close();

    }

    public void productosw() {
        String dato = "datos";
        Intent intent = new Intent(stock.this, MainActivity.class);
        intent.putExtra("DATO", dato);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.stockm, menu);
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


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "stock Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.salvaterra.tablayout/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "stock Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.salvaterra.tablayout/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}

