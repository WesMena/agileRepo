/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.controller;

import com.cci.model.Entrada;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.FlowEvent;

@ManagedBean(name = "eventWizardController")
@ViewScoped
public class eventWizardViewController {

    private List<Entrada> lstEntrada = new ArrayList<>();
    String nombreEntrada;
    double precioEntrada;
    String fechaFinEntrada;
    String horaFinEntrada;
    String fechaInicioEntrada;
    String horaInicioEntrada;
    Integer tipoEntrada;
    Integer cantidadEntrada;
    Integer indexEntrada;

    
    public String onFlowProcess(FlowEvent event) {

        return event.getNewStep();

    }

    public void nuevaEntrada() {

        Entrada nuevaE = new Entrada("Admision General", 0.00, "05/20/2020", "00:00", "05/20/2020", "00:00", 0, 1);
        lstEntrada.add(nuevaE);
    }

    public List<Entrada> getLstEntrada() {
        return lstEntrada;
    }

    public void setLstEntrada(List<Entrada> lstEntrada) {
        this.lstEntrada = lstEntrada;
    }

    public String getNombreEntrada() {
        return nombreEntrada;
    }

    public void setNombreEntrada(String nombreEntrada) {
        this.nombreEntrada = nombreEntrada;
    }

    public void seleccionarEntrada(String nombreEntrada, Integer tipoEntrada, Integer Cantidad, double Precio, String fechaInicioEntrada, String horaInicioEntrada, String fechaFinEntrada, String horaFinEntrada, Integer indexE) {
        this.nombreEntrada = nombreEntrada;
        this.tipoEntrada = tipoEntrada;
        this.cantidadEntrada = Cantidad;
        this.precioEntrada = Precio;
        this.fechaInicioEntrada = fechaInicioEntrada;
        this.horaInicioEntrada = horaInicioEntrada;
        this.fechaFinEntrada = fechaFinEntrada;
        this.horaFinEntrada = horaFinEntrada;
        this.indexEntrada = indexE;

    }

    public double getPrecioEntrada() {
        return precioEntrada;
    }

    public void setPrecioEntrada(double precioEntrada) {
        this.precioEntrada = precioEntrada;
    }

    public String getFechaFinEntrada() {
        return fechaFinEntrada;
    }

    public void setFechaFinEntrada(String fechaFinEntrada) {
        this.fechaFinEntrada = fechaFinEntrada;
    }

    public String getHoraFinEntrada() {
        return horaFinEntrada;
    }

    public void setHoraFinEntrada(String horaFinEntrada) {
        this.horaFinEntrada = horaFinEntrada;
    }

    public String getFechaInicioEntrada() {
        return fechaInicioEntrada;
    }

    public void setFechaInicioEntrada(String fechaInicioEntrada) {
        this.fechaInicioEntrada = fechaInicioEntrada;
    }

    public String getHoraInicioEntrada() {
        return horaInicioEntrada;
    }

    public void setHoraInicioEntrada(String horaInicioEntrada) {
        this.horaInicioEntrada = horaInicioEntrada;
    }

    public Integer getTipoEntrada() {
        return tipoEntrada;
    }

    public void setTipoEntrada(Integer tipoEntrada) {
        this.tipoEntrada = tipoEntrada;
    }

    public Integer getCantidadEntrada() {
        return cantidadEntrada;
    }

    public void setCantidadEntrada(Integer cantidadEntrada) {
        this.cantidadEntrada = cantidadEntrada;
    }


    public void actualizarEntrada() {
        lstEntrada.get(this.indexEntrada).setNombre(this.nombreEntrada);
        lstEntrada.get(this.indexEntrada).setTipo(this.tipoEntrada);
        lstEntrada.get(this.indexEntrada).setCantidad(this.cantidadEntrada);
        lstEntrada.get(this.indexEntrada).setPrecio(this.precioEntrada);
        lstEntrada.get(this.indexEntrada).setFechaInicio(this.fechaInicioEntrada);
        lstEntrada.get(this.indexEntrada).setHoraInicio(this.horaInicioEntrada);
        lstEntrada.get(this.indexEntrada).setFechaFin(this.fechaFinEntrada);
        lstEntrada.get(this.indexEntrada).setHoraFin(this.horaFinEntrada);
    }
    
    public void borrarEntrada(){
        
        lstEntrada.remove(this.indexEntrada);
        
    }

}
