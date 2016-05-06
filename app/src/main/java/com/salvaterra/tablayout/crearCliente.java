package com.salvaterra.tablayout;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by zurdotsg on 8/02/16.
 */
public class crearCliente extends AppCompatActivity  {
    private EditText et2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crearcliente);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        et2 = (EditText) findViewById(R.id.editText);
    }
    public void alta(View v) {
        BDcliente admin = new BDcliente(this,
                "administracion", null, 1);
        ContentValues registro = new ContentValues();
        int cont=1;
        String nombre = null;
        SQLiteDatabase bd = admin.getWritableDatabase();

            nombre = et2.getText().toString();


            registro.put("name", nombre);
        bd.insert("pass", null, registro);
            Toast.makeText(this, "Se cargaron los datos de la cuenta",
                    Toast.LENGTH_SHORT).show();

        bd.close();
        Intent q = new Intent(this, cliente.class);
        startActivity(q);

    }
    public void productosw() {
        String dato ="datos";
        Intent intent = new Intent(crearCliente.this, MainActivity.class);
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
