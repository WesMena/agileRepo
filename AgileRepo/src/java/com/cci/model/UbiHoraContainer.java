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
public class UbiHoraContainer {
    private String dia;
    private int ord;
    private String Ubifisica;
    private String link;
    private String zonaHoraria;
    private String hinicial;
    private String hfinal;

    public UbiHoraContainer() {
    }

    public UbiHoraContainer(String dia, String zonaHoraria, String hinicial, String hfinal) {
        this.dia = dia;
        this.zonaHoraria = zonaHoraria;
        this.hinicial = hinicial;
        this.hfinal = hfinal;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
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

    public int getOrd() {
        return ord;
    }

    public void setOrd(int ord) {
        this.ord = ord;
    }
    
    
    
    
}
