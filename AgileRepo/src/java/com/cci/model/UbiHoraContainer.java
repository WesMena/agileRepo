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
    private String Ubifisica;
    private String link;
    private String zonaHoraria;
    private String hinicial;
    private String hfinal;
    private String Fini;
    private String Ffin;

    public UbiHoraContainer() {
    }

    public UbiHoraContainer( String zonaHoraria, String hinicial, String hfinal,String Fini,String Ffin) {
        this.zonaHoraria = zonaHoraria;
        this.hinicial = hinicial;
        this.hfinal = hfinal;
        this.Fini = Fini;
        this.Ffin=Ffin;
    }

    public String getFini() {
        return Fini;
    }

    public void setFini(String Fini) {
        this.Fini = Fini;
    }

    public String getFfin() {
        return Ffin;
    }

    public void setFfin(String Ffin) {
        this.Ffin = Ffin;
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
     
}
