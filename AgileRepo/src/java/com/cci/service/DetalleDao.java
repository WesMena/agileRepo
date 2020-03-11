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
    
    public static List<DetalleEvento> detalles = new ArrayList<>();
    
    private int indiceEvento = 0;
    
    public DetalleDao(){}
    
    public DetalleDao(int evento) {
        /*
Este constructor trae los detalles de evento de la base de datos y crea una lista de los objetos
de tipo DetalleEvento que coincidan con el id que viene por parámetro

         */

        detalles = new ArrayList<>();
        ResultSet rs = null;
        Statement stmt = null;
        try {
            Conexion conexion = Conexion.getInstance();
            conexion.conectar();
            stmt = conexion.conn.createStatement();
            String sql;

            sql = "SELECT idDetalleEvento,indiceEvento,evento,duracion,titulo,descripcion,borrado,Objetivo,Categoria,ColorCategoria,Pasos,Materiales,bloqueo FROM detalleevento WHERE evento=" + evento + " ORDER BY indiceEvento";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {

                int id = rs.getInt("idDetalleEvento");
                int indice = rs.getInt("indiceEvento");
                int duracion = rs.getInt("duracion");
                String titulo = rs.getString("titulo");
                String desc = rs.getString("descripcion");
                int borrado = rs.getInt("borrado");
                String obj = rs.getString("Objetivo");
                String cat = rs.getString("Categoria");
                String colorcat = rs.getString("ColorCategoria");
                String pas = rs.getString("Pasos");
                String mat = rs.getString("Materiales");
                int bloqueo = rs.getInt("bloqueo");

                detalles.add(new DetalleEvento(titulo, desc, duracion, borrado, indice, evento, id, obj, cat, colorcat, pas, mat,bloqueo));
            }
            // Asigna el valor del tamano de la lista para el nuevo indice al final de la lista
            indiceEvento = detalles.size() + 1;

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void updateDetalle(int idDetalle,String titulo, String desc,String obj,String cat, String colorcat, String pas, String mat) {
        ResultSet rs = null;
        Statement stmt = null;
        try {
            Conexion conexion = Conexion.getInstance();
            conexion.conectar();
            stmt = conexion.conn.createStatement();
            String sql;

            sql = "UPDATE detalleevento SET titulo='"+titulo+"', descripcion='"+desc+"', Objetivo='"+obj+"', Categoria='"+cat+"', ColorCategoria='"+colorcat+"', Pasos='"+pas+"', Materiales='"+mat+"' WHERE (idDetalleEvento="+idDetalle+")";
            stmt.executeUpdate(sql);
            System.out.println("listo sql");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    
    /**
     * Inserta un Bloque en el detalle del evento 
     * LLama al evento insertDetalle con la bandera de bloqueo evaluando a True
     * @param evento id del evento donde se inserta el Bloque
     */
    public void insertarBloque(int evento){
        int bloqueo = 1; // Evalua a True
        insertDetalle(evento, bloqueo);
    }
    
    
    /**
     * Inserta un Slot en el detalle del evento 
     * Llama al evento insertDetalle con la bandera de bloqueo evaluando a Falso
     * @param evento id del evento donde se inserta el Slot
     */
    public void insertarSlot(int evento){
        int bloqueo = 0; // Evalua a False
        insertDetalle(evento, bloqueo);
    }

    /**
     * Inserta un Detalle al evento especificado, evalua en runtime si es bloqueo o slot normal
     * @param evento id del evento donde se insertara un slot/bloque al detalle
     * @param bloqueo se usa como una bandera para llamar la sentencia de insert correcta
     */
    public void insertDetalle(int evento, int bloqueo) {
        Statement stmt=null;
        try{
            Conexion conexion = Conexion.getInstance();
            conexion.conectar();
            stmt = conexion.conn.createStatement();
            String sql;
            sql = generarSentencia(evento, bloqueo); // De forma dinamica determina que tipo de Slot se esta insertando
            stmt.executeUpdate(sql);
            
        }catch(Exception e){
            e.printStackTrace();
        }        
    }
            
    /**
     * Este metodo retorna la sentencia adecuada para insertar condicionalmente un bloque o slot en la tabla
     * @param evento el id del evento
     * @param bloqueo 0 = false 1= true
     * @return 
     */
    private String generarSentencia(int evento, int bloqueo){
        String titulo;
        indiceEvento = detalles.size()+1;
        if(bloqueo==1){
            titulo = "Bloque";
        }else{
            titulo = "Slot"+indiceEvento;
        }
        String sentencia="INSERT INTO detalleevento ( indiceEvento, evento, titulo, bloqueo ) VALUES ('"+indiceEvento+"', '"+evento+"','"+titulo+"', '"+bloqueo+"')";

        return sentencia;
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
    public void update(DetalleEvento t) {
      //Se usa un array de 1 posición
        //Posición 0:Nombre Categoría
        Statement stmt=null;
        try{
            Conexion conexion = Conexion.getInstance();
            conexion.conectar();
            stmt = conexion.conn.createStatement();
            String sql;
            sql="UPDATE agilerepo.detalleevento SET indiceEvento="+t.getIndice()+",duracion="+t.getDuracion()+
                    ",titulo='"+t.getTitulo()+"',descripcion='"+t.getDescripcion()+
                    
                    "' WHERE idDetalleEvento="+t.getId();
            
            stmt.executeUpdate(sql);
            
        }catch(Exception e){
            e.printStackTrace();
        }
//        
    }

    @Override
    public void delete(DetalleEvento t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
