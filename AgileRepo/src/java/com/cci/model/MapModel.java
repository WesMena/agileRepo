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
public class MapModel {
    
    private String titulo;

    private double lat;
      
    private double lng;
    
    private String direccion;

    public MapModel() {
    }

    public MapModel(String titulo, double lat, double lng) {
        this.titulo = titulo;
        this.lat = lat;
        this.lng = lng;
    }

    public MapModel(String titulo, double lat, double lng, String direccion) {
        this.titulo = titulo;
        this.lat = lat;
        this.lng = lng;
        this.direccion = direccion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    
    
}
