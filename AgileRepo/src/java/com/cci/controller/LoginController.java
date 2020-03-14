/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.controller;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Nvidi
 */
@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController implements Serializable {

    @ManagedProperty("#{usuarioController}")
    private UsuarioLoginController usuarioController;

    public LoginController() {
    }

    public UsuarioLoginController getUsuarioController() {
        return usuarioController;
    }

    public void setUsuarioController(UsuarioLoginController usuarioController) {
        this.usuarioController = usuarioController;
    }

}
