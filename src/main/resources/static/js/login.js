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
    const response = await request.json();

    console.log(response);
}