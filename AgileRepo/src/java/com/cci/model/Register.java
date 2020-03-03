/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.model;

/**
 *
 * @author Nvidi
 */
public class Register {
    
    private String uid;
    private boolean rol;

    public boolean isRol() {
        return rol;
    }

    public void setRol(boolean rol) {
        this.rol = rol;
    }
    
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
    
    public Register(String uid,boolean rol){
        this.uid = uid;
        this.rol = rol;
    }
    
}
