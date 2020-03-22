/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var userloggued;
var auth;

auth = firebase.auth();
auth.onAuthStateChanged(function (user) {
    console.log('Cambio de  estado');
    if (user) {
        userloggued = user;
        getProfile(userloggued);
    } else {

    }
});


function getProfile(user) {
    if (user.photoURL) {
        //Logueado con PP
        document.getElementById("profileP").style.backgroundImage = ` url(${user.photoURL})`;
    } else {
        //Logueado sin PP

        //document.getElementById("profileP").style.backgroundImage = `url(#{resource['images:smiley.jpg']})`;
    }
    document.getElementById("profileP").style.backgroundImage = ` url(${user.photoURL})`;
    // document.getElementById('profileP').style.backgroundImage = `url(${user.photoURL})`;
    console.log('Fui llamado');
    console.log(` url(${user.photoURL})`);
    var span = document.getElementById('txtUsername');
    //= firebase.auth().currentUser.displayName;
    //document.getElementById('userPhoneNumber').value = user.phoneNumber;
    //document.getElementById('userEmail').value = user.email;
    console.log('Fui llamado');

    while (span.firstChild) {
        span.removeChild(span.firstChild);
    }
    span.appendChild(document.createTextNode(user.displayName));


}