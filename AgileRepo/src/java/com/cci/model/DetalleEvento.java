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

}
