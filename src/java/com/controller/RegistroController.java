/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.controller;

import com.config.Conexion;
import com.dao.RegistroDAO;
import com.google.gson.Gson;
import com.models.Registro;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * Class Name: RegistroController Date: 20 oct. 2022 Version: 1.0 CopyRight:
 * Free
 *
 * @author Geovanny Martinez(034519)
 */
@WebServlet(name = "RegistroController", urlPatterns = "/RegistroController")
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
            try {
                this.registroDAO = new RegistroDAO();
                Gson gsonConverter = new Gson();
                switch (request.getParameter("method")) {
                    case "addRegister":
                        PrintWriter out = response.getWriter();
                        Registro register = new Registro();
                        String fechaCreacion = request.getParameter("fechaCreacion");
                        int idParque = Integer.parseInt(request.getParameter("idParque"));
                        LocalDate date = LocalDate.parse(fechaCreacion);
                        boolean existe = this.registroDAO.checkDate(Date.valueOf(date), idParque);
                        if (existe) {
                            out.print(gsonConverter.toJson(null));
                            response.sendError(500, "La fecha Ingresada ya posee un registro, solo es posible agregar un registro por dia por parque");
                        } else {
                            register.setIdParque(idParque);
                            register.setUsuarioCreador(request.getParameter("usuarioCreador"));
                            register.setFechaCreacion(Date.valueOf(date));
                            int newRegister = this.registroDAO.addRegistro(register);
                            if (newRegister != -1) {
                                out.print(gsonConverter.toJson(String.format("{\"idRegister\": %s}", newRegister)));
                            } else {
                                out.print(gsonConverter.toJson(null));
                            }
                        }
                        break;

                    case "getReporte":
                        System.out.println("Entro");
                        Date fecha = Date.valueOf(request.getParameter("fecha"));
                        generarReporte(response, fecha);
                        break;
                    default:
                        throw new AssertionError();
                }
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("Ocurrio un error");
                response.sendError(500, e.getMessage());
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

    public void generarReporte(HttpServletResponse response, Date fecha) throws IOException {
        try {
            InputStream reporte = this.getServletConfig().getServletContext().getResourceAsStream("/reportes/reporteDetalles.jasper");
            if (reporte != null) {
                JasperReport reporteJasper = (JasperReport) JRLoader.loadObject(reporte);
                Map<String, Object> mapDs = new HashMap<>();
                mapDs.put("fecha", fecha);
                response.setContentType("application/pdf");
                response.addHeader("Content-disposition", "inline; filename=ReporteDetalles.pdf");
                JasperPrint jasperPrint = JasperFillManager.fillReport(reporteJasper, mapDs,Conexion.getInstance().getConnection());
                JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
            } else {
                response.sendError(400, "No se encontro el reporte");
            }
        } catch (IOException | ClassNotFoundException | SQLException | JRException e) {
            response.sendError(500, e.getMessage());
        }

    }
}
