/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.controller;

import com.dao.DetalleRegistroDAO;
import com.google.gson.Gson;
import com.models.DetalleRegistro;
import com.models.Estadistica;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class Name: DetalleRegistroController Date: 20 oct. 2022 Version: 1.0
 * CopyRight: Free
 *
 * @author Geovanny Martinez(034519)
 */
public class DetalleRegistroController extends HttpServlet {

    private DetalleRegistroDAO detalleRegistroDao;
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

                        int[] visitantes = {rango1, rango2, rango3, rango4, rango5, rango6};
                        List<DetalleRegistro> detalles = new ArrayList<>();
                        for (int i = 0; i < visitantes.length; i++) {
                            System.out.println(visitantes[i]);
                            detalles.add(new DetalleRegistro(idRegistro, i + 1, visitantes[i]));
                        }
                        int columnas = this.detalleRegistroDao.addDetallesRegistro(detalles);

                        if (columnas > 0) {
                            out.print(gsonConverter.toJson(true));
                        } else {
                            out.print(gsonConverter.toJson(null));
                        }
                        break;
                    // Get sum(visitors) per park between dates
                    case "getVisitorsSum":
                        int idParque = Integer.parseInt(request.getParameter("idParque"));
                        LocalDate date1 = LocalDate.parse(request.getParameter("fecha1"));
                        LocalDate date2 = LocalDate.parse(request.getParameter("fecha2"));
                        List<Estadistica> valores = new ArrayList<>();
                        valores = detalleRegistroDao.getVisitorsSum(idParque, Date.valueOf(date1), Date.valueOf(date2));
                        if (!valores.isEmpty()) {
                            out.print(gsonConverter.toJson(valores));
                        } else {
                            response.sendError(500, "El parque seleccionado no presenta Informacion");
                            out.print(gsonConverter.toJson("{'error': 'Error al Obtener la Data'}"));
                        }
                        break;
// Get sum(visitors) per park between dates
                    case "getVisitorsPerDay":
                        int idParqueCase2 = Integer.parseInt(request.getParameter("idParqueCase2"));
                        LocalDate date1Case2 = LocalDate.parse(request.getParameter("fecha1Case2"));
                        List<Estadistica> valoresCase2 = new ArrayList<>();
                        valoresCase2 = detalleRegistroDao.getVisitorsPerDay(idParqueCase2, Date.valueOf(date1Case2));
                        if (!valoresCase2.isEmpty()) {
                            out.print(gsonConverter.toJson(valoresCase2));
                        } else {
                            response.sendError(500, "El parque seleccionado no presenta Informacion");
                            out.print(gsonConverter.toJson("{'error': 'Error al Obtener la Data'}"));
                        }
                        break;
                    case "getVisitorsPerAgeAll":
                        List<Estadistica> valoresCase3 = new ArrayList<>();
                        valoresCase2 = detalleRegistroDao.getVisitorsPerAgeAll();
                        if (!valoresCase2.isEmpty()) {
                            out.print(gsonConverter.toJson(valoresCase2));
                        } else {
                            response.sendError(500, "No hay registros disponibles");
                            out.print(gsonConverter.toJson("{'error': 'Error al Obtener la Data'}"));
                        }
                        break;
                    case "sumByPark":
                        List<Estadistica> valoresCase4 = new ArrayList<>();
                        valoresCase2 = detalleRegistroDao.sumByPark();
                        if (!valoresCase2.isEmpty()) {
                            out.print(gsonConverter.toJson(valoresCase2));
                        } else {
                            response.sendError(500, "No hay registros disponibles");
                            out.print(gsonConverter.toJson("{'error': 'Error al Obtener la Data'}"));
                        }
                        break;
                    case "sumAllDays":
                        List<Estadistica> valoresCase5 = new ArrayList<>();
                        valoresCase2 = detalleRegistroDao.sumByDayAllDays();
                        if (!valoresCase2.isEmpty()) {
                            out.print(gsonConverter.toJson(valoresCase2));
                        } else {
                            response.sendError(500, "No hay registros disponibles");
                            out.print(gsonConverter.toJson("{'error': 'Error al Obtener la Data'}"));
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
