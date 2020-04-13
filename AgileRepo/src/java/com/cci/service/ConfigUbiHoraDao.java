/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.service;

import com.cci.model.ConfigUbiHora;
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
 * @author Nvidi
 */
public class ConfigUbiHoraDao implements Dao<ConfigUbiHora> {

    private Statement stm;
    private ResultSet rset;

    @Override
    public Optional<ConfigUbiHora> get(long id) {
        String isLink ="";
        Date fIni=null;
        Date fEnd=null;
        Optional returned = Optional.empty();
        Conexion conne = Conexion.getInstance();
        conne.conectar();
        ConfigUbiHora returnedObj = new ConfigUbiHora();
        try {
            stm = conne.conn.createStatement();
            rset = stm.executeQuery(String.format("select * from confighoraubi where idEvento = %1$d", id));
            while(rset.next()){
                isLink = rset.getString("link");
                returnedObj.setHoraFin(rset.getTime("hFin").toString());
                returnedObj.setHoraInicio(rset.getTime("hIni").toString());
                //No fisico
                if(!isLink.equals("None")){
                    returnedObj.setIsFisico(false);
                    returnedObj.setLink(rset.getString("link"));
                }else{
                //Si fisico
                    returnedObj.setIsFisico(true);
                    returnedObj.setUbicacion(rset.getString("ubiFisica"));
                }
                returnedObj.setZonaHoraria(rset.getString("zonaHoraria"));
                fIni = rset.getDate("FIni");
                fEnd = rset.getDate("FFin");
            }
            List<Date> range = new ArrayList<>();
            range.add(fIni);
            range.add(fEnd);
            returnedObj.setRangoFechas(range);
            
            returned = Optional.of(returnedObj);
            
        } catch (SQLException ex) {
            Logger.getLogger(ConfigUbiHoraDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returned;
    }

    @Override
    public List<ConfigUbiHora> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(ConfigUbiHora t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(ConfigUbiHora t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(ConfigUbiHora t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 

}
