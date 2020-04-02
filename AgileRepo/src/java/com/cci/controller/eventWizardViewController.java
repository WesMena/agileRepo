/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.controller;

import com.cci.model.Entrada;
import com.cci.service.EntradaDao;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "eventWizardController")
@ViewScoped
public class eventWizardViewController {

    //<editor-fold defaultstate="collapsed" desc="comment">
    //La variable indexEntrada, es la ubicacion en el ArrayList lstEntrada del objeto Entrada
    //La variable editarDivRend, es la que determina si la seccion de Editar Entrada se muestra o no 
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Variables">
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
    Boolean editarDivRend = false;
    Boolean validarEntradas = false;
    Integer idEvento = 1;


//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="comment">
    //Metodo nuevaEntrada, ingresa un Objeto Entrada en el ArrayList lstEntrada con informacion default
    //Metodo seleccionarEntrada, guarda los valores de la entrada seleccionada en las variables y cambia el valor de editarDivRend a true para que muestre la seccion de Editar
    //setTipoEntrada, se asegura que si el valor es 0, cambia el valor de PrecioEntrada a 0.00
    //Metodo actualizarEntrada, cambia los valores del Objeto Entrada seleccionado por los nuevos valores ingresados y cambia el valor de editarDivRend a false para que deje de mostrar la seccion de Editar
    //Metodo borrarEntrada, borra el Objeto Entrada del ArrayList lstEntrada y cambia el valor de editarDivRend a false para que deje de mostrar la seccion de Editar
    //Metodo cerrarEntrada, Cierra la seccion editar entrada sin aplicar cambios
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Metodos">
    public String onFlowProcess(FlowEvent event) {

        return event.getNewStep();

    }

    public void nuevaEntrada() {

        Entrada nuevaE = new Entrada("Admision General", 0.00, "2020-05-20", "00:00", "2020-05-20", "00:00", 0, 1);
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
        this.validarEntradas = true;
        this.nombreEntrada = nombreEntrada;
        this.tipoEntrada = tipoEntrada;
        this.cantidadEntrada = Cantidad;
        this.precioEntrada = Precio;
        this.fechaInicioEntrada = fechaInicioEntrada;
        this.horaInicioEntrada = horaInicioEntrada;
        this.fechaFinEntrada = fechaFinEntrada;
        this.horaFinEntrada = horaFinEntrada;
        this.indexEntrada = indexE;

        this.editarDivRend = true;
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
        if (this.tipoEntrada == 0) {
            this.precioEntrada = 0.00;
        }

    }

    public Integer getCantidadEntrada() {
        return cantidadEntrada;
    }

    public void setCantidadEntrada(Integer cantidadEntrada) {
        this.cantidadEntrada = cantidadEntrada;
    }

    public Boolean getEditarDivRend() {
        return editarDivRend;
    }

    public void setEditarDivRend(Boolean editarDivRend) {
        this.editarDivRend = editarDivRend;
    }

    public Boolean getValidarEntradas() {
        return validarEntradas;
    }

    public void setValidarEntradas(Boolean validarEntradas) {
        this.validarEntradas = validarEntradas;
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

        this.editarDivRend = false;
        this.validarEntradas = false;
    }

    public void borrarEntrada() {
        this.validarEntradas = false;
        lstEntrada.remove(lstEntrada.get(this.indexEntrada));
        this.editarDivRend = false;

    }

    public void cerrarEntrada() {
        this.validarEntradas = false;
        this.editarDivRend = false;

    }

    public void guardarEntradas() {

        EntradaDao eDao = new EntradaDao();

        for (Entrada ev : lstEntrada) {

            eDao.nuevaEntrada(this.idEvento, ev.getNombre(), ev.getPrecio(), ev.getFechaFin(), ev.getHoraFin(), ev.getFechaInicio(), ev.getHoraInicio(), ev.getTipo(), ev.getCantidad());

        }

        System.out.println("Ingreso de Entradas a la BD listo");
    }



//</editor-fold>
}
