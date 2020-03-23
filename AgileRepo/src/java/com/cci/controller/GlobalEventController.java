/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.controller;

import com.cci.model.Comentario;
import com.cci.model.Evento;
import com.cci.service.ComentarioDao;
import com.cci.service.Dao;
import com.cci.service.EventDao;
import com.cci.service.GlobalEventDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Nvidi
 */
@ManagedBean(name = "globalEventController")
@SessionScoped
public class GlobalEventController implements Serializable {

    
    private String nuevoComentario;
    private List<Comentario> lstComent = new ArrayList<>();
    private List<Evento> lstEvt = new ArrayList<>();
    private String BLACKCOLORCODE = "#000000";
    private String BLUECOLORCODE = "#0388e5";
    private String filtro = "";

    public List<Comentario> getLstComent() {
        return lstComent;
    }

    public void setLstComent(List<Comentario> lstComent) {
        this.lstComent = lstComent;
    }

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

    public String getNuevoComentario() {
        return nuevoComentario;
    }

    public void setNuevoComentario(String nuevoComentario) {
        this.nuevoComentario = nuevoComentario;
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
        if (!"".equals(filtro)) {
            lstEvt = ((GlobalEventDao) dao).getFiltered(filtro);
        } else {
            lstEvt = ((GlobalEventDao) dao).getAll();
        }
    }

    public void comentariosListener(ActionEvent e) {
        ComentarioDao dao = new ComentarioDao();
        Comentario cmt = new Comentario();
        Evento evt = new Evento();
        List<Comentario> comentarios = new ArrayList<>();
        
        evt = (Evento) e.getComponent().getAttributes().get("getEvt");
        this.lstComent.clear();
        comentarios.clear();
        comentarios = dao.getAll();
        System.out.println("HOLA");
        System.out.println(""+evt.getId());
        System.out.println(""+comentarios.size());
        for (int i = 0; i < comentarios.size(); i++) {
            if (comentarios.get(i).getEvento() == evt.getId()) {
                cmt = new Comentario(comentarios.get(i).getId(), comentarios.get(i).getAutor(), comentarios.get(i).getEvento(), comentarios.get(i).getAutor_displayName(), comentarios.get(i).getContenido(), comentarios.get(i).getFecha());
                this.lstComent.add(cmt);
                System.out.println("Objeto : " + cmt);
            }
        }

        PrimeFaces.current().ajax().update("frmdlg2:dlgC");
        PrimeFaces.current().executeScript("PF('dlgComent').show()");

    }
    
    public void onComment(ActionEvent e){ 
        System.out.println("Comentario : "+this.nuevoComentario);
    }

}
