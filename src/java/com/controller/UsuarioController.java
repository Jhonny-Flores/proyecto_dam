package com.controller;

import com.dao.UsuarioDAO;
import com.google.gson.Gson;
import com.models.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author rober
 */
@WebServlet(name = "UsuarioController", urlPatterns = "/UsuarioController")
public class UsuarioController extends HttpServlet {

    private UsuarioDAO usuarioDao;
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
                this.usuarioDao = new UsuarioDAO();
                Gson gsonConverter = new Gson();
                switch (request.getParameter("method")) {
                    case "signIn":
                        Usuario us;
                        us = this.usuarioDao.signIn(request.getParameter("username"), request.getParameter("password"));
                        if (!Objects.isNull(us)) {
                            Cookie visitanteN = new Cookie("logeado", gsonConverter.toJson(us));
                            response.addCookie(visitanteN);
                            out.print(gsonConverter.toJson(us));
                        } else {
                            out.print(gsonConverter.toJson(null));
                        }
                        break;

                    case "checkUser":
                        out.print(new Gson().toJson(this.usuarioDao.isUserNew(request.getParameter("username"))));
                        break;

                    case "logOut":
                        Cookie killMyCookie = new Cookie("logeado", null);
                        killMyCookie.setMaxAge(0);
                        response.addCookie(killMyCookie);
                        out.print(true);
                        break;

                    default:
                        throw new AssertionError();
                }
            } catch (ClassNotFoundException | NoSuchAlgorithmException | SQLException e) {
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
