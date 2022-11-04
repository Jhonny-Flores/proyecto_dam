<%-- 
    Document   : header
    Created on : 17 oct. 2022, 10:46:57
    Author     : C3rberus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String tipo = request.getParameter("tipo");
%>
<nav class="navbar navbar-expand-lg bg-light" style="--bs-bg-opacity: .85;" >
    <div class="container">
        <a class="navbar-brand" href="#">Administrador Parques</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <%
                    if (tipo.equals("Administrador")) {

                %>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="empleados.jsp">Empleados</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="usuarios.jsp">Usuarios</a>
                </li>
                <li>
                    <a class="nav-link active" aria-current="page" href="parques.jsp">Parques</a>
                </li>
                <li>
                    <a class="nav-link active" aria-current="page" href="admin.jsp">Home</a>
                </li>
                <%                } else if (tipo.equals("Registro")) {
                %>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="registro.jsp">Registro Visitantes</a>
                </li>
                <%
                } else if (tipo.equals("Estadistica")) {
                %>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="estadisticas.jsp">Estadisticas Visitantes</a>
                </li>
                <%
                } else if (tipo.equals("Reportes")) {
                %>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="reportes.jsp">Reportes Visitantes</a>
                </li>
                <%
                    }
                %>
            </ul>
            <form class="d-flex" method="post" id="frmLogout" action="UsuarioController">
                <button class="btn btn-outline-danger" type="submit" name="method" value="logOut">Salir <i class="bi bi-box-arrow-left"></i></button>
            </form>
        </div>
    </div>
</nav>
