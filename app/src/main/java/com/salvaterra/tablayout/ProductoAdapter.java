package com.salvaterra.tablayout;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import static android.app.PendingIntent.getActivity;

/**
 * Created by zurdotsg on 31/01/16.
 */
public class ProductoAdapter extends BaseAdapter {
    private ArrayList<Productos> productos;
    private listacompra activity;

    public ProductoAdapter(listacompra activity,ArrayList<Productos> productos) {

        this.productos = productos;
    }

    @Override
    public int getCount() {
        return productos.size();
    }

    @Override
    public Object getItem(int position) {
        return productos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    public static class ViewHolder{
        TextView textViewTitulo;
        TextView textViewDirector;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = new ViewHolder();
        LayoutInflater layoutInflater = LayoutInflater.from(activity.getApplicationContext());
        final Productos producto = productos.get(position);
        convertView = layoutInflater.inflate(R.layout.list_view_productos,
                parent,false);

        viewHolder.textViewTitulo = (TextView)
                convertView.findViewById(R.id.textViewTitulo);
        viewHolder.textViewTitulo.setTextColor(Color.DKGRAY);
        viewHolder.textViewDirector = (TextView)
                convertView.findViewById(R.id.textViewDirector);

        viewHolder.textViewTitulo.setText(producto.getTitulo());
        viewHolder.textViewDirector.setText(String.valueOf(producto.getPrecio()));

        return convertView;
    }
}
