/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.shaded.commons.io.FilenameUtils;
import org.primefaces.shaded.commons.io.IOUtils;

/**
 *
 * @author wesli
 */@ManagedBean(name = "portadacontroller")
@SessionScoped
public class portadaController implements Serializable {
 private UploadedFile foto;   
 
 //El valor que sale aquí es el de la foto que viene por default
 String ubicacionFoto="/AgileRepo/faces/javax.faces.resource/images/EventosSummary/imgDefault.png?ln=omega-layout";
     public void subirFoto(FileUploadEvent event){
         
         
         //Almacena la imagen en el controller;
         foto=event.getFile(); 
         
               
         try{
             save();
  
         }catch(IOException ex){
        Logger.getLogger(portadaController.class.getName()).log(Level.SEVERE, null, ex);   
         }
         
         
     }
     
       public void save() throws IOException {
          
           
           
    
       Calendar fechaActual=Calendar.getInstance();
       int anno=fechaActual.get(Calendar.YEAR);
       int mes=fechaActual.get(Calendar.MONTH);
       int dia=fechaActual.get(Calendar.DATE);
       int hora=fechaActual.get(Calendar.HOUR_OF_DAY);
       int minuto=fechaActual.get(Calendar.MINUTE);
       int segundo=fechaActual.get(Calendar.SECOND);
       String annoStr=Integer.toString(anno);
       String mesStr=Integer.toString(mes);
       String diaStr=Integer.toString(dia);
       String horaStr=Integer.toString(hora);
       String minutoStr=Integer.toString(minuto);
       String segundoStr=Integer.toString(segundo);
     
       //Crea el nombre de la nueva imagen y le coloca la extensión .png
   String filename = "img_"+annoStr+"_"+mesStr+"_"+diaStr+"_"+horaStr+"_"+minutoStr+"_"+segundoStr+".png";
     
    InputStream input = foto.getInputstream();
    
//Esta es la ubicación que es leída por el graphicImage del tab "Imagen principal" del wizard de publicarEventos
    this.ubicacionFoto="/AgileRepo/faces/javax.faces.resource/images/EventosSummary/"+filename+"?ln=omega-layout";
  
      //Esto debe adaptarse a la computadora de cada quien, pero siempre en 
      //   "Proyecto"/web/resources/omega-layout/images/EventosSummary
    OutputStream output = new FileOutputStream(new File("C:/Users/wesli/Documents/GitHub/agileRepo/AgileRepo/web/resources/omega-layout/images/EventosSummary", filename));
        
    
    
    try {
        IOUtils.copy(input, output);
    } finally {
        IOUtils.closeQuietly(input);
        IOUtils.closeQuietly(output);
    }
  
     PrimeFaces.current().ajax().update("portadaVista");
    }

    public UploadedFile getFoto() {
        return foto;
    }

    public void setFoto(UploadedFile foto) {
        this.foto = foto;
    }

    public String getUbicacionFoto() {
        return ubicacionFoto;
    }

    public void setUbicacionFoto(String ubicacionFoto) {
        this.ubicacionFoto = ubicacionFoto;
    }
   
    public String getPortada(){
        return ubicacionFoto;
    }   
            
}
