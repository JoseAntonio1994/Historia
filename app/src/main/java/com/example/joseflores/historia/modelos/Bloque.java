package com.example.joseflores.historia.modelos;


/**
 * Created by JoseFlores on 13/03/2018.
 */

public class Bloque {
    private String imagenEpoca;
    private String nombreEpoca;

    public Bloque(){}

    public Bloque(String urlImagen, String nombre) {
        this.imagenEpoca = urlImagen;
        this.nombreEpoca = nombre;
    }

    public String getImagenEpoca() {
        return imagenEpoca;
    }

    public void setImagenEpoca(String imagenEpoca) {
        this.imagenEpoca = imagenEpoca;
    }

    public String getNombreEpoca() {
        return nombreEpoca;
    }

    public void setNombreEpoca(String nombreEpoca) {
        this.nombreEpoca = nombreEpoca;
    }

}
