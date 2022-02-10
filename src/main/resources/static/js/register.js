$(document).ready(function() {

});

async function registerUser(){
    let datos = {};

    datos.nombre = document.querySelector("#txt_name").value;
    datos.apellido = document.querySelector("#txt_lastname").value;
    datos.email = document.querySelector("#txt_email").value;
    datos.telefono = document.querySelector("#txt_number").value;
    datos.password = document.querySelector("#txt_password").value;

    let repassword = document.querySelector("#txt_repassword").value;

    if(repassword != datos.password){
        alert("passwords aren't the same");
        return;
    }

        const request = await fetch('api/users', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(datos)
        });
        window.location.href = 'users.html';
}