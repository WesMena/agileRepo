/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.service;

import com.cci.model.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nvidi
 */
public class UsuarioDao implements Dao<Usuario> {

    private Statement stm;
    private ResultSet rst;

    @Override
    public Optional<Usuario> get(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Usuario> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(Usuario t) {
       Conexion conne = Conexion.getInstance();
       conne.conectar();
        try {
            stm = conne.conn.createStatement();
            stm.execute(String.format("Insert into usuarios values('%s',%o);", t.getUid(),t.getRole()));
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Usuario t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Usuario t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean exists(String uid) {
        boolean returned = false;
        int returnedInt = 0;
        Conexion conne = Conexion.getInstance();
        conne.conectar();
        try {
            stm = conne.conn.createStatement();
            rst = stm.executeQuery(String.format("select count(*) as exist from usuarios where uid = '%s'", uid));
            while (rst.next()) {
                returnedInt = rst.getInt("exist");
            }
            if(returnedInt==1)
                returned = true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return returned;
    }

}
