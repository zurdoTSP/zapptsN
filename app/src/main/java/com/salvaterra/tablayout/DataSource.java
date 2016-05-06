package com.salvaterra.tablayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zurdotsg on 12/02/16.
 */
public class DataSource {

    static List<Tarea> TAREAS=new ArrayList<Tarea>();

    static{

        TAREAS.add(new Tarea("Trotar 30 minutos","08:00",R.mipmap.ic_health));
        TAREAS.add(new Tarea("Estudiar análisis técnico","10:00",R.mipmap.ic_money));
        TAREAS.add(new Tarea("Comer 4 rebanadas de manzana","10:30",R.mipmap.ic_health));
        TAREAS.add(new Tarea("Asistir al taller de programación gráfica","15:45",R.mipmap.ic_carreer));
        TAREAS.add(new Tarea("Consignarle a Marta","18:00",R.mipmap.ic_money));

    }

}