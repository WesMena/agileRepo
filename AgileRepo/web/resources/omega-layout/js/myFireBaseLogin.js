/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
window.onload = ini;

let btnLogGoogle;
var googleProvider;
var frmaLogin;

async function ini() {
    //Asociando evento submit al form
    frmaLogin = document.getElementById('frmaLogin');

    frmaLogin.addEventListener('submit', signInWithEmailAndPassword, false);
    //Listener de sesion activa
    firebase.auth().onAuthStateChanged(function (user) {
        if (user) {
            console.log(user);
            //Pasar al landing
            //Seteando input escondido para enviar UID al bean
            if (user.displayName) {
                let resp = googleLogin([{name: 'googleResponse', value: user.uid}, {name: 'googleDisplayName', value: user.displayName}]);
            } else {
                let resp = googleLogin([{name: 'googleResponse', value: user.uid}, {name: 'googleDisplayName', value: user.email}]);
            }

            window.location.assign('faces/dashboard.xhtml');
        } else {
            //Nada
        }
    });


}

function stateChanged(response, callback) {

}

function signInWithEmailAndPassword(event) {
    //Valores de los campos asociados al form
    var usuario = event.target.txtEmail.value;
    var contra = event.target.txtPassword.value;
    //Boton escondido para pasar el UID a un bean


    if (usuario === '' || contra === '') {
        //Dialogo de bootstrap disparado con JQuery
        $("#myModal").modal();
    } else {
        //Llamada al SDK de Firebase
        firebase.auth().signInWithEmailAndPassword(usuario, contra)
                .then(function (result) {
                    //En caso de logueo correcto llevar al landing

                    //window.location.assign('faces/dashboard.xhtml');
                })
                .catch(function (error) {
                    //Dialogo de bootstrap disparado con JQuery
                    $("#myModal").modal();
                });
    }
}

function googleLogIn(event) {
    //Llamado al SDK de firebase
    firebase.auth().signInWithPopup(new firebase.auth.GoogleAuthProvider()).then(function (result) {
        //Todo bien
        //window.location.assign('faces/dashboard.xhtml');
    }).catch(function (error) {
        console.log(error);
    });
}


//Actualmente en desuso
function resetPassword(event, emailID) {

    event.preventDefault();
    let em = document.getElementById(emailID);
    var auth = firebase.auth();
    if (em.value === '') {
        PF('wdialogError').show();
    } else {
        auth.sendPasswordResetEmail(em.value).then(function () {
            //Modal de correo enviado
            alert("Correo enviado");
        }).catch(function (error) {
            alert("Error,intente de nuevo");
        });
    }
}


