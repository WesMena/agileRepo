/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.model;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nvidi
 */
public class InfoBasica   {
    private String tipo;
    private String nombre;
    private String organizador;
    private List<String> tags  = new ArrayList<>();;
    private InputStream  foto;
    private String descripcion;
    
    
    public InfoBasica(){
        tags = new ArrayList<>();
    }

    public InfoBasica(String tipo,String nombre, String organizador, List<String> tags,InputStream foto,String descripcion) {
        
        this.nombre = nombre;
        this.organizador = organizador;
        //this.tags = tags;
        this.foto = foto;
        this.tipo = tipo;
        this.descripcion = descripcion;
        tags.forEach((s) -> {
            this.tags.add(s);
        });
    }

    public InputStream getFoto() {
        return foto;
    }

    public void setFoto(InputStream foto) {
        this.foto = foto;
    }
    
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getOrganizador() {
        return organizador;
    }

    public void setOrganizador(String organizador) {
        this.organizador = organizador;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
 
    
    
}
