/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.model;

/**
 *
 * @author wesli
 */
public class EventSummary {
    String nombreEvento="";
    String Descripcion="";
    String portada="";
    int finalizado=0;
    String fecha="";
    String hora="";
    int id;
    public EventSummary() {
    }

    public EventSummary(String nombre,String desc, String portada, int finalizado, String fecha, String hora, int id){
        this.nombreEvento=nombre;
        this.Descripcion=desc;
        this.portada=portada;
        this.finalizado=finalizado;
        this.fecha=fecha;
        this.hora=hora;
        this.id=id;
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

    public int getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(int finalizado) {
        this.finalizado = finalizado;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public boolean evtFinalizado(){
      
        
        return (finalizado==1);
    }
    
    
}
