/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.service;

import static com.cci.controller.portadaController.iniIm2;
import com.cci.model.EventSummary;
import com.cci.model.EvtPDetails;
import com.cci.model.entradaID;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.time.DateUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author wesli
 */
public class EventoMoreDetailsDao implements Dao<EvtPDetails> {
   
    boolean repetido=false;

    public EventoMoreDetailsDao() {
    }

    
    
    
        public EvtPDetails getDetalles(int id){
            int resultado=1;
        EvtPDetails eventoDetalles=new EvtPDetails();
          ResultSet rs = null;
        Statement stmt = null;
              Conexion conexion = Conexion.getInstance();
        try {

            conexion.conectar();
            stmt = conexion.conn.createStatement();
            String sql;

            sql = "SELECT e.idEventoPublic, e.Nombre, e.Descripcion, e.portada,e.tipo, e.resumen,e.imgSecundaria, f.FIni, f.hIni,f.presencial,f.zonaHoraria,f.link,f.ubiFisica  FROM eventopublic e, confighoraubi f WHERE e.idEventoPublic=f.idEvento AND e.idEventoPublic="+id;
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                repetido=false;
                
                
                
                
                id=rs.getInt("idEventoPublic");
                int tipo=rs.getInt("tipo");
                String nombre=rs.getString("Nombre");
                String desc=rs.getString("Descripcion");
         
                String portada=rs.getString("portada");
              
                Date fecha = rs.getDate("FIni");
                Date hora=rs.getTime("hIni");

                String resumen=rs.getString("resumen");
                String secundaria=rs.getString("imgSecundaria");
                //Ajusta la hora que viene de bd para sea correcta
                String hora2=horaAjustada(hora);
                int presencial=rs.getInt("presencial");
                String zonaHoraria=rs.getString("zonaHoraria");
                String link=rs.getString("link");
                String direccion=rs.getString("ubiFisica");
                
                //Hay que agregarle un día a la que trae de bd para que esté bien
                   Calendar c = Calendar.getInstance();
                c.setTime(fecha);
                c.add(Calendar.DAY_OF_MONTH,1);
                
                fecha=c.getTime();
                
               SimpleDateFormat formatoFecha=new SimpleDateFormat("dd/MM/yyyy");
               
               String fecha2=formatoFecha.format(fecha);
               String tipoStr;
               
               if(tipo==1){
                   tipoStr="Charla";
               }else{
                   tipoStr="Seminario";
               }
              
               
               if(portada.isEmpty()){
                   portada="images/EventosSummary/imgDefault.png";
               }
               
               if(secundaria.isEmpty()){
                   secundaria="";
               }
              if(resultado==1){
           eventoDetalles=new EvtPDetails(id,presencial,direccion,link,secundaria,resumen,zonaHoraria,fecha2,hora2,portada,desc,nombre,tipoStr);
                   
              }
          
           
           resultado++;
            }
            
            
        

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.desconectar();
        }
        
        
        
          rs = null;
         stmt = null;
         conexion = Conexion.getInstance();
        try {

            conexion.conectar();
            stmt = conexion.conn.createStatement();
            String sql;

            sql = "SELECT tag FROM tagsevtpublico WHERE evento="+id;
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
          
        String tag=rs.getString("tag");
        System.out.println(tag);
        eventoDetalles.addTag(tag);
                
            }
            
            
        

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.desconectar();
        }
        
        
        
        
           rs = null;
         stmt = null;
         conexion = Conexion.getInstance();
        try {

            conexion.conectar();
            stmt = conexion.conn.createStatement();
            System.out.println(id);
            String sql;
sql="SELECT o.profileImage,o.organizador, o.descri, u.displayName FROM organizadoreseventos o, usuarios u WHERE evento="+id;

            rs = stmt.executeQuery(sql);

            int i=1;
            while (rs.next()) {
                System.out.println("Cuenta:"+i);
          String nombreOrg=rs.getString("displayName");
          
        String descOrg=rs.getString("descri");
        InputStream fotoBlob = rs.getBinaryStream("profileImage");
     StreamedContent fotoOrg = new DefaultStreamedContent(fotoBlob, "image/jpeg");       
        
    
     eventoDetalles.setFotoOrganizador(fotoOrg);
     eventoDetalles.setDescOrganizador(descOrg);
     eventoDetalles.setNomOrganizador(nombreOrg);
     i++;
            }
            
            
        

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.desconectar();
        }
        
     return eventoDetalles;     
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

    @Override
    public Optional<EvtPDetails> get(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EvtPDetails> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(EvtPDetails t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(EvtPDetails t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(EvtPDetails t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
