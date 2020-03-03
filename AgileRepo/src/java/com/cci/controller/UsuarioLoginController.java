/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Nvidi
 */
@ManagedBean(name = "usuarioController")
@SessionScoped
public class UsuarioLoginController {

    private String uid;
    public static  String UID="";
    
    public String getUid() {
        System.out.println("UID: " + this.uid);
        return uid;
    }

    public void setUid(String uid) {
        System.out.println("UID :" + this.uid);
        this.uid = uid;
        System.out.println("UID :" + this.uid);
    }

    public void saveUid(){
        UID = this.uid;
        System.out.println("Saved UID: "+UID);
    }
    
}
