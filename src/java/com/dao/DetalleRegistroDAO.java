/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.config.Conexion;
import java.sql.SQLException;
import java.util.List;
import com.models.DetalleRegistro;
import com.models.Estadistica;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author rober
 */
public class DetalleRegistroDAO {

    private final Conexion conexion;

    public DetalleRegistroDAO() throws SQLException, ClassNotFoundException {
        conexion = Conexion.getInstance();
    }

    public int addDetallesRegistro(List<DetalleRegistro> detalles) throws SQLException {
        conexion.getConnection().setAutoCommit(false);
        String query = "insert into detalleregistro (idRegistro,idClasificacion,totalVisitantes) values (?,?,?)";
        try {
            PreparedStatement psmt = conexion.getConnection().prepareStatement(query);
            Iterator<DetalleRegistro> it = detalles.iterator();
            while (it.hasNext()) {
                DetalleRegistro dr = it.next();
                psmt.setInt(1, dr.getIdRegistro());
                psmt.setInt(2, dr.getIdClasificacion());
                psmt.setInt(3, dr.getTotalVisitantes());
                psmt.addBatch();

            }
            int[] numUpdates = psmt.executeBatch();
            for (int i = 0; i < numUpdates.length; i++) {
                if (numUpdates[i] == -2) {
                    System.out.println("Execution " + i
                            + ": unknown number of rows updated");
                } else {
                    System.out.println("Execution " + i
                            + "successful: " + numUpdates[i] + " rows updated");
                }
            }
            conexion.getConnection().commit();
            conexion.getConnection().setAutoCommit(true);
            return numUpdates.length;
        } catch (SQLException e) {
            throw e;
        }
    }

    public List<Estadistica> getVisitorsSum(int idParque, Date fecha1, Date fecha2) throws SQLException {
        List<Estadistica> visitantesParque = new ArrayList<>();
        String query = "select r.fechaCreacion, sum(dr.totalVisitantes) from detalleregistro dr inner join registro r on dr.idRegistro = r.idRegistro "
                + "where r.idParque = ? and (r.fechaCreacion between ? and ?) group by dr.idRegistro order by r.fechaCreacion;";
        try {
            PreparedStatement psmt = conexion.getConnection().prepareStatement(query);
            psmt.setInt(1, idParque);
            psmt.setDate(2, fecha1);
            psmt.setDate(3, fecha2);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                Estadistica valor = new Estadistica(rs.getDate(1), rs.getInt(2));
                visitantesParque.add(valor);
            }
            return visitantesParque;
        } catch (SQLException e) {
            throw e;
        }
    }

    public List<Estadistica> getVisitorsPerDay(int idParque, Date fecha1) throws SQLException {
        List<Estadistica> visitantesParque = new ArrayList<>();
        String query = "select c.nombre, dr.totalVisitantes from detalleregistro dr inner join registro r on dr.idRegistro = r.idRegistro "
                + "inner join clasificacion c on dr.idClasificacion = c.idClasificacion "
                + "where r.idParque = ? and r.fechaCreacion = ? group by dr.idClasificacion;";
        try {
            PreparedStatement psmt = conexion.getConnection().prepareStatement(query);
            psmt.setInt(1, idParque);
            psmt.setDate(2, fecha1);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                Estadistica valor = new Estadistica(rs.getString(1), rs.getInt(2));
                visitantesParque.add(valor);
            }
            return visitantesParque;
        } catch (SQLException e) {
            throw e;
        }
    }

    public List<Estadistica> getVisitorsPerAgeAll() throws SQLException {
        List<Estadistica> visitantesParque = new ArrayList<>();
        String query = "select c.nombre, sum(dr.totalVisitantes) from detalleregistro dr inner join registro r on dr.idRegistro = r.idRegistro "
                + "inner join clasificacion c on dr.idClasificacion = c.idClasificacion group by dr.idClasificacion;";
        try {
            PreparedStatement psmt = conexion.getConnection().prepareStatement(query);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                Estadistica valor = new Estadistica(rs.getString(1), rs.getInt(2));
                visitantesParque.add(valor);
            }
            return visitantesParque;
        } catch (SQLException e) {
            throw e;
        }
    }
    public List<Estadistica> sumByPark() throws SQLException {
        List<Estadistica> visitantesParque = new ArrayList<>();
        String query = "select p.nombre, sum(dr.totalVisitantes) from detalleregistro dr inner join registro r on dr.idRegistro = r.idRegistro " +
                       "inner join parque p on r.idParque = p.idParque group by r.idParque;";
        try {
            PreparedStatement psmt = conexion.getConnection().prepareStatement(query);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                Estadistica valor = new Estadistica(rs.getString(1), rs.getInt(2));
                visitantesParque.add(valor);
            }
            return visitantesParque;
        } catch (SQLException e) {
            throw e;
        }
    }
    
    public List<Estadistica> sumByDayAllDays() throws SQLException {
        List<Estadistica> visitantesParque = new ArrayList<>();
        String query = "select r.fechaCreacion, sum(dr.totalVisitantes) from detalleregistro dr inner join registro r on dr.idRegistro = r.idRegistro "
                + "group by r.fechaCreacion order by r.fechaCreacion;";
        try {
            PreparedStatement psmt = conexion.getConnection().prepareStatement(query);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                Estadistica valor = new Estadistica(rs.getDate(1), rs.getInt(2));
                visitantesParque.add(valor);
            }
            return visitantesParque;
        } catch (SQLException e) {
            throw e;
        }
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

    public int updateDetalleRegistro(DetalleRegistro detail) throws SQLException {
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
