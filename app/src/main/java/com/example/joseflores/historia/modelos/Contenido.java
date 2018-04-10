package com.example.joseflores.historia.modelos;

/**
 * Created by JoseFlores on 06/04/2018.
 */

public class Contenido {
    private String urlContenido;
    private int recContenido;
    private int idTema;
    private int idBloque;

    public Contenido(){}

    public Contenido(String urlContenido, int recContenido) {
        this.urlContenido = urlContenido;
        this.recContenido = recContenido;
    }

    public int getIdTema() {
        return idTema;
    }

    public int getIdBloque() {
        return idBloque;
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
