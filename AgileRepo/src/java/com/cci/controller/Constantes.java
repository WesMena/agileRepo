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
    
    public static final String ubicacionFotos="C:/Users/Daniel/Documents/NetBeansProjects/agileRepo/AgileRepo/web/resources/omega-layout/images/EventosSummary";
    
    public static final String dirProyecto="C:/Users/Daniel/Documents/NetBeansProjects/agileRepo/AgileRepo/";
    
       //Credenciales de la cuenta que envía los correos
   public static final String cuentaSMTP="ventasestructurasulatina@gmail.com";
   public static final String contraSTMP="wvjjk611";
      
           //Correo de la cuenta a la que se quiere enviar los reportes de compra 
           //Si el usuario adquiere una entrada de pago
    public static final String correoReporte="weslinmena@gmail.com"; 
    
    
    
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
