/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package com.controller;

import com.dao.DetalleRegistroDAO;
import com.google.gson.Gson;
import com.models.DetalleRegistro;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class  Name: DetalleRegistroController
 * Date: 20 oct. 2022
 * Version: 1.0
 * CopyRight: Free
 * @author Geovanny Martinez(034519)
 */
public class DetalleRegistroController extends HttpServlet {
    private DetalleRegistroDAO detalleRegistroDao;
    private final Gson gson = new Gson();
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            try {
                this.detalleRegistroDao = new DetalleRegistroDAO();
                Gson gsonConverter = new Gson();
                switch (request.getParameter("method")) {
                    case "insertDetalles":
                        int idRegistro = Integer.parseInt(request.getParameter("idRegister"));
                        int rango1 = Integer.parseInt(request.getParameter("rango1"));
                        int rango2 = Integer.parseInt(request.getParameter("rango2"));
                        int rango3 = Integer.parseInt(request.getParameter("rango3"));
                        int rango4 = Integer.parseInt(request.getParameter("rango4"));
                        int rango5 = Integer.parseInt(request.getParameter("rango5"));
                        int rango6 = Integer.parseInt(request.getParameter("rango6"));
                        
                        int[] visitantes = {rango1,rango2,rango3,rango4,rango5,rango6};
                        List<DetalleRegistro> detalles = new ArrayList<>();
                        for (int i = 0; i < visitantes.length; i++) {
                            System.out.println(visitantes[i]);
                            detalles.add(new DetalleRegistro(idRegistro, i+1, visitantes[i]));
                        }
                        int columnas = this.detalleRegistroDao.addDetallesRegistro(detalles);
                        
                        if (columnas>0) {
                            out.print(gsonConverter.toJson(true));
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

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
