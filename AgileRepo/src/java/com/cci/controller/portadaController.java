/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.controller;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
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
import javax.imageio.ImageIO;
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
 //             <img id="portadaVista" cache="false"  src="#{portadacontroller.portada}"  width="310" height="140" style=" position:relative;  z-index:5;  width:100%; height:275px;" />    
 //El valor que sale aquí es el de la foto que viene por default
 String ubicacionFoto="url('/AgileRepo/faces/javax.faces.resource/images/EventosSummary/imgDefault.png?ln=omega-layout')";
// String ubicacionFoto="url(\"#{resource['omega-layout:images/landing/section1_bg.jpg']}\")";
 
 String ubicacionFotoSecundaria="url('/AgileRepo/faces/javax.faces.resource/images/EventosSummary/imgDefault.png?ln=omega-layout')";
 
 public void onLoad(){
     
     
     ubicacionFoto="images/EventosSummary/imagen1.jpg";
     PrimeFaces.current().ajax().update("ppOrg");
 }
 
 public void subirArchivo(){
      try{
             save();
 
         }catch(IOException ex){
        Logger.getLogger(portadaController.class.getName()).log(Level.SEVERE, null, ex);   
         }   
 }
 
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
     try{
    InputStream input = foto.getInputstream();
    Image fotito=ImageIO.read(input); 
    BufferedImage buffer=this.createResizedCopy(fotito,1920,1280, true);
    
ImageIO.write(buffer,"png",new File("C:/Users/wesli/Documents/GitHub/agileRepo/AgileRepo/web/resources/omega-layout/images/EventosSummary", filename));
     }catch(IOException e){
         e.printStackTrace();
     }
  
//Esta es la ubicación que es leída por el graphicImage del tab "Imagen principal" del wizard de publicarEventos
    this.ubicacionFoto="url('/AgileRepo/faces/javax.faces.resource/images/EventosSummary/"+filename+"?ln=omega-layout')";
  

    
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
    
    
    BufferedImage createResizedCopy(Image originalImage, int scaledWidth, int scaledHeight, boolean preserveAlpha) {
    int imageType = preserveAlpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
    BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, imageType);
    Graphics2D g = scaledBI.createGraphics();
    if (preserveAlpha) {
        g.setComposite(AlphaComposite.Src);
    }
    g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
    g.dispose();
    return scaledBI;
}

    public String getUbicacionFotoSecundaria() {
        return ubicacionFotoSecundaria;
    }

    public void setUbicacionFotoSecundaria(String ubicacionFotoSecundaria) {
        this.ubicacionFotoSecundaria = ubicacionFotoSecundaria;
    }
    
    public void subirFotoSecundaria(FileUploadEvent event){
                
         //Almacena la imagen en el controller;
         foto=event.getFile(); 
         
              
         try{
             saveFotoSecundaria();
  
         }catch(IOException ex){
        Logger.getLogger(portadaController.class.getName()).log(Level.SEVERE, null, ex);   
         }
    }
    
    public void saveFotoSecundaria() throws IOException{
           
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
     try{
    InputStream input = foto.getInputstream();
    Image fotito=ImageIO.read(input); 
    BufferedImage buffer=this.createResizedCopy(fotito,1920,1280, true);
    
ImageIO.write(buffer,"png",new File("C:/Users/wesli/Documents/GitHub/agileRepo/AgileRepo/web/resources/omega-layout/images/EventosSummary", filename));
     }catch(IOException e){
         e.printStackTrace();
     }
  
//Esta es la ubicación que es leída por el graphicImage del tab "Imagen principal" del wizard de publicarEventos
    this.ubicacionFotoSecundaria="url('/AgileRepo/faces/javax.faces.resource/images/EventosSummary/"+filename+"?ln=omega-layout')";
   
    }
    
}
