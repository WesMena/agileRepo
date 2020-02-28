/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

window.onload = inicializar;

let btnIrLogin ;

function inicializar() {
    btnIrLogin= document.getElementById('btnCulo');
    btnIrLogin.addEventListener('click', navLogin, false);
}




function navLogin() {
    window.location.assign('faces/login.xhtml');
}


            //<![CDATA[
            /**
             * Faces Validator
             */


            PrimeFaces.validator['custom.emailValidator'] = {

                pattern: /\S+@\S+/,

                validate: function (element, value) {
                    //use element.data() to access validation metadata, in this case there is none.
                    if (!this.pattern.test(value)) {
                        throw {
                            summary: 'Validation Error',
                            detail: value + '  no es un correo valido.'
                        };
                    }
                }
            };


            PrimeFaces.validator['custom.passwordValidator'] = {

                value1: document.getElementById('frmaReg:pass1').value,
                value2: document.getElementById('frmaReg:pass2').value,

                validate: function (element, value) {
                    //use element.data() to access validation metadata, in this case there is none.
                    if (this.value1 !== this.value2) {
                        throw {
                            summary: 'Validation Error',
                            detail: ' las contrasenias no coinciden.'
                        }
                    }
                }
            };



            PrimeFaces.validator['Password'] = {

                value1: document.getElementById('frmaReg:pass1').value,
                value2: document.getElementById('frmaReg:pass2').value,

                MESSAGE_ID: 'XXXXXXX',

                validate: function (element, value) {
                    var vc = PrimeFaces.util.ValidationContext;

                    if (this.value1 !== this.value2) {
                        var msgStr = element.data('p-email-msg'),
                                msg = msgStr ? {summary: msgStr, detail: msgStr} : vc.getMessage(this.MESSAGE_ID);

                        throw msg;
                    }
                }
            };

            PrimeFaces.validator['Email'] = {

                pattern: /\S+@\S+/,

                MESSAGE_ID: 'org.primefaces.examples.validate.email.message',

                validate: function (element, value) {
                    var vc = PrimeFaces.util.ValidationContext;

                    if (!this.pattern.test(value)) {
                        var msgStr = element.data('p-email-msg'),
                                msg = msgStr ? {summary: msgStr, detail: msgStr} : vc.getMessage(this.MESSAGE_ID);

                        throw msg;
                    }
                }
            };
            //]]>
