<%-- 
    Document   : admin
    Created on : 17 oct. 2022, 11:01:54
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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
        <script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
        <script type="text/javascript" src="assets/logout.js"></script>
        <title>Incio</title>
    </head>
    <body class="masthead" style="background-image: url('assets/f3.png');">
        <div class="container" >
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
            <header>
                <jsp:include page="WEB-INF/recursos/header.jsp">
                    <jsp:param name="tipo" value="Administrador"/>
                </jsp:include>
            </header>
            <br>

            <div id="carouselExampleSlidesOnly" class="carousel slide" data-bs-ride="carousel">
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img src="https://images7.alphacoders.com/897/897920.jpg" class="d-flex w-100" alt="...">
                    </div>
                    <div class="carousel-item">
                        <img src="https://images2.alphacoders.com/698/698367.jpg" class="d-flex w-100" alt="...">
                    </div>
                    <div class="carousel-item">
                        <img src="https://images.alphacoders.com/219/219371.jpg" class="d-flex w-100" alt="...">
                    </div>
                </div>
            </div>
            <br>
            <footer class="footer mt-auto py-3 bg-light" style="--bs-bg-opacity: .35;">
                <div class="d-flex flex-wrap justify-content-between align-items-center py-3 my-4 border-top container">
                    <div class="col-md-4 d-flex align-items-center">
                        <span class="text-muted">&copy; 2022 Company, Inc</span>
                    </div>
                    <ul class="nav col-md-7 justify-content-end list-unstyled d-flex">
                        <li class="nav-item me-3"><a href="#" class="nav-link active">ID Sesion: ${cookie.JSESSIONID.value} </a></li>
                        <li class="text-dark">HELLO! <%=valores%></li>
                    </ul>
                </div>
            </footer>
            <%
                } else {
                    response.sendError(response.SC_PROXY_AUTHENTICATION_REQUIRED, "Credenciales Incorrectas");
                }
            %>
        </div>


    </body>
</html>