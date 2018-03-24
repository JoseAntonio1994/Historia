package com.example.joseflores.historia.modelos;

import com.example.joseflores.historia.app.MyApplication;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by JoseFlores on 13/03/2018.
 */

public class Tema extends RealmObject {

    @PrimaryKey
    private int id;
    @Required
    private String urlImagen;
    @Required
    private String nombre;


    public Tema(){}

    public Tema(String urlImagen, String nombre) {
        this.id = MyApplication.TemasID.incrementAndGet();
        this.urlImagen = urlImagen;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
