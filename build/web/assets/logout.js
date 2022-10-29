function getCookie(name) {
    var dc = document.cookie;
    var prefix = name + "=";
    var begin = dc.indexOf("; " + prefix);
    if (begin == -1) {
        begin = dc.indexOf(prefix);
        if (begin != 0) return null;
    }
    else
    {
        begin += 2;
        var end = document.cookie.indexOf(";", begin);
        if (end == -1) {
        end = dc.length;
        }
    }
    // because unescape has been deprecated, replaced with decodeURI
    //return unescape(dc.substring(begin + prefix.length, end));
    return decodeURI(dc.substring(begin + prefix.length, end));
} 

$(document).ready(function () {
    
    var myCookie = getCookie("logeado");

    if (myCookie == null) {
        console.log("Hola");
        window.location.replace("index.jsp");
    }


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

