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

/**
 *
 * @author Nvidi
 */
@ManagedBean(name = "wizardController")
@SessionScoped

public class eventWizardViewController implements Serializable {

    /*Valores de Ubicacion y hora*/
    private String nombre;
    private String ubi;
    private String link;
    private horarioCompleto horario = new horarioCompleto();
    private Date ini;
    private Date fin;
    private boolean fisico;

    /**/
    public List<ZonaHoraria> lstZona = new ArrayList<>();
    public List<zonaPais> lstPais = new ArrayList<>();
    public List<horarioCompleto> lstcmpt = new ArrayList<>();
    public List<UbiHoraContainer> lstContainer = new ArrayList<>();

    //<editor-fold defaultstate="collapsed" desc="Getter Setter">
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

    public String format(Date fecha) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        return String.valueOf(simpleDateFormat.format(fecha, stringBuffer, new FieldPosition(0)));
        
    }

    public void fillContainer(ActionEvent e) {
        /*  System.out.println("Horario seleccionado : "+this.horario.getHorarioStr());*/
        
     
        UbiHoraContainer container = new UbiHoraContainer(this.nombre,this.horario.getHorarioStr().toString(),"s","f");
        
        if(this.fisico == true){
            container.setUbifisica(this.ubi);
           
        }else{
            container.setLink(this.link);
            
        }
        
        this.lstContainer.add(container);
        System.out.println(" -> Dia: " +container.getDia()+" agregado!");
    }

}
