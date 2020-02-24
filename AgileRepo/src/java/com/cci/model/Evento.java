/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wesli
 */
public class Evento {
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
        if(this.dias>1){
            duracion=dias+"d";
        }else{
            duracion=horas+"h";
        }
        
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

