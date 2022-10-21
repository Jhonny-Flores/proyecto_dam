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
public class Empleado {
    
    @SerializedName("idEmpleado")
    private int idEmpleado;
    
    @SerializedName("idParque")
    private int idParque;
    
    @SerializedName("nombre")
    private String nombre;
    
    @SerializedName("apellido")
    private String apellido;
    
    @SerializedName("edad")
    private int edad;
    
    @SerializedName("telefono")
    private String telefono;
    
    @SerializedName("direccion")
    private String direccion;

    public Empleado() {
    }

    public Empleado(int idEmpleado, int idParque, String nombre, String apellido, int edad, String telefono, String direccion) {
        this.idEmpleado = idEmpleado;
        this.idParque = idParque;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getIdParque() {
        return idParque;
    }

    public void setIdParque(int idParque) {
        this.idParque = idParque;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    
}
