package com.example.joseflores.historia.modelos;

/**
 * Created by JoseFlores on 13/03/2018.
 */

public class Tema {

    private String urlImagen;
    private String nombre;

    public Tema() {
    }

    public Tema(String urlImagen, String nombre) {
        this.urlImagen = urlImagen;
        this.nombre = nombre;
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
