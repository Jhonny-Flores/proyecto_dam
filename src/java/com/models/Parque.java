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
public class Parque {
    
    @SerializedName("idParque")
    private int idParque;
    
    @SerializedName("nombre")
    private String nombre;
    
    @SerializedName("pais")
    private String pais;
    
    @SerializedName("estado")
    private String estado;
    
    @SerializedName("ciudad")
    private String ciudad;
    
    @SerializedName("direccion")
    private String direccion;

    public Parque() {
    }

    public Parque(int idParque, String nombre, String pais, String estado, String ciudad, String direccion) {
        this.idParque = idParque;
        this.nombre = nombre;
        this.pais = pais;
        this.estado = estado;
        this.ciudad = ciudad;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public int getIdParque() {
        return idParque;
    }

    public void setIdParque(int idParque) {
        this.idParque = idParque;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    
    
}
