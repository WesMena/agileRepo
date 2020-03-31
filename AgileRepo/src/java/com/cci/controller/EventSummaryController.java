/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.controller;
import com.cci.model.EventSummary;
import com.cci.service.EventSummaryDao;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
/**
 *
 * @author wesli
 */
@ManagedBean(name = "eventsummarycontroller")
@SessionScoped

public class EventSummaryController {
    public List<EventSummary> eventSummary=new ArrayList<>();

    public List<EventSummary> getEventSummary() {
        return eventSummary;
    }

    public void setEventSummary(List<EventSummary> eventSummary) {
        this.eventSummary = eventSummary;
    }

    public EventSummaryController() {
        EventSummaryDao evtSum=new EventSummaryDao();
        this.eventSummary=evtSum.getAll();
        
    }
    
    
    
    
    
    
}
