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
public class Comentario {
    
    private int id;
    private String uId;
    private int evento;
    private String user;
    private String comentario;

    public Comentario() {
    }

    public Comentario(int id, String uId, int evento, String user, String comentario) {
        this.id = id;
        this.uId = uId;
        this.evento = evento;
        this.user = user;
        this.comentario = comentario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public int getEvento() {
        return evento;
    }

    public void setEvento(int evento) {
        this.evento = evento;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
    
    
}
