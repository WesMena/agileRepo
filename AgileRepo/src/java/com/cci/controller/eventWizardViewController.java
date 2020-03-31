/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.controller;

import com.cci.model.Usuario;
import com.cci.model.ZonaHoraria;
import com.cci.model.horarioCompleto;
import com.cci.model.zonaPais;
import com.cci.service.WizardDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Nvidi
 */
@ManagedBean(name = "wizardController")
@SessionScoped

public class eventWizardViewController implements Serializable {

    public List<ZonaHoraria> lstZona = new ArrayList<>();
    public List<zonaPais> lstPais = new ArrayList<>();
    public List<horarioCompleto> lstcmpt = new ArrayList<>();

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
        
        for(int x=0;x<= lstcmpt.size()-1;x++){
            System.out.println(lstcmpt.get(x)+"\n");
        }
        
       
    }

}
