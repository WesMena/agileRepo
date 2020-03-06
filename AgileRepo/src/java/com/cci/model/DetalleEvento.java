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

/**
 *
 * @author wesli
 */
public class DetalleEvento {

    private String titulo;
    private String descripcion;
    private int duracion;
    private int borrado;
    private int indice;
    private int evento;
    private int id;
    private String objetivo;
    private String categoria;
    private String colorCategoria;
    private String pasos;
    private String materiales;
    private int bloequeo;

    public DetalleEvento() {
    }

    public DetalleEvento(String titulo, String descripcion, int duracion, int borrado, int indice, int evento, int id, String obj,String cat,String catcolor,String pas,String mat) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.borrado = borrado;
        this.indice = indice;
        this.evento = evento;
        this.id = id;
        this.objetivo = obj;
        this.categoria = cat;
        this.colorCategoria = catcolor;
        this.pasos = pas;
        this.materiales = mat;
    }

    /**
     * Este constructor se usa para construir bloqueos, ya que no necesitamos mas valores
     * que el titulo y duracion saber si esta borrado y la llave foranea del evento
     * @param titulo
     * @param duracion
     * @param borrado
     * @param evento
     * @param bloequeo 
     */
    public DetalleEvento(String titulo, int duracion, int borrado, int evento, int bloequeo) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.borrado = borrado;
        this.evento = evento;
        this.bloequeo = bloequeo;
    }

    public DetalleEvento(String titulo, String descripcion, int duracion, int borrado, int indice, int evento, int id, String objetivo, String categoria, String colorCategoria, String pasos, String materiales, int bloequeo) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.borrado = borrado;
        this.indice = indice;
        this.evento = evento;
        this.id = id;
        this.objetivo = objetivo;
        this.categoria = categoria;
        this.colorCategoria = colorCategoria;
        this.pasos = pasos;
        this.materiales = materiales;
        this.bloequeo = bloequeo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getBorrado() {
        return borrado;
    }

    public void setBorrado(int borrado) {
        this.borrado = borrado;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public int getEvento() {
        return evento;
    }

    public void setEvento(int evento) {
        this.evento = evento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getColorCategoria() {
        return colorCategoria;
    }

    public void setColorCategoria(String colorCategoria) {
        this.colorCategoria = colorCategoria;
    }

    public String getPasos() {
        return pasos;
    }

    public void setPasos(String pasos) {
        this.pasos = pasos;
    }

    public String getMateriales() {
        return materiales;
    }

    public void setMateriales(String materiales) {
        this.materiales = materiales;
    }

    public int getBloequeo() {
        return bloequeo;
    }

    public void setBloequeo(int bloequeo) {
        this.bloequeo = bloequeo;
    }
    
    public boolean esBloque(){
        return bloequeo!=0;
    }

}
