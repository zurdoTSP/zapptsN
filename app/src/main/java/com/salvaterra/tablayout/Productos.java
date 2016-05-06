package com.salvaterra.tablayout;

/**
 * Created by zurdotsg on 31/01/16.
 */
public class Productos {

        private String titulo;
        private float precio;

        public Productos(String titulo, float director) {
            this.titulo = titulo;
            this.precio = director;
        }

        public String getTitulo() {
            return titulo;
        }

        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }

        public float getPrecio() {
            return precio;
        }

        public void setDirector(float director) {
            this.precio = director;
        }


}
