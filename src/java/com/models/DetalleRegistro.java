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
public class DetalleRegistro {
    
    @SerializedName("idDetalle")
    private int idDetalle;
    @SerializedName("idRegistro")
    private int idRegistro;
    @SerializedName("idClasificacion")
    private int idClasificacion;
    @SerializedName("totalVisitantes")
    private int totalVisitantes;

    public DetalleRegistro() {
    }

    public DetalleRegistro(int idDetalle, int idRegistro, int idClasificacion, int totalVisitantes) {
        this.idDetalle = idDetalle;
        this.idRegistro = idRegistro;
        this.idClasificacion = idClasificacion;
        this.totalVisitantes = totalVisitantes;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(int idRegistro) {
        this.idRegistro = idRegistro;
    }

    public int getIdClasificacion() {
        return idClasificacion;
    }

    public void setIdClasificacion(int idClasificacion) {
        this.idClasificacion = idClasificacion;
    }

    public int getTotalVisitantes() {
        return totalVisitantes;
    }

    public void setTotalVisitantes(int totalVisitantes) {
        this.totalVisitantes = totalVisitantes;
    }
    
    
    
}
