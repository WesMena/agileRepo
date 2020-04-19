/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.controller;

import com.OtherSource.FiltroDeAcceso;
import static com.cci.controller.EventWizardImagesController.profileImage;
import static com.cci.controller.EventWizardImagesController.upLoadedStream;
import com.cci.model.ConfigUbiHora;
import java.io.Serializable;
import org.primefaces.event.FlowEvent;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;

import com.cci.model.UbiHoraConfig;
import com.cci.model.UbiHoraContainer;
import com.cci.model.Usuario;
import com.cci.model.ZonaHoraria;
import com.cci.model.horarioCompleto;
import com.cci.model.zonaPais;
import com.cci.service.DetalleDao;
import com.cci.service.WizardDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.util.Date;
import java.text.ParseException;
import static java.time.Instant.now;
import java.util.HashSet;
import java.util.Random;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import static org.primefaces.behavior.confirm.ConfirmBehavior.PropertyKeys.message;
import com.cci.model.Entrada;
import com.cci.service.EntradaDao;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.UploadedFile;
import com.cci.model.InfoBasica;
import com.cci.service.ConfigUbiHoraDao;
import com.cci.service.Dao;
import com.cci.service.InfoBasicaDao;
import com.sun.faces.facelets.tag.ui.ComponentRef;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.PrimeFaces;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean(name = "wizardcontroller")
@SessionScoped
public class eventWizardViewController implements Serializable {

    public boolean tabImgPrincp = false;
    public String descripcion = "";
    public String resumen = "";
    public static Integer idEvento = -1;
   
    public static boolean editionMode = false;

    public String onFlowProcess(FlowEvent event) {

        return event.getNewStep();
    }

    public eventWizardViewController() {

    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Variables">
    private List<Tag> tags = new ArrayList<>();
    private String newTag;
    private String strTag = "0";

    //Hacer los cambios que haga falta
    private String descOrganizador;
    private String fechaFinStr;
    private String fechaIniStr;
    private String nombreOrganizador;
    private String nombreEvento;
    private String tipoEvento;
    private boolean skip = false;
    private boolean isFisico = false;
    private boolean isLink = true;

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Tab Ubicacion y hora">
    public boolean isIsFisico() {
        return isFisico;
    }

    public String getFechaFinStr() {
        return fechaFinStr;
    }

    public void setFechaFinStr(String fechaFinStr) {
        this.fechaFinStr = fechaFinStr;
    }

    public String getFechaIniStr() {
        return fechaIniStr;
    }

    public void setFechaIniStr(String fechaIniStr) {
        this.fechaIniStr = fechaIniStr;
    }

    public boolean isTabImgPrincp() {
        return tabImgPrincp;
    }

    public void setTabImgPrincp(boolean tabImgPrincp) {
        this.tabImgPrincp = tabImgPrincp;
    }

    public boolean isEditionMode() {
        return editionMode;
    }

    public void setEditionMode(boolean editionMode) {
        eventWizardViewController.editionMode = editionMode;
    }

    public void setIsFisico(boolean isFisico) {
        this.isFisico = isFisico;
    }

    public boolean isIsLink() {
        return isLink;
    }

    public void setIsLink(boolean isLink) {
        this.isLink = isLink;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;

    }

    public String getDescripcion() {

        return descripcion;
    }

    public void setDescripcion(String descripcion) {

        this.descripcion = descripcion;
        System.out.println(descripcion);
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {

        this.resumen = resumen;
    }

    public void cambioDesc(ValueChangeEvent e) {

        //Método que se dispara al escribir en el textEditor de la descripción
        //funciona con un valueChangeListener
        this.descripcion = e.getNewValue().toString();

    }

    public void cambioResumen(ValueChangeEvent e) {

        //Método que se dispara al escribir en el inputTextArea del resumen
        //funciona con un valueChangeListener
        this.resumen = e.getNewValue().toString();

    }

    /*Valores de Ubicacion y hora*/
    private String nombre;
    /* <- Nombre de la configuracion */
    private String ubi;
    /* <- Ubicacion Fisica de la configuracion */
    private String link;/* <- Ubicacion del Link de la configuracion */
    private horarioCompleto horario = new horarioCompleto();/* <- Objeto para armar la lista de Zonas horarias */
    private Date ini;/* <- Hora Inicio */
    private Date fin;/* <- Hora Final */
    private String strHini;/* <- String de la Hora de Inicio */
    private String strHfin;/* <- String de la Hora de Final */
    private String strIni;/* <- String de la fecha de Inicio */
    private String strFin;/* <- String de la fecha de Final */
    private Date Fini;
    private Date Ffin;
    private boolean fisico;
    private int idEvnt;
    private UbiHoraConfig config;
    private boolean draft = true;
    private boolean savedConfig = false;

    /*Lista de las diferentes zonas del mundo*/
    public List<ZonaHoraria> lstZona = new ArrayList<>();
    /*Lista de los Paises por zona horaria*/
    public List<zonaPais> lstPais = new ArrayList<>();
    /*Lista de las diferentes zonas horarias junto con sus paies*/
    public List<horarioCompleto> lstcmpt = new ArrayList<>();
    /*Lista contenedora de los diferentes objetos creados con la informacion del evento*/
    public List<UbiHoraContainer> lstContainer = new ArrayList<>();
    /*Lista con el orden de la configuracion*/
    public List<UbiHoraContainer> lstOrdenada = new ArrayList<>();
    /*Rango de fechas del evento*/
    private List<Date> range;

    public UbiHoraConfig getConfig() {
        return config;
    }

    //<editor-fold defaultstate="collapsed" desc="Getter Setter">
    public boolean isDraft() {
        return draft;
    }

    public void setDraft(boolean draft) {
        this.draft = draft;
    }

    public boolean isSavedConfig() {
        return savedConfig;
    }

    public void setSavedConfig(boolean savedConfig) {
        this.savedConfig = savedConfig;
    }

    public void setConfig(UbiHoraConfig config) {
        this.config = config;
    }

    public String getStrHini() {
        return strHini;
    }

    public void setStrHini(String strHini) {
        this.strHini = strHini;
    }

    public String getStrHfin() {
        return strHfin;
    }

    public void setStrHfin(String strHfin) {
        this.strHfin = strHfin;
    }

    public Date getFini() {
        return Fini;
    }

    public void setFini(Date Fini) {
        this.Fini = Fini;
    }

    public Date getFfin() {
        return Ffin;
    }

    public void setFfin(Date Ffin) {
        this.Ffin = Ffin;
    }

    public int getIdEvnt() {
        return idEvnt;
    }

    public void setIdEvnt(int idEvnt) {
        this.idEvnt = idEvnt;
    }

    public String getStrIni() {
        return strIni;
    }

    public void setStrIni(String strIni) {
        this.strIni = strIni;
    }

    public String getStrFin() {
        return strFin;
    }

    public void setStrFin(String strFin) {
        this.strFin = strFin;
    }

    public List<Date> getRange() {
        return range;
    }

    public void setRange(List<Date> range) {
        this.range = range;
    }

    public List<UbiHoraContainer> getLstOrdenada() {
        return lstOrdenada;
    }

    public void setLstOrdenada(List<UbiHoraContainer> lstOrdenada) {
        this.lstOrdenada = lstOrdenada;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbi() {
        return ubi;
    }

    public void setUbi(String ubi) {
        this.ubi = ubi;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public horarioCompleto getHorario() {
        return horario;
    }

    public void setHorario(horarioCompleto horario) {
        this.horario = horario;
    }

    public Date getIni() {
        return ini;
    }

    public void setIni(Date ini) {
        this.ini = ini;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public List<UbiHoraContainer> getLstContainer() {
        return lstContainer;
    }

    public void setLstContainer(List<UbiHoraContainer> lstContainer) {
        this.lstContainer = lstContainer;
    }

    public boolean isFisico() {
        return fisico;
    }

    public void setFisico(boolean fisico) {
        this.fisico = fisico;
    }

    public List<ZonaHoraria> getLstZona() {
        return lstZona;
    }

    public void setLstZona(List<ZonaHoraria> lstZona) {
        this.lstZona = lstZona;
    }

    public List<zonaPais> getLstPais() {
        return lstPais;
    }

    public void setLstPais(List<zonaPais> lstPais) {
        this.lstPais = lstPais;
    }

    public List<horarioCompleto> getLstcmpt() {
        return lstcmpt;
    }

    public void setLstcmpt(List<horarioCompleto> lstcmpt) {
        this.lstcmpt = lstcmpt;
    }
//</editor-fold>

    StringBuffer stringBuffer = new StringBuffer();

    /*Esta funcion llena el combo box de las zonas horarias*/
 /*La informacion de las zonas se trae de la BD*/
 /*Se verifica que el ID de la zona sea la misma y se concatena con el Pais para generar el string*/
    public void llenarCombo() {
        lstcmpt.clear();

        WizardDao wiz = new WizardDao();

        lstZona = wiz.getzonas();
        lstPais = wiz.getpaises();

        for (int i = 0; i <= lstZona.size() - 1; i++) {
            int valZona = lstZona.get(i).getId();

            for (int y = 0; y <= lstPais.size() - 1; y++) {

                int valPais = lstPais.get(y).getId();

                if (valZona == valPais) {

                    lstcmpt.add(new horarioCompleto(lstZona.get(i).getZona().toString() + "-" + lstPais.get(y).getPais().toString()));
                }

            }
        }
    }

    /*Funcion para dar formato a la hora*/
    public String format(Date fecha) {
        String hora = "";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");

        hora = String.valueOf(simpleDateFormat.format(fecha));
        return hora;

    }

    /*Funcion para dar formato a la fecha*/
    public String formatFecha(Date fecha) {
        String fechaN = "";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        fechaN = String.valueOf(simpleDateFormat.format(fecha));
        return fechaN;

    }

    public Date formatearHora(String hora) throws ParseException {
        Date date = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(hora);
        System.out.println("-> Hora Formateada: " + date.toString());
        return date;
    }

    /*Funcion para actualizar GUI*/
    private void updateUI() {

        PrimeFaces.current().ajax().update("publicarEventos:ubi-hora");
        PrimeFaces.current().ajax().update("publicarEvento");
    }

    /*Funcion para setear las fechas en que Inicia y finaliza el evento*/
    private void setearFechas(List<Date> list) {

        for (int i = 0; i <= list.size() - 1; i++) {

            if (i == 0) {
                this.Fini = list.get(i);
            } else if (i == list.size() - 1) {
                this.Ffin = list.get(i);
            }

        }

    }

    /*Genera un ID random para prueba*/
    public void ranID() {
        Random rand = new Random();
        this.idEvnt = rand.nextInt(1000);
    }

    public void displayError() {
        PrimeFaces.current().executeScript("PF('ErrorMsg').show()");
    }

    public boolean verificarFecha(Date ini, Date fin) {
        boolean value = true;
        Calendar c = Calendar.getInstance();
        /*Si el año de inicio mayor al final*/
        if (ini.getYear() > fin.getYear()) {

            value = false;
            /* Si el año inicial es menor al año final hace las otras validaciones*/
        } else if (ini.getYear() < fin.getYear()) {

            /*Si es en el mismo año*/
            if (ini.getYear() == fin.getYear()) {

                if (ini.getMonth() > fin.getMonth()) {
                    value = false;
                }
            }
            /*Si alguno de los Dias no es valido*/
        } else if (ini.getDay() > 31 || fin.getDay() > 31) {
            value = false;
        } else if (ini.getYear() < c.getTime().getYear() || fin.getYear() < c.getTime().getYear()) {
            value = false;
        } else if (ini.getMonth() > 12 && fin.getMonth() > 12) {
            value = false;
        }

        return value;
    }

    /*!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/
 /*!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/
 /*!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/
 /*||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||*/
 /*vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv*/
 /*Genera el objeto con el contenido requerido de Fechas,Horas y ubicacion*/
    public UbiHoraConfig fillContainer(ActionEvent e) {
        WizardDao dao = new WizardDao();
        boolean value = true;
        UbiHoraConfig container = null;
        //System.out.println(""+this.range);
        String[] splitFIni = this.fechaIniStr.split("-");
        String[] splitFFin = this.fechaFinStr.split("-");

        Calendar c = Calendar.getInstance();
        c.set(Integer.parseInt(splitFIni[2]), Integer.parseInt(splitFIni[1]), Integer.parseInt(splitFIni[0]));
        Fini = c.getTime();
        Fini.setMonth(Fini.getMonth() - 1);

        c.set(Integer.parseInt(splitFFin[2]), Integer.parseInt(splitFFin[1]), Integer.parseInt(splitFFin[0]));
        Ffin = c.getTime();
        Ffin.setMonth(Ffin.getMonth() - 1);

        value = verificarFecha(Fini, Ffin);

        if (value == true) {
            container = new UbiHoraConfig(this.idEvento, this.horario.getHorarioStr().toString(), this.strHini, this.strHfin, this.fisico, this.Fini, this.Ffin);

            if (this.fisico == true) {
                container.setUbifisica(this.ubi);
                container.setLink("NONE");
                System.out.println(" -> Container Creado!");
                System.out.println("Ubicacion: " + container.getUbifisica());
                System.out.println("Zona Horaria: " + container.getZonaHoraria());

            } else {
                container.setLink(this.link);
                container.setUbifisica("NONE");
                System.out.println(" -> Container Creado!");
                System.out.println("Link: " + container.getLink());
                System.out.println("Zona Horaria: " + container.getZonaHoraria());
            }

            try {
                dao.save(container);
                this.config = container;
                this.draft = false;
                this.savedConfig = true;
                PrimeFaces.current().ajax().update("test1:Todo");
            } catch (Exception x) {
                System.out.println("Error!");
            }
            /**/
        } else {
            displayError();
        }
        return container;
    }

    /*!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/
 /*!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/
 /*!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/
 /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
 /*||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||*/
    public void updtConfig(ActionEvent e) throws ParseException {

        WizardDao dao2 = new WizardDao();
        boolean value = true;

        if (dao2.savedUbiConfig(idEvento)) {
            //Algo guardado
            PrimeFaces.current().ajax().update("test1:Todo");
            WizardDao dao = new WizardDao();
            String[] splitFIni = this.fechaIniStr.split("-");
            String[] splitFFin = this.fechaFinStr.split("-");

            Calendar c = Calendar.getInstance();
            c.set(Integer.parseInt(splitFIni[2]), Integer.parseInt(splitFIni[1]), Integer.parseInt(splitFIni[0]));
            Fini = c.getTime();
            Fini.setMonth(Fini.getMonth() - 1);

            c.set(Integer.parseInt(splitFFin[2]), Integer.parseInt(splitFFin[1]), Integer.parseInt(splitFFin[0]));
            Ffin = c.getTime();
            Ffin.setMonth(Ffin.getMonth() - 1);

            value = verificarFecha(Fini, Ffin);

            if (value == true) {

                UbiHoraConfig container = new UbiHoraConfig(this.idEvento, this.horario.getHorarioStr().toString(), this.strHini, this.strHfin, this.fisico, this.Fini, this.Ffin);

                if (this.fisico == true) {
                    container.setUbifisica(this.ubi);
                    container.setLink("NONE");
                    System.out.println(" -> Container Creado!");
                    System.out.println("Ubicacion: " + container.getUbifisica());
                    System.out.println("Zona Horaria: " + container.getZonaHoraria());

                } else {
                    container.setLink(this.link);
                    container.setUbifisica("NONE");
                    System.out.println(" -> Container Creado!");
                    System.out.println("Link: " + container.getLink());
                    System.out.println("Zona Horaria: " + container.getZonaHoraria());
                }

                dao.updateUbiHora(container, container.getEvntID());
                System.out.println("Editado!");

            } else {
                //Nada guardado
                 displayError();
                fillContainer(new ActionEvent(new ComponentRef()));
            }
        }

        }

    

    public void saveMessage() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Informacioón Guardada!"));

    }

    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    public StringBuffer getStringBuffer() {
        return stringBuffer;
    }

    public void setStringBuffer(StringBuffer stringBuffer) {
        this.stringBuffer = stringBuffer;
    }

    private List<Entrada> lstEntrada = new ArrayList<>();
    String nombreEntrada;
    double precioEntrada;
    String fechaFinEntrada;
    String horaFinEntrada;
    String fechaInicioEntrada;
    String horaInicioEntrada;
    Integer tipoEntrada;
    Integer cantidadEntrada;
    Integer indexEntrada;
    Boolean editarDivRend = false;
    Boolean validarEntradas = false;

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="comment">
    //Metodo nuevaEntrada, ingresa un Objeto Entrada en el ArrayList lstEntrada con informacion default
    //Metodo seleccionarEntrada, guarda los valores de la entrada seleccionada en las variables y cambia el valor de editarDivRend a true para que muestre la seccion de Editar
    //setTipoEntrada, se asegura que si el valor es 0, cambia el valor de PrecioEntrada a 0.00
    //Metodo actualizarEntrada, cambia los valores del Objeto Entrada seleccionado por los nuevos valores ingresados y cambia el valor de editarDivRend a false para que deje de mostrar la seccion de Editar
    //Metodo borrarEntrada, borra el Objeto Entrada del ArrayList lstEntrada y cambia el valor de editarDivRend a false para que deje de mostrar la seccion de Editar
    //Metodo cerrarEntrada, Cierra la seccion editar entrada sin aplicar cambios
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Metodos">
    public void nuevaEntrada() {

        Entrada nuevaE = new Entrada("Admisión General", 0.00, this.fechaFinStr, this.strHfin, this.fechaIniStr, this.strHini, 0, 1, -1);
        this.lstEntrada.add(nuevaE);

        System.out.println("ESTE EL TAMANO DE LA LISTA AL CREAR NUEVA: " + lstEntrada.size());

    }

    public List<Entrada> getLstEntrada() {
        return lstEntrada;
    }

    public void setLstEntrada(List<Entrada> lstEntrada) {
        this.lstEntrada = lstEntrada;
    }

    public String getNombreEntrada() {
        return nombreEntrada;
    }

    public void setNombreEntrada(String nombreEntrada) {
        this.nombreEntrada = nombreEntrada;
    }

    public void seleccionarEntrada(String nombreEntrada, Integer tipoEntrada, Integer Cantidad, double Precio, String fechaInicioEntrada, String horaInicioEntrada, String fechaFinEntrada, String horaFinEntrada, Integer indexE) {
        this.validarEntradas = true;
        this.nombreEntrada = nombreEntrada;
        this.tipoEntrada = tipoEntrada;
        this.cantidadEntrada = Cantidad;
        this.precioEntrada = Precio;
        this.fechaInicioEntrada = fechaInicioEntrada;
        this.horaInicioEntrada = horaInicioEntrada;
        this.fechaFinEntrada = fechaFinEntrada;
        this.horaFinEntrada = horaFinEntrada;
        this.indexEntrada = indexE;

        this.editarDivRend = true;
    }

    public double getPrecioEntrada() {
        return precioEntrada;
    }

    public void setPrecioEntrada(double precioEntrada) {

        this.precioEntrada = precioEntrada;
    }

    public String getFechaFinEntrada() {
        return fechaFinEntrada;
    }

    public void setFechaFinEntrada(String fechaFinEntrada) {

        this.fechaFinEntrada = fechaFinEntrada;
    }

    public String getHoraFinEntrada() {
        return horaFinEntrada;
    }

    public void setHoraFinEntrada(String horaFinEntrada) {
        this.horaFinEntrada = horaFinEntrada;
    }

    public String getFechaInicioEntrada() {
        return fechaInicioEntrada;
    }

    public void setFechaInicioEntrada(String fechaInicioEntrada) {
        this.fechaInicioEntrada = fechaInicioEntrada;
    }

    public String getHoraInicioEntrada() {
        return horaInicioEntrada;
    }

    public void setHoraInicioEntrada(String horaInicioEntrada) {
        this.horaInicioEntrada = horaInicioEntrada;
    }

    public Integer getTipoEntrada() {
        return tipoEntrada;
    }

    public void setTipoEntrada(Integer tipoEntrada) {
        this.tipoEntrada = tipoEntrada;
        if (this.tipoEntrada == 0) {
            this.precioEntrada = 0.00;
        }

    }

    public Integer getCantidadEntrada() {
        return cantidadEntrada;
    }

    public void setCantidadEntrada(Integer cantidadEntrada) {
        this.cantidadEntrada = cantidadEntrada;
    }

    public Boolean getEditarDivRend() {
        return editarDivRend;
    }

    public void setEditarDivRend(Boolean editarDivRend) {
        this.editarDivRend = editarDivRend;
    }

    public Boolean getValidarEntradas() {
        return validarEntradas;
    }

    public void setValidarEntradas(Boolean validarEntradas) {
        this.validarEntradas = validarEntradas;
    }

    public void actualizarEntrada() {
        lstEntrada.get(this.indexEntrada).setNombre(this.nombreEntrada);
        lstEntrada.get(this.indexEntrada).setTipo(this.tipoEntrada);
        lstEntrada.get(this.indexEntrada).setCantidad(this.cantidadEntrada);
        lstEntrada.get(this.indexEntrada).setPrecio(this.precioEntrada);
        lstEntrada.get(this.indexEntrada).setFechaInicio(this.fechaInicioEntrada);
        lstEntrada.get(this.indexEntrada).setHoraInicio(this.horaInicioEntrada);
        lstEntrada.get(this.indexEntrada).setFechaFin(this.fechaFinEntrada);
        lstEntrada.get(this.indexEntrada).setHoraFin(this.horaFinEntrada);

        this.editarDivRend = false;
        this.validarEntradas = false;

        System.out.println("ESTE EL TAMANO DE LA LISTA AL ACTUALIZAR: " + lstEntrada.size());
    }

    public void borrarEntrada() {
        this.validarEntradas = false;
        lstEntrada.remove(lstEntrada.get(this.indexEntrada));
        this.editarDivRend = false;

    }

    public void cerrarEntrada() {
        this.validarEntradas = false;
        this.editarDivRend = false;

    }

    public void guardarEntradas() {

        System.out.println("ESTE EL TAMANO DE LA LISTA AL GUARDAR ANTES DE DELETE: " + lstEntrada.size());
        EntradaDao eDao = new EntradaDao();
        //Eliminando entradas anteriores
        //eDao.deleteAllByIdEvt(idEvento);
        //Insertando nuevas entradas

        System.out.println("ESTE EL TAMANO DE LA LISTA AL GUARDAR DESPUES DE DELETE: " + lstEntrada.size());

        for (Entrada ev : lstEntrada) {
            if (ev.getIdEntrada() != -1) {
                //Pedimos ID de entrada y forzamos ID
                System.out.println("ID" + ev.getIdEntrada());
                eDao.entradExistente(ev.getIdEntrada(), this.idEvento, ev.getNombre(), ev.getPrecio(), FechaCambiarIngresoBD(ev.getFechaFin()), ev.getHoraFin(), FechaCambiarIngresoBD(ev.getFechaInicio()), ev.getHoraInicio(), ev.getTipo(), ev.getCantidad());

            } else {
                eDao.nuevaEntrada(this.idEvento, ev.getNombre(), ev.getPrecio(), FechaCambiarIngresoBD(ev.getFechaFin()), ev.getHoraFin(), FechaCambiarIngresoBD(ev.getFechaInicio()), ev.getHoraInicio(), ev.getTipo(), ev.getCantidad());
            }
        }
        Dao dao = new EntradaDao();
        //Recargamos la lista 
        List<Entrada> tempE = new ArrayList<>();
        tempE = ((EntradaDao) dao).getAllByIdEvt(idEvento);
        this.lstEntrada.clear();
        for (Entrada e : tempE) {
            lstEntrada.add(e);
        }
        System.out.println("ESTE EL TAMANO DE LA LISTA AL GUARDAR DESPUES DE GUARDAR: " + lstEntrada.size());

    }

    public String BotonEntrada() {

        if (lstEntrada.size() > 0 && idEvento >= 1) {

            return "false";

        } else {

            return "true";

        }
    }

    public String BotonCrearEntrada() {
        if (idEvento != -1) {

            return "false";

        } else {

            return "true";

        }

    }

    public String getNombreOrganizador() {
        return nombreOrganizador;
    }

    public void setNombreOrganizador(String nombreOrganizador) {
        this.nombreOrganizador = nombreOrganizador;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public String getStrTag() {
        return strTag;
    }

    public void setStrTag(String strTag) {
        this.strTag = strTag;
    }

    public String getNewTag() {
        return newTag;
    }

    public void setNewTag(String newTag) {

        this.newTag = newTag.replaceAll("\\s+", "");

    }

    public List<Tag> getTags() {
        return tags;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Metodos">
    public void redireccionar(String xhtml) {

        try {

            HttpServletRequest request = (HttpServletRequest) FacesContext
                    .getCurrentInstance().getExternalContext().getRequest();

            FacesContext context = FacesContext.getCurrentInstance();

            FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .redirect(
                            request.getContextPath()
                            + String.format("/faces/%s", xhtml));
            //Cambiar

        } catch (Exception e) {

        }

    }

    public void finalizarConfiguracion(ActionEvent event) {

        cleanBean();
        redireccionar("dashEventosCreados.xhtml");
    }

    public void cleanBean() {
        //<editor-fold defaultstate="collapsed" desc="Limpieza infoBasica">
        this.descOrganizador = "";
        this.nombreEvento = "";
        this.newTag = "";
        this.tipoEvento = "";
        this.strTag = "0";
        InputStream iniIm = FiltroDeAcceso.class.getClassLoader().getResourceAsStream("com/OtherSource/nonuser.jpg");
        EventWizardImagesController.uploadedFile = null;
        EventWizardImagesController.upLoadedStream = null;
        EventWizardImagesController.profileImage = new DefaultStreamedContent(iniIm, "image/jpeg");

        this.tags = new ArrayList<>();
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Limpieza Imagen principal y secundaria">
        portadaController.upLoadedStream = null;
        portadaController.upLoadedStream2 = null;
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Limpieza resumen">
        this.resumen = "";
        this.descripcion = "";
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Limpieza entradas">
        this.lstEntrada = new ArrayList<>();
        this.cantidadEntrada = 0;
        this.nombreEntrada = "";
        this.tipoEntrada = null;
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Limpiar Ubicacion y horario">
        this.fisico = false;
        this.isFisico = false;
        this.isLink = true;
        this.strHfin = "";
        this.strHini = "";
        this.ubi = "";
        this.link = "";
        this.fechaFinStr = "";
        this.fechaIniStr = "";
        //</editor-fold>
    }

    public void saveInfoBasica(ActionEvent event) {

        List<String> tagStr = new ArrayList<>();
        Dao dao = new InfoBasicaDao();
        if (this.tags.isEmpty()) {
            //Mensaje de error
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("tagError", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Agregue al menos un tag.", ""));
        } else if (EventWizardImagesController.upLoadedStream == null) {
            //Mensaje de error
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("tagError", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Escoja una imagen que le represente.", ""));
        } else {
            //Todo al 100 
            this.tags.forEach((t) -> {
                tagStr.add(t.getTag());
            });

            if (idEvento != -1) {
                //Update :)
                this.tabImgPrincp = true;
                System.out.println("TB:" + tabImgPrincp);
                ((InfoBasicaDao) dao).update(new InfoBasica(this.tipoEvento, this.nombreEvento, this.nombreOrganizador, tagStr, EventWizardImagesController.upLoadedStream, this.descOrganizador));
                PrimeFaces.current().ajax().update("test1:crearEntrada");

            } else {
                this.tabImgPrincp = true;
                System.out.println("TB:" + tabImgPrincp);
                ((InfoBasicaDao) dao).save(new InfoBasica(this.tipoEvento, this.nombreEvento, this.nombreOrganizador, tagStr, EventWizardImagesController.upLoadedStream, this.descOrganizador));
                PrimeFaces.current().ajax().update("test1:crearEntrada");

            }
        }
    }

    public void addTag(ActionEvent e) {
        if (this.tags.size() == 10) {
            //Mensaje de error
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("tagError", new FacesMessage(FacesMessage.SEVERITY_ERROR, "El límite de tags para un evento corresponde a 10.", ""));
        } else {

            //Guardando el tag en la lista
            this.tags.add(new Tag(this.tags.size(), this.newTag));
            this.strTag = String.valueOf(tags.size());
            //Limpiando el tag
            this.newTag = "";

            PrimeFaces.current().ajax().update("test1:tagListDiv");
            PrimeFaces.current().ajax().update("test1:eventoTagC");
            PrimeFaces.current().ajax().update("test1:eventoTagCc");

        }
    }

    public void deleteTag(ActionEvent e) {
        //Obteniendo el atributo asociado a un objeto de tipo tag mediante el evento
        Tag t = new Tag();
        t = (Tag) e.getComponent().getAttributes().get("delTag");
        System.out.println("Tag del : " + t.id);
        //Eliminando de la lista
        this.tags.remove(t);
        this.strTag = String.valueOf(tags.size());
        //updateTagIndex();
        PrimeFaces.current().ajax().update("test1:tagListDiv");
        PrimeFaces.current().ajax().update("test1:counterContainerTag");
        PrimeFaces.current().ajax().update("messageErrorTag");
    }

    public void onLoadUpdateUI() {
        if (editionMode) {
            PrimeFaces.current().ajax().update("test1:tagListDiv");
            PrimeFaces.current().ajax().update("test1:counterContainerTag");
        }
    }

    @PreDestroy
    public void destroy() {
        //finalizarConfiguracion(new ActionEvent(new ComponentRef()));
    }

    public void onLoad() {
        Dao dao = new InfoBasicaDao();
        this.nombreOrganizador = UsuarioLoginController.displayName;
        if (editionMode) {
            if (((InfoBasicaDao) dao).infoExists(idEvento)) {
                //El evento existe

                //Carga de datos al bean
                //<editor-fold defaultstate="collapsed" desc="Informacion general del evento">
                Optional infoOp = ((InfoBasicaDao) dao).get(idEvento);
                InfoBasica infoB = (InfoBasica) infoOp.get();
                this.descripcion = infoB.getDescripcion();
                this.nombreEvento = infoB.getNombre();
                this.resumen = infoB.getResumen();
                this.tipoEvento = infoB.getTipo();
                //Portadas
                try {
                    String[] splitter = infoB.getPortadaDir().split("/");
                    String[] splitter2 = infoB.getImgSecDir().split("/");
                    portadaController.upLoadedStream = new FileInputStream(new File(Constantes.ubicacionFotos + "/" + splitter[2]));
                    portadaController.upLoadedStream2 = new FileInputStream(new File(Constantes.ubicacionFotos + "/" + splitter2[2]));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(eventWizardViewController.class.getName()).log(Level.SEVERE, null, ex);
                }

                portadaController.fotoPrincipal = new DefaultStreamedContent(portadaController.upLoadedStream, "image/jpeg");
                portadaController.fotoSecundaria = new DefaultStreamedContent(portadaController.upLoadedStream2, "image/jpeg");

                //Tags del evento
                this.tags = new ArrayList<>();
                int tagsSize = 0;
                System.out.println("Tags : " + infoB.getTags());
                for (String s : infoB.getTags()) {
                    this.newTag = s;
                    this.tags.add(new Tag(tagsSize, s));
                    tagsSize++;
                    this.newTag = "";
                }
                this.strTag = String.valueOf(tags.size());

                //Imagen y descripcion del organizador
                this.descOrganizador = infoB.getDescOrganizadorUp();
                EventWizardImagesController.upLoadedStream = infoB.getFoto();
                EventWizardImagesController.profileImage = new DefaultStreamedContent(EventWizardImagesController.upLoadedStream, "image/jpeg");
                //</editor-fold>

                //<editor-fold defaultstate="collapsed" desc="Entradas a evento">
                dao = new EntradaDao();
                //this.lstEntrada = new ArrayList<>();
                this.lstEntrada = ((EntradaDao) dao).getAllByIdEvt(idEvento);
                //List<Entrada> tempEntrada = ((EntradaDao) dao).getAllByIdEvt(idEvento);
                /*for (Entrada e : tempEntrada) {
                    this.lstEntrada.add(e);
                }*/
                
                EntradaDao comp = new EntradaDao();
                
                for (Entrada ent : lstEntrada) {

                    ent.setCantComprada(comp.compradas(ent.getIdEntrada()));

                }

                System.out.println("Entradas : " + this.lstEntrada.size());
                //</editor-fold>

                //<editor-fold defaultstate="collapsed" desc="Ubicacion y horario del evento">
                ConfigUbiHora configUH;
                dao = new ConfigUbiHoraDao();
                configUH = (ConfigUbiHora) ((ConfigUbiHoraDao) dao).get(idEvento).get();
                if (configUH.isIsFisico()) {
                    this.fisico = true;
                    this.isFisico = true;
                    this.isLink = false;
                    this.ubi = configUH.getUbicacion();
                } else {
                    this.fisico = false;
                    this.isFisico = false;
                    this.isLink = true;
                    this.link = configUH.getLink();
                }
                this.strHini = configUH.getHoraInicio();
                this.strHfin = configUH.getHoraFin();

                SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
                if (configUH.getRangoFechas().get(0) == null) {
                    this.fechaFinStr = "";
                    this.fechaIniStr = "";
                } else {
                    this.fechaIniStr = formatoFecha.format(configUH.getRangoFechas().get(0));
                    this.fechaFinStr = formatoFecha.format(configUH.getRangoFechas().get(1));
                }

                //this.range = configUH.getRangoFechas();
                //</editor-fold>
            } else {
                //El evento no existe
                FacesContext facesContext = FacesContext.getCurrentInstance();
                ExternalContext externalContext = facesContext.getExternalContext();
                externalContext.setResponseStatus(HttpServletResponse.SC_NOT_FOUND);
                try {
                    externalContext.dispatch("404.xhtml");
                } catch (IOException ex) {
                    Logger.getLogger(eventWizardViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
                facesContext.responseComplete();
            }
        } else {
            System.out.println("ID ENTRANTE CREACION : " + idEvento);
            if (idEvento == -1) {
                cleanBean();
            }
        }
    }

    public String handleFlow(FlowEvent event) {
        String currentStepId = event.getOldStep();
        String stepToGo = event.getNewStep();
        if (skip) {
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Inner class">
    //Clase interna porque son las 2am y que weba dar un click hacia los modelos
    public class Tag {

        private int id;
        private String tag;

        public Tag() {
        }

        ;
        
        public Tag(int id, String tag) {
            this.id = id;
            this.tag = tag;
        }

        public int getId() {
            return id;

        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }
    }
    //</editor-fold>

    public String getDescOrganizador() {
        return descOrganizador;
    }

    public void setDescOrganizador(String descOrganizador) {
        this.descOrganizador = descOrganizador;
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public Integer getIndexEntrada() {
        return indexEntrada;
    }

    public void setIndexEntrada(Integer indexEntrada) {
        this.indexEntrada = indexEntrada;
    }

    public void setDescripcionResumen() {
        WizardDao wiz = new WizardDao();
        wiz.enviarDescripcionResumen(idEvento, descripcion, resumen);
    }

    public void switchState() {
        System.out.println(String.valueOf(this.fisico));
        if (this.fisico == true) {
            this.isFisico = true;
            this.isLink = false;
        } else {
            this.isFisico = false;
            this.isLink = true;
        }
        PrimeFaces.current().ajax().update("test1:Todo");
    }

    public String FechaCambiarIngresoBD(String fecha) {
        String fechaN = "";
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date date = formatter.parse(fecha);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            fechaN = dateFormat.format(date);

        } catch (ParseException ex) {
            Logger.getLogger(eventWizardViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return fechaN;
    }

}
