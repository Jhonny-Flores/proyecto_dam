/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.controller;

import com.dao.ParqueDAO;
import com.dao.UsuarioDAO;
import com.google.gson.Gson;
import com.models.Parque;
import com.models.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class Name: ParqueController Date: 14 oct. 2022 Version: 1.0 CopyRight: Free
 *
 * @author Geovanny Martinez(034519)
 */
@WebServlet(name = "ParqueController", urlPatterns = "/ParqueController")
public class ParqueController extends HttpServlet {

    private ParqueDAO parqueDao;
    private final Gson gson = new Gson();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
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
                this.parqueDao = new ParqueDAO();
                System.out.println(request.getParameter("method"));
                Gson gsonConverter = new Gson();
                switch (request.getParameter("method")) {
                    case "InsertarParque":
                        Parque parque = new Parque();
                        String nombre = request.getParameter("nombre");
                        String pais = request.getParameter("pais");
                        String estado = request.getParameter("estado");
                        String ciudad = request.getParameter("ciudad");
                        String direccion = request.getParameter("direccion");

                        parque.setNombre(nombre);
                        parque.setPais(pais);
                        parque.setEstado(estado);
                        parque.setCiudad(ciudad);
                        parque.setDireccion(direccion);
                        
                        parqueDao.addParque(parque);

                        if (parqueDao != null) {
                            out.print(gsonConverter.toJson(String.format("{\"idParque\": %s}", parqueDao)));
                        } else {
                            out.print(gsonConverter.toJson(null));
                        }

                        break;
                    case "ModificarParque":
                        Parque parque2 = new Parque();
                        int idParque = Integer.parseInt(request.getParameter("id1"));
                        String nombre1 = request.getParameter("nombre1");
                        String pais1 = request.getParameter("pais1");
                        String estado1 = request.getParameter("estado1");
                        String ciudad1 = request.getParameter("ciudad1");
                        String direccion1 = request.getParameter("direccion1");
                        
                        parque2.setIdParque(idParque);
                        parque2.setNombre(nombre1);
                        parque2.setPais(pais1);
                        parque2.setEstado(estado1);
                        parque2.setCiudad(ciudad1);
                        parque2.setDireccion(direccion1);

                        this.parqueDao.updateParque(parque2);

                        if (parqueDao != null) {
                            out.print(gsonConverter.toJson(String.format("{\"idParque\": %s}", parqueDao)));
                        } else {
                            out.print(gsonConverter.toJson(null));
                        }
                        break;
                    case "EliminarParque": 
                        break;
                    case "getParques":
                        List<Parque> listadoParques = new ArrayList<>();
                        listadoParques = parqueDao.getAllParques();
                        if (!listadoParques.isEmpty()) {
                            out.print(gsonConverter.toJson(listadoParques));
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
