/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.controller;

import java.io.Serializable;
import org.primefaces.event.FlowEvent;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;

@ManagedBean(name = "wizardcontroller")
@SessionScoped
public class eventWizardViewController implements Serializable {
  public String descripcion="";
  public String resumen=""; 
      public String onFlowProcess(FlowEvent event) {
           
            return event.getNewStep();
        
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
  
        this.resumen = resumen;
    }

  public void cambioDesc(ValueChangeEvent e){
      
      //Método que se dispara al escribir en el textEditor de la descripción
      //funciona con un valueChangeListener
      this.descripcion=e.getNewValue().toString();
 
  }
  
public void cambioResumen(ValueChangeEvent e){
    
    //Método que se dispara al escribir en el inputTextArea del resumen
      //funciona con un valueChangeListener
      this.resumen=e.getNewValue().toString();
      
}
      
}
