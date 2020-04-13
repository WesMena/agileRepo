/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Nvidi
 */
public class ConfigUbiHora {
    private boolean isFisico;
    private String ubicacion;
    private String link;
    private String zonaHoraria;
    private String horarioStr;
    private List<Date> rangoFechas = new ArrayList<>();
    private String horaInicio;
    private String horaFin;
    
    public ConfigUbiHora(){};
    
    public boolean isIsFisico() {
        return isFisico;
    }

    public String getZonaHoraria() {
        return zonaHoraria;
    }

    public void setZonaHoraria(String zonaHoraria) {
        this.zonaHoraria = zonaHoraria;
    }
    
    

    public void setIsFisico(boolean isFisico) {
        this.isFisico = isFisico;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getHorarioStr() {
        return horarioStr;
    }

    public void setHorarioStr(String horarioStr) {
        this.horarioStr = horarioStr;
    }

    public List<Date> getRangoFechas() {
        return rangoFechas;
    }

    public void setRangoFechas(List<Date> rangoFechas) {
        this.rangoFechas = rangoFechas;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }
    
    
    
}
