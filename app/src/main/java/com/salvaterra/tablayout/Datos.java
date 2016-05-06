package com.salvaterra.tablayout;

/**
 * Created by zurdotsg on 19/02/16.
 */


/**
 * Created by jcmolanoro on 17/12/2015.
 */
public class Datos {
    private String titulo;
    private Integer img;

    public Datos(String titulo, Integer img){
        this.titulo= titulo;
        this.img = img;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getImg() {
        return img;
    }

    public void setImg(Integer img) {
        this.img= img;
    }
}
