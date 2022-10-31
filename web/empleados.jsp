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
        <title>Registro de empleados</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
        <script src="assets/empleado.js"></script>
        
        <style>
            body {
                background-image: url('https://images2.alphacoders.com/894/894456.jpg');
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
        
        <main class="mt-5 mb-3">
            <div class="container py-3 bg-light text-dark opacity-100" style="--bs-bg-opacity: .85;">
                <header>
                    <jsp:include page="WEB-INF/recursos/header.jsp">
                        <jsp:param name="tipo" value="Administrador"/>
                    </jsp:include>
                </header>

                <h4 align="center">EMPLEADOS</h4>
                <br>&nbsp;
                <button id="btnNuevo" class="btn btn-success"
                        data-bs-toggle="modal" data-bs-target="#ModalAgregar" value="Agregar empleado" onclick="cargarSelect();">
                    Agregar empleado
                </button>&nbsp;&nbsp;
                <button id="btnNuevo" class="btn btn-success"
                        data-bs-toggle="modal" data-bs-target="#ModalAgregarUsuario" value="Agregar usuario">
                    Agregar usuario
                </button>
                <hr>
                <div class="row">
                    <table class="table-responsive table table-hover table-sm mt-4 " id="tabla">
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
                                    <a class="btn btn-outline-light " type="submit" href="javascript:cargar(<%=emp.getIdEmpleado()%>,'<%=emp.getNombre()%>','<%=emp.getApellido()%>','<%=emp.getEdad()%>','<%=emp.getTelefono()%>','<%=emp.getDireccion()%>','<%=emp.getIdParque()%>')"><img src="assets/editar-usuario.png"/></a>
                                    <a class="btn btn-outline-light " type="submit" href="javascript:eliminar(<%=emp.getIdEmpleado()%>)"><img src="assets/eliminar-usuario.png"/></a>
                                </td>
                            </tr>
                            <%}%>
                        </tbody>
                    </table>
                </div>
            </div>
        </main>
        <%
            } else {
                response.sendError(response.SC_PROXY_AUTHENTICATION_REQUIRED, "Credenciales Incorrectas");
            }
        %>
    </body>
</html>

<div class="modal fade" id="ModalModificar" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title text-center" id="exampleModalLabel">Modificar empleados</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form id="form1" >
                <div class="modal-body">
                    <input type="text" id="txtIdEmpleado1" name="txtIdEmpleado1"
                           placeholder="" required class="form-control" readonly="true"><br>
                    <input type="text" id="txtNombre1" name="txtNombre1"
                           placeholder="Nombre del empleado" required class="form-control"><br>
                    <input type="text" id="txtApellido1" name="txtApellido1"
                           placeholder="Apellido" required class="form-control"><br>
                    <input type="text" id="txtEdad1" name="txtEdad1"
                           placeholder="Edad" required class="form-control"><br>
                    <input type="text" id="txtTelefono1" name="txtTelefono1"
                           placeholder="Teléfono" required class="form-control"><br>
                    <input type="text" id="txtDireccion1" name="txtDireccion1"
                           placeholder="Direccion" required class="form-control"><br>
                    <!-- pendiente -->
                    <select id="selectParque" name="selectParque">
                        <option value="0">Seleccione empleado</option>
                        <option value="1">Empleado 1</option>
                        <option value="2">Empleado 2</option>
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

<div class="modal fade" id="ModalAgregar" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Agregar empleados</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form id="form">
                <div class="modal-body">
                    <input type="text" id="txtNombre" name="txtNombre"
                           placeholder="Nombre del empleado" required class="form-control"><br>
                    <input type="text" id="txtApellido" name="txtApellido"
                           placeholder="Apellido" required class="form-control"><br>
                    <input type="text" id="txtEdad" name="txtEdad"
                           placeholder="Edad" required class="form-control"><br>
                    <input type="text" id="txtTelefono" name="txtTelefono"
                           placeholder="Teléfono" required class="form-control"><br>
                    <input type="text" id="txtDireccion" name="txtDireccion"
                           placeholder="Direccion" required class="form-control"><br>
                    <!-- pendiente -->
                    <select id="selectParque" name="selectParque">
                        <option value="0">Seleccione empleado</option>
                        <option value="1">Empleado 1</option>
                        <option value="2">Empleado 2</option>
                    </select>
                </div>
                <div class="modal-footer">
                    <button type="button" id="btnCerrarModal" class="btn btn-outline-light" data-bs-dismiss="modal">
                        <img src="assets/cerrar-sesion.png"/>
                    </button>
                    <button type="submit" id="btnModificar" name="btnModificar" class="btn btn-outline-light"
                            value="Guardar">
                        <img src="assets/disquete.png"/>
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="modal fade" id="ModalAgregarUsuario" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Agregar usuarios</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form id="form">
                <div class="modal-body">
                    <input type="text" id="txtUsuario" name="txtUsuario"
                           placeholder="Ingrese el usuario" required class="form-control"><br>
                    <input type="text" id="txtApellido" name="txtApellido"
                           placeholder="Apellido" required class="form-control"><br>
                    <input type="text" id="txtEdad" name="txtEdad"
                           placeholder="Edad" required class="form-control"><br>
                    <input type="text" id="txtTelefono" name="txtTelefono"
                           placeholder="Teléfono" required class="form-control"><br>
                    <input type="text" id="txtDireccion" name="txtDireccion"
                           placeholder="Direccion" required class="form-control"><br>
                    <!-- pendiente -->
                    <select id="selectParque" name="selectParque">
                        <option value="0">Seleccione empleado</option>
                        <option value="1">Empleado 1</option>
                        <option value="2">Empleado 2</option>
                    </select>
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