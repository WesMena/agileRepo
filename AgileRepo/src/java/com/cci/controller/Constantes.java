/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.controller;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Nvidi
 */

@ManagedBean(name ="constantes")
@SessionScoped
public class Constantes implements Serializable {
    public static String logguedUsserDisplayName;

    public static  String logguedUsserUID;
    
    public static final String ubicacionFotos="C:/Users/wesli/Documents/GitHub/agileRepo/AgileRepo/web/resources/omega-layout/images/EventosSummary";
    
    public  String getLogguedUsserDisplayName() {
        return logguedUsserDisplayName;
    }

    public static void setLogguedUsserDisplayName(String logguedUsserDisplayName) {
        Constantes.logguedUsserDisplayName = logguedUsserDisplayName;
    }

    public static String getLoggueUsserUID() {
        return logguedUsserUID;
    }

    public static void setLoggueUsserUID(String loggueUsserUID) {
        Constantes.logguedUsserUID = loggueUsserUID;
    }
    
    
    
}
