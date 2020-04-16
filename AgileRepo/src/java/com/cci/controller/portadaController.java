/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.controller;

import com.OtherSource.FiltroDeAcceso;
import com.cci.service.WizardDao;
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
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.primefaces.shaded.commons.io.FilenameUtils;
import org.primefaces.shaded.commons.io.IOUtils;

/**
 *
 * @author wesli
 */@ManagedBean(name = "portadacontroller")
@SessionScoped
public class portadaController implements Serializable {


 //Estas son las que deben ir a la base de datos(para no afectar la pantalla de eventos)
 String fotobd="images/EventosSummary/imagen1.jpg?ln=omega-layout";
 String fotoSecundariabd;
 
 
 
 public static InputStream iniIm = FiltroDeAcceso.class.getClassLoader().getResourceAsStream("com/OtherSource/imgDefault.png");
 
 
 public static StreamedContent fotoPrincipal= new DefaultStreamedContent(iniIm, "image/jpeg");
    
  
    public static UploadedFile uploadedFile;
   
    public static InputStream  upLoadedStream = null;

    
     public static InputStream iniIm2 = FiltroDeAcceso.class.getClassLoader().getResourceAsStream("com/OtherSource/imgDefault.png");
       public static StreamedContent fotoSecundaria = new DefaultStreamedContent(iniIm2, "image/jpeg");
  
    public static UploadedFile uploadedFile2;
   
    public static InputStream  upLoadedStream2 = null;
    
   
    public void onLoad() {
        //Revision de editado o creacion
        InputStream iniIm = FiltroDeAcceso.class.getClassLoader().getResourceAsStream("com/OtherSource/nonuser.jpg");
        InputStream iniIm2 = FiltroDeAcceso.class.getClassLoader().getResourceAsStream("com/OtherSource/nonuser.jpg");
        try {
            uploadedFile = null;
            upLoadedStream = null;
            fotoPrincipal = new DefaultStreamedContent(iniIm, "image/jpeg");
            fotoSecundaria=new DefaultStreamedContent(iniIm2,"image/jpeg");
            System.out.println("Stream : " + iniIm.available());
            //updateUI();
        } catch (IOException ex) {
            Logger.getLogger(EventWizardImagesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    public  StreamedContent getFotoPrincipal() {
           return new DefaultStreamedContent(fotoPrincipal.getStream(), "image/jpeg");
    }

    public  void setFotoPrincipal(StreamedContent fotoPrincipal) {
        this.fotoPrincipal = fotoPrincipal;
    }

    public  StreamedContent getFotoSecundaria() {
    return new DefaultStreamedContent(fotoSecundaria.getStream(), "image/jpeg");
    }

    public void setFotoSecundaria(StreamedContent fotoSecundaria) {
        this.fotoSecundaria = fotoSecundaria;
    }

    
    
    
    public static UploadedFile getUploadedFile2() {
        return uploadedFile2;
    }

    public static void setUploadedFile2(UploadedFile uploadedFile2) {
        portadaController.uploadedFile2 = uploadedFile2;
    }

    
    
    
    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    //Evento disparado cuando un archivo termina de cargar
    public void handleFileUpload(FileUploadEvent event) {
        uploadedFile = event.getFile();

        System.out.println("File : " + uploadedFile.getFileName());
        System.out.println("Extension : " + uploadedFile.getContentType());
        try {
            fotoPrincipal = new DefaultStreamedContent(uploadedFile.getInputstream(), "image/jpeg");
            upLoadedStream = uploadedFile.getInputstream();
            
            save();
        } catch (IOException ex) {
            Logger.getLogger(EventWizardImagesController.class.getName()).log(Level.SEVERE, null, ex);
        }
              update(); 
        //save();
     
    }
 
    public void handleSecundariaUpload(FileUploadEvent event){
        uploadedFile2 = event.getFile();

        System.out.println("File : " + uploadedFile2.getFileName());
        System.out.println("Extension : " + uploadedFile2.getContentType());
        try {
            fotoSecundaria = new DefaultStreamedContent(uploadedFile2.getInputstream(), "image/jpeg");
            upLoadedStream2 = uploadedFile2.getInputstream();
            
            saveFotoSecundaria();
        } catch (IOException ex) {
            Logger.getLogger(EventWizardImagesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         PrimeFaces.current().ajax().update("test1:panelImgSecundaria");  
             // update(); 
        //save(); 
    }
 
  
 
 
 
 public void subirArchivo(){
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
    InputStream input = uploadedFile.getInputstream();
    Image fotito=ImageIO.read(input); 
    
    BufferedImage buffer=this.createResizedCopy(fotito,1920,1280, true);
   
    File archivito=new File(Constantes.ubicacionFotos, filename);
   
ImageIO.write(buffer,"png",archivito);

  
  this.fotobd="images/EventosSummary/"+filename;
 WizardDao wiz=new WizardDao();
             wiz.enviarImagenPrincipal(eventWizardViewController.idEvento,fotobd);
       update();



System.out.println(archivito.exists());
     }catch(IOException e){
         e.printStackTrace();
     }
  
     
//Esta es la ubicación que es leída por el graphicImage del tab "Imagen principal" del wizard de publicarEventos

             //test1:GIPrincipal
    }

       
       
       public void update(){
        PrimeFaces.current().ajax().update("test1:panelImg");    
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
     
   this.fotoSecundariabd="images/EventosSummary/"+filename;
   
   
          WizardDao wiz=new WizardDao();
             wiz.enviarImagenSecundaria(eventWizardViewController.idEvento,fotoSecundariabd);
   
   
     try{
    InputStream input =  uploadedFile2.getInputstream();
    Image fotito=ImageIO.read(input); 
    BufferedImage buffer=this.createResizedCopy(fotito,1920,1280, true);
    
ImageIO.write(buffer,"png",new File(Constantes.ubicacionFotos, filename));
     }catch(IOException e){
         e.printStackTrace();
     }
     
     
    PrimeFaces.current().ajax().update("test1:secundariaVista");
//Esta es la ubicación que es leída por el graphicImage del tab "Imagen principal" del wizard de publicarEventos

    }

    public String getFotobd() {
        return fotobd;
    }

    public void setFotobd(String fotobd) {
        this.fotobd = fotobd;
    }

    public String getFotoSecundariabd() {
        return fotoSecundariabd;
    }

    public void setFotoSecundariabd(String fotoSecundariabd) {
        this.fotoSecundariabd = fotoSecundariabd;
    }
    
}
