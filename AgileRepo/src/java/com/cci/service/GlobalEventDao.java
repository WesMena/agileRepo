/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.service;

import com.cci.controller.UsuarioLoginController;
import com.cci.model.Evento;
import com.cci.model.Tag;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nvidi
 */
public class GlobalEventDao implements Dao<Evento> {

    private Statement stm;
    private ResultSet rset;

    @Override
    public Optional<Evento> get(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Evento> getAll() {
        Conexion conne = Conexion.getInstance();
        List<Evento> returned = new ArrayList<>();
        conne.conectar();
        try {
            stm = conne.conn.createStatement();
            rset = stm.executeQuery(String.format("SELECT * FROM agilerepo.eventos \n"
                    + "where propietario not in ('%s')"
                    + "order by idEvento desc ;", UsuarioLoginController.UID));
            while (rset.next()) {
                returned.add(new Evento(rset.getString("nombre"), rset.getString("descripcion"), rset.getInt("idEvento"), rset.getInt("horas"), rset.getInt("dias")));
            }

            stm = conne.conn.createStatement();

            rset = stm.executeQuery(String.format("SELECT idTag,tag,evento FROM tagseventos where evento in(select idEvento from eventos where propietario not in('%s'))", UsuarioLoginController.UID));
            while (rset.next()) {
                for (Evento e : returned) {
                    if (e.getId() == rset.getInt("evento")) {
                        e.setLosTags(e.getLosTags() + " #" + rset.getString("tag"));
                        e.agregarTag(new Tag(rset.getString("tag"), rset.getInt("idTag")));
                    }
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(GlobalEventDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returned;
    }

    public List<Evento> getFiltered(String filtro) {
        List<Evento> returned = new ArrayList<>();
        Conexion conne = Conexion.getInstance();
        conne.conectar();
        try {
            stm = conne.conn.createStatement();
            rset = stm.executeQuery("SELECT e.idEvento,e.nombre,e.descripcion,e.horas,e.dias FROM eventos e,tagseventos t WHERE e.idEvento=t.evento AND (e.nombre LIKE '" + filtro + "%' OR t.tag LIKE '" + filtro + "%') "
                    + "AND propietario not in  ('" + UsuarioLoginController.UID + "')"
                    + " GROUP BY e.idEvento ORDER BY e.idEvento Desc");
            while (rset.next()) {
                returned.add(new Evento(rset.getString("nombre"), rset.getString("descripcion"), rset.getInt("idEvento"), rset.getInt("horas"), rset.getInt("dias")));
            }

            stm = conne.conn.createStatement();

            rset = stm.executeQuery(String.format("SELECT idTag,tag,evento FROM tagseventos where evento in(select idEvento from eventos where propietario not in('%s'))", UsuarioLoginController.UID));
            while (rset.next()) {
                for (Evento e : returned) {
                    if (e.getId() == rset.getInt("evento")) {
                        e.setLosTags(e.getLosTags() + " #" + rset.getString("tag"));
                        e.agregarTag(new Tag(rset.getString("tag"), rset.getInt("idTag")));
                    }
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(GlobalEventDao.class.getName()).log(Level.SEVERE, null, e);
        }

        return returned;
    }

    @Override
    public void save(Evento t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Evento t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Evento t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
