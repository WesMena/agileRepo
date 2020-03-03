/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.controller;

import java.io.Serializable;
import com.cci.model.Register;
import com.cci.service.Dao;
import com.cci.service.RegisterDao;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Nvidi
 */
@ManagedBean(name = "RegisterController")
@SessionScoped
public class RegisterController implements Serializable {

    private String uid = "";

    public RegisterController() {
    }

    ;
    
    public void onClick() {

        if (!uid.isEmpty()) {
            Logger.getLogger(RegisterDao.class.getName()).log(Level.SEVERE, null, this.uid);
            Dao dao = new RegisterDao();
            ((RegisterDao) dao).save(new Register(this.uid, false));
        }
    }

    public String getUid() {
        Logger.getLogger(RegisterDao.class.getName()).log(Level.SEVERE, null, this.uid);
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
        Logger.getLogger(RegisterDao.class.getName()).log(Level.SEVERE, null, this.uid);
    }

}
