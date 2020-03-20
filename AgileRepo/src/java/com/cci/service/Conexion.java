/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.service;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author USER
 */
public class Conexion {
String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
  //Remoto  String DB_URL = "jdbc:mysql://173.230.153.195:3306/agilerepo?serverTimezone=UTC";
    String DB_URL = "jdbc:mysql://173.230.153.195:3306/agilerepo?serverTimezone=UTC";
    
     //Aquí van las credenciales, recordar cambiarlas según las de su pc.
    
   //Remoto String USER = "root";
   String USER = "root";
   
   //Remoto String PASS = "Rainbow+6+team";
   String PASS = "Rainbow+6+team";
    Connection conn = null;
    
private static Conexion INSTANCE = null;
   
private Conexion(){}

private synchronized static void createInstance(){
    if(INSTANCE == null) {
        INSTANCE = new Conexion();
    }
} 


public static Conexion getInstance(){
   
       if(INSTANCE == null){
       INSTANCE = new Conexion();
       }
       return INSTANCE;
   }
    
    
   public void conectar() {
        try {
         
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
   }
   
   
    public void desconectar(){
        try {
            if (!conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e) {
        //   e.printStackTrace();
        } 
    }
    
    
}
    

    

