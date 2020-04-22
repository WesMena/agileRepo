/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.controller;

import static com.cci.controller.eventWizardViewController.idEvento;
import com.cci.model.Entrada;
import com.cci.model.EvtPDetails;
import com.cci.model.entradaID;
import com.cci.service.EntradaDao;
import com.cci.service.EventSummaryDao;
import com.cci.service.EventoMoreDetailsDao;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.PrimeFaces;

/**
 *
 * @author wesli
 */
@ManagedBean(name = "evtpdetails")
@SessionScoped

public class EvtPDetailsController implements Serializable {

    EvtPDetails detalles = new EvtPDetails();
    String urlFondo;
    int idEvento = 0;
    String tipo;
    String observacion;
    String nombreEntrada;
    String fechaEntrada;
    double precioEntrada;
    int idEntrada;
    String nombre;
    String correo;
    String telefono;
    int cantcompra = 1;
    boolean pnlMostrarE = false;
    
    private List<Entrada> lstEntrada = new ArrayList<>();
    int cantidadTotal;
    int cantidadCompradaT;
    
    
    public EvtPDetailsController() {
    }

    public EvtPDetails getDetalles() {
        return detalles;
    }

    public void setDetalles(EvtPDetails detalles) {
        this.detalles = detalles;
    }

    public void obtenerEvento() throws IOException {
        EventoMoreDetailsDao evtDetails = new EventoMoreDetailsDao();

        detalles = evtDetails.getDetalles(idEvento);

        this.urlFondo = "url(/AgileRepo/faces/javax.faces.resource/" + detalles.getPortada() + "?ln=omega-layout)";

        if (detalles.getId() == 0) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            externalContext.setResponseStatus(HttpServletResponse.SC_NOT_FOUND);
            externalContext.dispatch("404.xhtml");
            facesContext.responseComplete();
        }
    }

    public String getUrlFondo() {
        return urlFondo;
    }

    public void setUrlFondo(String urlFondo) {
        this.urlFondo = urlFondo;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public List<Entrada> getLstEntrada() {
        return lstEntrada;
    }

    public void setLstEntrada(List<Entrada> lstEntrada) {

        this.lstEntrada = lstEntrada;
    }

    public void onLoad() {

        EntradaDao dao = new EntradaDao();
        this.lstEntrada = ((EntradaDao) dao).getAllByIdEvt(idEvento);

        for (Entrada ent : lstEntrada) {

            ent.setCantComprada(dao.compradas(ent.getIdEntrada()));
            System.out.println("NUMERO DE CANTCOMPRADA:" + ent.getCantComprada());
            System.out.println("NUMERO DEL DAO:" + dao.compradas(ent.getIdEntrada()));
        }

    }

    public String getNombreEntrada() {
        return nombreEntrada;
    }

    public void setNombreEntrada(String nombreEntrada) {
        this.nombreEntrada = nombreEntrada;
    }

    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public double getPrecioEntrada() {
        return precioEntrada;
    }

    public void setPrecioEntrada(double precioEntrada) {
        this.precioEntrada = precioEntrada;
    }

    public int getCantcompra() {
        return cantcompra;
    }

    public void setCantcompra(int cantcompra) {
        this.cantcompra = cantcompra;
    }

    public int getIdEntrada() {
        return idEntrada;
    }

    public void setIdEntrada(int idEntrada) {
        this.idEntrada = idEntrada;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isPnlMostrarE() {
        return pnlMostrarE;
    }

    public void setPnlMostrarE(boolean pnlMostrarE) {
        this.pnlMostrarE = pnlMostrarE;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(int cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public int getCantidadCompradaT() {
        return cantidadCompradaT;
    }

    public void setCantidadCompradaT(int cantidadCompradaT) {
        this.cantidadCompradaT = cantidadCompradaT;
    }
     
    
    
    
    public void seleccionarEntrada(int idEntrada, String nombreEntrada, double Precio, int cantTotal, int cantCompradaT) {

        this.idEntrada = idEntrada;
        this.nombreEntrada = nombreEntrada;
        this.precioEntrada = Precio;
        this.pnlMostrarE = true;
        this.cantidadTotal = cantTotal;
        this.cantidadCompradaT = cantCompradaT;

    }

    public void regresarVista() {

        this.pnlMostrarE = false;

    }

    public void comprarEntrada() throws FileNotFoundException {

        EntradaDao daoC = new EntradaDao();
        daoC.compraEntrada(this.idEvento, this.idEntrada, this.cantcompra, this.nombre, this.correo, this.telefono,this.observacion);
        
       
        entradaID entrada=daoC.getIdTransaccion(this.idEvento,this.idEntrada,this.nombre,this.correo);
        MailController correoController=new MailController();
        
       //(int idTransaccion,String nombreCompleto, String nombreEvento, 
            //String fechaYHora, String tipoEntrada, Double precio, String correoUser,
           // String telUser) 
    
           String fechaHoraInicio=detalles.getFecha()+", "+detalles.getHora();
           
           Double total=this.precioEntrada*this.cantcompra;
        correoController.enviarCorreo(entrada.getIdTransaccion(),this.nombre,entrada.getNomEvt(),fechaHoraInicio,this.nombreEntrada,total,this.correo, this.telefono,this.observacion,this.cantcompra);
        
        this.nombre = "";
        this.correo = "";
        this.telefono = "";
        
        
        onLoad();

        regresarVista();
        
    }         
        


}
