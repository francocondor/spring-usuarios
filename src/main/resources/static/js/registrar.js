// Call the dataTables jQuery plugin
$(document).ready(function() {
    // on ready
});


async function registrarUsuario() {
    let datos = {
    };
    datos.nombre=$('#txtNombre').val();
    datos.apellido=$('#txtApellido').val();
    datos.email=$('#txtEmail').val();
    // datos.telefono=$('#txtNombre').val();
    datos.password=$('#txtPassword').val();

    let repetirPassword = $('#txtRepeatPassword').val();

    if(repetirPassword != datos.password){
        alert('Contraseña diferente');
        return;
    }

    const request = await fetch('api/usuarios', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
    });

    alert('La cuenta fue creada con exito');
    window.location.href = 'login.html';
}