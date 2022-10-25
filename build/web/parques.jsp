<%-- 
    Document   : parques
    Created on : 21 oct. 2022, 14:10:41
    Author     : Katherine
--%>

<%@page import="com.dao.ParqueDAO"%>
<%@page import="com.models.Parque"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ParqueDAO daoParque = new ParqueDAO();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Registro</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
        <script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
        <script type="text/javascript" src="assets/parque.js"></script>
        <script type="text/javascript" src="assets/logout.js"></script>
    </head>
    <body class="masthead" style="background-image: url('https://images2.alphacoders.com/894/894456.jpg');">


        <br>
        <br>
        <main class="mt-5 mb-3">
            <div class="container py-3 bg-light text-dark opacity-100 ">


                <div class="">
                    <!-- -menu -->
                    <header>
                        <jsp:include page="WEB-INF/recursos/header.jsp">
                            <jsp:param name="tipo" value="Administrador"/>
                        </jsp:include>
                    </header>


                    <br>
                    <h4 align="center">PARQUES</h4>
                    <br>&nbsp;
                    <button id="btnNuevo" class="btn btn-outline-light"
                            data-bs-toggle="modal" data-bs-target="#exampleModal" value="Nuevo Registro ">
                        <img src="assets/agregar-archivo.png"/>
                    </button>
                    <hr>
                    <div class="row">
                        <table class="table-responsive table table-hover table-sm mt-4 " id="tabla">
                            <thead>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Pais</th>
                            <th>Estado</th>
                            <th>Ciudad</th>
                            <th>Direccion</th>
                            <th>ACCION</th>
                            </thead>

                            <tbody>
                                <%
                                    List<Parque> listaParque = daoParque.getAllParques();
                                    for (Parque pa : listaParque) {
                                %>
                                <tr>
                                    <td><%=pa.getIdParque()%></td>
                                    <td><%=pa.getNombre()%></td>
                                    <td><%=pa.getPais()%></td>
                                    <td><%=pa.getEstado()%></td>
                                    <td><%=pa.getCiudad()%></td>
                                    <td><%=pa.getDireccion()%></td>

                                    <td>
                                        <a class="btn btn-outline-light " type="submit" href="javascript:cargar(<%=pa.getIdParque()%>,'<%=pa.getNombre()%>','<%=pa.getPais()%>','<%=pa.getEstado()%>','<%=pa.getCiudad()%>','<%=pa.getDireccion()%>')"><img src="assets/editar.png"/></a>
                                        <a class="btn btn-outline-light " type="submit" href="javascript:eliminar(<%=pa.getIdParque()%>)"><img src="assets/eliminar.png"/></a>
                                    </td>
                                </tr>
                                <%}%>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </main>
    </body>
</html>

<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Agregar Parques</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form id="form1" >
                <div class="modal-body">
                    <input type="text" id="txtNombre" name="txtNombre"
                           placeholder="Nombre del parque" required class="form-control"><br>
                    <input type="text" id="txtPais" name="txtPais"
                           placeholder="País" required class="form-control"><br>
                    <input type="text" id="txtEstado" name="txtEstado"
                           placeholder="Estado" required class="form-control"><br>
                    <input type="text" id="txtCiudad" name="txtCiudad"
                           placeholder="Ciudad" required class="form-control"><br>
                    <input type="text" id="txtDireccion" name="txtDireccion"
                           placeholder="Direccion" required class="form-control"><br>

                </div>
                <div class="modal-footer">
                    <button type="button" id="btnCerrarModal" class="btn btn-secondary btn-sm" data-bs-dismiss="modal">Close</button>
                    <button type=" submit" id="btnGuardar" name="method" class="btn btn-primary btn-sm"
                            value="Guardar"><i class="bi bi-save"></i> Guardar</button>

                </div>
            </form>
        </div>
    </div>
</div>

<div class="modal fade" id="ModalModificar" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Modificar Parques</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form id="form2">
                <div class="modal-body">
                    <input type="text" id="txtID" name="txtID"
                           placeholder="" required class="form-control" readonly><br>
                    <input type="text" id="txtNombre1" name="txtNombre"
                           placeholder="Nombre del parque" required class="form-control"><br>
                    <input type="text" id="txtPais1" name="txtPais"
                           placeholder="País" required class="form-control"><br>
                    <input type="text" id="txtEstado1" name="txtEstado"
                           placeholder="Estado" required class="form-control"><br>
                    <input type="text" id="txtCiudad1" name="txtCiudad"
                           placeholder="Ciudad" required class="form-control"><br>
                    <input type="text" id="txtDireccion1" name="txtDireccion1"
                           placeholder="Direccion" required class="form-control"><br>

                </div>
                <div class="modal-footer">
                    <button type="button" id="btnCerrarModal1" class="btn btn-secondary btn-sm" data-bs-dismiss="modal">Close</button>
                    <input type="submit" id="btnModificar" name="btnModificar" class="btn btn-warning btn-sm"
                           value="Modificar">
                </div>
            </form>
        </div>
    </div>
</div>
