/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.controller;
import com.cci.model.Evento;
import com.cci.service.EventDao;
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
@ManagedBean(name="eventcontroller")
@SessionScoped

public class EventController implements Serializable {
private String filtro="";    


    public List<Evento> listaEventos(){
       /*
Este es el método que envía los Eventos que se obtienen de EventDao 
al dashboard. Si la variable de clase "filtro" está vacía, muestra todos los 
eventos existentes, pero si se le envía algo, buscará coincidencias a nivel 
de nombre y tags
*/ 
        
        EventDao eventos=new EventDao(filtro);
        return eventos.getAll();
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
        System.out.println(filtro);
    }

  
    
    
}
