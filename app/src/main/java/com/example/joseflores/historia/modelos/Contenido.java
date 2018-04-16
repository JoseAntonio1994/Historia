package com.example.joseflores.historia.modelos;

/**
 * Created by JoseFlores on 06/04/2018.
 */

public class Contenido {
    private String conUID;
    private int recContenido;

    public Contenido(){}

    public Contenido(String UID, int recContenido) {
        this.conUID = UID;
        this.recContenido = recContenido;
    }

    public String getConUID() {
        return conUID;
    }

    public void setConUID(String conUID) {
        this.conUID = conUID;
    }

    public int getRecContenido() {
        return recContenido;
    }

    public void setRecContenido(int recContenido) {
        this.recContenido = recContenido;
    }

}
