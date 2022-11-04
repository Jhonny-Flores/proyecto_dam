<%-- 
    Document   : estadisticas
    Created on : 17 oct. 2022, 11:54:08
    Author     : C3rberus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Estadisticas</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
        <script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/chart.js@3.9.1/dist/chart.min.js" integrity="sha256-+8RZJua0aEWg+QVVKg4LEzEEm/8RFez5Tb4JBNiV5xA=" crossorigin="anonymous"></script>
        <script type="text/javascript" src="assets/logout.js"></script>
        <link rel="stylesheet" href="assets/graficos.css"/>
    </head>
    <body class="masthead" style="background-image: url('assets/f3.png');">
        <%
            boolean logeado = false;
            Cookie[] cookies = request.getCookies();
            String valores = "";
            //Verificar si esta logeado
            if (cookies != null) {
                for (Cookie c : cookies) {
                    if (c.getName().equals("logeado") && c.getValue().contains("rol") && c.getValue().contains("Estadistica")) {
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
                <jsp:param name="tipo" value="Estadistica"/>
            </jsp:include>
        </header>
        <main class="mt-5 mb-3">
            <div class="container  bg-light" style="--bs-bg-opacity: .90;" >
                <h1>Estadisticas</h1>
                <hr>
                <h2>Graficos Generales</h2>
                <div class="row">
                    <div class="col mt-5">
                        <h3>Grafico de Total Visitantes Todos Los Parques</h3>
                        <canvas class="my-4 w-100" id="sumAllDaysGraphs" width="900" height="380"></canvas>
                    </div>
                </div>
                <div class="row">
                    <div class="col mt-5">
                        <h3>Grafico de Total Visitantes Por Parque</h3>
                        <canvas class="my-4 w-100" id="allVisitorsSumPerPark" width="900" height="380"></canvas>
                    </div>
                </div>
                <div class="row">
                    <div class="col mt-5">
                        <h3>Grafico Visitantes Por Rango (Todos los Parques)</h3>
                        <canvas class="my-4" id="allParksPerAgeGraph" width="400" height="150"></canvas>
                    </div>
                </div>
                <hr>
                <h2>Graficos Parametrizados</h2>
                <div class="row">
                    <div class="col mt-5">
                        <h3>Suma de Visitantes por Dia</h3>
                        <form class="row g-3" id="visitorsSum">
                            <div class="col-md-4">
                                <label for="selectParque" class="form-label">Parque</label>
                                <select class="form-select selectParques" id="selectParque1" name="selectParque2" required>
                                    <option value="" disabled="" selected="">Seleccione un Parque</option>
                                </select>
                            </div>
                            <div class="col-md-4">
                                <label for="fecha1" class="form-label">Fecha1</label>
                                <input type="date" class="form-control" id="fecha1" name="fecha1" required="">
                            </div>
                            <div class="col-md-4">
                                <label for="fecha1" class="form-label">Fecha1</label>
                                <input type="date" class="form-control" id="fecha2" name="fecha2" required="">
                            </div>
                            <div class="col-12">
                                <button type="submit" class="btn btn-primary" name="getVisitorsSum">Ver Grafico</button>
                            </div>
                        </form>
                        <canvas class="my-4 w-100" id="visitorsSumGraph" width="900" height="380"></canvas>
                    </div>
                </div>

                <div class="row mb-4">
                    <div class="col mt-5">
                        <h3>Visitantes por Edad por Parque en un dia</h3>
                        <form class="row g-3" id="visitorsPerAge">
                            <div class="col-md-4">
                                <label for="selectParque" class="form-label">Parque</label>
                                <select class="form-select selectParques" id="selectParque2" name="selectParque2" required>
                                    <option value="" disabled="" selected="">Seleccione un Parque</option>
                                </select>
                            </div>
                            <div class="col-md-4">
                                <label for="fecha1" class="form-label">Fecha Visitantes</label>
                                <input type="date" class="form-control" id="fechaVisitantes" name="fechaVisitantes" required="">
                            </div>
                            <div class="col-12">
                                <button type="submit" class="btn btn-primary" name="getVisitorsPerDay">Ver Grafico</button>
                            </div>
                        </form>
                        <canvas class="mt-4 w-100" id="otro" width="400" height="150"></canvas>
                    </div>
                </div>

            </div>
        </main>
        <script src="https://cdn.jsdelivr.net/npm/feather-icons@4.28.0/dist/feather.min.js" integrity="sha384-uO3SXW5IuS1ZpFPKugNNWqTZRRglnUJK6UAZ/gxOX80nxEkN9NcGZTftn6RzhGWE" crossorigin="anonymous"></script><script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.4/dist/Chart.min.js" integrity="sha384-zNy6FEbO50N+Cg5wap8IKA4M/ZnLJgzc6w2NqACZaK0u0FXfOWRRJOnQtpZun8ha" crossorigin="anonymous"></script>
        <script type="text/javascript" src="assets/estadisticas.js"></script>
        <%
            } else {
                response.sendError(response.SC_PROXY_AUTHENTICATION_REQUIRED, "Credenciales Incorrectas");
            }
        %>
    </body>
</html>
