/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.model;

import java.util.Date;

/**
 *
 * @author Nvidi
 */
public class Respuesta {

    private String autor;
    private String autor_displayName;
    private String contenido;
    private Date fecha;
    private int comentario;

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getAutor_displayName() {
        return autor_displayName;
    }

    public void setAutor_displayName(String autor_displayName) {
        this.autor_displayName = autor_displayName;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getComentario() {
        return comentario;
    }

    public void setComentario(int comentario) {
        this.comentario = comentario;
    }
    
    

}
