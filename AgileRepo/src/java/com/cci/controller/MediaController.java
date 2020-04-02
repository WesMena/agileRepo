/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.controller;

import com.OtherSource.FiltroDeAcceso;
import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import javax.faces.event.ActionEvent;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.primefaces.shaded.commons.io.FilenameUtils;
import org.primefaces.shaded.commons.io.IOUtils;


@ManagedBean(name = "mediaController")
@RequestScoped
public class MediaController implements Serializable {
 
    public static StreamedContent profileImage = null;


    public MediaController() {

    }

    public void onLoad() {

        InputStream iniIm = FiltroDeAcceso.class.getClassLoader().getResourceAsStream("com/OtherSource/nonuser.jpg");
        try {
            profileImage = new DefaultStreamedContent(iniIm, "image/jpeg");
            System.out.println("Stream : " + iniIm.available());
            updateUI();
        } catch (IOException ex) {
            Logger.getLogger(MediaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public  StreamedContent getProfileImage() {
        System.out.println("Imagen : " + FiltroDeAcceso.class.getClassLoader().getResource("com/OtherSource/404.png"));
        return profileImage;
    }

    public void setProfileImage(StreamedContent profileImage) {

        System.out.println("Imagen : " + FiltroDeAcceso.class.getClassLoader().getResource("com/OtherSource/404.png"));
        this.profileImage = profileImage;
    }

    private UploadedFile uploadedFile;

    //Evento disparado cuando un archivo termina de cargar
    public void handleFileUpload(FileUploadEvent event) {
        uploadedFile = event.getFile();

        System.out.println("File : " + uploadedFile.getFileName());
        System.out.println("Extension : " + uploadedFile.getContentType());
        try {
            profileImage = new DefaultStreamedContent(uploadedFile.getInputstream(), "image/jpeg");
        } catch (IOException ex) {
            Logger.getLogger(MediaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        updateUI();
        //save();
    }

    public void save() throws IOException {
        //Se optiene el nombre del archivo
        String filename = FilenameUtils.getName(uploadedFile.getFileName());
        //extrae el stream de lo que se ha subido
        InputStream input = uploadedFile.getInputstream();
        //Crea el archivo en una direccion
        OutputStream output = new FileOutputStream(new File("C:/Users/Nvidi/Downloads", filename));
        //Aca se p[odria subir la imagen a la BD

        try {
            IOUtils.copy(input, output);
        } finally {
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(output);
        }
    }

    private void updateUI() {
        PrimeFaces.current().ajax().update("ppOrg");
      
        //PrimeFaces.current().ajax().update("publicarEvento:pnlHelp");
    }
    
    //"Eliminar una fotografia"
    public void deletFile(ActionEvent e){
        onLoad();
    }
    
}