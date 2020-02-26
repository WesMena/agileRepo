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
@ManagedBean(name="detalle")
@SessionScoped
/**
 *
 * @author wesli
 */
public class DetalleEvento {
 private   String titulo;
 private   String descripcion;
 private   int    duracion;
 private   int    borrado;
 private   int    indice;
 private   int    evento;
 private   int    id;


    public DetalleEvento() {
    }

    public DetalleEvento(String titulo, String descripcion, int duracion, int borrado, int indice, int evento, int id) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.borrado = borrado;
        this.indice = indice;
        this.evento = evento;
        this.id = id;
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
    
}
