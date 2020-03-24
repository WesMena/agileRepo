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
        document.getElementById("profileP").style.backgroundImage = "url('https://image.flaticon.com/icons/svg/660/660611.svg')";
    }
    var span = document.getElementById('txtUsername');


    while (span.firstChild) {
        span.removeChild(span.firstChild);
    }
    if (user.displayName) {
        span.appendChild(document.createTextNode(user.displayName));
    } else {
        span.appendChild(document.createTextNode(user.email));
    }



}