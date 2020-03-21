/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.controller;

import com.cci.model.Cronograma;
import com.cci.model.DetalleEvento;
import com.cci.model.Evento;
import com.cci.service.Dao;
import com.cci.service.DetalleDao;
import com.cci.service.EventDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
@ManagedBean(name = "eventcontroller")
@SessionScoped

public class EventController implements Serializable {

    private Evento delObject = new Evento();
    private String BLACKCOLORCODE = "#000000";
    private String BLUECOLORCODE = "#0388e5";
    private List<Evento> lstEvt = new ArrayList<>();
    private List<Cronograma> lstCrono = new ArrayList<>();

    public List<Cronograma> getLstCrono() {
        return lstCrono;
    }

    public void setLstCrono(List<Cronograma> lstCrono) {
        this.lstCrono = lstCrono;
    }
    
    
    public String getBLACKCOLORCODE() {
        return BLACKCOLORCODE;
    }

    public List<Evento> getLstEvt() {
        return lstEvt;
    }

    public void setLstEvt(List<Evento> lstEvt) {
        this.lstEvt = lstEvt;
    }

    public void setBLACKCOLORCODE(String BLACKCOLORCODE) {
        this.BLACKCOLORCODE = BLACKCOLORCODE;
    }
    private String nombre;
    private String desc;
    private String duracion;
    public static String colorCode = "#0388e5";

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public int getOnRowEdit() {
        return onRowEdit;
    }

    public void setOnRowEdit(int onRowEdit) {
        this.onRowEdit = onRowEdit;
    }
    public static int onRowEdit = 1;
    private String tags;
    private int id;
    private String filtro = "";
    private boolean onEdit = false;

    public EventController() {
        this.lstEvt = listaEventos();
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getNombre() {
        System.out.println("" + this.nombre + " get");
        return nombre;
    }

    public void setNombre(String nombre) {
        System.out.println("" + this.nombre + " set");
        this.nombre = nombre;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isOnEdit() {
        return onEdit;
    }

    public void setOnEdit(boolean onEdit) {
        System.out.println("" + onEdit);
        this.onEdit = onEdit;
        System.out.println("" + this.onEdit);
    }
    
    public void onLoad(){
        this.lstEvt = listaEventos();
    }

    public List<Evento> listaEventos() {
        /*
            Este es el método que envía los Eventos que se obtienen de EventDao 
            al dashboard. Si la variable de clase "filtro" está vacía, muestra todos los 
            eventos existentes, pero si se le envía algo, buscará coincidencias a nivel 
            de nombre y tags
         */

        EventDao eventos = new EventDao(filtro);
        return eventos.getAll();
    }

    public void filtrarEventos() {
        System.out.println("Entró");
        EventDao eventos = new EventDao(filtro);
        lstEvt = eventos.getAll();
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
        System.out.println(filtro + "filtro");
    }

    //Modificacion externa de un Evento
    public void edit(String nombre, String desc, int id, String tags) {
        //Escondiendo el boton
        Dao dao = new EventDao();
        ((EventDao) dao).update(new Evento(nombre, desc, id, tags));
        System.out.println("" + nombre + " entrando a edicion");

    }
    //Modificacion externa de un Evento

    public void edit(Evento ev) {
        //Escondiendo el boton
        Dao dao = new EventDao();
        System.out.println("" + tags + " Tags");
        ((EventDao) dao).update(new Evento(ev.getNombre(), ev.getDesc(), ev.getId(), ev.getLosTags()));
        System.out.println("" + ev.getNombre() + " entrando a edicion");
        System.out.println("" + ev.getDesc());
    }

    public void edit(int i) {
        //Escondiendo el boton
        System.out.println("" + this.lstEvt.get(i).getNombre() + "  LastHope");
    }

    public String getBLUECOLORCODE() {
        return BLUECOLORCODE;
    }

    public void setBLUECOLORCODE(String BLUECOLORCODE) {
        this.BLUECOLORCODE = BLUECOLORCODE;
    }

    public void refrescar() {
        try {
            this.lstEvt = listaEventos();
            HttpServletRequest request = (HttpServletRequest) FacesContext
                    .getCurrentInstance().getExternalContext().getRequest();

            FacesContext context = FacesContext.getCurrentInstance();
            FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .redirect(
                            request.getContextPath()
                            + String.format("/faces/%s", "dashboard.xhtml"));

        } catch (Exception e) {

        }

    }

    public String getidParam(FacesContext fc) {

        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        return params.get("idDel");

    }

    //Metodo que hace de actionListener del menuItem Borrar
    public void deleteListener(ActionEvent event) {
        Evento n = new Evento();
        //Obtencion de un parametro enviado utilizando la etiqueta f:atribute
        n = (Evento) event.getComponent().getAttributes().get("delItem");
        this.delObject = n;

        //Script utilizado para hacer visible el Dialogo
        PrimeFaces.current().executeScript("PF('cd').show()");

    }

    public void delete() {
        //Invocacion del DAO
        Dao dao = new EventDao();
        ((EventDao) dao).delete(this.delObject);
        //Actualizar la lista de eventos despues de la eliminacion
        this.lstEvt = listaEventos();
        //Refresh de la pagina
        refrescar();
    }
    public void agregarEvento() {
        EventDao evtd = new EventDao();
        evtd.nuevoEvento();
        refrescar();
    }
    
    
    /* Construccion del Cronograma*/
    
        public void CronoListener(ActionEvent e){
        DetalleDao dao = new DetalleDao();     
        Cronograma crono=new Cronograma();    
        Evento evt = new Evento(); 
        /*-> Se limpia la lista del Cronograma*/
        this.lstCrono.clear();
        
        List<DetalleEvento> detalles = new ArrayList<>();
       /*-> Se obtiene el objeto de Evento*/  
        evt = (Evento) e.getComponent().getAttributes().get("getEvt");
        /*-> Se Obtiene la lista de todos los detalles*/
        detalles = dao.todo();
        
         for(int i=0; i<= detalles.size()-1;i++){
             /*-> Se filtra por ID de Evento y si el slot está activo */
             if(detalles.get(i).getEvento() == evt.getId() && detalles.get(i).esBloque() == true &&  detalles.get(i).getBorrado()!= 1){
                 
                 crono = new Cronograma(detalles.get(i).getIndice(),detalles.get(i).getTitulo(),true);
                 this.lstCrono.add(crono);
                 System.out.println("Se metio bloque " +crono.getOrd() +" en ciclo " +i);
             }else
             
            if(detalles.get(i).getEvento() == evt.getId() && detalles.get(i).getBorrado()!= 1){
                /*-> Se agrega a la lista*/
                
                crono=new Cronograma(detalles.get(i).getIndice(),detalles.get(i).getTitulo(),false); 
                crono.setHoraInicio(detalles.get(i).getHoraInicioStr());
                crono.setHoraFinal((dao.recalcularHora(detalles.get(i).getHoraInicioStr(), detalles.get(i).getDuracion())));
                crono.setColor(detalles.get(i).getColorCategoria());
                crono.setDuracion(detalles.get(i).getDuracion());

              this.lstCrono.add(crono);
                 System.out.println("Se metio slot " +crono.getOrd() +"Titulo: "+crono.getTitulo()+" en ciclo " +i);
                /*
              this.lstCrono.add(new Cronograma(detalles.get(i).getIndice(),detalles.get(i).getTitulo(),detalles.get(i).getHoraInicioStr(),(dao.recalcularHora(detalles.get(i).getHoraInicioStr(), detalles.get(i).getDuracion())),detalles.get(i).getColorCategoria(),detalles.get(i).getDuracion())); 
                */
            }
                
             System.out.println(" -> Lista en Ciclo: "+i +" <-");
             for(int y=0; y<= this.lstCrono.size()-1;y++){
             System.out.println(this.lstCrono.get(y).getOrd()+") "+this.lstCrono.get(y).getTitulo());    
             }
             
        }
               
        /*-> Se Refresca los componentes y se abre el modal*/
        PrimeFaces.current().ajax().update("frmDlg:dlg1");
        openModal();
    }
  
    /* Abro el modal del Cornograma*/
    public void openModal(){
          PrimeFaces.current().executeScript("PF('dlg2').show()");
    }
    
  
    
}
