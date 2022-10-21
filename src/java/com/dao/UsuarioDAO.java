/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.config.Conexion;
import com.utils.Utils;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import com.models.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author rober
 */
public class UsuarioDAO {

    private final Conexion conexion;

    public UsuarioDAO() throws SQLException, ClassNotFoundException {
        conexion = Conexion.getInstance();
    }

    public Usuario getUsuarioByUsername(String username) throws NoSuchAlgorithmException, SQLException {
        String query = "select username,rol,idEmpleado from usuario where username=?";
        try {
            PreparedStatement psmt = conexion.getConnection().prepareStatement(query);
            psmt.setString(1, username);
            ResultSet rs = psmt.executeQuery();
            if (rs.next()) {
                Usuario us = new Usuario();
                us.setUsername(rs.getString(1));
                us.setRol(rs.getString(2));
                us.setIdEmpleado(rs.getInt(3));
                return us;
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw e;
        }
    }

    public Usuario signIn(String username, String password) throws NoSuchAlgorithmException, SQLException {
        String query = "select username, rol, idEmpleado from usuario where username=? and pswd=? and estado='Activo'";
        try {
            String encryptedPassword = Utils.encrytPassword(password);
            PreparedStatement psmt = conexion.getConnection().prepareStatement(query);
            psmt.setString(1, username);
            psmt.setString(2, encryptedPassword);
            ResultSet rs = psmt.executeQuery();
            if (rs.next()) {
                Usuario us = new Usuario();
                us.setUsername(rs.getString(1));
                us.setRol(rs.getString(2));
                us.setIdEmpleado(rs.getInt(3));
                return us;
            } else {
                return null;
            }

        } catch (NoSuchAlgorithmException | SQLException e) {
            throw e;
        }
    }

    public boolean isUserNew(String username) throws SQLException {
        String query = "select * from usuario where username=?";
        try {
            PreparedStatement psmt = conexion.getConnection().prepareStatement(query);
            psmt.setString(1, username);
            ResultSet rs = psmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw e;
        }
    }

    public int signUp(Usuario newUsuario) throws NoSuchAlgorithmException, SQLException {
        String query = "insert into usuario(username,pswd,idEmpleado,rol,estado) "
                + "values(?,?,?,?,?)";
        try {
            PreparedStatement psmt = conexion.getConnection().prepareStatement(query);
            psmt.setString(1, newUsuario.getUsername());
            psmt.setString(2, Utils.encrytPassword(newUsuario.getPswd()));
            psmt.setInt(3, newUsuario.getIdEmpleado());
            psmt.setString(4, newUsuario.getRol());
            psmt.setString(5, newUsuario.getEstado());
            return psmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }
    
    public int disableUser(String username) throws SQLException{
        String query = "update usuario set estado='Inactivo'  where username=?";
        try {
            PreparedStatement psmt = conexion.getConnection().prepareStatement(query);
            psmt.setString(1, username);
            return psmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }      
    }
    
    public int enableUser(String username) throws SQLException{
        String query = "update usuario set estado='Activo'  where username=?";
        try {
            PreparedStatement psmt = conexion.getConnection().prepareStatement(query);
            psmt.setString(1, username);
            return psmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }      
    }

    public int deleteUser(String username) throws SQLException {
        String query = "delete from usuario where username=?";
        try {
            PreparedStatement psmt = conexion.getConnection().prepareStatement(query);
            psmt.setString(1, username);
            return psmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    public int updatePassword(String username, String password) throws NoSuchAlgorithmException, SQLException {
        String query = "update usuario set pswd=? where username=?";
        try {
            PreparedStatement psmt = conexion.getConnection().prepareStatement(query);
            psmt.setString(1, Utils.encrytPassword(password));
            psmt.setString(2, username);
            return psmt.executeUpdate();
        } catch (NoSuchAlgorithmException | SQLException e) {
            throw e;
        }
    }

}
