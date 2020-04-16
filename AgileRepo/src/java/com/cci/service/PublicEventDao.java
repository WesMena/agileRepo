/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.service;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Daniel
 */
public class PublicEventDao {
    private Statement stm;
    private ResultSet rst;
    
    
    public void deleteEvent(int evt){
         Statement stmt = null;
        Conexion conexion = Conexion.getInstance();
        try {

            conexion.conectar();
            stmt = conexion.conn.createStatement();
            String sql;

            sql = "call DeleteEventosPublic("+evt+");";
                 
            stmt.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.desconectar();
        }
    }
    
}
