/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.config.Conexion;
import java.sql.SQLException;
import java.util.List;
import com.models.Registro;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author rober
 */
public class RegistroDAO {

    private final Conexion conexion;

    public RegistroDAO() throws SQLException, ClassNotFoundException {
        conexion = Conexion.getInstance();
    }
    
    public boolean checkDate(Date fecha, int idParque) throws SQLException {
        String query = "select * from registro where fechaCreacion = ? and idParque = ?;";
        try {
            PreparedStatement pstm = conexion.getConnection().prepareStatement(query);
            pstm.setDate(1, fecha);
            pstm.setInt(2, idParque);
            ResultSet rs = pstm.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw e;
        }
    }

    public int addRegistro(Registro newRegistro) throws SQLException {
        String query = "insert into registro (idParque,usuarioCreador,fechaCreacion) values (?,?,?)";
        try {
            PreparedStatement psmt = conexion.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            psmt.setInt(1, newRegistro.getIdParque());
            psmt.setString(2, newRegistro.getUsuarioCreador());
            psmt.setDate(3, newRegistro.getFechaCreacion());
            psmt.executeUpdate();
            ResultSet rs = psmt.getGeneratedKeys();
            if (rs.next()) {
                int idRegistro = rs.getInt(1);
                return idRegistro;
            } else {
                return -1;
            }
        } catch (SQLException e) {
            throw e;
        }
    }

    public List<Registro> getAllRegistros() throws SQLException {
        List<Registro> allRegistros = new ArrayList<>();
        String query = "select * from registro";
        try {
            Statement st = conexion.getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Registro registro = new Registro();
                registro.setIdRegistro(rs.getInt(1));
                registro.setIdParque(rs.getInt(2));
                registro.setUsuarioCreador(rs.getString(3));
                registro.setFechaCreacion(rs.getDate(4));
                allRegistros.add(registro);
            }
            return allRegistros;

        } catch (SQLException e) {
            throw e;
        }
    }

    public List<Registro> getAllRegistrosByIdParque(int id) throws SQLException {
        List<Registro> allRegistros = new ArrayList<>();
        String query = "select * from registro where idParque=?";
        try {
            PreparedStatement psmt = conexion.getConnection().prepareStatement(query);
            psmt.setInt(1, id);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                Registro registro = new Registro();
                registro.setIdRegistro(rs.getInt(1));
                registro.setIdParque(rs.getInt(2));
                registro.setUsuarioCreador(rs.getString(3));
                registro.setFechaCreacion(rs.getDate(4));
                allRegistros.add(registro);
            }
            return allRegistros;

        } catch (SQLException e) {
            throw e;
        }
    }

    public List<Registro> getAllRegistrosByParqueAndDate(int id, Date fecha) throws SQLException {
        List<Registro> allRegistros = new ArrayList<>();
        String query = "select * from registro where idParque=? and fechaCreacion=?";
        try {
            PreparedStatement psmt = conexion.getConnection().prepareStatement(query);
            psmt.setInt(1, id);
            psmt.setDate(1, new java.sql.Date(fecha.getTime()));
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                Registro registro = new Registro();
                registro.setIdRegistro(rs.getInt(1));
                registro.setIdParque(rs.getInt(2));
                registro.setUsuarioCreador(rs.getString(3));
                registro.setFechaCreacion(rs.getDate(4));
                allRegistros.add(registro);
            }
            return allRegistros;

        } catch (SQLException e) {
            throw e;
        }
    }

    public List<Registro> getAllRegistrosByDate(Date fecha) throws SQLException {
        List<Registro> allRegistros = new ArrayList<>();
        String query = "select * from registro where fechaCreacion=?";
        try {
            PreparedStatement psmt = conexion.getConnection().prepareStatement(query);
            psmt.setDate(1, new java.sql.Date(fecha.getTime()));
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                Registro registro = new Registro();
                registro.setIdRegistro(rs.getInt(1));
                registro.setIdParque(rs.getInt(2));
                registro.setUsuarioCreador(rs.getString(3));
                registro.setFechaCreacion(rs.getDate(4));
                allRegistros.add(registro);
            }
            return allRegistros;

        } catch (SQLException e) {
            throw e;
        }
    }

    public List<Registro> getAllRegistrosByUsername(String username) throws SQLException {
        List<Registro> allRegistros = new ArrayList<>();
        String query = "select * from registro where usuarioCreador=?";
        try {
            PreparedStatement psmt = conexion.getConnection().prepareStatement(query);
            psmt.setString(1, username);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                Registro registro = new Registro();
                registro.setIdRegistro(rs.getInt(1));
                registro.setIdParque(rs.getInt(2));
                registro.setUsuarioCreador(rs.getString(3));
                registro.setFechaCreacion(rs.getDate(4));
                allRegistros.add(registro);
            }
            return allRegistros;

        } catch (SQLException e) {
            throw e;
        }
    }

    public Registro getRegistroById(int id) throws SQLException {
        String query = "select * from registro where idRegistro=?";
        try {
            PreparedStatement psmt = conexion.getConnection().prepareStatement(query);
            psmt.setInt(1, id);
            ResultSet rs = psmt.executeQuery();
            if (rs.next()) {
                Registro registro = new Registro();
                registro.setIdRegistro(rs.getInt(1));
                registro.setIdParque(rs.getInt(2));
                registro.setUsuarioCreador(rs.getString(3));
                registro.setFechaCreacion(rs.getDate(4));
                return registro;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw e;
        }
    }

    public int deleteRegistro(int id) throws SQLException {
        String query = "delete from registro where idRegistro=?";
        try {
            PreparedStatement psmt = conexion.getConnection().prepareStatement(query);
            psmt.setInt(1, id);
            return psmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

}
