package com.salvaterra.tablayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by zurdotsg on 19/02/16.
 */
public class ListaAdapter extends BaseAdapter implements Filterable {

    // Declare Variables
    Context context;
    ArrayList<Datos> Datos;
    LayoutInflater inflater;
    CustomFilter filtro;
    ArrayList<Datos> filtroList;

    public ListaAdapter(Context context, ArrayList<Datos> Datos) {
        this.context = context;
        this.Datos = Datos;
        this.filtroList = Datos;
    }

    @Override
    public int getCount() {
        return Datos.size();
    }

    @Override
    public Object getItem(int position) {
        return Datos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Datos.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        // Declare Variables
        TextView txtTitle;
        ImageView imgImg;

        //http://developer.android.com/intl/es/reference/android/view/LayoutInflater.html
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(view == null){
            view = inflater.inflate(R.layout.lista_formato, parent, false);
        }


        // Locate the TextViews in listview_item.xml
        txtTitle = (TextView) view.findViewById(R.id.tituloLista);
        imgImg = (ImageView) view.findViewById(R.id.iconLista);

        // Capture position and set to the TextViews
        txtTitle.setText(Datos.get(position).getTitulo());
        imgImg.setImageResource(Datos.get(position).getImg());

        return view;
    }


    @Override
    public Filter getFilter() {
        if(filtro == null){
            filtro = new CustomFilter();
        }

        return filtro;
    }

    class CustomFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults resulst = new FilterResults();
            if(constraint != null && constraint.length()>0){
                //pasamos a mayusculas
                constraint = constraint.toString().toUpperCase();

                ArrayList<Datos> filtro = new ArrayList<Datos>();

                for(Integer i=0;i<filtroList.size();i++){
                    if(filtroList.get(i).getTitulo().toUpperCase().contains(constraint)){
                        Datos d= new Datos(filtroList.get(i).getTitulo(),filtroList.get(i).getImg());

                        filtro.add(d);
                    }
                }
                resulst.count= filtro.size();
                resulst.values = filtro;
            }else{
                resulst.count= filtroList.size();
                resulst.values = filtroList;
            }

            return resulst;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            Datos = (ArrayList<Datos>) results.values;
            notifyDataSetChanged();

        }
    }
}