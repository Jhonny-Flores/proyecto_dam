/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
function getUser() {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; logeado=`);
    if (parts.length === 2) {
        const temp = JSON.parse(parts.pop().split(';').shift());
        return JSON.parse(temp);
    }
}

function cargarSelect() {
    $.ajax({
        url: 'ParqueController',
        type: 'POST',
        data: {method: "getParques"}
    }).done((resp) => {
        console.log(resp);
        resp.forEach(parque => {
            const opt = document.createElement("option");
            opt.value = parque.idParque;
            opt.text = parque.nombre;
            $('#selectParque').append(opt);
        })
    }).fail((error) => {
        console.log(error);
    })
}

$(document).ready(function () {
    cargarSelect();

    $('#frmRegistros').submit(e => {
        e.preventDefault();
        $.ajax({
            url: 'RegistroController',
            type: 'POST',
            data: {method: "addRegister", idParque: $('#selectParque').val(), usuarioCreador: getUser().username, fechaCreacion: $('#txtFecha').val()}
        }).done((resp) => {
            let respuesta = JSON.parse(resp);
            if (respuesta?.idRegister) {
                console.log(respuesta);
                $.ajax({
                    url: 'DetalleRegistroController',
                    type: 'POST',
                    data: {method: "insertDetalles", rango1: $('#rango1').val(), rango2: $('#rango2').val(),
                        rango3: $('#rango3').val(), rango4: $('#rango4').val(), rango5: $('#rango5').val(), rango6: $('#rango6').val(), idRegister: respuesta.idRegister}
                }).done((resp) => {
                    Swal.fire({
                        icon: 'success',
                        title: 'Guardado Correctamente',
                        text: `La informacion ha sido guardada correctamente`
                    }).then(function () {
                        $('#frmRegistros')[0].reset();
                    })
                }).fail((error) => {
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: 'Ocurrio un error inesperado, intentalo de nuevo mas tarde',
                    })
                })
            } else {
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'Ocurrio un error inesperado, intentalo de nuevo mas tarde',
                })
            }
        }).fail((error) => {
            console.log(error.statusText);
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: `El servidor dice: ${error.statusText}`,
            });
        })
    })
});
