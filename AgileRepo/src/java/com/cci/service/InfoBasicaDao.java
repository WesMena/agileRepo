/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.service;

import com.cci.controller.eventWizardViewController;
import com.cci.model.InfoBasica;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Nvidi
 */
public class InfoBasicaDao implements Dao<InfoBasica> {

    private Statement stm;
    private ResultSet rset;
    private PreparedStatement prep;
    private CallableStatement call;

    @Override
    public Optional<InfoBasica> get(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<InfoBasica> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(InfoBasica t) {
        //
        Conexion conexion = Conexion.getInstance();
        conexion.conectar();
        try {
            System.out.println(""+t.getTags().size());
            call = conexion.conn.prepareCall("{call insert_infoBasica (?,?,?)} ");
            call.setString(1, t.getNombre());
            call.setString(2, t.getTipo());
            call.registerOutParameter(3,Types.INTEGER );
            
            //stm.execute(String.format("insert into eventopublic (Nombre,finalizado,tipo)  values('%1$s',b'%2$d',(select idTipo from  tiposeventop where tipoEvento = '%3$s' ))", t.getNombre(), 0, t.getTipo()));
            call.execute();
            int idEvt = call.getInt(3);
            System.out.println("IDEVENTO: "+idEvt);
            eventWizardViewController.idEvento = idEvt;
            
            conexion.conectar();
            
            for (String s :  t.getTags()) {
               stm = conexion.conn.createStatement();
               stm.execute(String.format("Insert into tagsevtpublico  (tag,evento) values('%2$s','%1$d');",idEvt,s));
            }
 
            
            
            
            prep = conexion.conn.prepareStatement("update usuarios\n"
                    + "set profileImageP = ?");
            prep.setBlob(1, t.getFoto());
            //prep.execute();
            
        } catch (SQLException e) {
            System.out.println(""+e.getMessage()+e.getSQLState());
        }

    }

    @Override
    public void update(InfoBasica t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(InfoBasica t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
