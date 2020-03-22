/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.service;

import com.cci.controller.UsuarioLoginController;
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

/**
 *
 * @author Nvidi
 */
public class GlobalEventDao implements Dao<Evento> {

    private Statement stm;
    private ResultSet rset;

    @Override
    public Optional<Evento> get(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Evento> getAll() {
        Conexion conne = Conexion.getInstance();
        List<Evento> returned = new ArrayList<>();
        conne.conectar();
        try {
            String sql;
            sql = "WITH "
                    + "EventoInfo AS (SELECT e.idEvento,"
                    + "e.nombre,"
                    + "e.descripcion,"
                    + "e.dias, "
                    + "u.displayName "
                    + "FROM eventos e,tagseventos t,usuarios u "
                    + "WHERE u.uid = e.propietario "
                    + "AND e.idEvento=t.evento AND propietario not in ('" + UsuarioLoginController.UID + "') "
                    + "GROUP BY e.idEvento ORDER BY e.idEvento Desc), "
                    + " "
                    + "EventoTiempo AS (SELECT evento, FORMAT(SUM(duracion)/60,1) AS horas"
                    + " FROM detalleevento WHERE bloqueo = 0 GROUP BY evento),"
                    + " "
                    + "EventoInicio AS (SELECT evento, horaInicio FROM detalleevento WHERE indiceEvento = 1)"
                    + " "
                    + "SELECT EventoInfo.idEvento,"
                    + "	EventoInfo.displayName,"
                    + " EventoInfo.nombre,"
                    + " EventoInfo.descripcion,"
                    + " EventoInfo.dias,"
                    + " IF(EventoTiempo.horas IS NULL,0,EventoTiempo.horas) AS horas,"
                    + " IF(EventoInicio.horaInicio IS NULL,'00:00:00',EventoInicio.horaInicio) AS horaInicio FROM EventoInfo LEFT JOIN EventoTiempo ON"
                    + " EventoInfo.idEvento = EventoTiempo.evento LEFT JOIN EventoInicio ON"
                    + " EventoTiempo.evento = EventoInicio.evento;";
            stm = conne.conn.createStatement();

            rset = stm.executeQuery(sql);
            while (rset.next()) {
                returned.add(new Evento(rset.getString("nombre"), rset.getString("descripcion"), rset.getInt("idEvento"), rset.getDouble("horas"), rset.getInt("dias"), horaAjustada(rset.getTime("horaInicio")), rset.getString("displayName")));
            }

            stm = conne.conn.createStatement();

            rset = stm.executeQuery(String.format("SELECT idTag,tag,evento FROM tagseventos where evento in(select idEvento from eventos where propietario not in('%s'))", UsuarioLoginController.UID));
            while (rset.next()) {
                for (Evento e : returned) {
                    if (e.getId() == rset.getInt("evento")) {
                        e.setLosTags(e.getLosTags() + " #" + rset.getString("tag"));
                        e.agregarTag(new Tag(rset.getString("tag"), rset.getInt("idTag")));
                    }
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(GlobalEventDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returned;
    }

    public List<Evento> getFiltered(String filtro) {
        List<Evento> returned = new ArrayList<>();
        Conexion conne = Conexion.getInstance();
        conne.conectar();
        try {
            String sql;
            sql = "WITH "
                    + "EventoInfo AS (SELECT e.idEvento,"
                    + "e.nombre,"
                    + "e.descripcion,"
                    + "e.dias, "
                    + "u.displayName "
                    + "FROM eventos e,tagseventos t,usuarios u "
                    + "WHERE u.uid = e.propietario "
                    + "AND e.idEvento=t.evento AND propietario not in ('" + UsuarioLoginController.UID + "') "
                    + "AND (e.nombre LIKE '"+filtro+"%' OR t.tag LIKE '"+filtro+"%') "
                    + "GROUP BY e.idEvento ORDER BY e.idEvento Desc), "
                    + " "
                    + "EventoTiempo AS (SELECT evento, FORMAT(SUM(duracion)/60,1) AS horas"
                    + " FROM detalleevento WHERE bloqueo = 0 GROUP BY evento),"
                    + " "
                    + "EventoInicio AS (SELECT evento, horaInicio FROM detalleevento WHERE indiceEvento = 1)"
                    + " "
                    + "SELECT EventoInfo.idEvento,"
                    + "	EventoInfo.displayName,"
                    + " EventoInfo.nombre,"
                    + " EventoInfo.descripcion,"
                    + " EventoInfo.dias,"
                    + " IF(EventoTiempo.horas IS NULL,0,EventoTiempo.horas) AS horas,"
                    + " IF(EventoInicio.horaInicio IS NULL,'00:00:00',EventoInicio.horaInicio) AS horaInicio FROM EventoInfo LEFT JOIN EventoTiempo ON"
                    + " EventoInfo.idEvento = EventoTiempo.evento LEFT JOIN EventoInicio ON"
                    + " EventoTiempo.evento = EventoInicio.evento;";
            stm = conne.conn.createStatement();
            rset = stm.executeQuery(sql);
            while (rset.next()) {
                returned.add(new Evento(rset.getString("nombre"), rset.getString("descripcion"), rset.getInt("idEvento"), rset.getInt("horas"), rset.getInt("dias"),horaAjustada(rset.getTime("horaInicio")),rset.getString("displayName")));
            }

            stm = conne.conn.createStatement();

            rset = stm.executeQuery(String.format("SELECT idTag,tag,evento FROM tagseventos where evento in(select idEvento from eventos where propietario not in('%s'))", UsuarioLoginController.UID));
            while (rset.next()) {
                for (Evento e : returned) {
                    if (e.getId() == rset.getInt("evento")) {
                        e.setLosTags(e.getLosTags() + " #" + rset.getString("tag"));
                        e.agregarTag(new Tag(rset.getString("tag"), rset.getInt("idTag")));
                    }
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(GlobalEventDao.class.getName()).log(Level.SEVERE, null, e);
        }

        return returned;
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

}
