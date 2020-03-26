/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;
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
 *
 * /**
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
    private int primeroDelDia;
    private Date horaInicio;
    private String horaInicioStr;
    private boolean bloqueEditable = false;

    private String horaFinalStr;

    /*Se aconseja utilizar horaInicioStr por encima de horaInicio, ya que la 
    primera ya esta adaptada al formato hh:mm en 24h. Además, es independiente 
    de la zona horaria, así que no presenta el inconveniente de que la hora se 
    cargue atrasada por 6 horas o similar. 
     */
    public DetalleEvento() {
    }

    public DetalleEvento(String titulo, String descripcion, int duracion, int borrado, int indice, int evento, int id, String obj, String cat, String catcolor, String pas, String mat) {
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
     * Este constructor se usa para construir bloqueos, ya que no necesitamos
     * mas valores que el titulo y duracion saber si esta borrado y la llave
     * foranea del evento
     *
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

    public boolean esBloque() {
        return bloequeo != 0;
    }

    public DetalleEvento(String titulo, String descripcion, int duracion, int borrado, int indice, int evento, int id, String objetivo, String categoria, String colorCategoria, String pasos, String materiales, int bloequeo, int primeroDelDia, Date horaInicio) {
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
        this.primeroDelDia = primeroDelDia;
        this.horaInicio = horaInicio;
    }

    public DetalleEvento(String titulo, String descripcion, int duracion, int borrado, int indice, int evento, int id, String objetivo, String categoria, String colorCategoria, String pasos, String materiales, int bloequeo, int primeroDelDia, Date horaInicio, String horaInicioStr) {
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
        this.primeroDelDia = primeroDelDia;
        this.horaInicio = horaInicio;
        this.horaInicioStr = horaInicioStr;

    }

    public int getPrimeroDelDia() {
        return primeroDelDia;
    }

    public void setPrimeroDelDia(int primeroDelDia) {
        this.primeroDelDia = primeroDelDia;
    }

    public Date getHoraInicio() {

        if (this.horaInicio == null) {
            horaInicio = GregorianCalendar.getInstance().getTime();
        }
        return horaInicio;

    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public boolean esPrimeroDia() {
        return this.primeroDelDia != 0;
    }

    public String getHoraInicioStr() {
        return horaInicioStr;
    }

    public void setHoraInicioStr(String horaInicioStr) {
        this.horaInicioStr = horaInicioStr;
    }

    public DetalleEvento(int id, String horaInicioStr) {
        this.id = id;
        this.horaInicioStr = horaInicioStr;
    }

    public DetalleEvento(int duracion, int id) {
        this.duracion = duracion;
        this.id = id;
    }

    public String tituloResumido() {
        String tituloR = "";
        if (this.titulo.length() > 20) {
            tituloR = this.titulo.substring(0, 20) + "...";
        } else {
            tituloR = this.titulo;
        }

        return tituloR;
    }

    public String getHoraFinalStr() {
        return horaFinalStr;
    }

    public void setHoraFinalStr(String horaFinalStr) {
        this.horaFinalStr = horaFinalStr;
    }

    public DetalleEvento(String titulo, String descripcion, int duracion, int borrado, int indice, int evento, int id, String objetivo, String categoria, String colorCategoria, String pasos, String materiales, int bloequeo, int primeroDelDia, Date horaInicio, String horaInicioStr, String horaFinalStr) {
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
        this.primeroDelDia = primeroDelDia;
        this.horaInicio = horaInicio;
        this.horaInicioStr = horaInicioStr;
        this.horaFinalStr = horaFinalStr;
    }

    public boolean isBloqueEditable() {
        return bloqueEditable;
    }

    public void setBloqueEditable(boolean bloqueEditable) {
        this.bloqueEditable = bloqueEditable;
    }

    public void toggleEditable() {
        bloqueEditable = !(bloqueEditable);
         
    }

    public int getColsBloque() {
        if (titulo == null) {
            return 6;
        }
        if (bloqueEditable) {
            return 50;
        }
        return titulo.length();
    }
}
