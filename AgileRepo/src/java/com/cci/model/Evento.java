/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.model;

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
/**
 *
 * @author wesli
 */
@ManagedBean(name="evento")
@SessionScoped
public class Evento implements Serializable {
    private String nombre;
   private String desc;
   private int id;
   private int horas; 
   private int dias;
   private String duracion;
   private String losTags="";
   public static List<Tag> tags=new ArrayList<>();
   
    public Evento() {
    }

    public Evento(String nombre, String desc, int id, int horas, int dias) {
        this.nombre = nombre;
     
        this.id = id;
        this.horas = horas;
        this.dias = dias;
        
        //Esto es lo que se usa para saber qué mostrar en el cuadro de duración 
        //de las tarjetas de evento 
        if(this.dias>1){
            duracion=dias+"d";
        }else{
            duracion=horas+"h";
        }
        
        
        //Limita el tamaño de la descripción que se muestra en las tarjetas de evento
        if(desc.length()>80){
            this.desc=desc.substring(0,80)+"...";
        }else{
            this.desc=desc;
        }
      
       
    }

   
    public Evento(String nombre, String desc) {
        this.nombre = nombre;
        this.desc = desc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
   
   public void agregarTag(Tag nuevo){
       
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
 
    
    
}

