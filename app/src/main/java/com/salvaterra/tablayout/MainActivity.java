package com.salvaterra.tablayout;


import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.support.v7.app.ActionBarActivity;
import android.widget.EditText;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    Button btnCambioActivity;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar); // Conectamos el Layout al objeto Toolbar

        // Configuración de la barra de herramientas (toolbar ) como la Barra de acciones (ActionBar)
        // con la llamada de setSupportActionBar ()
        setSupportActionBar(toolbar);

//Implementamos el evento “click” del botón
        btnCambioActivity = (Button) findViewById(R.id.button);

    }


    public void onClick(View v ) {
        String dato ="datos";
        Intent intent = new Intent(MainActivity.this, stock.class);
        intent.putExtra("DATO", dato);
        startActivity(intent);
    }
    public void ircliente(View v ) {
        String dato ="datos";
        Intent intent = new Intent(MainActivity.this, cliente.class);
        intent.putExtra("DATO", dato);
        startActivity(intent);
    }
    public void productos(View v) {
        String dato ="datos";
        Intent intent = new Intent(MainActivity.this, productos.class);
        intent.putExtra("DATO", dato);
        startActivity(intent);
    }
    public void productosw() {
        String dato ="datos";
        Intent intent = new Intent(MainActivity.this, productos.class);
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

