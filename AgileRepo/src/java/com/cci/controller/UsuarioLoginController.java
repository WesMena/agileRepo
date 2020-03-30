/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.controller;

import com.cci.model.Usuario;
import com.cci.service.Dao;
import com.cci.service.UsuarioDao;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Nvidi
 */
@ManagedBean(name = "usuarioController")
@RequestScoped
public class UsuarioLoginController {

    public UsuarioLoginController() {
    }

    @ManagedProperty("#{param.googleResponse}")
    private String googleResponse;

    @ManagedProperty("#{param.googleDisplayName}")
    private String googleDisplayName;

    public static String displayName = "";

    public  String getDisplayName() {
        return displayName;
    }

    public static void setDisplayName(String displayName) {
        UsuarioLoginController.displayName = displayName;
    }
    
    private String uid;
    
    public static String UID = "";

    public String getUid() {
        System.out.println("UID: " + this.uid);
        return uid;
    }

    public String getGoogleResponse() {
        return googleResponse;
    }

    public void setGoogleResponse(String googleResponse) {
        this.googleResponse = googleResponse;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        UsuarioLoginController.UID = UID;
    }

    public String getGoogleDisplayName() {
        return googleDisplayName;
    }

    public void setGoogleDisplayName(String googleDisplayName) {
        this.googleDisplayName = googleDisplayName;
    }

    public void setUid(String uid) {
        System.out.println("UID :" + this.uid);
        this.uid = uid;
        System.out.println("UID :" + this.uid);
    }

    public void saveUid() {
        UID = this.uid;
        System.out.println("Saved UID: " + UID);
    }

    public void setResponse() {
        Dao dao = new UsuarioDao();

        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String googleResponse = params.get("googleResponse");
        String disName =  params.get("googleDisplayName");
        System.out.println("Respuesta :" + googleResponse);
        System.out.println("Respuesta name:" + disName);
        UID = googleResponse;
        displayName = disName;
        Constantes.logguedUsserDisplayName = displayName;
           
        //Registrar a quienes se logueen y no lo esten
        if (!((UsuarioDao) dao).exists(UID)) {
            ((UsuarioDao) dao).save(new Usuario(UID, 0,disName));
        }
    }

}
