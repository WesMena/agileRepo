/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.service;
import com.cci.model.DetalleEvento;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 *
 * @author wesli
 */
public class DetalleDao implements Dao<DetalleEvento> {
    
public static List<DetalleEvento> detalles=new ArrayList<>();

    public DetalleDao(int evento) {
    detalles=new ArrayList<>();    
    ResultSet rs=null;
    Statement stmt=null;
    try{
     Conexion conexion = Conexion.getInstance();
           conexion.conectar();
           stmt = conexion.conn.createStatement();
           String sql; 
           
          sql="SELECT idDetalleEvento,indiceEvento,evento,duracion,titulo,descripcion,borrado FROM detalleevento WHERE evento="+evento+" ORDER BY indiceEvento";
           rs=stmt.executeQuery(sql);  
           
           while(rs.next()){
               
               int id=rs.getInt("idDetalleEvento");
               int indice=rs.getInt("indiceEvento");
               int duracion=rs.getInt("duracion");
               String titulo=rs.getString("titulo");
               String desc= rs.getString("descripcion");
               int borrado=rs.getInt("borrado");
               
               detalles.add(new DetalleEvento(titulo,desc,duracion,borrado,indice,evento,id));
           }
        
        
        
    }catch(Exception e){
        e.printStackTrace();
    }
        
    
    }

   
    
    public Optional<DetalleEvento> get(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DetalleEvento> getAll() {
     return detalles;
    }

    @Override
    public void save(DetalleEvento t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(DetalleEvento t, String[] params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(DetalleEvento t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
