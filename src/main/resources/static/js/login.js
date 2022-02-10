$(document).ready(function() {

});

async function loginUser(){
    let datos = {};

    datos.email = document.querySelector("#txt_email").value;
    datos.password = document.querySelector("#txt_password").value;

        const request = await fetch('api/login', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(datos)
        });
    const users = await request.text();
    if(users != 'FAIL'){
        localStorage.token = users;
        localStorage.email = datos.email;
        window.location.href = 'users.html';
    }else{
        alert("Las credenciales no coinsiden");
        return;
    }
}