package com.example.joseflores.historia.modelos;

/**
 * Created by JoseFlores on 13/03/2018.
 */

public class Tema {

    private String imagenTema;
    private String nombreTema;

    public Tema() {
    }

    public Tema(String urlImagen, String nombre) {
        this.imagenTema = urlImagen;
        this.nombreTema = nombre;
    }

    public String getImagenTema() {
        return imagenTema;
    }

    public void setImagenTema(String imagenTema) {
        this.imagenTema = imagenTema;
    }

    public String getNombreTema() {
        return nombreTema;
    }

    public void setNombreTema(String nombreTema) {
        this.nombreTema = nombreTema;
    }
}
