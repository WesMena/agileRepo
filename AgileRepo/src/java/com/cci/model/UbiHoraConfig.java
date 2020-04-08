/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.model;

import java.util.Date;

/**
 *
 * @author Daniel
 */
public class UbiHoraConfig {
    
    private int evntID;
    private String Ubifisica;
    private String link;
    private String zonaHoraria;
    private String hinicial;
    private String hfinal;
    private boolean fisico;
    private Date Fini;
    private Date Ffin;

    public UbiHoraConfig(int evntID, String zonaHoraria, String hinicial, String hfinal, boolean fisico, Date Fini, Date Ffin) {
        this.evntID = evntID;
        this.zonaHoraria = zonaHoraria;
        this.hinicial = hinicial;
        this.hfinal = hfinal;
        this.fisico = fisico;
        this.Fini = Fini;
        this.Ffin = Ffin;
    }

    public int getEvntID() {
        return evntID;
    }

    public void setEvntID(int evntID) {
        this.evntID = evntID;
    }

    public String getUbifisica() {
        return Ubifisica;
    }

    public void setUbifisica(String Ubifisica) {
        this.Ubifisica = Ubifisica;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getZonaHoraria() {
        return zonaHoraria;
    }

    public void setZonaHoraria(String zonaHoraria) {
        this.zonaHoraria = zonaHoraria;
    }

    public String getHinicial() {
        return hinicial;
    }

    public void setHinicial(String hinicial) {
        this.hinicial = hinicial;
    }

    public String getHfinal() {
        return hfinal;
    }

    public void setHfinal(String hfinal) {
        this.hfinal = hfinal;
    }

    public boolean isFisico() {
        return fisico;
    }

    public void setFisico(boolean fisico) {
        this.fisico = fisico;
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
    
    
    
    
    
}
