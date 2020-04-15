/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.controller;

import com.cci.model.EventSummary;
import com.cci.service.Dao;
import com.cci.service.EventSummaryDao;
import com.sun.org.apache.bcel.internal.classfile.Constant;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.PrimeFaces;

/**
 *
 * @author wesli
 */
@ManagedBean(name = "eventsummarycontroller")
@SessionScoped

public class EventSummaryController {
    
    public List<EventSummary> eventSummary = new ArrayList<>();
    public List<EventSummary> eventSummaryPublic = new ArrayList<>();
    int idEventoPublicar;
    int idEvento = 0;
    String urlFondo = "";
    EventSummary eventoDetalle = new EventSummary();
    String descripcion = "<p style=\"text-align: justify;\"><em>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Enim facilisis gravida neque convallis a. Fa</em>mes ac turpis egestas sed tempus urna et pharetra. Aliquam eleif<strong>end mi in nulla posuere sollicitudin aliquam ultrices sagittis. Tellus at urna condimentum</strong> mattis pellentesque id nibh tortor id. Vel facilisis volutpat est velit egestas dui. M<span style=\"color: #ff0000;\">orbi blandit cursus risus at ultrices mi. Morbi blandit cursus risus at ultrices. Sit amet luctus venenatis lectus magna fringilla urna porttitor. Pretium nibh ipsum consequat nisl vel pretium lectus quam.</span></p>\n"
            + "<p style=\"text-align: justify;\">M<strong>orbi tristique senectus et netus et malesuada fames ac. Feugiat in ante metus dictum at tempor commodo. Ullamcorper eget nulla facilisi etiam dignissim. At lectus urna duis</strong> convallis convallis tellus. Risus commodo viverra maecenas accumsan lacus vel facilisis volutpat est. Id diam maecenas ultricies mi eget mauris pharetra et. Lectus urna duis convallis convallis. Eleifend donec pretium vulputate sapien nec sagittis aliquam malesuada. Enim diam vulputate ut pharetra sit. Consequat mauris nunc congue nisi. Senectus et netus et malesuada fames ac. Amet mauris commodo quis imperdiet massa. Orci ac auctor augue mauris augue. Lorem donec massa sapien faucibus et molestie. Gravida cum sociis natoque penatibus et. Sed faucibus turpis in eu mi bibendum. At lectus urna duis convallis convallis tellus id interdum velit.</p>\n"
            + "<p style=\"text-align: justify;\">Nullam ac tortor vitae purus faucibus ornare. Vivamus at augue eget arcu dictum varius duis at. Quam elementum pulvinar etiam non quam lacus suspendisse faucibus interdum. Proin sed libero enim sed faucibus. Risus feugiat in ante metus dictum. Adipiscing at in tellus integer feugiat. Feugiat in fermentum posuere urna nec tincidunt praesent semper. Ac turpis egestas integer eget. Porttitor lacus luctus accumsan tortor posuere ac ut consequat. Sem fringilla ut morbi tincidunt augue interdum velit euismod in. Ipsum a arcu cursus vitae congue mauris rhoncus aenean. Diam maecenas sed enim ut sem viverra aliquet. Tincidunt arcu non sodales neque sodales ut etiam sit.</p>\n"
            + "<p style=\"text-align: justify;\">Feugiat in ante metus dictum. At in tellus integer feugiat scelerisque varius morbi enim. In iaculis nunc sed augue lacus viverra. Ut sem viverra aliquet eget sit amet. Dui id ornare arcu odio ut sem nulla pharetra diam. Arcu dui vivamus arcu felis bibendum ut tristique et. A diam sollicitudin tempor id eu nisl. Cras tincidunt lobortis feugiat vivamus at augue. Mauris in aliquam sem fringilla ut. Elit scelerisque mauris pellentesque pulvinar pellentesque habitant morbi. Mauris in aliquam sem fringilla. Purus sit amet volutpat consequat mauris. Morbi tristique senectus et netus et malesuada fames ac turpis. Volutpat odio facilisis mauris sit. Tincidunt dui ut ornare lectus sit amet est placerat in. Tincidunt nunc pulvinar sapien et ligula ullamcorper malesuada. Nibh venenatis cras sed felis eget.</p>\n"
            + "<p style=\"text-align: justify;\">Volutpat consequat mauris nunc congue nisi vitae. Sed felis eget velit aliquet. Eleifend mi in nulla posuere sollicitudin aliquam. Leo a diam sollicitudin tempor id eu nisl nunc. Aliquam sem et tortor consequat. Sed euismod nisi porta lorem. Ut faucibus pulvinar elementum integer enim neque volutpat. In eu mi bibendum neque. Porttitor leo a diam sollicitudin tempor id eu. Bibendum ut tristique et egestas. Urna cursus eget nunc scelerisque viverra mauris in aliquam sem. Magna fringilla urna porttitor rhoncus dolor purus non. Sollicitudin nibh sit amet commodo.<blockquote class=\"embedly-card\"><h4><a href=\"https://www.instagram.com/p/B-kfO5cD2Ct/\">Cuando has visto #LCDP4 un poquito demasiado rápido.⁣ ⁣ When you've finished #LCDP4 a little too fast.⁣ ⁣ Quando você assiste #LCDP4 rápido demais.⁣</a></h4><p>777.2k Likes, 8,419 Comments - La Casa de Papel (@lacasadepapel) on Instagram: \"Cuando has visto #LCDP4 un poquito demasiado rápido.⁣ ⁣ When you've finished #LCDP4 a little too...\"</p></blockquote>\n"
            + "<script async src=\"//cdn.embedly.com/widgets/platform.js\" charset=\"UTF-8\"></script>  <iframe width=\"853\" height=\"480\" src=\"https://www.youtube.com/embed/6V1vlwwr4M0\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe> </p>";
    public boolean btnAddEstado = true;
    
    public List<EventSummary> getEventSummary() {
        
        if (eventSummary.size() > 0) {
            this.btnAddEstado = false;
        }
        
        return eventSummary;
    }
    
    public void setEventSummary(List<EventSummary> eventSummary) {
        this.eventSummary = eventSummary;
    }
    
    public void onLoadPubblic(){
        Dao dao = new EventSummaryDao();
        this.eventSummaryPublic = ((EventSummaryDao)dao).getAllPublic();
    }
    
    public void onLoad() {
        EventSummaryDao evtSum = new EventSummaryDao();
        this.eventSummary = evtSum.getAllByUID(Constantes.logguedUsserUID);
    }
    
    public EventSummaryController() {
        
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public int getIdEventoPublicar() {
        return idEventoPublicar;
    }
    
    public void setIdEventoPublicar(int idEventoPublicar) {
        this.idEventoPublicar = idEventoPublicar;
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
    
    public String getUrlFondo() {
        return urlFondo;
    }
    
    public void setUrlFondo(String urlFondo) {
        this.urlFondo = urlFondo;
    }
    
    public boolean isBtnAddEstado() {
        return btnAddEstado;
    }

    public List<EventSummary> getEventSummaryPublic() {
        return eventSummaryPublic;
    }

    public void setEventSummaryPublic(List<EventSummary> eventSummaryPublic) {
        this.eventSummaryPublic = eventSummaryPublic;
    }
    
    
    
    public void setBtnAddEstado(boolean btnAddEstado) {
        this.btnAddEstado = btnAddEstado;
    }
    
    public void publicar(ActionEvent event) {
        
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int idEvtP = Integer.parseInt(params.get("idEvtPublic"));
        Dao dao = new EventSummaryDao();
        ((EventSummaryDao) dao).publicar(idEvtP);
        PrimeFaces.current().ajax().update("pnlUIrepeat");
        
    }
    
    public boolean readyToPublic(int idEvt) {
        boolean returned = false;
        Dao dao = new EventSummaryDao();
        returned = ((EventSummaryDao) dao).readyToPublic(idEvt);
        
        return returned;
    }
    
    public boolean isAlreadyPublic(int idEvento){
        Dao dao = new EventSummaryDao();
        boolean returned = ((EventSummaryDao)dao).isAlreadyPublic(idEvento);
        return returned;
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
    
}
