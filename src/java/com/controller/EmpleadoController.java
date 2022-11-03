
package com.controller;

import com.dao.EmpleadoDAO;
import com.google.gson.Gson;
import com.models.Empleado;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jhonn
 */
@WebServlet(name = "EmpleadoController", urlPatterns = "/EmpleadoController")
public class EmpleadoController extends HttpServlet {
    
    private EmpleadoDAO empleadoDAO;
    private final Gson gson = new Gson();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            try {
                this.empleadoDAO = new EmpleadoDAO();
                System.out.println(request.getParameter("method"));
                Gson gsonConverter = new Gson();
                switch (request.getParameter("method")) {
                    case "InsertarEmpleado":
                        Empleado empleado = new Empleado();
                        String nombre = request.getParameter("nombre");
                        String apellido = request.getParameter("apellido");
                        String edad = request.getParameter("edad");
                        String telefono = request.getParameter("telefono");
                        String direccion = request.getParameter("direccion");
                        String idParque = request.getParameter("idParque");

                        empleado.setNombre(nombre);
                        empleado.setApellido(apellido);
                        empleado.setEdad(Integer.parseInt(edad));
                        empleado.setTelefono(telefono);
                        empleado.setDireccion(direccion);
                        empleado.setIdParque(Integer.parseInt(idParque));
                        
                        empleadoDAO.addEmpleado(empleado);

                        if (empleadoDAO != null) {
                            out.print(gsonConverter.toJson(String.format("{\"idEmpleado\": %s}", empleadoDAO)));
                        } else {
                            out.print(gsonConverter.toJson(null));
                        }

                        break;
                    case "ModificarEmpleado":
                        Empleado empleado1 = new Empleado();
                        int idEmpleado1 = Integer.parseInt(request.getParameter("idEmpleado1"));
                        String nombre1 = request.getParameter("nombre1");
                        String apellido1 = request.getParameter("apellido1");
                        String edad1 = request.getParameter("edad1");
                        String telefono1 = request.getParameter("telefono1");
                        String direccion1 = request.getParameter("direccion1");
                        String idParque1 = request.getParameter("idParque1");

                        empleado1.setIdEmpleado(idEmpleado1);
                        empleado1.setNombre(nombre1);
                        empleado1.setApellido(apellido1);
                        empleado1.setEdad(Integer.parseInt(edad1));
                        empleado1.setTelefono(telefono1);
                        empleado1.setDireccion(direccion1);
                        empleado1.setIdParque(Integer.parseInt(idParque1));
                        
                        empleadoDAO.updateEmpleado(empleado1);

                        if (empleadoDAO != null) {
                            out.print(gsonConverter.toJson(String.format("{\"idEmpleado\": %s}", empleadoDAO)));
                        } else {
                            out.print(gsonConverter.toJson(null));
                        }
                        break;
                    case "getEmpleados":
                        List<Empleado> listadoEmpleados = new ArrayList<>();
                        listadoEmpleados = empleadoDAO.getAllEmpleados();
                        if (!listadoEmpleados.isEmpty()) {
                            out.print(gsonConverter.toJson(listadoEmpleados));
                        } else {
                            out.print(gsonConverter.toJson(null));
                        }
                        break;
                    case "getEmpleadosWAccount":
                        List<Empleado> listadoEmpleadosW = new ArrayList<>();
                        listadoEmpleadosW = empleadoDAO.getAllEmpleadosWithoutAccount();
                        if(!listadoEmpleadosW.isEmpty()){
                            out.print(gsonConverter.toJson(listadoEmpleadosW));
                        } else {
                            out.print(gsonConverter.toJson(null));
                        }
                        break;
                    case "eliminarEmpleado":
                        Empleado empleado2 = new Empleado();
                        int idEmpleado = Integer.parseInt(request.getParameter("idEmpleado1"));
                        
                        empleado2.setIdEmpleado(idEmpleado);
                        
                        this.empleadoDAO.deleteEmpleado(idEmpleado);
                        
                        if (empleadoDAO != null) {
                            out.print(gsonConverter.toJson(String.format("{\"idEmpleado\": %s}", empleadoDAO)));
                        } else {
                            out.print(gsonConverter.toJson(null));
                        }
                        break;

                    default:
                        throw new AssertionError();
                }
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("Ocurrio un error");
                response.sendError(500, e.getMessage());
            } finally {
                out.flush();
            }

        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
