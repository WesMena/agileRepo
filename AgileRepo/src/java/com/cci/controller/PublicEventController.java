/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.controller;

import com.cci.model.Cronograma;
import com.cci.model.DetalleEvento;
import com.cci.model.EventSummary;
import com.cci.model.Evento;
import com.cci.service.DetalleDao;
import com.cci.service.PublicEventDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import org.primefaces.PrimeFaces;
import com.cci.controller.EventSummaryController;

/**
 *
 * @author Daniel
 */
@ManagedBean(name = "publiceventcontroller")
@SessionScoped
public class PublicEventController implements Serializable {

    private int target;
    public List<EventSummary> eventSummary = new ArrayList<>();

    public List<EventSummary> getEventSummary() {
        return eventSummary;
    }

    public void setEventSummary(List<EventSummary> eventSummary) {
        this.eventSummary = eventSummary;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public void openModal() {
        PrimeFaces.current().executeScript("PF('confrDialog').show()");
    }
    
    public void update(){
        EventSummaryController evtcnt = new  EventSummaryController();
        evtcnt.refreshList();
        PrimeFaces.current().ajax().update("content:servicios");
    }

    public void getTarget(ActionEvent e) {
        EventSummary evt = new EventSummary();
        evt = (EventSummary) e.getComponent().getAttributes().get("getEvt");
        System.out.println("idEvnt: " + evt.getId());
        this.target = evt.getId();
        openModal();
        deleteAction();
       
    }

    public void deleteAction() {
        System.out.println("Entra al delete");
        PublicEventDao dao = new PublicEventDao();
        dao.deleteEvent(target);
        update();
    }

}
