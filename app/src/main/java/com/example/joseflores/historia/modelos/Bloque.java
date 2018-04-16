package com.example.joseflores.historia.modelos;


/**
 * Created by JoseFlores on 13/03/2018.
 */

public class Bloque {
    private String UID;
    private String imagenEpoca;
    private String nombreEpoca;

    public Bloque(){}

    public Bloque(String urlImagen, String nombre) {
        this.imagenEpoca = urlImagen;
        this.nombreEpoca = nombre;
    }

    public Bloque(String UID, String imagenEpoca, String nombreEpoca) {
        this.UID = UID;
        this.imagenEpoca = imagenEpoca;
        this.nombreEpoca = nombreEpoca;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
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
