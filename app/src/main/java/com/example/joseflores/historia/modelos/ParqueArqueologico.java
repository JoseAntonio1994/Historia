package com.example.joseflores.historia.modelos;

/**
 * Created by JoseFlores on 23/04/2018.
 */

public class ParqueArqueologico {
    private String parUID;
    private String urlParque;
    private String nomParque;
    private String descParque;

    public ParqueArqueologico(){}

    public ParqueArqueologico(String parUID, String urlParque, String nomParque, String descParque) {
        this.parUID = parUID;
        this.urlParque = urlParque;
        this.nomParque = nomParque;
        this.descParque = descParque;
    }

    public String getParUID() {
        return parUID;
    }

    public void setParUID(String parUID) {
        this.parUID = parUID;
    }

    public String getUrlParque() {
        return urlParque;
    }

    public void setUrlParque(String urlParque) {
        this.urlParque = urlParque;
    }

    public String getNomParque() {
        return nomParque;
    }

    public void setNomParque(String nomParque) {
        this.nomParque = nomParque;
    }

    public String getDescParque() {
        return descParque;
    }

    public void setDescParque(String descParque) {
        this.descParque = descParque;
    }
}
