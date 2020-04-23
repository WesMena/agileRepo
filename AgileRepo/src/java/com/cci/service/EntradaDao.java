/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.service;

import com.cci.controller.UsuarioLoginController;
import com.cci.controller.eventWizardViewController;
import com.cci.model.Entrada;
import com.cci.model.entradaID;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.time.DateUtils;

/**
 *
 * @author josem
 */
public class EntradaDao implements Dao<Entrada> {

    private Statement stm;
    private ResultSet rset;

    @Override
    public Optional<Entrada> get(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void deleteAllByIdEvt(int idEvt) {
        Conexion conne = Conexion.getInstance();
        conne.conectar();
        try {
            stm = conne.conn.createStatement();
            stm.execute(String.format("Delete from entrada where EventoId = %1$d;", idEvt));
        } catch (SQLException ex) {
            Logger.getLogger(EntradaDao.class.getName()).log(Level.SEVERE, null, ex);
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

    public List<Entrada> getAllByIdEvt(int idEvt) {
        List<Entrada> returnedOpt = new ArrayList<>();
        Entrada returned = new Entrada();
        Conexion conne = Conexion.getInstance();
        conne.conectar();
        try {

            stm = conne.conn.createStatement();
            rset = stm.executeQuery(String.format("select * from entrada where EventoId = %1$d", idEvt));
            while (rset.next()) {

                Calendar c = Calendar.getInstance();
                SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");

                Date fechaIni;
                Date fechaFin;

                String fechaIniStr;
                String fechaFinStr;
                Date timeIni;
                Date timeFin;

                fechaIni = rset.getDate("fechaInicio");
                c.setTime(fechaIni);
                c.add(Calendar.DAY_OF_MONTH, 1);
                fechaIni = c.getTime();

                fechaFin = rset.getDate("fechaFin");
                c.setTime(fechaFin);
                c.add(Calendar.DAY_OF_MONTH, 1);
                fechaFin = c.getTime();

                timeIni = rset.getDate("horaInicio");
                timeFin = rset.getDate("horaFin");

                fechaIniStr = formatoFecha.format(fechaIni);
                fechaFinStr = formatoFecha.format(fechaFin);

                returned.setIdEntrada(rset.getInt("idEntrada"));

                returned.setNombre(rset.getString("nombreEntrada"));
                returned.setPrecio(rset.getDouble("precio"));

                returned.setFechaFin(fechaFinStr);
                returned.setFechaInicio(fechaIniStr);

                returned.setHoraFin(horaAjustada(timeFin));
                returned.setHoraInicio(horaAjustada(timeIni));

                returned.setTipo(rset.getInt("Tipo"));
                returned.setCantidad(rset.getInt("cantidad"));
                returnedOpt.add(returned);
                System.out.println("HINIE : " + returned.getHoraInicio());
                System.out.println("HFE   : " + returned.getHoraFin());
                System.out.println("FINI :" + returned.getFechaInicio());
                System.out.println("FFIN :" + returned.getFechaFin());
                returned = new Entrada();

            }

        } catch (SQLException ex) {
            Logger.getLogger(EntradaDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return returnedOpt;
    }

    @Override
    public List<Entrada> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(Entrada t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Entrada t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Entrada t) {

    }

    public void nuevaEntrada(Integer idEvento, String nombre, double precio, String fechaFin, String horaFin, String fechaInicio, String horaInicio, Integer tipo, Integer cantidad) {
        ResultSet rs = null;
        Statement stmt = null;

        try {
            Conexion conexion = Conexion.getInstance();
            conexion.conectar();
            stmt = conexion.conn.createStatement();
            String sql;

            //Ingresa un Evento nuevo en la base de datos
            sql = "INSERT INTO `agilerepo`.`entrada` (`EventoId`, `cantidad`, `nombreEntrada`,`precio`,`fechaInicio`,`horaInicio`,`fechaFin`,`horaFin`,`Tipo`) VALUES "
                    + "(" + idEvento + "," + cantidad + ",'" + nombre + "'," + precio + ",'" + fechaInicio + "','" + horaInicio + "','" + fechaFin + "','" + horaFin + "'," + tipo + ")";
            stmt.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
        public void viejaEntrada(Integer idEntrada,Integer idEvento, String nombre, double precio, String fechaFin, String horaFin, String fechaInicio, String horaInicio, Integer tipo, Integer cantidad) {
        ResultSet rs = null;
        Statement stmt = null;

        try {
            Conexion conexion = Conexion.getInstance();
            conexion.conectar();
            stmt = conexion.conn.createStatement();
            String sql;

            //Ingresa un Evento nuevo en la base de datos
            sql = "INSERT INTO `agilerepo`.`entrada` (`idEntrada`,`EventoId`, `cantidad`, `nombreEntrada`,`precio`,`fechaInicio`,`horaInicio`,`fechaFin`,`horaFin`,`Tipo`) VALUES "
                    + "("+ idEntrada + ","+ idEvento + "," + cantidad + ",'" + nombre + "'," + precio + ",'" + fechaInicio + "','" + horaInicio + "','" + fechaFin + "','" + horaFin + "'," + tipo + ")";
            stmt.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    

    public void compraEntrada(int idEvento, int idEntrada, int Cantidad, String Nombre, String Correo, String Telefono) {
        ResultSet rs = null;
        Statement stmt = null;

        try {
            Conexion conexion = Conexion.getInstance();
            conexion.conectar();
            stmt = conexion.conn.createStatement();
            String sql;

            //Ingresa compra de Entrada a la BD
            sql = "INSERT INTO `agilerepo`.`entradacomprada` (`idEvento`, `idEntrada`, `Cantidad`,`Nombre`,`Correo`,`Telefono`) VALUES "
                    + "(" + idEvento + "," + idEntrada + "," + Cantidad + ",'" + Nombre + "','" + Correo + "','" + Telefono + "')";
            stmt.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public int compradas(int idEntrada) {
        ResultSet rs = null;
        Statement stmt = null;
        int entradascompradas = 0;

        try{
            
            Conexion conexion = Conexion.getInstance();
            conexion.conectar();
            stmt = conexion.conn.createStatement();
            String sql;
            sql = "SELECT sum(Cantidad) AS cant FROM entradacomprada WHERE idEntrada="+idEntrada;
            rs = stmt.executeQuery(sql);
            while(rs.next()){
            entradascompradas = rs.getInt("cant");
            }
            
        }catch(Exception e){
            
            e.printStackTrace();
            
        }
                
        return entradascompradas;
    }
    
public void entradExistente(int idEntrada,Integer idEvento, String nombre, double precio, String fechaFin, String horaFin, String fechaInicio, String horaInicio, Integer tipo, Integer cantidad) {
        ResultSet rs = null;
        Statement stmt = null;

        try {
            Conexion conexion = Conexion.getInstance();
            conexion.conectar();
            stmt = conexion.conn.createStatement();
            String sql;
            String sqlDos;
            sqlDos = String.format("UPDATE entrada \n"
                    + "set cantidad =%2$d , \n"
                    + "nombreEntrada ='%3$s' , \n"
                    + "precio = %4$f, \n"
                    + "fechaInicio='%5$s', \n"
                    + "horaInicio ='%6$s', \n"
                    + "fechaFin ='%7$s', \n"
                    + "horaFin='%8$s', \n"
                    + "Tipo = %9$d \n"
                    + "where idEntrada = %1$d;",idEntrada,cantidad,nombre,precio,fechaInicio,horaInicio,fechaFin,horaFin,tipo );
            stmt.executeUpdate(sqlDos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


  public entradaID getIdTransaccion(int idEvento, int idEntrada, String nombreUsuario,String correo){
        
              ResultSet rs = null;
        Statement stmt = null;
              Conexion conexion = Conexion.getInstance();
            
      
       String nomEvt="";
       int idTransaccion=0;
        
        try {

            conexion.conectar();
            stmt = conexion.conn.createStatement();
          
            
            
            String sql;
sql="SELECT max(e.idCompra) as idTrans, evt.Nombre FROM  entradacomprada e, eventopublic evt WHERE e.idEvento='"+idEvento+"' AND e.idEntrada='"+idEntrada+"' AND e.Nombre='"+nombreUsuario+"' AND e.Correo='"+correo+"' AND e.idEvento=evt.idEventoPublic;";

            rs = stmt.executeQuery(sql);

          
            while (rs.next()) {
             nomEvt=rs.getString("Nombre");
             idTransaccion=rs.getInt("idTrans");
                
            }
            
            
        

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.desconectar();
        }
            
          entradaID entrada=new entradaID(idTransaccion,nomEvt);
          
          
            
            
         return entrada;   
        }





}
