/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.service;

import com.cci.model.Evento;
import com.cci.model.Tag;
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
public class EventDao implements Dao<Evento> {
public static List<Evento> eventos=new ArrayList<>();
 

      public EventDao(String filtro) {
     /*
Este constructor trae los eventos de la base de datos y crea una lista de los objetos
de tipo Evento cuyo nombre o uno de sus tags inicie con lo definido en el parámetro
"filtro"(si está vacío, manda a llamar a todos los que están en la tabla.

La segunda sentencia de SQL se encarga de asignar los tags correspondientes a cada 
uno de los eventos que se encuentran en el ArrayList "eventos"
*/     
          
      eventos=new ArrayList<>();
      ResultSet rs=null;
      Statement stmt=null;
      try{
        Conexion conexion = Conexion.getInstance();
           conexion.conectar();
           stmt = conexion.conn.createStatement();
           String sql; 
           
          sql="SELECT e.idEvento,e.nombre,e.descripcion,e.horas,e.dias FROM eventos e,tagseventos t WHERE e.idEvento=t.evento AND (e.nombre LIKE '"+filtro+"%' OR t.tag LIKE '"+filtro+"%') GROUP BY e.idEvento";
           rs=stmt.executeQuery(sql);  
           
           while(rs.next()){
               int id=rs.getInt("idEvento");
               String nombre=rs.getString("nombre");
               String desc=rs.getString("descripcion");
               int horas=rs.getInt("horas");
               int dias=rs.getInt("dias");
               eventos.add(new Evento(nombre,desc,id,horas,dias));
            
           }
           
      }catch(Exception e){
          e.printStackTrace();
      }     
      
      
        rs=null;
      stmt=null;
      try{
        Conexion conexion = Conexion.getInstance();
           conexion.conectar();
           stmt = conexion.conn.createStatement();
           String sql; 
           
          sql="SELECT idTag,tag,evento FROM tagseventos";
           rs=stmt.executeQuery(sql);  
           
           while(rs.next()){
               for(Evento e:eventos){
                   if(e.getId()==rs.getInt("evento")){

                     int idTag=rs.getInt("idTag");
                     String tag=rs.getString("tag");
                     Tag nuevo=new Tag(tag,idTag);
                     e.setLosTags(e.getLosTags()+" #"+tag);
                    
                     
                     e.agregarTag(nuevo);
                       
                   }
               }
             
           }
           
      }catch(Exception e){
          e.printStackTrace();
      }     
      
      
    
      
      
      
    }
    public Optional<Evento> get(long id) {
      throw new UnsupportedOperationException("Not supported yet.");
    }


    @Override
    public List<Evento> getAll() {
        return eventos;
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
