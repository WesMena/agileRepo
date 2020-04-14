/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.controller;

import java.io.Serializable;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Nvidi
 */
@ManagedBean(name = "callWizard")
@SessionScoped
public class CallWizard implements Serializable {

    private Integer idEvt;
    private boolean mode;

    public void callingWizard(ActionEvent evt) {
        //Recepcion de parametros y seteo
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        idEvt = Integer.parseInt(params.get("idEvt"));
        if(params.get("mode").equals("false"))
            mode = false;
        else
            mode = true;
         
        
        eventWizardViewController.idEvento = idEvt;
        eventWizardViewController.editionMode = mode;
        redireccionar("publicarEventos.xhtml");

    }

    public void redireccionar(String xhtml) {

        try {

            HttpServletRequest request = (HttpServletRequest) FacesContext
                    .getCurrentInstance().getExternalContext().getRequest();

            FacesContext context = FacesContext.getCurrentInstance();

            FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .redirect(
                            request.getContextPath()
                            + String.format("/faces/%s", xhtml));
            //Cambiar

        } catch (Exception e) {

        }

    }

    public Integer getIdEvt() {
        return idEvt;
    }

    public void setIdEvt(Integer idEvt) {
        this.idEvt = idEvt;
    }

    public boolean isMode() {
        return mode;
    }

    public void setMode(boolean mode) {
        this.mode = mode;
    }

}
