/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 * @author rober
 */
public class Usuario {
    
    @SerializedName("username")
    private String username;
    @SerializedName("pswd")
    private String pswd;
    @SerializedName("idEmpleado")
    private int idEmpleado;
    @SerializedName("rol")
    private String rol;
    @SerializedName("estado")
    private String estado;

    public Usuario() {
    }

    public Usuario(String username, String pswd, int idEmpleado, String rol, String estado) {
        this.username = username;
        this.pswd = pswd;
        this.idEmpleado = idEmpleado;
        this.rol = rol;
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
