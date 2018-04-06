package com.example.joseflores.historia.modelos;

import android.widget.ImageView;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by JoseFlores on 06/04/2018.
 */

public class Contenido extends RealmObject{

    @PrimaryKey
    private int id;

    private String urlContenido;

    private int recContenido;

    public Contenido(){}

    public Contenido(String urlContenido, int recContenido) {
        this.urlContenido = urlContenido;
        this.recContenido = recContenido;
    }

    public int getId() {
        return id;
    }

    public String getUrlContenido() {
        return urlContenido;
    }

    public void setUrlContenido(String urlContenido) {
        this.urlContenido = urlContenido;
    }

    public int getRecContenido() {
        return recContenido;
    }

    public void setRecContenido(int recContenido) {
        this.recContenido = recContenido;
    }
}
