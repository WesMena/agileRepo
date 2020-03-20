/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.controller;

import com.cci.model.DetalleEvento;
import com.cci.service.Dao;
import com.cci.service.DetalleDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

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

    private String horaEdit = "";
    private String duracionEdit = "";
    private int idEvento;
    private String nombreEvento;

    private List<DetalleEvento> detalles = new ArrayList<>();

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

    public void init() {
        detalles = new ArrayList<>();
        DetalleDao detalle = new DetalleDao(idEvento);
        detalles = detalle.getAll();
    }



    /* Este es el método que envía los Eventos que se obtienen de DetalleDao 
     * a DetalleEvento.xhtml. El parámetro idEvento es la que se encarga de definir 
     * cuales detalles de qué evento se van a cargar
     */
    public List<DetalleEvento> listaDetalles() {
        DetalleDao detalle = new DetalleDao(idEvento);
        return detalle.getAll();
    }

    public DetalleController() {
        this.idEvento = 5;
    }

    public void onLoad() {
        List<DetalleEvento> evts = listaDetalles();
        this.detalles = evts;
        /*
        for (DetalleEvento evt : evts) {
            this.detalles.add(evt);
            System.out.println("" + evt.toString());
        }
         */
    }

    public DetalleController(int idEvento) {
        this.idEvento = idEvento;
    }

    public void redireccionar(int id, String nombre) {
        this.idEvento = id;
        this.nombreEvento = nombre;
        init();
        try {

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

    ///Refresca la pagina de los Slots del Evento 
    public void redireccionar() {

        //this.onLoad();
        try {

            HttpServletRequest request = (HttpServletRequest) FacesContext
                    .getCurrentInstance().getExternalContext().getRequest();

            FacesContext context = FacesContext.getCurrentInstance();
            FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .redirect(
                            request.getContextPath()
                            + String.format("/faces/%s", "DetalleEvento.xhtml"));

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

    public void onReorder() {
        //Actualiza el índice de los slots y dispara un update con ajax para que 
        //Se puedan ver los cambios en las horas al reordenarse(aunque la lógica de eso
        //viene propiamente de init(), que a su vez llama al constructor de DetalleDao)

        updateIndex();
        init();
        PrimeFaces.current().ajax().update("eventos");
    }

    public List<DetalleEvento> getListaDetalles() {
        return detalles;
    }

    public void setListaDetalles(List<DetalleEvento> listaDetalles) {
        this.detalles = listaDetalles;
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

    public String getHoraParam(FacesContext fc) {
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        return params.get("horaSlot");
    }

    public String getDuracionParam(FacesContext fc) {
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        return params.get("duracionSlot");
    }

    public String outcomeDuracion() {
        //Carga los parámetros necesarios para editar la duración de un slot.
        //Envía estos datos al modal editDuracion

        FacesContext fc = FacesContext.getCurrentInstance();

        this.duracionEdit = getDuracionParam(fc);

        this.id = Integer.parseInt(getidParam(fc));
        return "result";
    }

    public String outcomeHora() {

        //Carga los parámetros necesarios para editar la hora inicial.
        //Envía estos datos al modal editHora
        FacesContext fc = FacesContext.getCurrentInstance();
        this.horaEdit = getHoraParam(fc);

        this.id = Integer.parseInt(getidParam(fc));

        return "result";
    }

    ///Les asigna a cada una de las variables los valores tomados del Slot
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

        return colorCategoria;
    }

    public void setColorCategoria(String colorCategoria) {

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

    /// Se actualizan los valores en la base de datos del Slot y se refresca la pantalla con redireccionar()
    public void updateSlot() {
        //Revision de propiedad
        if (this.propiedad(id)) {
            DetalleDao upt = new DetalleDao();
            upt.updateDetalle(this.id, this.titulo, this.descripcion, this.objetivo, this.categoria, this.colorCategoria, this.pasos, this.materiales);
            redireccionar();
        }else{
            redireccionar();
        }
    }

    public void insertSlot() {
        //Revion de propiedad
        if (this.propiedad(idEvento)) {
            DetalleDao upt = new DetalleDao();
            upt.insertarSlot(idEvento);
            redireccionar();
        }else{
            redireccionar();
        }
    }

    public void insertBloque() {
        //Revision de propiedad
        if (this.propiedad(idEvento)) {
            DetalleDao upt = new DetalleDao();
            upt.insertarBloque(idEvento);
            redireccionar();
        }else{
            redireccionar();
        }
    }

    public void updateIndex() {
        //Revision de propiedad
        if (this.propiedad(idEvento)) {
            System.out.print(detalles);

            DetalleDao detalle = new DetalleDao(idEvento);

            int i = 1;

            for (DetalleEvento det : detalles) {
                det.setIndice(i);
                detalle.updateReorder(det);

                i++;
            }
        }
    }

    public String getHoraEdit() {
        return horaEdit;
    }

    public void setHoraEdit(String horaEdit) {
        this.horaEdit = horaEdit;
    }

    public void leer(int idDetalle) {
        System.out.println("Entró");
        System.out.println(idDetalle);

    }

    public void editarHora() {
        //Método que permite editar la hora inicial de un slot, es llamado por el 
        //modal editHora

        //Incluye algunas validaciones
        //Revision de propiedad
        if (this.propiedad(id)) {
            boolean invalido = false;

            String signo = horaEdit.substring(2, 3);

            if (!signo.equalsIgnoreCase(":")) {
                invalido = true;
            }

            if (horaEdit.length() != 5) {
                invalido = true;
            }
            char prueba;
            boolean esNum = false;
            for (int i = 0; i < horaEdit.length(); i++) {
                prueba = horaEdit.charAt(i);
                if (i != 2) {
                    esNum = Character.isDigit(prueba);
                }

                if (esNum == false) {
                    invalido = true;
                }

            }

            if (invalido == false) {
                DetalleDao det = new DetalleDao();
                DetalleEvento detalle = new DetalleEvento(id, horaEdit);
                det.updateHora(detalle);
            }

            init();
            
        }else{
            //No es propietario
            init();
            PrimeFaces.current().ajax().update("eventos");
        }
    }

    public void editarDuracion() {
        //Método que permite editar la duración de un slot, es llamado por el 
        //modal editDuracion
        //Incluye algunas validaciones
        //Revision de propiedad
        if (this.propiedad(id)) {
            DetalleDao det = new DetalleDao();
            int duracion = 0;
            try {
                duracion = Integer.parseInt(duracionEdit);
            } catch (Exception e) {
                init();
                PrimeFaces.current().ajax().update("eventos");

            }

            try {
                if (duracion >= 1) {
                    DetalleEvento detalle = new DetalleEvento(duracion, id);
                    det.updateDuracion(detalle);

                }
                init();

            } catch (Exception e) {
                init();

            }
            PrimeFaces.current().ajax().update("eventos");
        } else {
            //No es propietario
            init();
            PrimeFaces.current().ajax().update("eventos");
        }
    }

    public String getDuracionEdit() {
        return duracionEdit;
    }

    public void setDuracionEdit(String duracionEdit) {
        this.duracionEdit = duracionEdit;
    }

    //Metodo que determina quien esta ingresando a ver los slots
    public boolean propiedad(int evaluarId) {
        Dao dao = new DetalleDao();
        String propietario = "";

        //Chequeando propiedad mediante el ID de slot
        String slotE = ((DetalleDao) dao).propiedadSlot(evaluarId);
        if (!"".equals(slotE)) {
            propietario = slotE;
        }

        //Chequeando propiedad mediante el ID de Evento
        String eventE = ((DetalleDao) dao).propiedadEvento(evaluarId);
        if (!"".equals(eventE)) {
            propietario = eventE;
        }

        return propietario.equals(UsuarioLoginController.UID);
    }

}
