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
public class Usuario {
    private String uid;
    private int role;
    
    public Usuario(){};
    
    public Usuario(String uid,int role){
        this.uid = uid;
        this.role = role;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
    
    
}
