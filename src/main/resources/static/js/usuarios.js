// Call the dataTables jQuery plugin
$(document).ready(function() {

  cargarUsuarios();
  $('#tblUsuarios').DataTable();
  actualizarEmailDelUsuario();
});

function actualizarEmailDelUsuario() {
    $('txt-email-usuario').val(localStorage.email);
}

async function cargarUsuarios() {
    const request = await fetch('api/usuarios', {
      method: 'GET',
      headers: getHeaders()
    });
    const usuarios = await request.json();

    console.log(usuarios);

    let tblUsuarios = "";

    for (let us of usuarios){
        let botonEliminar = `
                    <a href="#" onclick="eliminarUsuario(`+us.id+`)" class="btn btn-danger btn-circle btn-sm">
                        <i class="fas fa-trash"></i>
                    </a>`;
        let telfTexto = us.telefono == null ? '-' : us.telefono;
        const user =
            `<tr>
                <td>`+us.id+`</td>
                <td>`+us.nombre+" "+us.apellido+`</td>
                <td>`+us.email+`</td>
                <td>`+telfTexto+`</td>
                <td>`+botonEliminar+`</td>
            </tr>`;
        tblUsuarios += user;
    }



    $('tbody').html(tblUsuarios);
}

async function eliminarUsuario(id) {

    if(!confirm('¿Desea eliminar el usuario?')) {
        return;
    }
    const request = await fetch('api/usuarios/'+id, {
        method: 'DELETE',
        headers: getHeaders()
    });

    location.reload();
}

function getHeaders(){
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': localStorage.token
    }
}