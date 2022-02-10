// Call the dataTables jQuery plugin
$(document).ready(function() {
  loadUsers();
  $('#users').DataTable();
});

async function loadUsers(){
        const request = await fetch('api/users', {
            method: 'GET',
            headers: getHeaders()
    });
    const users = await request.json();

    let listadoHTML = '';

    for(let usuario of users){
        let buttonDelete = '<a href="#" onclick="deleteUser('+usuario.id+')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';

        let userHTML = '<tr><td>'+usuario.id+'</td><td>'+usuario.nombre+' '+usuario.apellido+'</td><td>'
        +usuario.telefono+'</td><td>'+usuario.email
        +'</td><td>'+buttonDelete+'</td></tr>';

        listadoHTML += userHTML;
    }

    document.querySelector('#users tbody').outerHTML = listadoHTML;
}

function getHeaders(){
    return {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization' : localStorage.token
    };
}

async function deleteUser(id){

    if(confirm('¿Desea eliminar el usuario?')){
        const request = await fetch('api/user/'+id, {
                method: 'DELETE',
                headers: getHeaders()
            });
            alert("User delete successfully");
    }else{
        alert("Delete cancel");
    }

    location.reload();
}