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
//Control del evento onSubmit del form de registro
function fireReg(event) {
    let email = event.target.txtEmail.value;
    let pass1 = event.target.pass1.value;
    let pass2 = event.target.pass2.value;
    var doClick = document.getElementById('lkRegister');
    if (email === '') {
        $('#myModal').modal();
    } else {
        if (pass1 !== pass2) {
            $('#myModal').modal();
        } else {
            //Deteniendo la recarga de la pagina
            event.preventDefault();
            firebase.auth().createUserWithEmailAndPassword(email, pass1).then(function (result) {
                //Limpiar valores
                //Obtener UID
                document.getElementById('frmaHidden:txtHidden').value = result.user.uid;
                event.target.txtEmail.value = '';
                event.target.pass1.value = '';
                event.target.pass2.value = '';
                //doClick.click();
                if (result.user.displayName) {
                    let resp = googleLogin([{name: 'googleResponse', value: result.user.uid}, {name: 'googleDisplayName', value: result.user.displayName}]);
                } else {
                    let resp = googleLogin([{name: 'googleResponse', value: result.user.uid}, {name: 'googleDisplayName', value: result.user.email}]);
                }
                $('#myModalOk').modal();
                console.log(result);
                //return true;
            }).catch(function (error) {
                $('#myModal').modal();
                console.log(error);
            });
        }
    }

}

//Controlando cierre del modal y pasando al login
$('#myModalOk').on('hidden.bs.modal', function () {
    window.location.assign('faces/login.xhtml');
});