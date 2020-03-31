/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.service;


import com.cci.model.DetalleEvento;
import com.cci.model.ZonaHoraria;
import com.cci.model.zonaPais;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel
 */
public class WizardDao {
    
    private Statement stm;
    private ResultSet rst;
    public List<ZonaHoraria>zonas = new ArrayList<>();
    public List<zonaPais>  paiseslst = new ArrayList<>();
    
    public List<ZonaHoraria> getzonas() {
       
        zonas.clear();
        ResultSet rs = null;
        Statement stmt = null;
        
        try {
            Conexion conexion = Conexion.getInstance();
            conexion.conectar();
            stmt = conexion.conn.createStatement();
            String sql;

            sql = "SELECT idZona,zonaHoraria FROM zonashorarias";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {

                int idZona = rs.getInt("idZona");
                String zonaHoraria = rs.getString("zonaHoraria");
       

                zonas.add(new ZonaHoraria(idZona,zonaHoraria));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return zonas;
    }
    
    
    
      public List<zonaPais> getpaises() {
          
        paiseslst.clear();
          
        ResultSet rs = null;
        Statement stmt = null;
        
        try {
            Conexion conexion = Conexion.getInstance();
            conexion.conectar();
            stmt = conexion.conn.createStatement();
            String sql;

            sql = "SELECT fkZona,paisZona FROM paiszona";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {

                int idZona = rs.getInt("fkZona");
                String pais = rs.getString("paisZona");
       

                paiseslst.add(new zonaPais(idZona,pais));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return paiseslst;
    }
    
    
}
