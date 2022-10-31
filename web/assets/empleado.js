
async function cargar(idEmpleado, nombre, apellido, edad, telefono, direccion, idParque) {
    $('#ModalModificar').modal('show');
    $("#txtIdEmpleado1").val(idEmpleado).prop('readonly', true);
    $("#txtNombre1").val(nombre);
    $("#txtApellido1").val(apellido);
    $("#txtEdad1").val(edad);
    $("#txtTelefono1").val(telefono);
    $("#txtDireccion1").val(direccion);
    $("#selectParque").val(idParque);
}


$(document).ready(function () {

    $('#form').submit(e => {
        e.preventDefault();
        $.ajax({
            url: 'EmpleadoController',
            type: 'POST',
            data: {method: "InsertarEmpleado", nombre: $('#txtNombre').val(), apellido: $('#txtApellido').val(), edad: $('#txtEdad').val(),
                telefono: $('#txtTelefono').val(), direccion: $('#txtDireccion').val(), idParque: $('#selectParque')}
        }).done((resp) => {
            Swal.fire({
                icon: 'success',
                title: 'Guardado Correctamente',
                text: 'La informacion ha sido guardada correctamente'
            }).then(function () {
                $('#form')[0].reset();
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
    
    $('#form1').submit(e => {
        e.preventDefault();
        $.ajax({
            url: 'EmpleadoController',
            type: 'POST',
            data: {method: "ModificarEmpleado", idEmpleado1: $('#txtIdEmpleado1'), nombre1: $('#txtNombre1').val(), apellido: $('#txtApellido').val(), edad: $('#txtEdad').val(),
                telefono: $('#txtTelefono').val(), direccion: $('#txtDireccion').val(), idParque: $('#selectParque')}
        }).done((resp) => {
            Swal.fire({
                icon: 'success',
                title: 'Actualizado Correctamente',
                text: 'La informacion ha sido Actualizada correctamente'
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