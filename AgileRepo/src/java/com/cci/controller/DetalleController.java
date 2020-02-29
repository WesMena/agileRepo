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
import javax.annotation.PostConstruct;
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
    private List<DetalleEvento> detalles=new ArrayList<>();
    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }
    
   @PostConstruct
    public void init(){
        /*
Este es el método que envía los Eventos que se obtienen de DetalleDao 
a DetalleEvento.xhtml. El parámetro idEvento es la que se encarga de definir 
     cuales detalles de qué evento se van a cargar
*/   
        detalles=new ArrayList<>();
        DetalleDao detalle=new DetalleDao(idEvento);
        detalles=detalle.getAll();
        
     
    }
    
    
    public DetalleController() {
     
    }

    public DetalleController(int idEvento) {
        this.idEvento = idEvento;
    }

     public void redireccionar(int id,String nombre){
        this.idEvento=id;
        this.nombreEvento=nombre;
        init();
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

    public List<DetalleEvento> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleEvento> detalles) {
        this.detalles = detalles;
    }

   
    
  public void onReorder(){
      
  updateIndex();
  
      
}
  public void updateIndex(){
       
    System.out.print(detalles);
    
   DetalleDao detalle=new DetalleDao(idEvento);
   
    int i=1;

     for(DetalleEvento det:detalles){
     det.setIndice(i);
     detalle.update(det);
         
         i++;
     }
    
     
     
  
   
  }
  
}