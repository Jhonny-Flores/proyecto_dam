$(document).ready(function () {

    $('#formLogin').submit(e => {
        e.preventDefault();
        const formData = new FormData(e.target);
        $.ajax({
            url: 'UsuarioController',
            type: 'post',
            data: {method: "signIn", username: $("#txtUsername").val(), password: $("#txtPassword").val()}
        }).done(function (resp) {
            if (resp === null) {
                Swal.fire({
                    icon: 'warning',
                    title: 'Oops...',
                    text: 'Credenciales Incorrectas o usuario desactivado',
                });
            }else{
                console.log(resp);
                Swal.fire({
                    icon: 'success',
                    title: 'Bienvenido',
                    text: `Bienvenido nuevamente ${resp.username}`
                }).then(function() {
                    if(resp.rol === "Administrador"){
                        window.location.replace("admin.jsp");
                    } else if(resp.rol === "Registro") {
                        window.location.replace("registro.jsp");
                    } else if(resp.rol === "Estadistica") {
                        window.location.replace("estadisticas.jsp");
                    } else if(resp.rol === "Reporte") {
                        window.location.replace("reporte.jsp");
                    }
                });
            }
        }).fail(function (resp) {
            console.log(resp);
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Ocurrio un error inesperado, intentalo de nuevo mas tarde',
            })
        });
    })
});