<%-- 
    Document   : registro
    Created on : 17 oct. 2022, 11:53:54
    Author     : C3rberus
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <link rel="stylesheet" href="assets/registro.css"/>
        <script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
        <script type="text/javascript" src="assets/registro.js"></script>
        <script type="text/javascript" src="assets/logout.js"></script>
    </head>
    <body class="masthead" style="background-image: url('assets/f3.png');">
        <%
            boolean logeado = false;
            Cookie[] cookies = request.getCookies();
            String valores = "";
            //Verificar si esta logeado
            if (cookies != null) {
                for (Cookie c : cookies) {
                    if (c.getName().equals("logeado") && c.getValue().contains("rol") && c.getValue().contains("Registro")) {
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
                <jsp:param name="tipo" value="Registro"/>
            </jsp:include>
        </header>
        <main class="mt-5 mb-3">
            <div class="container bg-light" style="--bs-bg-opacity: .85;" >
                <h1 class="text-center mx-auto">Formulario de Ingreso de Visitantes</h1>
                <form class="row g-4 mt-2 shadow-lg mx-auto" id="frmRegistros" action="" method="post">
                    <div class="col-12 mx-auto">
                        <div class="row">
                            <div class="col-md-3 col-sm-3 col-lg-2">
                                <img src="assets/montana-rusa.png" class="img-thumbnail img-test" alt="alt"/>
                            </div>
                            <div class="col-md-7 col-sm-7 col-lg-8">
                                <label for="selectParque" class="form-label">Parque de Diversiones</label>
                                <select class="form-select" id="selectParque" name="selectParque" required>
                                    <option value="" disabled="" selected="">Seleccione un Parque</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-12 mx-auto">
                        <div class="row">
                            <div class="col-md-3 col-sm-3 col-lg-2">
                                <img src="assets/calendario.png" class="img-thumbnail img-test" alt="alt"/>
                            </div>
                            <div class="col-md-7 col-sm-7 col-lg-8">
                                <label for="txtFecha" class="form-label">Fecha Datos</label>
                                <input type="date" class="form-control" id="txtFecha" name="txtFecha" required>  
                            </div>
                        </div>
                    </div>
                    <div class="col-12 mx-auto">
                        <div class="row">
                            <div class="col-md-3 col-sm-3 col-lg-2">
                                <img src="assets/1Color.png" class="img-thumbnail img-test" alt="alt"/>
                            </div>
                            <div class="col-md-7 col-sm-7 col-lg-8">
                                <label for="rango1" class="form-label">Primera niñez (0-6 años)</label>
                                <input type="number" step="1" min="0" max="1000" class="form-control" id="rango1" name="rango1" placeholder="Numero Visitantes" required>  
                            </div>
                        </div>
                    </div>
                    <div class="col-12 mx-auto">
                        <div class="row">
                            <div class="col-md-3 col-sm-3 col-lg-2">
                                <img src="assets/2Color.png" class="img-thumbnail img-test" alt="alt"/>
                            </div>
                            <div class="col-md-7 col-sm-7 col-lg-8">
                                <label for="rango2" class="form-label">Segunda niñez (7-12 años)</label>
                                <input type="number" step="1" min="0" max="1000" class="form-control" id="rango2" name="rango2" placeholder="Numero Visitantes" required>  
                            </div>
                        </div>
                    </div>
                    <div class="col-12 mx-auto">
                        <div class="row">
                            <div class="col-md-3 col-sm-3 col-lg-2">
                                <img src="assets/3Color.png" class="img-thumbnail img-test" alt="alt"/>
                            </div>
                            <div class="col-md-7 col-sm-7 col-lg-8">
                                <label for="rango3" class="form-label">Adolescentes (13-18 años)</label>
                                <input type="number" step="1" min="0" max="1000" class="form-control" id="rango3" name="rango3" placeholder="Numero Visitantes" required>  
                            </div>
                        </div>
                    </div>
                    <div class="col-12 mx-auto">
                        <div class="row">
                            <div class="col-md-3 col-sm-3 col-lg-2">
                                <img src="assets/4.png" class="img-thumbnail img-test" alt="alt"/>
                            </div>
                            <div class="col-md-7 col-sm-7 col-lg-8">
                                <label for="rango4" class="form-label">Jóvenes adultos (19-26 años)</label>
                                <input type="number" step="1" min="0" max="1000" class="form-control" id="rango4" name="rango4" placeholder="Numero Visitantes" required>  
                            </div>
                        </div>
                    </div>
                    <div class="col-12 mx-auto">
                        <div class="row">
                            <div class="col-md-3 col-sm-3 col-lg-2">
                                <img src="assets/5.png" class="img-thumbnail img-test" alt="alt"/>
                            </div>
                            <div class="col-md-7 col-sm-7 col-lg-8">
                                <label for="rango5" class="form-label">Adultos (27-59 años)</label>
                                <input type="number" step="1" min="0" max="1000" class="form-control" id="rango5" name="rango5" placeholder="Numero Visitantes" required>  
                            </div>
                        </div>
                    </div>
                    <div class="col-12 mx-auto">
                        <div class="row">
                            <div class="col-md-3 col-sm-3 col-lg-2">
                                <img src="assets/6.png" class="img-thumbnail img-test" alt="alt"/>
                            </div>
                            <div class="col-md-7 col-sm-7 col-lg-8">
                                <label for="rango6" class="form-label">Adultos mayores (60 en adelante)</label>
                                <input type="number" step="1" min="0" max="1000" class="form-control" id="rango6" name="rango6" placeholder="Numero Visitantes" required>  
                            </div>
                        </div>
                    </div>
                    <div class="col-12 mb-3">
                        <button type="submit" name="method" class="btn btn-primary">Enviar</button>
                    </div>
                </form>
            </div>
        </main>
        <%
            } else {
                response.sendError(response.SC_PROXY_AUTHENTICATION_REQUIRED, "Credenciales Incorrectas");
            }
        %>
    </body>
</html>
