/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.config.Conexion;
import java.sql.SQLException;
import java.util.List;
import com.models.DetalleRegistro;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
/**
 *
 * @author rober
 */
public class DetalleRegistroDAO {

    private final Conexion conexion;

    public DetalleRegistroDAO() throws SQLException, ClassNotFoundException {
        conexion = Conexion.getInstance();
    }

    public List<DetalleRegistro> getAllDetallesRegistros() throws SQLException {
        List<DetalleRegistro> allDetallesRegistros = new ArrayList<>();
        String query = "select * from detalleregistro";
        try {
            Statement st = conexion.getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                DetalleRegistro detail = new DetalleRegistro();
                detail.setIdDetalle(rs.getInt(1));
                detail.setIdRegistro(rs.getInt(2));
                detail.setIdClasificacion(rs.getInt(3));
                detail.setTotalVisitantes(rs.getInt(4));
            }
            return allDetallesRegistros;

        } catch (SQLException e) {
            throw e;
        }
    }
    
     public List<DetalleRegistro> getAllDetallesRegistrosByIdRegistro(int id) throws SQLException {
        List<DetalleRegistro> allDetallesRegistros = new ArrayList<>();
        String query = "select * from detalleregistro where idRegistro=?";
        try {
            PreparedStatement psmt = conexion.getConnection().prepareStatement(query);
            psmt.setInt(1, id);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                DetalleRegistro detail = new DetalleRegistro();
                detail.setIdDetalle(rs.getInt(1));
                detail.setIdRegistro(rs.getInt(2));
                detail.setIdClasificacion(rs.getInt(3));
                detail.setTotalVisitantes(rs.getInt(4));
            }
            return allDetallesRegistros;

        } catch (SQLException e) {
            throw e;
        }
    }
     
    public List<DetalleRegistro> getAllDetallesRegistrosByIdClasificacion(int id) throws SQLException {
        List<DetalleRegistro> allDetallesRegistros = new ArrayList<>();
        String query = "select * from detalleregistro where idClasificacion=?";
        try {
            PreparedStatement psmt = conexion.getConnection().prepareStatement(query);
            psmt.setInt(1, id);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                DetalleRegistro detail = new DetalleRegistro();
                detail.setIdDetalle(rs.getInt(1));
                detail.setIdRegistro(rs.getInt(2));
                detail.setIdClasificacion(rs.getInt(3));
                detail.setTotalVisitantes(rs.getInt(4));
            }
            return allDetallesRegistros;

        } catch (SQLException e) {
            throw e;
        }
    }  
    
    public List<DetalleRegistro> getAllDetallesRegistrosByRegistroAndClasificacion(int idCla, int idRe) throws SQLException {
        List<DetalleRegistro> allDetallesRegistros = new ArrayList<>();
        String query = "select * from detalleregistro where idClasificacion=? and idRegistro=?";
        try {
            PreparedStatement psmt = conexion.getConnection().prepareStatement(query);
            psmt.setInt(1, idCla);
            psmt.setInt(2, idRe);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                DetalleRegistro detail = new DetalleRegistro();
                detail.setIdDetalle(rs.getInt(1));
                detail.setIdRegistro(rs.getInt(2));
                detail.setIdClasificacion(rs.getInt(3));
                detail.setTotalVisitantes(rs.getInt(4));
            }
            return allDetallesRegistros;

        } catch (SQLException e) {
            throw e;
        }
    }  
    
    public int deleteDetalleRegistro(int id) throws SQLException {
        String query = "delete from detalleregistro where idDetalle=?";
        try {
            PreparedStatement psmt = conexion.getConnection().prepareStatement(query);
            psmt.setInt(1, id);
            return psmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }
    
    public int updateDetalleRegistro(DetalleRegistro detail) throws SQLException{
        String query = "update detalleregistro set idRegistro=?,idClasificacion=1,totalVisitantes=? where idDetalle=?";
        try {
            PreparedStatement psmt = conexion.getConnection().prepareStatement(query);
            psmt.setInt(1, detail.getIdRegistro());
            psmt.setInt(2, detail.getIdClasificacion());
            psmt.setInt(3, detail.getTotalVisitantes());
            psmt.setInt(4, detail.getIdRegistro());
            return psmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }        
    }

}
