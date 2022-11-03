/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.config.Conexion;
import java.sql.SQLException;
import java.util.List;
import com.models.Empleado;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author rober
 */
public class EmpleadoDAO {

    private final Conexion conexion;

    public EmpleadoDAO() throws SQLException, ClassNotFoundException {
        conexion = Conexion.getInstance();
    }

    public List<Empleado> getAllEmpleados() throws SQLException {
        List<Empleado> allEmpleados = new ArrayList<>();
        String query = "select * from empleado";
        try {
            Statement st = conexion.getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Empleado empleado = new Empleado();
                empleado.setIdEmpleado(rs.getInt(1));
                empleado.setIdParque(rs.getInt(2));
                empleado.setNombre(rs.getString(3));
                empleado.setApellido(rs.getString(4));
                empleado.setEdad(rs.getInt(5));
                empleado.setTelefono(rs.getString(6));
                empleado.setDireccion(rs.getString(7));
                allEmpleados.add(empleado);
            }
            return allEmpleados;

        } catch (SQLException e) {
            throw e;
        }
    }

    public List<Empleado> getAllEmpleadosByIdParque(int id) throws SQLException {
        List<Empleado> allEmpleados = new ArrayList<>();
        String query = "select * from empleado where idParque=?";
        try {
            PreparedStatement psmt = conexion.getConnection().prepareStatement(query);
            psmt.setInt(1, id);
            ResultSet rs = psmt.executeQuery(query);

            while (rs.next()) {
                Empleado empleado = new Empleado();
                empleado.setIdEmpleado(rs.getInt(1));
                empleado.setIdParque(rs.getInt(2));
                empleado.setNombre(rs.getString(3));
                empleado.setApellido(rs.getString(4));
                empleado.setEdad(rs.getInt(5));
                empleado.setTelefono(rs.getString(6));
                empleado.setDireccion(rs.getString(7));
                allEmpleados.add(empleado);
            }
            return allEmpleados;

        } catch (SQLException e) {
            throw e;
        }
    }
    
    public List<Empleado> getAllEmpleadosWithoutAccount() throws SQLException {
        List<Empleado> allEmpleados = new ArrayList<>();
        String query = "select * from empleado where idEmpleado not in (select idEmpleado from usuario)";
        try {
            Statement st = conexion.getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Empleado empleado = new Empleado();
                empleado.setIdEmpleado(rs.getInt(1));
                empleado.setIdParque(rs.getInt(2));
                empleado.setNombre(rs.getString(3));
                empleado.setApellido(rs.getString(4));
                empleado.setEdad(rs.getInt(5));
                empleado.setTelefono(rs.getString(6));
                empleado.setDireccion(rs.getString(7));
                allEmpleados.add(empleado);
            }
            return allEmpleados;

        } catch (SQLException e) {
            throw e;
        }
    }

    public Empleado getEmpleadoById(int id) throws SQLException {
        String query = "select * from empleado where idEmpleado=?";
        try {
            PreparedStatement psmt = conexion.getConnection().prepareStatement(query);
            psmt.setInt(1, id);
            ResultSet rs = psmt.executeQuery();
            if (rs.next()) {
                Empleado empleado = new Empleado();
                empleado.setIdEmpleado(rs.getInt(1));
                empleado.setIdParque(rs.getInt(2));
                empleado.setNombre(rs.getString(3));
                empleado.setApellido(rs.getString(4));
                empleado.setEdad(rs.getInt(5));
                empleado.setTelefono(rs.getString(6));
                empleado.setDireccion(rs.getString(7));
                return empleado;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw e;
        }
    }

    public int addEmpleado(Empleado newEmpleado) throws SQLException {
        String query = "insert into empleado(idParque,nombre,apellido,edad,telefono,direccion) "
                + "values(?,?,?,?,?,?)";
        try {
            PreparedStatement psmt = conexion.getConnection().prepareStatement(query);
            psmt.setInt(1, newEmpleado.getIdParque());
            psmt.setString(2, newEmpleado.getNombre());
            psmt.setString(3, newEmpleado.getApellido());
            psmt.setInt(4, newEmpleado.getEdad());
            psmt.setString(5, newEmpleado.getTelefono());
            psmt.setString(6, newEmpleado.getDireccion());
            return psmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    public int updateEmpleado(Empleado empleado) throws SQLException {
        String query = "update empleado set idParque=?, nombre=?, apellido=?, edad=?, telefono=?, direccion=? "
                + "where idEmpleado=?";
        try {
            PreparedStatement psmt = conexion.getConnection().prepareStatement(query);
            psmt.setInt(1, empleado.getIdParque());
            psmt.setString(2, empleado.getNombre());
            psmt.setString(3, empleado.getApellido());
            psmt.setInt(4, empleado.getEdad());
            psmt.setString(5, empleado.getTelefono());
            psmt.setString(6, empleado.getDireccion());
            psmt.setInt(7, empleado.getIdEmpleado());
            return psmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    public int deleteEmpleado(int id) throws SQLException {
        String query = "delete from empleado where idEmpleado=?";
        try {
            PreparedStatement psmt = conexion.getConnection().prepareStatement(query);
            psmt.setInt(1, id);
            return psmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    public int deleteEmpleadosByParqueId(int id) throws SQLException {
        String query = "delete from empleado where idParque=?";
        try {
            PreparedStatement psmt = conexion.getConnection().prepareStatement(query);
            psmt.setInt(1, id);
            return psmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

}
