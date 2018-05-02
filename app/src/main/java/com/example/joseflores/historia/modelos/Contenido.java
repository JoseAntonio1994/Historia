package com.example.joseflores.historia.modelos;

/**
 * Created by JoseFlores on 06/04/2018.
 */

public class Contenido {
    private String conUID;
    private String imgContenido;
    private String recContenido;

    public Contenido(){}

    public Contenido(String conUID, String imgContenido, String recContenido) {
        this.conUID = conUID;
        this.imgContenido = imgContenido;
        this.recContenido = recContenido;
    }

    public String getImgContenido() {
        return imgContenido;
    }

    public void setImgContenido(String imgContenido) {
        this.imgContenido = imgContenido;
    }

    public String getConUID() {
        return conUID;
    }

    public void setConUID(String conUID) {
        this.conUID = conUID;
    }

    public String getRecContenido() {
        return recContenido;
    }

    public void setRecContenido(String recContenido) {
        this.recContenido = recContenido;
    }

}
