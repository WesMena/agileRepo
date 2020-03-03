/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.model;

import com.cci.controller.EventController;
import com.cci.controller.UsuarioLoginController;
import com.cci.service.Dao;
import com.cci.service.EventDao;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.faces.event.ActionEvent;
import org.primefaces.PrimeFaces;

/**
 *
 * @author wesli
 */
public class Evento implements Serializable {

    private boolean onEdit = false;
    private String nombre;

    public boolean isOnEdit() {
        return onEdit;
    }

    public void setOnEdit(boolean onEdit) {
        this.onEdit = onEdit;
        System.out.println("" + onEdit);
    }

    public static List<Tag> getTags() {
        return tags;
    }

    public static void setTags(List<Tag> tags) {
        Evento.tags = tags;
    }
    private int onRowEdit = 1;

    public int getOnRowEdit() {
        return onRowEdit;
    }

    public void setOnRowEdit(int onRowEdit) {
        this.onRowEdit = onRowEdit;
    }
    private String desc;
    private int id;
    private int horas;
    private int dias;
    private String duracion;
    private String losTags = "";
    public static List<Tag> tags = new ArrayList<>();

    public Evento() {
    }

    public Evento(String nombre, String desc, int id, int horas, int dias) {
        this.nombre = nombre;

        this.id = id;
        this.horas = horas;
        this.dias = dias;

        //Esto es lo que se usa para saber qué mostrar en el cuadro de duración 
        //de las tarjetas de evento 
        if (this.dias > 1) {
            duracion = dias + "d";
        } else {
            duracion = horas + "h";
        }

        //Limita el tamaño de la descripción que se muestra en las tarjetas de evento
//        if (desc.length() > 80) {
//            this.desc = desc.substring(0, 80) + "...";
//        } else {
//            this.desc = desc;
//        }
        this.desc = desc;

    }

    public Evento(String nombre, String desc, int id) {
        this.nombre = nombre;
        this.desc = desc;
        this.id = id;
    }

    public Evento(String nombre, String desc, int id, String losTags) {
        this.nombre = nombre;
        this.desc = desc;
        this.id = id;
        this.losTags = losTags;
    }

    public Evento(String nombre, String desc) {
        this.nombre = nombre;
        this.desc = desc;
    }

    public String getNombre() {
        System.out.println("" + this.nombre + " getter");
        System.out.println("Saved UID: "+UsuarioLoginController.UID);
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
        System.out.println("" + this.nombre + "seteando propiedad");
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public void agregarTag(Tag nuevo) {

        tags.add(nuevo);
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getLosTags() {
        return losTags;
    }

    public void setLosTags(String losTags) {
        this.losTags = losTags;
    }

    //Modificacion externa de un Evento
    public void edit() {
        //Escondiendo el boton
        Dao dao = new EventDao();
        System.out.println("" + losTags + " Tags");
        ((EventDao) dao).update(new Evento(this.nombre, this.desc, this.id, this.losTags), new String[]{});
        System.out.println("" + this.nombre + " entrando a edicion");
        System.out.println("" + this.nombre);
    }

    //Modificacion externa de un Evento
    public void edit(String nombre, String desc, int id, String tags) {
        //Escondiendo el boton
        Dao dao = new EventDao();
        System.out.println("" + losTags + " Tags");
        ((EventDao) dao).update(new Evento(nombre, desc, id, tags), new String[]{});
        System.out.println("" + this.nombre + " entrando a edicion");
        System.out.println("" + this.nombre);
    }
}
