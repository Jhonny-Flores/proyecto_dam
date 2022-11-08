<%-- 
    Document   : usuarios
    Created on : 2 nov. 2022, 17:56:18
    Author     : C3rberus
--%>

<%@page import="java.util.List"%>
<%@page import="com.models.Usuario"%>
<%@page import="com.dao.UsuarioDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    UsuarioDAO daoUsuario = new UsuarioDAO();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Registro de empleados</title>
        <link rel="apple-touch-icon" sizes="180x180" href="assets/favicon/apple-touch-icon.png">
        <link rel="icon" type="image/png" sizes="32x32" href="assets/favicon/favicon-32x32.png">
        <link rel="icon" type="image/png" sizes="16x16" href="assets/favicon/favicon-16x16.png">
        <link rel="manifest" href="assets/favicon/site.webmanifest">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
        <script src="assets/usuarios.js"></script>
        <script type="text/javascript" src="assets/logout.js"></script>
    </head>
    <body class="masthead" style="background-image: url('assets/f2.png');">
        <%
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Expires", "0");
            boolean logeado = false;
            Cookie[] cookies = request.getCookies();
            String valores = "";
            //Verificar si esta logeado
            if (cookies != null) {
                for (Cookie c : cookies) {
                    if (c.getName().equals("logeado") && c.getValue().contains("rol") && c.getValue().contains("Administrador")) {
                        System.out.println(c.getValue());
                        //Si existe la cookie, el visitante ya esta logeado
                        valores = c.getValue();
                        logeado = true;
                        break;
                    }
                }
            }
            if (logeado) {
        %>
        <div class="container p-2">
            <header>
                <jsp:include page="WEB-INF/recursos/header.jsp">
                    <jsp:param name="tipo" value="Administrador"/>
                </jsp:include>
            </header>
            <br>
            <div class="container py-3 bg-light text-dark opacity-100" style="--bs-bg-opacity: .85;">

                <h4 align="center">USUARIOS</h4>
                <br>&nbsp;
                </button>&nbsp;&nbsp;
                <button id="btnNuevo" class="btn btn-outline-light border border-success shadow-lg p-3 rounded"
                        data-bs-toggle="modal" data-bs-target="#ModalAgregarUsuario" value="Agregar usuario">
                    <img src="assets/useradd.png"/>
                </button>
                <hr>
                <div class="row">
                    <div class="col">
                        <table class="table table-responsive table-hover table-sm mt-4 " id="tabla">
                            <thead>
                            <th>Username</th>
                            <th>ID Empleado</th>
                            <th>ROL</th>
                            <th>Estado</th>
                            <th>ACCION</th>
                            </thead>

                            <tbody>
                                <%
                                    List<Usuario> listaUsuarios = daoUsuario.getAllUsuarios();
                                    for (Usuario us : listaUsuarios) {
                                %>
                                <tr>
                                    <td><%=us.getUsername()%></td>
                                    <td><%=us.getIdEmpleado()%></td>
                                    <td><%=us.getRol()%></td>
                                    <td><%=us.getEstado()%></td>

                                    <td>
                                        <a class="btn btn-outline-light border border-success shadow-lg rounded " type="submit" href="javascript:cargar('<%=us.getUsername()%>','<%=us.getIdEmpleado()%>','<%=us.getRol()%>','<%=us.getEstado()%>')"><img src="assets/editar.png"/></a>
                                        <% if (us.getEstado().equals("Activo")) {
                                        %>
                                        <a class="btn btn-outline-light border border-success shadow-lg rounded " type="submit" href="javascript:desactivar('<%=us.getUsername()%>')"><img src="assets/apagar.png"/></a>
                                        <% } else {
                                        %>
                                        <a class="btn btn-outline-light border border-success shadow-lg rounded" type="submit" href="javascript:activar('<%=us.getUsername()%>')"><img src="assets/encender.png"/></a>
                                        <%
                                            }
                                        %>
                                    </td>
                                </tr>
                                <%}%>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <%
            } else {
                response.sendError(response.SC_PROXY_AUTHENTICATION_REQUIRED, "Credenciales Incorrectas");
            }
        %>
    </body>
    <div class="modal fade" id="ModalAgregarUsuario" tabindex="-1" aria-labelledby="ModalAgregarUsuarioLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="ModalAgregarUsuarioLabel">Agregar Usuario</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form id="frmAgregarUsuario">
                    <div class="modal-body">
                        <div class="mb-3">
                            <label for="txtUsername" class="form-label">Username</label>
                            <input type="text" class="form-control" id="txtUsername" name="username" required>
                        </div>
                        <div class="mb-3">
                            <label for="txtPassword" class="form-label">Password</label>
                            <input type="password" class="form-control" id="txtPassword" name="password" required>
                        </div>
                        <div class="mb-3">
                            <label for="selectEmpleado" class="form-label">Empleado</label>
                            <select id="selectEmpleado" class="form-select" name="idEmpleado" required>
                                <option value="" selected="">Seleccione un Empleado</option>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="selectRol" class="form-label">Rol</label>
                            <select id="selectRol" class="form-select" name="rol" required>
                                <option value="" selected="">Seleccione un Rol</option>
                                <option value="Administrador">Administrador</option>
                                <option value="Estadistica">Estadistica</option>
                                <option value="Reporte">Reporte</option>
                                <option value="Registro">Registro</option>
                            </select>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" id="btnCerrarModal" class="btn btn-outline-light" data-bs-dismiss="modal">
                            <img src="assets/cerrar-sesion.png"/>
                        </button>
                        <button type="submit" id="btnModificar" name="btnModificar" class="btn btn-outline-light"
                                value="Modificar">
                            <img src="assets/disquete.png"/>
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <div class="modal fade" id="ModalEditar" tabindex="-1" aria-labelledby="ModalEditarLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="ModalEditarLabel">Agregar Usuario</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form id="frmEditarUsuario">
                    <div class="modal-body">
                        <div class="mb-3">
                            <label for="txtUsername1" class="form-label">Username</label>
                            <input type="text" class="form-control" id="txtUsername1" name="username" readonly="" disabled="">
                        </div>
                        <div class="form-check form-switch">
                            <input class="form-check-input" data-toggle="toggle" type="checkbox" role="switch"
                                   id="switchPassword" readonly>
                            <label class="form-check-label" for="switchPassword">Actualizar contraseña</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input disabled placeholder="Contraseña" id="txtPasswordUpdate" name="pswd" required
                                   class="form-control" type="password"/>
                            <label for="txtPasswordUpdate">Contraseña</label>
                        </div>
                        <div class="mb-3">
                            <label for="selectRol1" class="form-label">Rol</label>
                            <select id="selectRol1" class="form-select" name="rol" required>
                                <option value="" selected="">Seleccione un Rol</option>
                                <option value="Administrador">Administrador</option>
                                <option value="Estadistica">Estadistica</option>
                                <option value="Reporte">Reporte</option>
                                <option value="Registro">Registro</option>
                            </select>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" id="btnCerrarModal" class="btn btn-outline-light" data-bs-dismiss="modal">
                            <img src="assets/cerrar-sesion.png"/>
                        </button>
                        <button type="submit" id="btnModificar" name="btnModificar" class="btn btn-outline-light"
                                value="Modificar">
                            <img src="assets/editar.png"/>
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>

</html>
