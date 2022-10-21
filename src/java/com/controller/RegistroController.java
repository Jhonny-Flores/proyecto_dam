/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.controller;

import com.dao.RegistroDAO;
import com.google.gson.Gson;
import com.models.Registro;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class Name: RegistroController Date: 20 oct. 2022 Version: 1.0 CopyRight:
 * Free
 *
 * @author Geovanny Martinez(034519)
 */
public class RegistroController extends HttpServlet {

    private RegistroDAO registroDAO;
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
                this.registroDAO = new RegistroDAO();
                Gson gsonConverter = new Gson();
                switch (request.getParameter("method")) {
                    case "addRegister":
                        Registro register = new Registro();
                        register.setIdParque(Integer.parseInt(request.getParameter("idParque")));
                        register.setUsuarioCreador(request.getParameter("usuarioCreador"));
                        LocalDate date = LocalDate.parse(request.getParameter("fechaCreacion"));
                        register.setFechaCreacion(Date.valueOf(date));
                        int newRegister = this.registroDAO.addRegistro(register);
                        if (newRegister != -1) {
                            out.print(gsonConverter.toJson(String.format("{\"idRegister\": %s}", newRegister)));
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
