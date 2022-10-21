/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.config.Conexion;
import java.sql.SQLException;
import java.util.List;
import com.models.Parque;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author rober
 */
public class ParqueDAO {

    private final Conexion conexion;

    public ParqueDAO() throws SQLException, ClassNotFoundException {
        conexion = Conexion.getInstance();
    }

    public List<Parque> getAllParques() throws SQLException {
        List<Parque> allParques = new ArrayList<>();
        String query = "select * from parque";
        try {
            Statement st = conexion.getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Parque parque = new Parque();
                parque.setIdParque(rs.getInt(1));
                parque.setNombre(rs.getString(2));
                parque.setPais(rs.getString(3));
                parque.setEstado(rs.getString(4));
                parque.setCiudad(rs.getString(5));
                parque.setDireccion(rs.getString(6));
                allParques.add(parque);
            }
            return allParques;

        } catch (SQLException e) {
            throw e;
        }
    }

    public int addParque(Parque newParque) throws SQLException {
        String query = "insert into parque(nombre,pais,estado,ciudad,direccion) "
                + "values(?,?,?,?,?)";
        try {
            PreparedStatement psmt = conexion.getConnection().prepareStatement(query);
            psmt.setString(1, newParque.getNombre());
            psmt.setString(2, newParque.getPais());
            psmt.setString(3, newParque.getEstado());
            psmt.setString(4, newParque.getCiudad());
            psmt.setString(5, newParque.getDireccion());
            return psmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    public Parque getParqueById(int id) throws SQLException {
        String query = "select * from parque where idParque=?";
        try {
            PreparedStatement psmt = conexion.getConnection().prepareStatement(query);
            psmt.setInt(1, id);
            ResultSet rs = psmt.executeQuery();
            if(rs.next()){
                Parque parque = new Parque();
                parque.setIdParque(rs.getInt(1));
                parque.setIdParque(rs.getInt(1));
                parque.setNombre(rs.getString(2));
                parque.setPais(rs.getString(3));
                parque.setEstado(rs.getString(4));
                parque.setCiudad(rs.getString(5));
                parque.setDireccion(rs.getString(6));
                return parque;
            }else{
                return null;
            }
        } catch (SQLException e) {
            throw e;
        }
    }

    public int updateParque(Parque parque) throws SQLException {
        String query = "update parque set nombre=?, pais=?, estado=?, ciudad=?, direccion=? where idParque=?";
        try {
            PreparedStatement psmt = conexion.getConnection().prepareStatement(query);
            psmt.setString(1, parque.getNombre());
            psmt.setString(2, parque.getPais());
            psmt.setString(3, parque.getEstado());
            psmt.setString(4, parque.getCiudad());
            psmt.setString(5, parque.getDireccion());
            psmt.setInt(6, parque.getIdParque());
            return psmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    public int deleteParque(int id) throws SQLException {
        String query = "delete from parque where idParque=?";
        try {
            PreparedStatement psmt = conexion.getConnection().prepareStatement(query);
            psmt.setInt(1, id);
            return psmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }
}
