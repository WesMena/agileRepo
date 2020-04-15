/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.service;
import com.cci.model.EventSummary;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.time.DateUtils;
/**
 *
 * @author wesli
 */
public class EventSummaryDao implements Dao<EventSummary> {
        private Statement stm;
    private ResultSet rset;

    public static List<EventSummary> eventosSummary=new ArrayList<>();
boolean repetido=false;
    
    public EventSummaryDao() {
        
        //Esto manda a traer los eventos que se muestran en Eventos.xhtml
        eventosSummary=new ArrayList<>();
          ResultSet rs = null;
        Statement stmt = null;
              Conexion conexion = Conexion.getInstance();
        try {

            conexion.conectar();
            stmt = conexion.conn.createStatement();
            String sql;

            sql = "SELECT e.idEventoPublic, e.Nombre, e.Descripcion, e.finalizado, e.portada, f.FIni, f.hIni  FROM eventopublic e, confighoraubi f WHERE e.idEventoPublic=f.idEvento";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                
                repetido=false;
                
                
                
                
                int id = rs.getInt("idEventoPublic");
                String nombre=rs.getString("Nombre");
                String desc=rs.getString("Descripcion");
                int finalizado=rs.getInt("finalizado");
                String portada=rs.getString("portada");
              
                Date fecha = rs.getDate("FIni");
                Date hora=rs.getTime("hIni");

                System.out.println(nombre);
           
                //Ajusta la hora que viene de bd para sea correcta
                String hora2=horaAjustada(hora);
                
                
                //Hay que agregarle un día a la que trae de bd para que esté bien
                   Calendar c = Calendar.getInstance();
                c.setTime(fecha);
                c.add(Calendar.DAY_OF_MONTH,1);
                
                fecha=c.getTime();
                
               SimpleDateFormat formatoFecha=new SimpleDateFormat("dd/MM/yyyy");
               
               String fecha2=formatoFecha.format(fecha);
                
               
          
               //Esto se asegura de que no salgan eventos repetidos en los que tienen varias fechas
               
               for(EventSummary evt:eventosSummary){
                   if(id==evt.getId()){
                       repetido=true;
                   }
               }
                if(repetido==false){
                    
                    eventosSummary.add(new EventSummary(nombre,desc,portada,finalizado,fecha2,hora2,id));
                }
               
            }
        

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.desconectar();
        }
        
    }
    

    
 

            
        public String horaAjustada(Date hora) {
        /*
        Le da el formato hh:mm 24h a la fecha y la retorna como un string.
        
        
         */
        hora = DateUtils.addHours(hora, 6);

        SimpleDateFormat formato = new SimpleDateFormat("hh:mm aa");

        String hora2 = formato.format(hora);

        String horaAux = hora2.substring(0, 2);
        String AMPM = hora2.substring(6, 8);

        if (horaAux.equals("12") && AMPM.equals("AM")) {
            hora2 = hora2.replaceFirst(horaAux, "00");
        } else {

            int horaNum = Integer.parseInt(horaAux);

            if (horaNum < 12 && AMPM.equals("PM")) {
                horaNum += 12;
                hora2 = hora2.replaceFirst(horaAux, String.valueOf(horaNum));
            }

        }

        hora2 = hora2.substring(0, 5);

        return hora2;
    }
    
    
    
    
    
    
    
    //Detalles del evento(los que se cargan en EventoMoreDetails.xhtml)
    public EventSummary obtenerDetalles(int id){
    EventSummary eventoDetalle=new EventSummary();
          ResultSet rs = null;
        Statement stmt = null;
              Conexion conexion = Conexion.getInstance();
        try {

            conexion.conectar();
            stmt = conexion.conn.createStatement();
            String sql;

            sql = "SELECT e.idEventoPublic, e.Nombre, e.Descripcion, e.finalizado, e.portada, e.resumen,e.imgSecundaria, f.FIni, f.hIni  FROM eventopublic e, confighoraubi f WHERE e.idEventoPublic=f.idEvento AND e.idEventoPublic="+id;
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                repetido=false;
                
                
                
                
                id=rs.getInt("idEventoPublic");
                String nombre=rs.getString("Nombre");
                String desc=rs.getString("Descripcion");
                int finalizado=rs.getInt("finalizado");
                String portada=rs.getString("portada");
              
                Date fecha = rs.getDate("FIni");
                Date hora=rs.getTime("hIni");

                String resumen=rs.getString("resumen");
                String secundaria=rs.getString("imgSecundaria");
                //Ajusta la hora que viene de bd para sea correcta
                String hora2=horaAjustada(hora);
                
                
                //Hay que agregarle un día a la que trae de bd para que esté bien
                   Calendar c = Calendar.getInstance();
                c.setTime(fecha);
                c.add(Calendar.DAY_OF_MONTH,1);
                
                fecha=c.getTime();
                
               SimpleDateFormat formatoFecha=new SimpleDateFormat("dd/MM/yyyy");
               
               String fecha2=formatoFecha.format(fecha);
                
               
          
               //Esto se asegura de que no salgan eventos repetidos en los que tienen varias fechas
            eventoDetalle=new EventSummary(nombre,desc,portada,finalizado,fecha2,hora2,id,resumen,secundaria);
               
            }
        

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.desconectar();
        }
     return eventoDetalle;     
    }
    
    
    public Optional<EventSummary> get(long id) {
  throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List<EventSummary> getAll() {
        return eventosSummary;
    }

    
  
    
    
    @Override
    public void save(EventSummary t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(EventSummary t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(EventSummary t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
