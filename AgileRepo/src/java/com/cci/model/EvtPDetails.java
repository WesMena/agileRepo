/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.model;

import java.util.ArrayList;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author wesli
 */
public class EvtPDetails {
        String nombreEvento="";
    String Descripcion="";
    String portada="";

    String fecha="";
    String hora="";
    String zonaHoraria="";
    
    int id;
    String resumen="";
    String imgSecundaria="";
    String nomOrganizador="";
    String link="";
    String direccion="";
    String tipo;
    String descOrganizador="";
    StreamedContent fotoOrganizador;
   
    ArrayList<String> listaTags=new ArrayList<String>();
    int presencial=0;
    public EvtPDetails(int id, int presencial,String direccion, String link,String imgSecundaria,String resumen,String zonaHoraria,String fecha,String hora, String portada, String descripcion, String nombre,String tipo) {
        this.id = id;
      this.tipo=tipo;
        this.presencial=presencial; 
     
        this.direccion=direccion;
        this.link=link;
       
        this.imgSecundaria=imgSecundaria;
        this.resumen=resumen;
        this.zonaHoraria=zonaHoraria;
        this.fecha=fecha;
        this.hora=hora;
        this.portada=portada;
        this.Descripcion=descripcion;
        this.nombreEvento=nombre;
        
    }

    
    public EvtPDetails() {
    }
    
    
    public void addTag(String tag){
        this.listaTags.add(tag);
    }
    
    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }



    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getZonaHoraria() {
        return zonaHoraria;
    }

    public void setZonaHoraria(String zonaHoraria) {
        this.zonaHoraria = zonaHoraria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getImgSecundaria() {
        return imgSecundaria;
    }

    
    public boolean esPresencial(){
        
        return(presencial==1);
    }
    public void setImgSecundaria(String imgSecundaria) {
        this.imgSecundaria = imgSecundaria;
    }

    public String getNomOrganizador() {
        return nomOrganizador;
    }

    public void setNomOrganizador(String nomOrganizador) {
        this.nomOrganizador = nomOrganizador;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDescOrganizador() {
        return descOrganizador;
    }

    public void setDescOrganizador(String descOrganizador) {
        this.descOrganizador = descOrganizador;
    }

    public StreamedContent getFotoOrganizador() {
        return fotoOrganizador;
    }

    public void setFotoOrganizador(StreamedContent fotoOrganizador) {
        this.fotoOrganizador = fotoOrganizador;
    }

   

    public int getPresencial() {
        return presencial;
    }

    public void setPresencial(int presencial) {
        this.presencial = presencial;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public ArrayList<String> getListaTags() {
        return listaTags;
    }

    public void setListaTags(ArrayList<String> listaTags) {
        this.listaTags = listaTags;
    }
    
    
    
    
}
