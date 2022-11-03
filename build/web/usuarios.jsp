<%-- 
    Document   : usuarios
    Created on : 2 nov. 2022, 17:56:18
    Author     : C3rberus
--%>

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
    </head>
    <body>
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

                <h4 align="center">USUARIOS</h4>
                <br>&nbsp;
                </button>&nbsp;&nbsp;
                <button id="btnNuevo" class="btn btn-success"
                        data-bs-toggle="modal" data-bs-target="#ModalAgregarUsuario" value="Agregar usuario">
                    Agregar usuario
                </button>
                <hr>
                <div class="row">
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
