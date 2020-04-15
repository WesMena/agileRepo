/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.controller;

import com.cci.model.EvtPDetails;
import com.cci.service.EventSummaryDao;
import com.cci.service.EventoMoreDetailsDao;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author wesli
 */

@ManagedBean(name = "evtpdetails")
@SessionScoped

public class EvtPDetailsController implements Serializable {
    EvtPDetails detalles=new EvtPDetails();
    String urlFondo;
    int idEvento=0;
    String tipo;
    public EvtPDetailsController() {
    }

    public EvtPDetails getDetalles() {
        return detalles;
    }

    public void setDetalles(EvtPDetails detalles) {
        this.detalles = detalles;
    }
    
       public void obtenerEvento() throws IOException {
         EventoMoreDetailsDao evtDetails=new EventoMoreDetailsDao();  
           
  detalles=evtDetails.getDetalles(idEvento);
      
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
   
       
       
}
