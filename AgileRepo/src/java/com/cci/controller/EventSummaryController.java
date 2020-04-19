/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.controller;

import com.cci.model.EventSummary;
import com.cci.service.Dao;
import com.cci.service.EventSummaryDao;
import com.cci.service.PublicEventDao;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.PrimeFaces;

/**
 *
 * @author wesli
 */
@ManagedBean(name = "eventsummarycontroller")
@SessionScoped

public class EventSummaryController {

    String filtro = "";
    String filtroPublic = "";
    public List<EventSummary> eventSummary = new ArrayList<>();
    public List<EventSummary> eventSummaryPublic = new ArrayList<>();
    int idEventoPublicar;
    public boolean btnAddEstado = true;
    int idEvento = 0;
    String urlFondo = "";
    EventSummary eventoDetalle = new EventSummary();
    private int target;
    String descripcion = "";
    
    String acercaDe="<p style=\"text-align: justify; color:#787878;\">Este es un proyecto desarrollado por los estudiantes del curso <span style=\"color: #000000;\"><a style=\"text-decoration: none;\" href=\"https://www.youtube.com/watch?v=wpV-gGA4PSk\"><span style=\"color: #787878;\">BISOFT-12</span></a></span> Proyecto de Ingenier&iacute;a de Software II de la carrera de Ingenier&iacute;a de Software de la Universidad Latina de Costa Rica durante el primer cuatrimestre del a&ntilde;o 2020.&nbsp;</p>" +
"<p style=\"text-align: justify; \">&nbsp;</p>" +
"<p style=\"text-align: justify;color:#787878;\">Los participantes del proyecto fueron:</p>" +
"<p style=\"text-align: justify;\">&nbsp;</p>" +
"<ul>" +
"<li style=\"text-align: justify; color:#787878;\">Jose Miguel Chaves Miranda</li>" +
"<li style=\"text-align: justify;color:#787878;\">Jes&eacute; Abraham Ch&aacute;vez Rivas</li>" +
"<li style=\"text-align: justify;color:#787878;\">Daniel Hern&aacute;ndez S&aacute;nchez</li>" +
"<li style=\"text-align: justify;color:#787878;\">Weslin Osvaldo Mena Montero&nbsp;</li>" +
"</ul>";

    //Obtenido de GC Sprt7
    public List<EventSummary> getEventSummary() {

        if (eventSummary.size() > 0) {
            this.btnAddEstado = false;
        }

        return eventSummary;
    }

    public void setEventSummaryPublic(List<EventSummary> eventSummaryPublic) {
        this.eventSummaryPublic = eventSummaryPublic;
    }

    public void setEventSummary(List<EventSummary> eventSummary) {
        this.eventSummary = eventSummary;
    }

    public EventSummaryController() {
//        EventSummaryDao evtSum = new EventSummaryDao();
//        this.eventSummary = evtSum.getAll();

    }

    public void refreshList() {
        EventSummaryDao evtSum = new EventSummaryDao();
        this.eventSummary = evtSum.getAll();
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
    
    public boolean hayProximos(){
       //Para ver si hay eventos próximos 
       boolean proximos=false;
        
          for(EventSummary e:eventSummaryPublic){
          if(e.getFinalizado()==0){
              proximos=true;
          }
      }
       
      return proximos; 
       
        
    }

    
    public boolean hayPasados(){
    //Para ver si hay eventos pasados 
      boolean pasados=false; 
      
      for(EventSummary e:eventSummaryPublic){
          if(e.getFinalizado()==1){
              pasados=true;
          }
      }
       
      return pasados; 
    }
    
    public void lanzarVistaPrevia(ActionEvent evt){
          Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int idEvt = Integer.parseInt(params.get("idEvt"));
       
        try {

            HttpServletRequest request = (HttpServletRequest) FacesContext
                    .getCurrentInstance().getExternalContext().getRequest();

            FacesContext context = FacesContext.getCurrentInstance();
            FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .redirect(
                            request.getContextPath()
                            + String.format("/faces/%s", "vistaPreviaEvt.xhtml?id="+idEvt));

        } catch (Exception e) {

        }
        
    }
    
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public EventSummary getEventoDetalle() {
        return eventoDetalle;
    }

    public void setEventoDetalle(EventSummary eventoDetalle) {
        this.eventoDetalle = eventoDetalle;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public void obtenerEvento() throws IOException {
        EventSummaryDao evtSummary = new EventSummaryDao();
        eventoDetalle = evtSummary.obtenerDetalles(idEvento);

        this.urlFondo = "url(/AgileRepo/faces/javax.faces.resource/" + eventoDetalle.getPortada() + "?ln=omega-layout)";
        if (eventoDetalle.getId() == 0) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            externalContext.setResponseStatus(HttpServletResponse.SC_NOT_FOUND);
            externalContext.dispatch("404.xhtml");
            facesContext.responseComplete();
        }
    }

    public void setBtnAddEstado(boolean btnAddEstado) {
        this.btnAddEstado = btnAddEstado;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public int getIdEventoPublicar() {
        return idEventoPublicar;
    }

    public void setIdEventoPublicar(int idEventoPublicar) {
        this.idEventoPublicar = idEventoPublicar;
    }

    public String getFiltroPublic() {
        return filtroPublic;
    }

    public void setFiltroPublic(String filtroPublic) {
        this.filtroPublic = filtroPublic;
    }

    public List<EventSummary> getEventSummaryPublic() {
        return eventSummaryPublic;
    }

    public boolean isBtnAddEstado() {
        return btnAddEstado;
    }

    public String getUrlFondo() {
        return urlFondo;
    }

    public void setUrlFondo(String urlFondo) {
        this.urlFondo = urlFondo;
    }

    /*--------------------------*/
    public void openModal() {
        PrimeFaces.current().executeScript("PF('confrDialog').show()");
    }

    public void update() {
        refreshList();
        PrimeFaces.current().ajax().update("content");
    }

    public void getTarget(ActionEvent e) {
        EventSummary evt = new EventSummary();
        evt = (EventSummary) e.getComponent().getAttributes().get("getEvt");
        System.out.println("idEvnt: " + evt.getId());
        this.target = evt.getId();
        openModal();

    }

    public void deleteAction() {
        System.out.println("Entra al delete");
        PublicEventDao dao = new PublicEventDao();
        dao.deleteEvent(target);
        redireccionar();
    }

    public void redireccionar() {
        try {

            HttpServletRequest request = (HttpServletRequest) FacesContext
                    .getCurrentInstance().getExternalContext().getRequest();

            FacesContext context = FacesContext.getCurrentInstance();
            FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .redirect(
                            request.getContextPath()
                            + String.format("/faces/%s", "dashEventosCreados.xhtml"));

        } catch (Exception e) {

        }

    }

    /*---------------------*/
    //<editor-fold defaultstate="collapsed" desc="Metodos utilizados por GC para el control de los eventos en la parte externa e interna">
    public void onLoadPubblic() {
        Dao dao = new EventSummaryDao();
        this.eventSummaryPublic = ((EventSummaryDao) dao).getAllPublic(filtroPublic);
    }

    public boolean readyToPublic(int idEvt) {
        boolean returned = false;
        Dao dao = new EventSummaryDao();
        returned = ((EventSummaryDao) dao).readyToPublic(idEvt);

        return returned;
    }

    public void applyFilterRedirect() {
        Dao dao = new EventSummaryDao();
        this.eventSummaryPublic = ((EventSummaryDao) dao).getAllPublic(filtroPublic);

        try {

            HttpServletRequest request = (HttpServletRequest) FacesContext
                    .getCurrentInstance().getExternalContext().getRequest();

            FacesContext context = FacesContext.getCurrentInstance();

            FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .redirect(
                            request.getContextPath()
                            + String.format("/faces/%s", "Eventos.xhtml"));
            //Cambiar

        } catch (Exception e) {

        }
    }

    public void publicar(ActionEvent event) {

        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int idEvtP = Integer.parseInt(params.get("idEvtPublic"));
        Dao dao = new EventSummaryDao();
        ((EventSummaryDao) dao).publicar(idEvtP);
        PrimeFaces.current().ajax().update("pnlUIrepeat");
        PrimeFaces.current().executeScript("PF('dlgPEvent').show()");
    }

    public void applyFilterPublico() throws FileNotFoundException {
       
   
        
        
        Dao dao = new EventSummaryDao();
        this.eventSummaryPublic = ((EventSummaryDao) dao).getAllPublic(filtroPublic);

    }

    public void applyFilter() {

        EventSummaryDao evtSum = new EventSummaryDao();
        this.eventSummary = evtSum.getAllByUID(Constantes.logguedUsserUID, filtro);
        PrimeFaces.current().ajax().update("pnlUIrepeat");
    }

    public void onLoad() {
        EventSummaryDao evtSum = new EventSummaryDao();
        this.eventSummary = evtSum.getAllByUID(Constantes.logguedUsserUID, filtro);
    }

    public boolean isAlreadyPublic(int idEvento) {
        Dao dao = new EventSummaryDao();
        boolean returned = ((EventSummaryDao) dao).isAlreadyPublic(idEvento);
        return returned;
    }
    
    
//</editor-fold>

    public String getAcercaDe() {
        return acercaDe;
    }

    public void setAcercaDe(String acercaDe) {
        this.acercaDe = acercaDe;
    }
}
