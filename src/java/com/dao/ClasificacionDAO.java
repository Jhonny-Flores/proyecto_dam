/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.config.Conexion;
import java.sql.SQLException;
import java.util.List;
import com.models.Clasificacion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author rober
 */
public class ClasificacionDAO {

    private final Conexion conexion;

    public ClasificacionDAO() throws SQLException, ClassNotFoundException {
        conexion = Conexion.getInstance();
    }

    public List<Clasificacion> getAllClasificaciones() throws SQLException {
        List<Clasificacion> allClasificaciones = new ArrayList<>();
        String query = "select * from clasificacion";
        try {
            Statement st = conexion.getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Clasificacion clasificacion = new Clasificacion();
                clasificacion.setIdClasificacion(rs.getInt(1));
                clasificacion.setNombre(rs.getString(2));
                clasificacion.setRangoInicial(rs.getInt(3));
                clasificacion.setRangoFinal(rs.getInt(4));
                allClasificaciones.add(clasificacion);
            }
            return allClasificaciones;

        } catch (SQLException e) {
            throw e;
        }
    }

    public Clasificacion getClasificacionById(int id) throws SQLException {
        String query = "select * from clasificacion where idClasificacion=?";
        try {
            PreparedStatement psmt = conexion.getConnection().prepareStatement(query);
            psmt.setInt(1, id);
            ResultSet rs = psmt.executeQuery();
            if (rs.next()) {
                Clasificacion clasificacion = new Clasificacion();
                clasificacion.setIdClasificacion(rs.getInt(1));
                clasificacion.setNombre(rs.getString(2));
                clasificacion.setRangoInicial(rs.getInt(3));
                clasificacion.setRangoFinal(rs.getInt(4));
                return clasificacion;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw e;
        }
    }

}
