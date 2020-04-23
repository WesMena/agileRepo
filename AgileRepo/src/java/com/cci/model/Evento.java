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
    private boolean onComment = false;
    private String nombre;
    private String propietario;
    private List<Comentario> comentarios = new ArrayList<>();
    private List<String> losChips;

    public void addComment(Comentario c) {
        this.comentarios.add(c);
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public List<String> getLosChips() {
        return losChips;
    }

    public void setLosChips(List<String> losChips) {
        this.losChips = losChips;
    }

    public String getProietario() {
        return propietario;
    }

    public void setProietario(String proietario) {
        this.propietario = proietario;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public boolean isOnEdit() {
        return onEdit;
    }

    public void setOnEdit(boolean onEdit) {
        this.onEdit = onEdit;
        tituloCorto();
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
    private double horas;
    private int dias;
    private String duracion;
    private String horaInicio;
    private String losTags = "";
    public static List<Tag> tags = new ArrayList<>();

    public Evento() {
        this.losChips = new ArrayList<>();
    }

    public Evento(String nombre, String desc, int id, double horas, int dias, String inicio) {
        this.losChips = new ArrayList<>();
        this.nombre = nombre;

        this.id = id;
        this.horas = horas;
        this.dias = dias;
        this.horaInicio = inicio;
        System.out.println(horaInicio);
        duracion = horas + "h";
        //Esto es lo que se usa para saber qué mostrar en el cuadro de duración 
        //de las tarjetas de evento 
/*        if (this.dias > 1) {
            duracion = dias + "d";
        } else {
            duracion = horas + "h";
        }
         */

        //Limita el tamaño de la descripción que se muestra en las tarjetas de evento
//        if (desc.length() > 80) {
//            this.desc = desc.substring(0, 80) + "...";
//        } else {
//            this.desc = desc;
//        }
        this.desc = desc;
        tituloCorto();
    }

    public Evento(String nombre, String desc, int id, double horas, int dias, String inicio, String propietario) {
        this.losChips = new ArrayList<>();
        this.nombre = nombre;

        this.id = id;
        this.horas = horas;
        this.dias = dias;
        this.horaInicio = inicio;
        this.propietario = propietario;
        System.out.println(horaInicio);
        duracion = horas + "h";
        this.desc = desc;
        tituloCorto();
    }

    public Evento(String nombre, String desc, int id) {
        this.losChips = new ArrayList<>();
        this.nombre = nombre;
        this.desc = desc;
        this.id = id;
        tituloCorto();
    }

    public Evento(String nombre, String desc, int id, String losTags) {
        this.losChips = new ArrayList<>();
        this.nombre = nombre;
        this.desc = desc;
        this.id = id;
        this.losTags = losTags;
        tituloCorto();
    }

    public Evento(String nombre, String desc) {
        this.losChips = new ArrayList<>();
        this.nombre = nombre;
        this.desc = desc;
        tituloCorto();
    }

    public String getNombre() {
        System.out.println("" + this.nombre + " getter");
        System.out.println("Saved UID: " + UsuarioLoginController.UID);
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

    public double getHoras() {
        return horas;
    }

    public void setHoras(double horas) {
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
        ((EventDao) dao).update(new Evento(this.nombre, this.desc, this.id, this.losTags));
        System.out.println("" + this.nombre + " entrando a edicion");
        System.out.println("" + this.nombre);
    }

    //Modificacion externa de un Evento
    public void edit(String nombre, String desc, int id, String tags) {
        //Escondiendo el boton
        Dao dao = new EventDao();
        System.out.println("" + losTags + " Tags");
        ((EventDao) dao).update(new Evento(nombre, desc, id, tags));
        System.out.println("" + this.nombre + " entrando a edicion");
        System.out.println("" + this.nombre);
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    String tituloCorto = "dfdd";

    public void tituloCorto() {

        if (this.nombre.length() > 20) {
            this.tituloCorto = this.nombre.substring(0, 20) + "...";
        } else {
            this.tituloCorto = this.nombre;
        }

    }

    public boolean retornarEdit() {

        return this.onEdit;
    }

    public String getTituloCorto() {
        return tituloCorto;
    }

    public void setTituloCorto(String tituloCorto) {
        this.tituloCorto = tituloCorto;
    }

    public Evento(String nombre, String desc, int id, double horas, int dias) {
        this.losChips = new ArrayList<>();
        this.nombre = nombre;
        this.desc = desc;
        this.id = id;
        this.horas = horas;
        this.dias = dias;
    }

    public boolean isOnComment() {
        return onComment;
    }

    public void setOnComment(boolean onComment) {
        this.onComment = onComment;
    }
    
    public void addChip(String chip){
        this.losChips.add(chip);
    }

}
