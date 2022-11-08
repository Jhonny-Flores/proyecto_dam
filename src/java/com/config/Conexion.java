/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class Name: Conexion Date: 14 oct. 2022 Version: 1.0 Copyright: Free
 *
 * @author Geovanny Martinez (034519)
 */
public class Conexion {

    private static Conexion instance;
    private Connection connection;
    private final String URL = "jdbc:mysql://localhost:3306/dbparques";
    private final String USERNAME = "admin";
    private final String PASSWORD = "contrasenaVergas";
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";

    private Conexion() throws SQLException, ClassNotFoundException {
        try {
            Class.forName(DRIVER);
            setConnection(DriverManager.getConnection(URL, USERNAME, PASSWORD));
        } catch (ClassNotFoundException | SQLException ex) {
            throw ex;
        }
    }

    public static Conexion getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new Conexion();
            System.out.println("Nueva instancia");
        } else if (instance.getConnection().isClosed()) {
            instance = new Conexion();
        }else{
            System.out.println("Ya existe");
        }
        return instance;
    }

    public Connection getConnection(){
        return connection;
    }

    private void setConnection(Connection connection) {
        this.connection = connection;
    }
    
}
