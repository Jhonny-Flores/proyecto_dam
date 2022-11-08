<%-- 
    Document   : index
    Created on : 12 oct. 2022, 09:14:07
    Author     : Katherine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
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
        <link rel="stylesheet" href="assets/login.css">
        <script type="text/javascript" src="assets/login.js"></script>
    </head>
    <body>
        <div class="masthead" style="background-image: url('https://images3.alphacoders.com/698/698366.jpg');">
            <div class="container">
                <div class="d-flex align-items-center" style="min-height: 100vh">
                    <div class="box w-100">
                        <div class="row justify-content-center">
                            <div class="col-md-7 col-lg-6 col-sm-7">
                                <div class="border border-3 border-dark bg-light opacity-75 rounded">
                                    <div class="border border-5 shadow-lg p-3 bg-body rounded">
                                        <div class="p-4">
                                            <div class="d-flex">
                                                <div class="w-100">
                                                    <h3 class="mb-4 text-center text-dark">Iniciar Sesión</h3>
                                                </div>
                                            </div>
                                            <form method="post" id="formLogin" action="UsuarioController">
                                                <div class="form-group">
                                                    <label class="control-label fw-bold text-dark" for="txtUsername">
                                                        Username
                                                    </label>
                                                    <div class="input-group">
                                                        <div class="input-group-prepend ">
                                                            <div class="input-group-text border border-2 border-secondary">
                                                                <i class="bi bi-person-circle "></i>
                                                            </div>
                                                        </div>
                                                        <input type="text" class="form-control border border-2 border-secondary" id="txtUsername" name="username" title="Ingrese un Email" required>
                                                    </div>
                                                </div>
                                                <div class="form-group mt-2">
                                                    <label class="control-label fw-bold text-dark" for="txtPassword">
                                                        Contraseña
                                                    </label>
                                                    <div class="input-group">
                                                        <div class="input-group-prepend">
                                                            <div class="input-group-text border border-2 border-secondary">
                                                                <i class="bi bi-fingerprint"></i>
                                                            </div>
                                                        </div>
                                                        <input id="txtPassword" name="password" type="password" class="form-control border border-2 border-secondary" title="Ingrese una contraseña" required>
                                                    </div>
                                                </div>
                                                <div class="form-group mt-4">
                                                    <button type="submit" name="method" value="signIn" class="form-control btn btn-info rounded submit px-3">Ingresar</button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </body>
</html>