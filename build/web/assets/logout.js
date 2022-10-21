$(document).ready(function () {

    $('#frmLogout').submit(e => {
        e.preventDefault();
        const formData = new FormData(e.target);
        $.ajax({
            url: 'UsuarioController',
            type: 'post',
            data: {method: "logOut"}
        }).done(function () {
            window.location.replace("index.jsp");
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

