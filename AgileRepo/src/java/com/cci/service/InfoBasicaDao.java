/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.service;

import com.cci.controller.Constantes;
import com.cci.controller.EventWizardImagesController;
import com.cci.controller.eventWizardViewController;
import com.cci.model.InfoBasica;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nvidi
 */
public class InfoBasicaDao implements Dao<InfoBasica> {

    private Statement stm;
    private ResultSet rset;
    private PreparedStatement prep;
    private CallableStatement call;

    public boolean infoExists(int idEvento) {
        boolean returned = false;
        Conexion conne = Conexion.getInstance();
        conne.conectar();
        try {
            stm = conne.conn.createStatement();
            rset = stm.executeQuery(String.format("select count(*) from eventopublic where idEventoPublic = %1$d;", idEvento));
            while (rset.next()) {
                if (rset.getInt(1) == 1) {
                    returned = true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(InfoBasicaDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return returned;
    }

    @Override
    public Optional<InfoBasica> get(long id) {
        InfoBasica returned = new InfoBasica();
        Conexion conne = Conexion.getInstance();
        conne.conectar();
        try {
             //<editor-fold defaultstate="collapsed" desc="Informacion general del evento">
            stm = conne.conn.createStatement();
            rset = stm.executeQuery(String.format("select * from eventopublic where idEventoPublic = %1$d", id));
            while (rset.next()) {
                //Solo en este caso voy a comportar la descripcion como una propiedad del modelo de infoBasica
                returned.setDescripcion(rset.getString("Descripcion"));
                returned.setNombre(rset.getString("Nombre"));
                returned.setResumen(rset.getString("resumen"));
                if (rset.getString("imgSecundaria") != null) 
                    returned.setImgSecDir(rset.getString("imgSecundaria"));
                else
                     returned.setImgSecDir("images/EventosSummary/imgDefault.png");
                
                if(rset.getString("portada")!=null)
                    returned.setPortadaDir(rset.getString("portada"));
                else
                    returned.setPortadaDir("images/EventosSummary/imgDefault.png");
                
            }
            //Tipo de evento
            conne.conectar();
            stm = conne.conn.createStatement();
            rset = stm.executeQuery(String.format("select tp.tipoEvento from eventopublic as ip\n"
                    + "inner join  tiposeventop as tp on ip.tipo = tp.idTipo\n"
                    + "where ip.idEventoPublic = %1$d", id));
            while (rset.next()) {
                returned.setTipo(rset.getString("tipoEvento"));
            }
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="Tags de eventos">
            conne.conectar();
            stm = conne.conn.createStatement();
            rset = stm.executeQuery(String.format("select * from tagsevtpublico where evento = %1$d;", id));
            List<String> tags = new ArrayList<>();
            while (rset.next()) {
                tags.add(rset.getString("tag"));
            }

            returned.setTags(tags);
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="Informacion relacionada a la portada del organizador,su descripcion">
            conne.conectar();
            stm = conne.conn.createStatement();

            rset = stm.executeQuery(String.format("select * from organizadoreseventos where evento = %1$d", id));

            while (rset.next()) {
                returned.setFoto(rset.getBlob("profileImage").getBinaryStream());
                returned.setDescOrganizadorUp(rset.getString("descri"));
            }

            //</editor-fold>
        } catch (SQLException ex) {
            Logger.getLogger(InfoBasicaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Optional.of(returned);
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
            System.out.println("" + t.getTags().size());
            call = conexion.conn.prepareCall("{call insert_infoBasica (?,?,?)} ");
            call.setString(1, t.getNombre());
            call.setString(2, t.getTipo());
            call.registerOutParameter(3, Types.INTEGER);

            //stm.execute(String.format("insert into eventopublic (Nombre,finalizado,tipo)  values('%1$s',b'%2$d',(select idTipo from  tiposeventop where tipoEvento = '%3$s' ))", t.getNombre(), 0, t.getTipo()));
            call.execute();
            int idEvt = call.getInt(3);
            System.out.println("IDEVENTO: " + idEvt);
            eventWizardViewController.idEvento = idEvt;

            conexion.conectar();

            for (String s : t.getTags()) {
                stm = conexion.conn.createStatement();
                stm.execute(String.format("Insert into tagsevtpublico  (tag,evento) values('%2$s','%1$d');", idEvt, s));
            }

            //Insert de la descripcion y la vara esa de l organizador qk
            conexion.conectar();
            // System.out.println("Uploaded file : " + EventWizardImagesController.uploadedFile.getInputstream().available());

            prep = conexion.conn.prepareStatement("insert into organizadoreseventos (organizador,evento,descri,profileImage) values(?,?,?,?)");
            prep.setString(1, Constantes.logguedUsserUID);
            prep.setInt(2, eventWizardViewController.idEvento);
            prep.setString(3, t.getDescripcion());
            prep.setBlob(4, t.getFoto());
            prep.execute();

        } catch (SQLException e) {
            System.out.println("" + e.getMessage() + e.getSQLState());
        }

    }

    @Override
    public void update(InfoBasica t) {
        System.out.println("ID del evento : " + eventWizardViewController.idEvento);
        //Update al evento nombre y tipo
        Conexion conne = Conexion.getInstance();
        conne.conectar();
        try {
            stm = conne.conn.createStatement();
            stm.execute(String.format("update eventopublic set nombre = '%2$s', tipo = (select idTipo from tiposeventop where tipoEvento = '%3$s') where idEventoPublic = %1$d;", eventWizardViewController.idEvento, t.getNombre(), t.getTipo()));
            //Eliminar tags y volverlos a crear jsjsjjs
            conne.conectar();
            stm = conne.conn.createStatement();
            stm.execute(String.format("delete from tagsevtpublico where  evento = '%1$d'  ", eventWizardViewController.idEvento));

            for (String tag : t.getTags()) {
                stm = conne.conn.createStatement();
                stm.execute(String.format("Insert into tagsevtpublico (tag,evento) values('%2$s','%1$d') ", eventWizardViewController.idEvento, tag));

            }
            //Update de la descripcion y la foto del organizador
            prep = conne.conn.prepareStatement("update organizadoreseventos set descri = ? ,profileImage = ? where evento = ?");
            prep.setString(1, t.getDescripcion());
            prep.setBlob(2, t.getFoto());
            prep.setInt(3, eventWizardViewController.idEvento);
            prep.execute();

        } catch (SQLException ex) {
            Logger.getLogger(InfoBasicaDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void delete(InfoBasica t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
