package com.example.joseflores.historia.modelos;

import com.example.joseflores.historia.app.MyApplication;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by JoseFlores on 13/03/2018.
 */

public class Bloque extends RealmObject {

    @PrimaryKey
    private int id;
    @Required
    private String urlImagen;
    @Required
    private String nombre;

    private RealmList<Tema> listTemas;

    public Bloque(){}

    public Bloque(String urlImagen, String nombre) {
        this.id = MyApplication.BloquesID.incrementAndGet();
        this.urlImagen = urlImagen;
        this.nombre = nombre;
        this.listTemas = new RealmList<Tema>();
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

    public RealmList<Tema> getListTemas() {
        return listTemas;
    }

    public void setListTemas(RealmList<Tema> listTemas) {
        this.listTemas = listTemas;
    }
}
