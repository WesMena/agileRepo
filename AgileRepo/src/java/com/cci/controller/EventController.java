/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.controller;

import com.cci.model.Evento;
import com.cci.service.Dao;
import com.cci.service.EventDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.faces.event.ActionEvent;

/**
 *
 * @author wesli
 */
@ManagedBean(name = "eventcontroller")
@SessionScoped

public class EventController implements Serializable {

    private String BLACKCOLORCODE = "#000000";
    private String BLUECOLORCODE = "#0388e5";
    private List<Evento> lstEvt = new ArrayList<>();

    public String getBLACKCOLORCODE() {
        return BLACKCOLORCODE;
    }

    public List<Evento> getLstEvt() {
        return lstEvt;
    }

    public void setLstEvt(List<Evento> lstEvt) {
        this.lstEvt = lstEvt;
    }

    public void setBLACKCOLORCODE(String BLACKCOLORCODE) {
        this.BLACKCOLORCODE = BLACKCOLORCODE;
    }
    private String nombre;
    private String desc;
    private String duracion;
    public static String colorCode = "#0388e5";

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public int getOnRowEdit() {
        return onRowEdit;
    }

    public void setOnRowEdit(int onRowEdit) {
        this.onRowEdit = onRowEdit;
    }
    public static int onRowEdit = 1;
    private String tags;
    private int id;
    private String filtro = "";
    private boolean onEdit = false;

    public EventController() {
        this.lstEvt = listaEventos();
    }

    
    
    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getNombre() {
        System.out.println("" + this.nombre + " get");
        return nombre;
    }

    public void setNombre(String nombre) {
        System.out.println("" + this.nombre + " set");
        this.nombre = nombre;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isOnEdit() {
        return onEdit;
    }

    public void setOnEdit(boolean onEdit) {
        System.out.println("" + onEdit);
        this.onEdit = onEdit;
        System.out.println("" + this.onEdit);
    }

    public List<Evento> listaEventos() {
        /*
            Este es el método que envía los Eventos que se obtienen de EventDao 
            al dashboard. Si la variable de clase "filtro" está vacía, muestra todos los 
            eventos existentes, pero si se le envía algo, buscará coincidencias a nivel 
            de nombre y tags
         */
        
        EventDao eventos = new EventDao(filtro);
        return eventos.getAll();
    }

    public void filtrarEventos(){
            System.out.println("Entró");
         EventDao eventos = new EventDao(filtro);
         lstEvt =eventos.getAll();
    }
    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
        System.out.println(filtro + "filtro");
    }

    //Modificacion externa de un Evento
    public void edit(String nombre, String desc, int id, String tags) {
        //Escondiendo el boton
        Dao dao = new EventDao();
        ((EventDao) dao).update(new Evento(nombre, desc, id, tags));
        System.out.println("" + nombre + " entrando a edicion");

    }
    //Modificacion externa de un Evento

    public void edit(Evento ev) {
        //Escondiendo el boton
        Dao dao = new EventDao();
        System.out.println("" + tags + " Tags");
        ((EventDao) dao).update(new Evento(ev.getNombre(), ev.getDesc(), ev.getId(), ev.getLosTags()));
        System.out.println("" + ev.getNombre() + " entrando a edicion");
        System.out.println("" + ev.getDesc());
    }
        public void edit(int i) {
        //Escondiendo el boton
            System.out.println(""+this.lstEvt.get(i).getNombre()+"  LastHope");
    }
    public String getBLUECOLORCODE() {
        return BLUECOLORCODE;
    }

    public void setBLUECOLORCODE(String BLUECOLORCODE) {
        this.BLUECOLORCODE = BLUECOLORCODE;
    }

}
