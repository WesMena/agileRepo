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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.time.DateUtils;

/**
 *
 * @author wesli
 */
public class EventSummaryDao implements Dao<EventSummary> {

    private Statement stm;
    private ResultSet rset;

    public boolean readyToPublic(int idEvt) {
        boolean returned = false;
        Conexion connne = Conexion.getInstance();

        try {
            stm = connne.conn.createStatement();
            rset = stm.executeQuery(String.format("select count(*) as ready from eventopublic as ep \n"
                    + "inner join confighoraubi as cu on cu.idEvento = ep.idEventoPublic\n"
                    + "inner join entrada as en on en.EventoId = ep.idEventoPublic\n"
                    + "where ep.idEventoPublic=%1$d \n"
                    + "and ep.imgSecundaria  !='' \n"
                    + "and ep.portada   !='' \n"
                    + "and ep.resumen !='' \n"
                    + "and ep.Descripcion  !=''; ", idEvt));
            int response =-1;
            while(rset.next()){
                response = rset.getInt("ready");
            }
            if(response!=0)
                returned = true;
            
        } catch (SQLException ex) {
            Logger.getLogger(EventSummaryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returned;
    }

    public static List<EventSummary> eventosSummary = new ArrayList<>();
    boolean repetido = false;

    public List<EventSummary> getAllByUID(String UID) {
        List<EventSummary> returned = new ArrayList<>();
        EventSummary returnedAdd = new EventSummary();

        Conexion conne = Conexion.getInstance();
        conne.conectar();
        try {
            stm = conne.conn.createStatement();
            rset = stm.executeQuery(String.format("select * from eventopublic as ep\n"
                    + "inner join  organizadoreseventos as oe on ep.idEventoPublic = oe.evento\n"
                    + "where organizador = '%1$s';", UID));
            while (rset.next()) {
                returnedAdd = new EventSummary();
                returnedAdd.setId(rset.getInt("idEventoPublic"));
                returnedAdd.setNombreEvento(rset.getString("Nombre"));
                if (rset.getString("portada") == null) {
                    returnedAdd.setPortada("images/EventosSummary/imgDefault.png");
                } else {
                    returnedAdd.setPortada(rset.getString("portada"));
                }
                returned.add(returnedAdd);
            }
            //Obtencion de fechas
            for (EventSummary evt : returned) {
                stm = conne.conn.createStatement();
                rset = stm.executeQuery(String.format("select * from confighoraubi \n"
                        + "where idEvento = %1$d;", evt.getId()));
                Date fecha;
                Date hora;
                while (rset.next()) {
                    if (rset.getDate("Fini") != null) {
                        fecha = rset.getDate("FIni");
                        hora = rset.getDate("Hini");
                        Calendar c = Calendar.getInstance();
                        c.setTime(fecha);
                        c.add(Calendar.DAY_OF_MONTH, 1);

                        fecha = c.getTime();

                        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

                        String fecha2 = formatoFecha.format(fecha);

                        evt.setFecha(fecha2);
                        evt.setHora(horaAjustada(hora));
                    } else {
                        evt.setFecha("0/0/0");
                        evt.setHora("00:00:00");
                    }
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(EventSummaryDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return returned;
    }

    public EventSummaryDao() {


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
    public EventSummary obtenerDetalles(int id) {
        EventSummary eventoDetalle = new EventSummary();
        ResultSet rs = null;
        Statement stmt = null;
        Conexion conexion = Conexion.getInstance();
        try {

            conexion.conectar();
            stmt = conexion.conn.createStatement();
            String sql;

            sql = "SELECT e.idEventoPublic, e.Nombre, e.Descripcion, e.finalizado, e.portada, e.resumen,e.imgSecundaria, f.FIni, f.hIni  FROM eventopublic e, confighoraubi f WHERE e.idEventoPublic=f.idEvento AND e.idEventoPublic=" + id;
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                repetido = false;

                id = rs.getInt("idEventoPublic");
                String nombre = rs.getString("Nombre");
                String desc = rs.getString("Descripcion");
                int finalizado = rs.getInt("finalizado");
                String portada = rs.getString("portada");

                Date fecha = rs.getDate("FIni");
                Date hora = rs.getTime("hIni");

                String resumen = rs.getString("resumen");
                String secundaria = rs.getString("imgSecundaria");
                //Ajusta la hora que viene de bd para sea correcta
                String hora2 = horaAjustada(hora);

                //Hay que agregarle un día a la que trae de bd para que esté bien
                Calendar c = Calendar.getInstance();
                c.setTime(fecha);
                c.add(Calendar.DAY_OF_MONTH, 1);

                fecha = c.getTime();

                SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

                String fecha2 = formatoFecha.format(fecha);

                //Esto se asegura de que no salgan eventos repetidos en los que tienen varias fechas
                eventoDetalle = new EventSummary(nombre, desc, portada, finalizado, fecha2, hora2, id, resumen, secundaria);

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
