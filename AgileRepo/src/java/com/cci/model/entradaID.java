/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.model;

/**
 *
 * @author wes
 */
public class entradaID {
  int idTransaccion=0; 
  String nomEvt="";

    public int getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public String getNomEvt() {
        return nomEvt;
    }

    public void setNomEvt(String nomEvt) {
        this.nomEvt = nomEvt;
    }

    public entradaID() {
    }
  
  public entradaID(int id,String nom){
      this.idTransaccion=id;
      this.nomEvt=nom;
  }
  
}

