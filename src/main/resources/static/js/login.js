// Call the dataTables jQuery plugin
$(document).ready(function() {
    // on ready
});


async function iniciarSesion() {
    let datos = {
    };
    datos.email=$('#txtEmail').val();
    datos.password=$('#txtPassword').val();

    const request = await fetch('api/login', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
    });
    const response = await request.text();

    if(response != 'FAIL'){
        localStorage.email = datos.email;
        localStorage.token = response;
        window.location.href = 'usuarios.html';
    } else {
        alert('Intente nuevamente');
    }
}