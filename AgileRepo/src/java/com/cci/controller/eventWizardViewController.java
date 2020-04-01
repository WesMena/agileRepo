/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.PrimeFaces;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Nvidi
 */
@ManagedBean(name = "eventWizardController")
@SessionScoped
public class eventWizardViewController implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Constructores">
    public eventWizardViewController() {

    }
    ;    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Variables">
    private List<Tag> tags = new ArrayList<>();
    private String newTag;
    private String strTag = "0";
    private String descripcion;
    private String nombreOrganizador;
    private String nombreEvento;
    private String tipoEvento;

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Getters y Setters">
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }
    
    

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreOrganizador() {
        return nombreOrganizador;
    }

    public void setNombreOrganizador(String nombreOrganizador) {
        this.nombreOrganizador = nombreOrganizador;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public String getStrTag() {
        return strTag;
    }

    public void setStrTag(String strTag) {
        this.strTag = strTag;
    }

    public String getNewTag() {
        return newTag;
    }

    public void setNewTag(String newTag) {
        this.newTag = newTag;

    }

    public List<Tag> getTags() {
        return tags;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Metodos">
    public void addTag(ActionEvent e) {
        if (this.tags.size() == 10) {
            //Mensaje de error
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("tagError", new FacesMessage(FacesMessage.SEVERITY_ERROR, "El límite de tags para un evento corresponde a 10.", ""));
        } else {
            System.out.println("Nuevo Tag : " + this.newTag);
            //Guardando el tag en la lista
            this.tags.add(new Tag(this.tags.size(), this.newTag));
            this.strTag = String.valueOf(tags.size());
            //Limpiando el tag
            newTag = "";
            System.out.println("Lista : " + this.tags.size());
            PrimeFaces.current().ajax().update("tagListDiv");
            PrimeFaces.current().ajax().update("counterContainerTag");

        }
    }

    public void deleteTag(ActionEvent e) {
        //Obteniendo el atributo asociado a un objeto de tipo tag mediante el evento
        Tag t = new Tag();
        t = (Tag) e.getComponent().getAttributes().get("delTag");
        System.out.println("Tag del : " + t.id);
        //Eliminando de la lista
        this.tags.remove(t);
        this.strTag = String.valueOf(tags.size());
        //updateTagIndex();
        PrimeFaces.current().ajax().update("tagListDiv");
        PrimeFaces.current().ajax().update("counterContainerTag");
        PrimeFaces.current().ajax().update("messageErrorTag");
    }

    private void updateUI() {
        PrimeFaces.current().ajax().update("publicarEvento");
    }

    public void onLoad() {
        this.nombreOrganizador = UsuarioLoginController.displayName;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Inner class">
    //Clase interna porque son las 2am y que weba dar un click hacia los modelos
    public class Tag {

        private int id;
        private String tag;

        public Tag() {
        }

        ;
        
        public Tag(int id, String tag) {
            this.id = id;
            this.tag = tag;
        }

        public int getId() {
            return id;

        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }
    }
    //</editor-fold>

}
