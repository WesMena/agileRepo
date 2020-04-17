/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.service;

import com.cci.model.ConfigUbiHora;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.time.DateUtils;

/**
 *
 * @author Nvidi
 */
public class ConfigUbiHoraDao implements Dao<ConfigUbiHora> {

    private Statement stm;
    private ResultSet rset;

    @Override
    public Optional<ConfigUbiHora> get(long id) {
        String isLink = "";
        Date fIni = null;
        Date fEnd = null;
        Optional returned = Optional.empty();
        Calendar c = Calendar.getInstance();
        Conexion conne = Conexion.getInstance();
        conne.conectar();
        ConfigUbiHora returnedObj = new ConfigUbiHora();
        try {
            stm = conne.conn.createStatement();
            rset = stm.executeQuery(String.format("select * from confighoraubi where idEvento = %1$d", id));
            while (rset.next()) {
                isLink = rset.getString("link");
                returnedObj.setHoraFin(horaAjustada(rset.getDate("hFin")));
                returnedObj.setHoraInicio(horaAjustada(rset.getDate("hIni")));
                //No fisico
                if (!isLink.equals("NONE")) {
                    returnedObj.setIsFisico(false);
                    returnedObj.setLink(rset.getString("link"));
                } else {
                    //Si fisico
                    returnedObj.setIsFisico(true);
                    returnedObj.setUbicacion(rset.getString("ubiFisica"));
                }
                returnedObj.setZonaHoraria(rset.getString("zonaHoraria"));

                fIni = rset.getDate("FIni");
                c.setTime(fIni);
                c.add(Calendar.DAY_OF_MONTH, 1);
                fIni = c.getTime();

                fEnd = rset.getDate("FFin");
                c.setTime(fEnd);
                c.add(Calendar.DAY_OF_MONTH, 1);
                fEnd = c.getTime();

            }
            List<Date> range = new ArrayList<>();
            range.add(fIni);
            range.add(fEnd);
            returnedObj.setRangoFechas(range);

            returned = Optional.of(returnedObj);

        } catch (SQLException ex) {
            Logger.getLogger(ConfigUbiHoraDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returned;
    }

    @Override
    public List<ConfigUbiHora> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(ConfigUbiHora t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(ConfigUbiHora t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(ConfigUbiHora t) {
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