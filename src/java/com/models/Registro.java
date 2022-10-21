/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.models;

import com.google.gson.annotations.SerializedName;
import java.sql.Date;

/**
 *
 * @author rober
 */
public class Registro {
    @SerializedName("idRegistro")
    private int idRegistro;
    
    @SerializedName("idParque")
    private int idParque;
    
    @SerializedName("usuarioCreador")
    private String usuarioCreador;
    
    @SerializedName("fechaCreacion")
    private Date fechaCreacion;

    public Registro() {
    }

    public Registro(int idRegistro, int idParque, String usuarioCreador, Date fechaCreacion) {
        this.idRegistro = idRegistro;
        this.idParque = idParque;
        this.usuarioCreador = usuarioCreador;
        this.fechaCreacion = fechaCreacion;
    }

    public int getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(int idRegistro) {
        this.idRegistro = idRegistro;
    }

    public int getIdParque() {
        return idParque;
    }

    public void setIdParque(int idParque) {
        this.idParque = idParque;
    }

    public String getUsuarioCreador() {
        return usuarioCreador;
    }

    public void setUsuarioCreador(String usuarioCreador) {
        this.usuarioCreador = usuarioCreador;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    
    
}
