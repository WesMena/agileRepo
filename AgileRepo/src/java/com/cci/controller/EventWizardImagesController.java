/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.controller;

import com.OtherSource.FiltroDeAcceso;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseId;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.primefaces.shaded.commons.io.FilenameUtils;
import org.primefaces.shaded.commons.io.IOUtils;

/**
 *
 * @author Nvidi
 */
@ManagedBean(name = "eventWizardImageController")
@SessionScoped
public class EventWizardImagesController implements Serializable {

    public static StreamedContent profileImage = null;
    public static UploadedFile uploadedFile;
    public boolean btnEliminarImgInfoBasica = true;

    public EventWizardImagesController() {

    }

    public void onLoad() {

        InputStream iniIm = FiltroDeAcceso.class.getClassLoader().getResourceAsStream("com/OtherSource/nonuser.jpg");
        try {
            uploadedFile = null;
            profileImage = new DefaultStreamedContent(iniIm, "image/jpeg");
            System.out.println("Stream : " + iniIm.available());
            updateUI();
        } catch (IOException ex) {
            Logger.getLogger(EventWizardImagesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public StreamedContent getProfileImage() {
        System.out.println("Imagen : " + FiltroDeAcceso.class.getClassLoader().getResource("com/OtherSource/404.png"));
        
        return new DefaultStreamedContent(profileImage.getStream(), "image/jpeg");
        
    }

    public void setProfileImage(StreamedContent profileImage) {
        
        System.out.println("Imagen : " + FiltroDeAcceso.class.getClassLoader().getResource("com/OtherSource/404.png"));
        this.profileImage = profileImage;
        
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
            profileImage = new DefaultStreamedContent(uploadedFile.getInputstream(), "image/jpeg");
            save();
        } catch (IOException ex) {
            Logger.getLogger(EventWizardImagesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        updateUI();
        //save();
        btnEliminarImgInfoBasica = false;
    }

    public void save() throws IOException {
        //Se optiene el nombre del archivo
        String filename = FilenameUtils.getName(uploadedFile.getFileName());
        //extrae el stream de lo que se ha subido
        InputStream input = uploadedFile.getInputstream();
        //Crea el archivo en una direccion
        OutputStream output = new FileOutputStream(new File("C:/Users/Nvidi/source", filename));
        //Aca se p[odria subir la imagen a la BD
        
        try {
            IOUtils.copy(input, output);
        } finally {
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(output);
        }
    }

    private void updateUI() {
        PrimeFaces.current().ajax().update("test1:ppOrg");
        PrimeFaces.current().ajax().update("test1:counterContainerTag");
               
        //PrimeFaces.current().ajax().update("publicarEvento:pnlHelp");
    }

    //"Eliminar una fotografia"
    public void deletFile(ActionEvent e) {
        onLoad();
    }

    public boolean isBtnEliminarImgInfoBasica() {
        return btnEliminarImgInfoBasica;
    }

    public void setBtnEliminarImgInfoBasica(boolean btnEliminarImgInfoBasica) {
        this.btnEliminarImgInfoBasica = btnEliminarImgInfoBasica;
    }
    
    
}
