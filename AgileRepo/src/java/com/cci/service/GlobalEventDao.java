/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.service;

import com.cci.controller.UsuarioLoginController;
import com.cci.model.Evento;
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
            while(rset.next()){
                returned.add(new Evento(rset.getString("nombre"),rset.getString("descripcion"),rset.getInt("idEvento"),rset.getInt("horas"),rset.getInt("dias")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GlobalEventDao.class.getName()).log(Level.SEVERE, null, ex);
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
