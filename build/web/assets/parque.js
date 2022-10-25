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

function eliminar(idParque){
    Swal.fire({
        title: 'Esta seguro que desea eliminar el producto?',
        showDenyButton: true,
        confirmButtonText: 'Eliminar',
        denyButtonText: `Cancelar`,
    }).then((result) => {
        if (result.isConfirmed) {
                //codigo
                $.ajax({
                    url:'ParqueController',
                    type:'POST',
                    data:{method:'eliminarParque',idParque:idParque}
                }).done(function(resp){
                    if(resp){
                        Swal.fire({
                            icon: 'success',
                            title: 'Eliminado Correctamente',
                            showConfirmButton: false,
                            timer: 1500
                        })
                        cargarTabla();
                    }else{
                        Swal.fire({
                            icon: 'error',
                            title: 'Oops...',
                            text: 'Error al Eliminar1111',
                        })
                    }
                }).fail(function(){
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: 'Error al Eliminar',
                    })
                });
            } else if (result.isDenied) {
                Swal.fire('Producto no Eliminado', '', 'info')
            }
        });
}


$(document).ready(function () {

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
                text: 'Ocurrio un error inesperado, intentalo de nuevo mas tardeFFFFFFFF',
            })
        })
    })
    
    $('#form2').submit(e => {
        e.preventDefault();
        $.ajax({
            url: 'ParqueController',
            type: 'POST',
            data: {method: "ModificarParque", nombre1: $('#txtNombre1').val(), pais1: $('#txtPais1').val(), estado1: $('#txtEstado1').val(),
                ciudad1: $('#txtCiudad1').val(), direccion1: $('#txtDireccion1').val(), id1:$('#txtID').val()}
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
                text: 'Ocurrio un error inesperado, intentalo de nuevo mas tardeFFFFFFFF',
            })
        })
    })
    
    
    
});