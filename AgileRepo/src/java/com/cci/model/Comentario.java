/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Nvidi
 */
public class Comentario {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    private String autor;
    private String autor_displayName;
    private String contenido;
    private String fecha;
    private int evento;
    private List<Respuesta> respuestas = new ArrayList<>();

    public Comentario(String autor, String autor_displayName, String contenido, String fecha, int evento) {
        this.autor = autor;
        this.autor_displayName = autor_displayName;
        this.contenido = contenido;
        this.fecha = fecha;
        this.evento = evento;
    }

    public Comentario(int id, String uId, int evento, String displayName, String comentario, String fecha) {
       this.id = id;
       this.autor = uId;
       this.autor_displayName = displayName;
       this.evento = evento;
       this.contenido = comentario;
       this.fecha = fecha;
    }
    
    public Comentario( String autor, String autor_displayName, String contenido, int evento) {
        this.autor = autor;
        this.autor_displayName = autor_displayName;
        this.contenido = contenido;
        this.evento = evento;
    }

    public Comentario() {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

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

    public String getFecha() {
        return fecha;
    }
    


    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getEvento() {
        return evento;
    }

    public void setEvento(int evento) {
        this.evento = evento;
    }

    public List<Respuesta> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<Respuesta> respuestas) {
        this.respuestas = respuestas;
    }

    public static String dateFormat(Date fecha) {
        Date date = fecha;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = formatter.format(date);

        return strDate;
    }

}
