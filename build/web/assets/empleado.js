async function cargar(idEmpleado, nombre, apellido, edad, telefono, direccion, idParque) {
    $("#txtIdEmpleado1").val(idEmpleado).prop('readonly', true);
    $("#txtNombre1").val(nombre);
    $("#txtApellido1").val(apellido);
    $("#txtEdad1").val(edad);
    $("#txtTelefono1").val(telefono);
    $("#txtDireccion1").val(direccion);
    $("#selectParque1").val(idParque);
    $('#ModalModificarEmpleado').modal('show');
}

async function eliminar(id) {
    Swal.fire({
        title: '¿Esta seguro que desea eliminar el Empleado?',
        showDenyButton: true,
        confirmButtonText: 'Eliminar',
        denyButtonText: `Cancelar`,
        icon: 'question'
    }).then(async (result) => {
        if (result.isConfirmed) {
            await $.ajax({
                url: 'EmpleadoController',
                type: 'post',
                data: {method: 'eliminarEmpleado', idEmpleado1: id}
            }).done(function (resp) {
                if (resp === null) {
                    Swal.fire({
                        icon: 'warning',
                        title: 'Alerta',
                        text: 'No se pudo eliminar el Empleado, intentelo nuevamente',
                    });
                } else {
                    Swal.fire({
                        icon: 'success',
                        title: 'Exito',
                        text: 'Registro Eliminado Correctamente'
                    }).then(function () {
                        location.reload();
                    });

                }
            }).fail(function () {
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'Error al Cargar el Select',
                });
            });
        } else if (result.isDenied) {
            Swal.fire('Detalle Registro no Eliminado', '', 'info');
        }
    })
}

function cargarSelectParque() {
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
            $('.slParks').append(opt);
        });
    }).fail((error) => {
        console.log(error);
    });
}


$(document).ready(function () {

    cargarSelectParque();

    $('#btnNuevo').click(function () {
        $('#frmEmpleadoAgregar')[0].reset();
    });

    $('#frmEmpleadoAgregar').submit(e => {
        e.preventDefault();
        $.ajax({
            url: 'EmpleadoController',
            type: 'POST',
            data: {method: "InsertarEmpleado", nombre: $('#txtNombre').val(), apellido: $('#txtApellido').val(), edad: $('#txtEdad').val(),
                telefono: $('#txtTelefono').val(), direccion: $('#txtDireccion').val(), idParque: $('#selectParque').val()}
        }).done((resp) => {
            Swal.fire({
                icon: 'success',
                title: 'Guardado Correctamente',
                text: 'La informacion ha sido guardada correctamente'
            }).then(function () {
                $('#frmEmpleadoAgregar')[0].reset();
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

    $('#frmEmpleadoModificar').submit(e => {
        e.preventDefault();
        Swal.fire({
            icon: 'question',
            title: 'Confirmacion',
            text: "Esta a punto de modificar a un empleado, esta accion es irreversible",
            showDenyButton: true,
            confirmButtonText: 'Modificar',
            denyButtonText: `Cancelar`,
        }).then(async (result) => {
            if (result.isConfirmed) {
                await $.ajax({
                    url: 'EmpleadoController',
                    type: 'POST',
                    data: {method: "ModificarEmpleado", nombre1: $('#txtNombre1').val(), apellido1: $('#txtApellido1').val(), edad1: $('#txtEdad1').val(),
                        telefono1: $('#txtTelefono1').val(), direccion1: $('#txtDireccion1').val(), idParque1: $('#selectParque1').val(),
                        idEmpleado1: $('#txtIdEmpleado1').val()}
                }).done((resp) => {
                    Swal.fire({
                        icon: 'success',
                        title: 'Actualizado Correctamente',
                        text: 'La informacion ha sido guardada correctamente'
                    }).then(function () {
                        $('#frmEmpleadoModificar')[0].reset();
                        location.reload();
                    });
                }).fail((error) => {
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: 'Ocurrio un error inesperado, intentalo de nuevo mas tardeFFFFFFFF',
                    });
                });
            }
        })
    });
});