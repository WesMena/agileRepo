/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.controller;

import com.cci.model.DetalleEvento;
import com.cci.service.DetalleDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.faces.event.ActionEvent;
import org.primefaces.PrimeFaces;

/**
 *
 * @author wesli
 */
@ManagedBean(name = "detallecontroller")
@SessionScoped
public class DetalleController implements Serializable {

    private int idEvento;
    private String nombreEvento;
    private List<DetalleEvento> listaDetalles = new ArrayList<>();
    private int id;
    private String titulo;
    private String descripcion;
    private String objetivo;
    private String categoria;
    private String colorCategoria;
    private String pasos;
    private String materiales;

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public List<DetalleEvento> listaDetalles() {
        /*
Este es el método que envía los Eventos que se obtienen de DetalleDao 
a DetalleEvento.xhtml. El parámetro idEvento es la que se encarga de definir 
     cuales detalles de qué evento se van a cargar
         */

        DetalleDao detalle = new DetalleDao(idEvento);
        return detalle.getAll();
    }

    public DetalleController() {
        this.idEvento = 5;

    }

    public void onLoad() {
        List<DetalleEvento> evts = listaDetalles();
        this.listaDetalles.clear();
        for (DetalleEvento evt : evts) {
            this.listaDetalles.add(evt);
            System.out.println("" + evt.toString());
        }
    }

    public DetalleController(int idEvento) {
        this.idEvento = idEvento;
    }

    public void redireccionar(int id, String nombre) {
        this.idEvento = id;
        this.nombreEvento = nombre;
        try {
//Cerrar sesion
            HttpServletRequest request = (HttpServletRequest) FacesContext
                    .getCurrentInstance().getExternalContext().getRequest();

            FacesContext context = FacesContext.getCurrentInstance();

            FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .redirect(
                            request.getContextPath()
                            + String.format("/faces/%s", "DetalleEvento.xhtml"));
            //Cambiar

        } catch (Exception e) {

        }

    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public List<DetalleEvento> getListaDetalles() {
        return listaDetalles;
    }

    public void setListaDetalles(List<DetalleEvento> listaDetalles) {
        this.listaDetalles = listaDetalles;
    }

    public void slotinfo(DetalleEvento evt) {
        String var = String.format("var objeto = {"
                + "titulo :" + "'%1$s',"
                + "descripcion: " + "'%2$s'"
                + "}", evt.getTitulo(), evt.getDescripcion());
        PrimeFaces.current().executeScript(var + "\n" + " console.log(objeto)");

    }

    public String getTituloParam(FacesContext fc) {

        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        return params.get("tituloSlot");

    }

    public String getidParam(FacesContext fc) {

        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        return params.get("idSlot");

    }

    public String getdescParam(FacesContext fc) {

        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        return params.get("descSlot");

    }

    public String getobjParam(FacesContext fc) {

        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        return params.get("objSlot");

    }

    public String getcatParam(FacesContext fc) {

        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        return params.get("catSlot");

    }

    public String getcolcatParam(FacesContext fc) {

        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        return params.get("colcatSlot");

    }

    public String getpasParam(FacesContext fc) {

        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        return params.get("pasSlot");

    }

    public String getmatParam(FacesContext fc) {

        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        return params.get("matSlot");

    }

    public String outcome() {

        FacesContext fc = FacesContext.getCurrentInstance();
        this.titulo = getTituloParam(fc);
        this.id = Integer.parseInt(getidParam(fc));
        this.descripcion = getdescParam(fc);
        this.objetivo = getobjParam(fc);
        this.categoria = getcatParam(fc);
        this.colorCategoria = getcolcatParam(fc);
        this.pasos = getpasParam(fc);
        this.materiales = getmatParam(fc);
        return "result";
    }

    public String getTitulo() {
        
        return titulo;
        
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getColorCategoria() {
        System.out.println("GET "+this.colorCategoria);
        return colorCategoria;
    }

    public void setColorCategoria(String colorCategoria) {
        System.out.println("SET "+this.colorCategoria);
        this.colorCategoria = colorCategoria;
    }

    public String getPasos() {
        return pasos;
    }

    public void setPasos(String pasos) {
        this.pasos = pasos;
    }

    public String getMateriales() {
        return materiales;
    }

    public void setMateriales(String materiales) {
        this.materiales = materiales;
    }
    
    public void updateSlot(){
    
        DetalleDao upt = new DetalleDao();
        upt.updateDetalle(this.id,this.titulo,this.descripcion,this.objetivo,this.categoria,this.colorCategoria,this.pasos,this.materiales);
        
    
    }
    

}
