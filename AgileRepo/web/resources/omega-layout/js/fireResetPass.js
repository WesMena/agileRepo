/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

window.onload = ini;

var frmaReset;

function ini() {
    frmaReset = document.getElementById('frmaX');

    frmaReset.addEventListener('submit', resetPassword, false);
}



function resetPassword(event) {
    let em = event.target.txtEmail.value;
    var auth = firebase.auth();
    if (em === '') {
        $("#myModal").modal();
    } else {
        auth.sendPasswordResetEmail(em).then(function () {
            //Modal de correo enviado
            alert("Correo enviado");
             window.location.assign('faces/login.xhtml');
        }).catch(function (error) {
            $("#myModal").modal();
        });
    }
}

