/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.model;

/**
 *
 * @author Daniel
 */
public class Cronograma {
   
    private int ord;
    private String titulo;
    private String horaInicio;
    private String color;
    private int duracion;

    public Cronograma() {
    }

    public Cronograma(int ord, String titulo, String horaInicio, String color, int duracion) {
        this.ord = ord;
        this.titulo = titulo;
        this.horaInicio = horaInicio;
        this.color = color;
        this.duracion = duracion;
    }
   

    public int getOrd() {
        return ord;
    }

    public void setOrd(int ord) {
        this.ord = ord;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
    
    
    
    
}
