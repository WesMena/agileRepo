/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Daniel
 */
@ManagedBean(name="horarioCompleto")
@SessionScoped 
public class horarioCompleto {
    
    private String horarioStr;

    public horarioCompleto(String horarioStr) {
        this.horarioStr = horarioStr;
    }

    public horarioCompleto() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getHorarioStr() {
        return horarioStr;
    }

    public void setHorarioStr(String horarioStr) {
        this.horarioStr = horarioStr;
    }
    
   
    
    
    
    
}
