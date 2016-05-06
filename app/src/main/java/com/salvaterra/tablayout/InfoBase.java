package com.salvaterra.tablayout;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zurdotsg on 26/01/16.
 */
public class InfoBase extends Activity {
    private TextView t1,t2,t3,t4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_base);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        t1= (TextView)findViewById(R.id.textView7);
        t2= (TextView)findViewById(R.id.textView9);
        t3= (TextView)findViewById(R.id.textView12);
        t4= (TextView)findViewById(R.id.textView14);
        recuperarCONTACTOS();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_info_base, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.home) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void recuperarCONTACTOS() {
        Paint p = new Paint();
        p.setColor(Color.BLUE);

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        Bundle bundle = getIntent().getExtras();
        String cod=bundle.getString("direccion");
        t1.setText(cod);
        String selection = "pag";
        SQLiteDatabase bd = admin.getWritableDatabase();
        List lista_contactos = new ArrayList();
        String[] valores_recuperar = {"pag", "user", "correo", "contrase"};
        //Cursor c = bd.query("pass", valores_recuperar,
        //       null, null, null, null, null, null);
        Cursor c = bd.query("pass", valores_recuperar,
                null, null, null, null, null, null);
        if (c.moveToFirst()) {
            do {

                if(c.getString(0).equals(cod)) {
                    t1.setText(cod);//lista_contactos.add("prueba");
                    t1.setTextColor(Color.rgb(88, 88, 88));
                    t2.setText(c.getString(3));
                    t3.setText(c.getString(1));
                    t4.setText(c.getString(2));
                    t3.setTextColor(Color.rgb(88, 88, 88));
                    t4.setTextColor(Color.rgb(88, 88, 88));
                    t2.setTextColor(Color.rgb(88, 88, 88));
                }



            } while (c.moveToNext());
        } else
            Toast.makeText(this, "No existe un artículo con dicho código",
                    Toast.LENGTH_SHORT).show();
        bd.close();
        c.close();
        Toast notificacion = Toast.makeText(this, cod, Toast.LENGTH_LONG);

    }
    public void borrar(View v)
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        Bundle bundle = getIntent().getExtras();
        String cod=bundle.getString("direccion");
        String selection = "pag";

        bd.delete("pass", "pag" + "=?", new String[]{cod});
        //  int cant = bd.delete("pass", "pag=" + cod, null);

        Intent q = new Intent(this, NuevaActivity.class);
        startActivity(q);

    }
}
