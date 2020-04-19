/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.model;

/**
 *
 * @author josem
 */
public class Entrada {
    
    String nombre;
    double precio;
    String fechaFin;
    String horaFin;
    String fechaInicio;
    String horaInicio;
    Integer tipo;
    Integer cantidad;
    Integer cantComprada = 0;
    Integer idEntrada;

    public Entrada(String nombre, double precio, String fechaFin, String horaFin, String fechaInicio, String horaInicio, Integer tipo, Integer cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.fechaFin = fechaFin;
        this.horaFin = horaFin;
        this.fechaInicio = fechaInicio;
        this.horaInicio = horaInicio;
        this.tipo = tipo;
        this.cantidad = cantidad;
        
    }
    
    public Entrada(String nombre, double precio, String fechaFin, String horaFin, String fechaInicio, String horaInicio, Integer tipo, Integer cantidad, Integer idEntrada) {
        this.nombre = nombre;
        this.precio = precio;
        this.fechaFin = fechaFin;
        this.horaFin = horaFin;
        this.fechaInicio = fechaInicio;
        this.horaInicio = horaInicio;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.idEntrada = idEntrada;

    }
    

    public Entrada() {
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getCantComprada() {
        return cantComprada;
    }

    public void setCantComprada(Integer cantComprada) {
        this.cantComprada = cantComprada;
    }

    public Integer getIdEntrada() {
        return idEntrada;
    }

    public void setIdEntrada(Integer idEntrada) {
        this.idEntrada = idEntrada;
    }

    
    
    
    
}
