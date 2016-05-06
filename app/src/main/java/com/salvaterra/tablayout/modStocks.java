package com.salvaterra.tablayout;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zurdotsg on 14/02/16.
 */
public class modStocks  extends Activity {
    Button btnCambioActivity;
    private EditText et1;

    private int id;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modstocks);
        btnCambioActivity = (Button) findViewById(R.id.button8);
        Bundle bundle = getIntent().getExtras();
        et1=(EditText)findViewById(R.id.editText2);
        id=bundle.getInt("direccion");

    }



    public void cerrar(View view) {
        finish();
    }
    public void modificarCONTACTO(View v){
        BDcliente admin = new BDcliente(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put("cantidad", Integer.parseInt(et1.getText().toString()) );
        bd.update("stock", valores, "_ID=" + id, null);
        bd.close();
        Intent i = new Intent(this, stock.class);
        i.putExtra("direccion",5);
        startActivity(i);
    }
    public void productosw() {
        String dato ="datos";
        Intent intent = new Intent(modStocks.this, MainActivity.class);
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

