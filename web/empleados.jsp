<%-- 
    Document   : empleados
    Created on : 10-23-2022, 10:12:54 AM
    Author     : jhonn
--%>

<%@page import="com.dao.EmpleadoDAO"%>
<%@page import="com.models.Empleado"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>

<%
    EmpleadoDAO daoEmpleado = new EmpleadoDAO();
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Registro de empleados</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
        <script src="assets/empleado.js"></script>
        <script type="text/javascript" src="assets/logout.js"></script>

        <style>
            body {
                background-image: url('assets/fparque.png');
                background-repeat: no-repeat;
            }
            .container {

            }
        </style>

    </head>
    <body class="masthead" >

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


                <h4 align="center">EMPLEADOS</h4>
                <br>&nbsp;
                <button id="btnNuevo" class="btn btn-outline-light border border-primary shadow-lg p-3 rounded"
                        data-bs-toggle="modal" data-bs-target="#ModalAgregarEmpleado" value="Agregar empleado">
                    <img src="assets/cv.png"/>
                </button>&nbsp;&nbsp;
                <hr>
                <div class="row">
                    <div class="col">
                        <table class="table table-responsive table-hover table-sm mt-4 " id="tabla">
                            <thead>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Apellido</th>
                            <th>Edad</th>
                            <th>Teléfono</th>
                            <th>Direccion</th>
                            <th>Parque</th>
                            <th>ACCION</th>
                            </thead>

                            <tbody>
                                <%
                                    List<Empleado> listaParque = daoEmpleado.getAllEmpleados();
                                    for (Empleado emp : listaParque) {
                                %>
                                <tr>
                                    <td><%=emp.getIdEmpleado()%></td>
                                    <td><%=emp.getNombre()%></td>
                                    <td><%=emp.getApellido()%></td>
                                    <td><%=emp.getEdad()%></td>
                                    <td><%=emp.getTelefono()%></td>
                                    <td><%=emp.getDireccion()%></td>
                                    <td><%=emp.getIdParque()%></td>

                                    <td>
                                        <a class="btn btn-outline-light border border-primary shadow-lg rounded" type="submit" href="javascript:cargar(<%=emp.getIdEmpleado()%>,'<%=emp.getNombre()%>','<%=emp.getApellido()%>','<%=emp.getEdad()%>','<%=emp.getTelefono()%>','<%=emp.getDireccion()%>','<%=emp.getIdParque()%>')"><img src="assets/editar.png"/></a>
                                        <a class="btn btn-outline-light border border-primary shadow-lg rounded" type="submit" href="javascript:eliminar(<%=emp.getIdEmpleado()%>)"><img src="assets/eliminar-usuario.png"/></a>
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
</html>

<div class="modal fade" id="ModalModificarEmpleado" tabindex="-1" aria-labelledby="ModalModificarEmpleadoLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title text-center" id="ModalModificarEmpleadoLabel">Modificar empleados</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form id="frmEmpleadoModificar" >
                <div class="modal-body">
                    <label class="form-label" for="txtIdEmpleado1">Id Empleado</label>
                    <input type="number" id="txtIdEmpleado1" name="idEmpleado"
                           placeholder="ID Empleado" required class="form-control" readonly="" disabled=""><br>

                    <label class="form-label" for="txtNombre1">Nombre Empleado</label>
                    <input type="text" id="txtNombre1" name="txtNombre1"
                           placeholder="Nombre del empleado" required class="form-control"><br>

                    <label class="form-label" for="txtApellido1">Apellido Empleado</label>
                    <input type="text" id="txtApellido1" name="txtApellido1"
                           placeholder="Apellido" required class="form-control"><br>

                    <label class="form-label" for="txtEdad1">Edad Empleado</label>
                    <input type="number" min="0" max="120" title="Ingrese una edad" id="txtEdad1" name="txtEdad1"
                           placeholder="Edad" required class="form-control"><br>

                    <label class="form-label" for="txtTelefono1">Teléfono</label>
                    <input type="text" id="txtTelefono1" pattern="[0-9]{4}-[0-9]{4}" name="txtTelefono1"
                           placeholder="Teléfono" required class="form-control"><br>

                    <label class="form-label" for="txtDireccion1">Direccion Empleado</label>
                    <input type="text" id="txtDireccion1" name="txtDireccion1"
                           placeholder="Direccion" required class="form-control"><br>

                    <label class="form-label" for="selectParque1">Parque Empleado</label>
                    <select id="selectParque1" class="form-select slParks" name="selectParque1" required="">
                        <option value="" selected>Seleccione Parque</option>
                    </select>

                </div>
                <div class="modal-footer">
                    <button type="button" id="btnCerrarModal" class="btn btn-outline-light" data-bs-dismiss="modal">
                        <img src="assets/cerrar-sesion.png"/>
                    </button>
                    <button type=" submit" id="btnGuardar" name="method" class="btn btn-outline-light"
                            value="Modificar"><img src="assets/editar.png"/></button>

                </div>
            </form>
        </div>
    </div>
</div>

<div class="modal fade" id="ModalAgregarEmpleado" tabindex="-1" aria-labelledby="ModalAgregarEmpleadoLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="ModalAgregarEmpleadoLabel">Agregar Empleado</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form id="frmEmpleadoAgregar">
                <div class="modal-body">
                    <label class="form-label" for="txtNombre">Nombre Empleado</label>
                    <input type="text" id="txtNombre" name="txtNombre"
                           placeholder="Nombre del empleado" required class="form-control"><br>

                    <label class="form-label" for="txtApellido">Apellido Empleado</label>
                    <input type="text" id="txtApellido" name="txtApellido"
                           placeholder="Apellido" required class="form-control"><br>

                    <label class="form-label" for="txtEdad">Edad Empleado</label>
                    <input type="number" min="0" max="120" title="Ingrese una edad" id="txtEdad" name="txtEdad"
                           placeholder="Edad" required class="form-control"><br>

                    <label class="form-label" for="txtTelefono">Teléfono Empleado</label>
                    <input type="text" id="txtTelefono" pattern="[0-9]{4}-[0-9]{4}" name="txtTelefono"
                           placeholder="Teléfono" required class="form-control"><br>

                    <label class="form-label" for="txtDireccion">Direccion Empleado</label>
                    <input type="text" id="txtDireccion" name="txtDireccion"
                           placeholder="Direccion" required class="form-control"><br>

                    <label class="form-label" for="selectParque">Parque Empleado</label>
                    <select id="selectParque" class="form-select slParks" name="selectParque" required="">
                        <option value="" selected>Seleccione Parque</option>
                    </select>
                </div>
                <div class="modal-footer">
                    <button type="button" id="btnCerrarModalAgregarEmpleado" class="btn btn-outline-light" data-bs-dismiss="modal">
                        <img src="assets/cerrar-sesion.png"/>
                    </button>
                    <button type="submit" id="btnModificarAgregarEmpleado" name="btnModificarAgregarEmpleado" class="btn btn-outline-light"
                            value="Guardar">
                        <img src="assets/disquete.png"/>
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
