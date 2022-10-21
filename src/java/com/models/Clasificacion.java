/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.models;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author rober
 */
public class Clasificacion {
    
    @SerializedName("idClasificacion")
    private int idClasificacion;
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("rangoInicial")
    private int rangoInicial;
    @SerializedName("rangoFinal")
    private Integer rangoFinal;

    public Clasificacion(int idClasificacion, String nombre, int rangoInicial, Integer rangoFinal) {
        this.idClasificacion = idClasificacion;
        this.nombre = nombre;
        this.rangoInicial = rangoInicial;
        this.rangoFinal = rangoFinal;
    }

    public Clasificacion() {
    }

    public int getIdClasificacion() {
        return idClasificacion;
    }

    public void setIdClasificacion(int idClasificacion) {
        this.idClasificacion = idClasificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getRangoInicial() {
        return rangoInicial;
    }

    public void setRangoInicial(int rangoInicial) {
        this.rangoInicial = rangoInicial;
    }

    public Integer getRangoFinal() {
        return rangoFinal;
    }

    public void setRangoFinal(Integer rangoFinal) {
        this.rangoFinal = rangoFinal;
    }
}
