/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.controller;
import com.cci.model.DetalleEvento;
import com.cci.service.DetalleDao;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.faces.event.ActionEvent;
/**
 *
 * @author wesli
 */
@ManagedBean(name="detallecontroller")
@SessionScoped
public class DetalleController implements Serializable {
    private int idEvento;
    private String nombreEvento;

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }
    public List<DetalleEvento>listaDetalles(){
        DetalleDao detalle=new DetalleDao(idEvento);
        return detalle.getAll();
    }
    
    
    public DetalleController() {
        this.idEvento=5;
    }

    public DetalleController(int idEvento) {
        this.idEvento = idEvento;
    }

     public void redireccionar(int id,String nombre){
        this.idEvento=id;
        this.nombreEvento=nombre;
       try {
//Cerrar sesion
            HttpServletRequest request = (HttpServletRequest) FacesContext

                    .getCurrentInstance().getExternalContext().getRequest();



            FacesContext context = FacesContext.getCurrentInstance();

            FacesContext

                    .getCurrentInstance()

                    .getExternalContext()

                    .redirect(

                            request.getContextPath()+
                            String.format("/faces/%s","DetalleEvento.xhtml"));
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
    
}
