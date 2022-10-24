/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.models;

import com.google.gson.annotations.SerializedName;
import java.sql.Date;

/**
 * Class  Name: Estadisticas
 * Date: 21 oct. 2022
 * Version: 1.0
 * Copyright: Free
 * @author Geovanny Martinez (034519)
 */
public class Estadistica {
    @SerializedName("idDetalle")
    private int idDetalle;
    @SerializedName("idRegistro")
    private int idRegistro;
    @SerializedName("fechaCreacion")
    private Date fechaCreacion;
    @SerializedName("totalVisitantes")
    private int totalVisitantes;
    @SerializedName("nombreCategoria")
    private String nombreCategoria;

    public Estadistica() {
    }

    public Estadistica(Date fechaCreacion, int totalVisitantes) {
        this.fechaCreacion = fechaCreacion;
        this.totalVisitantes = totalVisitantes;
    }

    public Estadistica(String nombreCategoria, int totalVisitantes) {
        this.totalVisitantes = totalVisitantes;
        this.nombreCategoria = nombreCategoria;
    }
    

    public Estadistica(int idDetalle, int idRegistro, Date fechaCreacion, int totalVisitantes) {
        this.idDetalle = idDetalle;
        this.idRegistro = idRegistro;
        this.fechaCreacion = fechaCreacion;
        this.totalVisitantes = totalVisitantes;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
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

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getTotalVisitantes() {
        return totalVisitantes;
    }

    public void setTotalVisitantes(int totalVisitantes) {
        this.totalVisitantes = totalVisitantes;
    }
    
    
}
