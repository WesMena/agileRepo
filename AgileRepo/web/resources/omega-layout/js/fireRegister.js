/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


window.onload = ini;
var frma;


function ini() {
    frma = document.getElementById('frmaReg');
    frma.addEventListener('submit', fireReg, false);
}

function fireReg(event) {
    let email = event.target.txtEmail.value;
    let pass1 = event.target.pass1.value;
    let pass2 = event.target.pass2.value;
    if (email === '') {
        $('#myModal').modal();
    } else {
        if (pass1 !== pass2) {
            $('#myModal').modal();
        } else {
            firebase.auth().createUserWithEmailAndPassword(email, pass1).then(function (result) {
                //Limpiar valores
                 event.target.txtEmail.value ='';
                 event.target.pass1.value='';
                 event.target.pass2.value='';
                $('#myModalOk').modal();
                console.log(result);
                return true;
            }).catch(function (error) {
                $('#myModal').modal();
                console.log(error)
            });
        }
    }

}