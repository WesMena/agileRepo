/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.controller;

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
import org.primefaces.PrimeFaces;

/**
 *
 * @author Nvidi
 */
@ManagedBean(name = "wizardController")
@SessionScoped

public class eventWizardViewController implements Serializable {

    /*Valores de Ubicacion y hora*/
    private String nombre; /* <- Nombre de la configuracion */
    private String ubi; /* <- Ubicacion Fisica de la configuracion */
    private String link;/* <- Ubicacion del Link de la configuracion */
    private horarioCompleto horario = new horarioCompleto();/* <- Objeto para armar la lista de Zonas horarias */
    private Date ini;/* <- Hora Inicio */
    private Date fin;/* <- Hora Final */
    private String strIni;/* <- String de la fecha de Inicio */
    private String strFin;/* <- String de la fecha de Final */
    private boolean fisico;

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

    //<editor-fold defaultstate="collapsed" desc="Getter Setter">
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
    
    
    /*Funcion para actualizar GUI*/
    private void updateUI() {

        PrimeFaces.current().ajax().update("publicarEventos:ubi-hora");
    }
    
    
    /*Funcion para setear las fechas en que Inicia y finaliza el evento*/
    private void setearFechas(List<Date> list) {

        for (int i = 0; i <= list.size() - 1; i++) {

            if (i == 0) {
                this.strIni = this.formatFecha(list.get(i));
            } else if (i == list.size() - 1) {
                this.strFin = this.formatFecha(list.get(i));
            }

        }

    }
    /*!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/
    /*!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/
    /*!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/
    /*||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||*/
    /*vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv*/
    /*Genera el objeto con el contenido requerido de Fechas,Horas y ubicacion*/
    public UbiHoraContainer fillContainer(ActionEvent e) {
        DetalleDao dao = new DetalleDao();
        setearFechas(this.range);
        
        UbiHoraContainer container = new UbiHoraContainer(this.horario.getHorarioStr().toString(), format(ini), format(this.fin),this.strIni,this.strFin);

        if (this.fisico == true) {
            container.setUbifisica(this.ubi);
            System.out.println(" -> Container Creado!");
            System.out.println("Ubicacion: "+container.getUbifisica());
            System.out.println("Zona Horaria: "+container.getZonaHoraria());
            
        } else {
            container.setLink(this.link);
            System.out.println(" -> Container Creado!");
            System.out.println("Link: "+container.getLink());
            System.out.println("Zona Horaria: "+container.getZonaHoraria());
        }

        return container;
    }
     /*!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/
    /*!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/
    /*!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/
    /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
    /*||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||*/
         

}
