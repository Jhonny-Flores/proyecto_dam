<%-- 
    Document   : reportes
    Created on : 17 oct. 2022, 11:54:16
    Author     : C3rberus
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login</title>
        <link rel="apple-touch-icon" sizes="180x180" href="assets/favicon/apple-touch-icon.png">
        <link rel="icon" type="image/png" sizes="32x32" href="assets/favicon/favicon-32x32.png">
        <link rel="icon" type="image/png" sizes="16x16" href="assets/favicon/favicon-16x16.png">
        <link rel="manifest" href="assets/favicon/site.webmanifest">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
        <script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
        <script type="text/javascript" src="assets/logout.js"></script>
        <title>Incio</title>
    </head>
    <body>
        <%
            boolean logeado = false;
            Cookie[] cookies = request.getCookies();
            String valores = "";
            //Verificar si esta logeado
            if (cookies != null) {
                for (Cookie c : cookies) {
                    if (c.getName().equals("logeado") && c.getValue().contains("rol") && c.getValue().contains("Reporte")) {
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
        <header>
            <jsp:include page="WEB-INF/recursos/header.jsp">
                <jsp:param name="tipo" value="Reporte"/>
            </jsp:include>
        </header>
        <main>
            <div class="container mt-5">
                <div class="row mt-2 justify-content-center">
                    <div class="col-md-8 shadow-lg rounded p-3 mb-3 bg-body">
                        <h5>Detalles de ingresos en una fecha especifica</h5>
                        <form id="frmReporte" action="RegistroController" method="post" class="row g-3">
                            <div class="col-md-4">
                                <label for="txtFecha1" class="form-label">Fecha</label>
                                <input type="date" class="form-control" id="txtFecha" name="fecha" required>
                            </div>
                            <div class="col-12">
                                <button type="submit" class="btn btn-primary" name="method" value="getReporte">Ver</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </main>
        <footer class="footer mt-auto py-3 bg-light" style="--bs-bg-opacity: .35;">
            <div class="d-flex flex-wrap justify-content-between align-items-center py-3 my-4 border-top container">
                <div class="col-md-4 d-flex align-items-center">
                    <span class="text-muted">&copy; 2022 Company, Inc</span>
                </div>
                <ul class="nav col-md-7 justify-content-end list-unstyled d-flex">
                    <li class="nav-item me-3"> <span class="">ID Sesion: ${cookie.JSESSIONID.value}</span></li>
                </ul>
            </div>
        </footer>
        <%
            } else {
                response.sendError(response.SC_PROXY_AUTHENTICATION_REQUIRED, "Credenciales Incorrectas");
            }
        %>
    </body>
</html>
