/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.controller;

import com.cci.model.Evento;
import com.cci.service.Dao;
import com.cci.service.EventDao;
import com.cci.service.GlobalEventDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Nvidi
 */
@ManagedBean(name = "globalEventController")
@SessionScoped
public class GlobalEventController implements Serializable {

    private List<Evento> lstEvt = new ArrayList<>();
    private String BLACKCOLORCODE = "#000000";
    private String BLUECOLORCODE = "#0388e5";
    private String filtro = "";

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public List<Evento> getLstEvt() {
        return lstEvt;
    }

    public void setLstEvt(List<Evento> lstEvt) {
        this.lstEvt = lstEvt;
    }

    public String getBLACKCOLORCODE() {
        return BLACKCOLORCODE;
    }

    public void setBLACKCOLORCODE(String BLACKCOLORCODE) {
        this.BLACKCOLORCODE = BLACKCOLORCODE;
    }

    public String getBLUECOLORCODE() {
        return BLUECOLORCODE;
    }

    public void setBLUECOLORCODE(String BLUECOLORCODE) {
        this.BLUECOLORCODE = BLUECOLORCODE;
    }

    public GlobalEventController() {
        onLoad();
    }

    ;
    
    //OnLoad Metodo a llamar en la recarga de la pagina
    
    public void onLoad() {
        //Cargar lstEvent con datos
        Dao dao = new GlobalEventDao();
        this.lstEvt = ((GlobalEventDao) dao).getAll();
    }

    //Metodo de refrescamiento
    public void refrescar() {
        try {
            //Recarga de los datos
            onLoad();
            HttpServletRequest request = (HttpServletRequest) FacesContext
                    .getCurrentInstance().getExternalContext().getRequest();

            FacesContext context = FacesContext.getCurrentInstance();
            FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .redirect(
                            request.getContextPath()
                            + String.format("/faces/%s", "eventosGlobal.xhtml"));

        } catch (Exception e) {

        }

    }

    //Filtrado
    public void filtrarEventos() {
        Dao dao = new GlobalEventDao();
        System.out.println("Entró");
        if(!"".equals(filtro))
            lstEvt = ((GlobalEventDao)dao).getFiltered(filtro);
        else
            lstEvt = ((GlobalEventDao)dao).getAll();
    }

}
