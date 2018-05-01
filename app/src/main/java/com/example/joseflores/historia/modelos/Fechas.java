package com.example.joseflores.historia.modelos;

/**
 * Created by JoseFlores on 30/04/2018.
 */

public class Fechas {

    private String idFecha;
    private String imageFecha;
    private String nomFecha;
    private String descFecha;

    public Fechas() {
    }

    public Fechas(String idFecha, String imageFecha, String nomFecha, String descFecha) {
        this.idFecha = idFecha;
        this.imageFecha = imageFecha;
        this.nomFecha = nomFecha;
        this.descFecha = descFecha;
    }

    public String getIdFecha() {
        return idFecha;
    }

    public void setIdFecha(String idFecha) {
        this.idFecha = idFecha;
    }

    public String getImageFecha() {
        return imageFecha;
    }

    public void setImageFecha(String imageFecha) {
        this.imageFecha = imageFecha;
    }

    public String getNomFecha() {
        return nomFecha;
    }

    public void setNomFecha(String nomFecha) {
        this.nomFecha = nomFecha;
    }

    public String getDescFecha() {
        return descFecha;
    }

    public void setDescFecha(String descFecha) {
        this.descFecha = descFecha;
    }
}
