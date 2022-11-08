/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

async function cargar(idParque, nombre, pais, estado, ciudad, direccion) {
    $('#ModalModificar').modal('show');
    $("#txtID").val(idParque).prop('readonly', true);
    $("#txtNombre1").val(nombre);
    $("#txtPais1").val(pais);
    $("#txtEstado1").val(estado);
    $("#txtCiudad1").val(ciudad);
    $("#txtDireccion1").val(direccion);
}


$(document).ready(function () {

    $('#btnNuevo').click(function () {
        $('#form1')[0].reset();
    });

    $('#form1').submit(e => {
        e.preventDefault();
        $.ajax({
            url: 'ParqueController',
            type: 'POST',
            data: {method: "InsertarParque", nombre: $('#txtNombre').val(), pais: $('#txtPais').val(), estado: $('#txtEstado').val(),
                ciudad: $('#txtCiudad').val(), direccion: $('#txtDireccion').val()}
        }).done((resp) => {
            Swal.fire({
                icon: 'success',
                title: 'Guardado Correctamente',
                text: `La informacion ha sido guardada correctamente`
            }).then(function () {
                $('#form1')[0].reset();
                location.reload();
            })
        }).fail((error) => {
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Ocurrio un error inesperado, intentalo de nuevo mas tarde',
            })
        })
    })

    $('#form2').submit(e => {
        e.preventDefault();
        Swal.fire({
            icon: 'question',
            title: 'Confirmacion',
            text: "Esta a punto de modificar a un parque, esta accion es irreversible",
            showDenyButton: true,
            confirmButtonText: 'Modificar',
            denyButtonText: `Cancelar`,
        }).then(async (result) => {
            if (result.isConfirmed) {
                $.ajax({
                    url: 'ParqueController',
                    type: 'POST',
                    data: {method: "ModificarParque", nombre1: $('#txtNombre1').val(), pais1: $('#txtPais1').val(), estado1: $('#txtEstado1').val(),
                        ciudad1: $('#txtCiudad1').val(), direccion1: $('#txtDireccion1').val(), id1: $('#txtID').val()}
                }).done((resp) => {
                    Swal.fire({
                        icon: 'success',
                        title: 'Guardado Correctamente',
                        text: `La informacion ha sido guardada correctamente`
                    }).then(function () {
                        $('#form1')[0].reset();
                        location.reload();
                    })
                }).fail((error) => {
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: 'Ocurrio un error inesperado, intentalo de nuevo mas tarde',
                    });
                });
            }
        });
    });



});