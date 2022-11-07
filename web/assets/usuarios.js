async function cargar(username, empleado, rol) {
    $("#txtUsername1").val(username).prop('readonly', true);
    $("#selectRol1").val(rol);
    $('#ModalEditar').modal('show');
}

async function activar(user) {
    Swal.fire({
        title: '¿Esta seguro que desea Activar el Usuario?',
        showDenyButton: true,
        confirmButtonText: 'Activar',
        denyButtonText: `Cancelar`,
        icon: 'question'
    }).then(async (result) => {
        if (result.isConfirmed) {
            await $.ajax({
                url: 'UsuarioController',
                type: 'post',
                data: {method: 'activateUser', username: user}
            }).done(function (resp) {
                if (resp === null) {
                    Swal.fire({
                        icon: 'warning',
                        title: 'Alerta',
                        text: 'No se pudo Activar el Empleado, intentelo nuevamente',
                    });
                } else {
                    Swal.fire({
                        icon: 'success',
                        title: 'Exito',
                        text: 'Usuario activado Correctamente'
                    }).then(function () {
                        location.reload();
                    });

                }
            }).fail(function () {
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'Error al Cargar',
                });
            });
        } else if (result.isDenied) {
            Swal.fire('Usuario No Desactivado', '', 'info');
        }
    });
}
async function desactivar(user) {
    Swal.fire({
        title: '¿Esta seguro que desea Desactivar el Usuario?',
        showDenyButton: true,
        confirmButtonText: 'Desactivar',
        denyButtonText: `Cancelar`,
        icon: 'question'
    }).then(async (result) => {
        if (result.isConfirmed) {
            await $.ajax({
                url: 'UsuarioController',
                type: 'post',
                data: {method: 'deactivateUser', username: user}
            }).done(function (resp) {
                if (resp === null) {
                    Swal.fire({
                        icon: 'warning',
                        title: 'Alerta',
                        text: 'No se pudo desactivar el Empleado, intentelo nuevamente',
                    });
                } else {
                    Swal.fire({
                        icon: 'success',
                        title: 'Exito',
                        text: 'Usuario desactivado Correctamente'
                    }).then(function () {
                        location.reload();
                    });

                }
            }).fail(function () {
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'Error al Cargar',
                });
            });
        } else if (result.isDenied) {
            Swal.fire('Usuario No Desactivado', '', 'info');
        }
    });
}

function cargarSelectEmpleados() {
    $.ajax({
        url: 'EmpleadoController',
        type: 'POST',
        data: {method: "getEmpleadosWAccount"}
    }).done((resp) => {
        if (resp === null) {
            /*Swal.fire({
                icon: 'warning',
                title: 'Advertencia',
                text: 'No hay empleados sin cuenta'
            })*/
            console.log("No Disponibles");
        } else {
            console.log(resp);
            resp.forEach(empleado => {
                const opt = document.createElement("option");
                opt.value = empleado.idEmpleado;
                opt.text = `${empleado.nombre} ${empleado.apellido}`;
                $('#selectEmpleado').append(opt);
            });
        }

    }).fail((error) => {
        console.log(error);
    });
}


$(document).ready(function () {

    cargarSelectEmpleados();
    
    $('#btnNuevo').click(function() {
        $('#frmAgregarUsuario')[0].reset();
    });

    $('#frmAgregarUsuario').submit(e => {
        e.preventDefault();
        $.ajax({
            url: 'UsuarioController',
            type: 'POST',
            data: {method: "InsertarUsuario", username: $('#txtUsername').val(), password: $('#txtPassword').val(), idEmpleado: $('#selectEmpleado').val(),
                rol: $('#selectRol').val()}
        }).done((resp) => {
            Swal.fire({
                icon: 'success',
                title: 'Guardado Correctamente',
                text: 'La informacion ha sido guardada correctamente'
            }).then(function () {
                $('#frmAgregarUsuario')[0].reset();
                location.reload();
            });
        }).fail((error) => {
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Ocurrio un error inesperado, intentalo de nuevo mas tarde',
            });
        });
    });

    $('#frmEditarUsuario').submit(e => {
        e.preventDefault();
        $.ajax({
            url: 'UsuarioController',
            type: 'POST',
            data: {method: "UpdateUser", username: $('#txtUsername1').val(), password: $('#txtPasswordUpdate').val(), rol: $('#selectRol1').val()}
        }).done((resp) => {
            Swal.fire({
                icon: 'success',
                title: 'Guardado Correctamente',
                text: 'La informacion ha sido guardada correctamente'
            }).then(function () {
                $('#frmAgregarUsuario')[0].reset();
                location.reload();
            });
        }).fail((error) => {
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Ocurrio un error inesperado, intentalo de nuevo mas tarde',
            });
        });
    });

    $("#switchPassword").on('change', e => {
        if (e.target.checked) {
            $("#txtPasswordUpdate").prop('disabled', false)
        } else {
            $("#txtPasswordUpdate").prop('disabled', true)
        }
    });

});