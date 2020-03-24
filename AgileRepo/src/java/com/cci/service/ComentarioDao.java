/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.service;

/**
 *
 * @author Daniel
 */


import com.cci.controller.UsuarioLoginController;
import com.cci.model.Comentario;
import com.cci.model.DetalleEvento;
import com.cci.model.Evento;
import com.cci.model.Tag;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.time.DateUtils;


public class ComentarioDao implements Dao<Comentario>{
    
    private Statement stm;
    private ResultSet rset;

    public static List<Comentario> comentarios = new ArrayList<>();

    @Override
    public Optional<Comentario> get(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Comentario> getAll() {
        ResultSet rs = null;
        Statement stmt = null;
        
        try {
            Conexion conexion = Conexion.getInstance();
            conexion.conectar();
            
            stmt = conexion.conn.createStatement();
            String sql;

            sql = "SELECT idComentario,uId,evento,displayName,comentario,fecha FROM comentarios";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {

                int id = rs.getInt("idComentario");
                String uId = rs.getString("uId");
                int evento = rs.getInt("evento");
                String displayName = rs.getString("displayName");
                String comentario = rs.getString("comentario");
                Date fecha =rs.getDate("fecha");
              
                comentarios.add(new Comentario(id, uId, evento, displayName, comentario,fecha));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return comentarios;
    }

    @Override
    public void save(Comentario t) {
         ResultSet rs = null;
        Statement stmt = null;
        try {
            Conexion conexion = Conexion.getInstance();
            conexion.conectar();
            stmt = conexion.conn.createStatement();
            String sql;

            //Ingresa un Evento nuevo en la base de datos
            sql = "INSERT INTO `agilerepo`.`comentarios` (`uId`,`evento`, `displayName`, `comentario`, `fecha`) VALUES (`"+t.getuId()+"`,`"+t.getEvento()+"`,`"+t.getUser()+"`,`"+t.getComentario()+"`,`"+t.getFecha()+"`);";
            stmt.executeUpdate(sql);
          
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Comentario t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Comentario t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    
}
