package com.salvaterra.tablayout;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class FragmentTwo extends Fragment {

    private int recuperar;
    private List<Integer> myCoords = new ArrayList<Integer>();
    private ArrayList<Productos> producto;
    private ProductoAdapter peliculaAdapter;
    private ListView lv1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fragment_two, container, false);
        lv1 = (ListView)rootView.findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, recuperar());
        lv1.setAdapter(adapter);

        return rootView;
    }
    public String recuperarCONTACTO(int id) {
        BDcliente  admin= new BDcliente(getContext(),
                "administracion", null, 1);
        ContentValues registro = new ContentValues();
        int cont=1;
        String nombre = null;
        SQLiteDatabase bd = admin.getWritableDatabase();
        String[] valores_recuperar = {"_ID", "id_cliente"};
        Cursor c = bd.query("prueba", valores_recuperar, "_ID=" + "1",
                null, null, null, null, null);
        if(c != null) {
            c.moveToFirst();
        }
        recuperar=c.getInt(1);

        bd.close();
        c.close();
        return "hola";
    }
    public List recuperar() {
        BDcliente admin = new BDcliente(getContext(),
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        List lista_contactos = new ArrayList();

        String[] valores_recuperar = {"_ID","name","id_cliente","precio"};
        Cursor c = bd.query("producto", valores_recuperar,
                null, null, null, null, null, null);
        if (c.moveToFirst()) {
            do {
                if(c.getInt(2)==recuperar) {
                    lista_contactos.add(c.getString(1) + c.getInt(3));
                    myCoords.add(c.getInt(3));

                    Toast notificacion = Toast.makeText(getContext(),String.valueOf(c.getInt(3)), Toast.LENGTH_SHORT);
                    notificacion.show();
                }
            } while (c.moveToNext());
        }
        bd.close();
        c.close();
        return lista_contactos;
    }

}