/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.service;

import com.cci.model.DetalleEvento;
import com.cci.model.UbiHoraConfig;
import com.cci.model.ZonaHoraria;
import com.cci.model.zonaPais;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel
 */
public class WizardDao {

 private Statement stm;
    private ResultSet rst;
    public List<ZonaHoraria> zonas = new ArrayList<>();
    public List<zonaPais> paiseslst = new ArrayList<>();

    public List<ZonaHoraria> getzonas() {

        zonas.clear();
        ResultSet rs = null;
        Statement stmt = null;

        try {
            Conexion conexion = Conexion.getInstance();
            conexion.conectar();
            stmt = conexion.conn.createStatement();
            String sql;

            sql = "SELECT idZona,zonaHoraria FROM zonashorarias";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {

                int idZona = rs.getInt("idZona");
                String zonaHoraria = rs.getString("zonaHoraria");

                zonas.add(new ZonaHoraria(idZona, zonaHoraria));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return zonas;
    }

    public List<zonaPais> getpaises() {

        paiseslst.clear();

        ResultSet rs = null;
        Statement stmt = null;

        try {
            Conexion conexion = Conexion.getInstance();
            conexion.conectar();
            stmt = conexion.conn.createStatement();
            String sql;

            sql = "SELECT fkZona,paisZona FROM paiszona";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {

                int idZona = rs.getInt("fkZona");
                String pais = rs.getString("paisZona");

                paiseslst.add(new zonaPais(idZona, pais));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return paiseslst;
    }

    public void save(UbiHoraConfig objt) {

        ResultSet rs = null;
        Statement stmt = null;

        System.out.println("!--------Contenedor------------!");
        System.out.println(" --> Datos en el objeto <--");
        System.out.println("-> ID: " + objt.getEvntID());
        System.out.println("-> Direccion: " + objt.getUbifisica());
        System.out.println("-> Link: " + objt.getLink());
        System.out.println("-> ZonaHoraria: " + objt.getZonaHoraria());
        System.out.println("-> Hora Inicio: " + objt.getHinicial().toString());
        System.out.println("-> Hora Final: " + objt.getHfinal().toString());
        System.out.println("-> isFisico: " + objt.isFisico());
        System.out.println("-> Fecha Inicio: " + objt.getFini().toString());
        System.out.println("-> Fecha Final: " + objt.getFfin().toString());
        System.out.println("!-------------------------------!");

        try {
            Conexion conexion = Conexion.getInstance();
            conexion.conectar();
            stmt = conexion.conn.createStatement();
            String sql;
            sql = "INSERT INTO `agilerepo`.`confighoraubi`(`idEvento`,`ubiFisica`,`link`,`zonaHoraria`,`hIni`,`hFin`,`presencial`,`FIni`,`FFin`) VALUES (" + objt.getEvntID() + ",'" + objt.getUbifisica() + "','" + objt.getLink() + "','" + objt.getZonaHoraria() + "','" + objt.getHinicial() + "','" + objt.getHfinal() + "'," + objt.isFisico() + ",'" + dateFormatSQL(objt.getFini()) + "','" + dateFormatSQL(objt.getFfin()) + "');";
            stmt.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String formatFecha(Date fecha) {
        String fechaN = "";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        fechaN = String.valueOf(simpleDateFormat.format(fecha));
        return fechaN;

    }

    public String formatHora(Date fecha) {
        String hora = "";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

        hora = String.valueOf(simpleDateFormat.format(fecha));
        return hora;

    }

    public Date dateFormatSQL(Date fecha) throws ParseException {
        System.out.println("!-----  Dando Formato-------!");
        System.out.println("//!-> Fecha entra: " + fecha.toString());
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date myDate = formatter.parse(this.formatFecha(fecha));
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
        System.out.println("//!-> Fecha Sale: " + sqlDate.toString());
        System.out.println("!------- Fin Formato --------!");
        return sqlDate;
    }

    public Date HoraFormatSQL(Date hora) throws ParseException {
        System.out.println("!-----  Dando Formato-------!");
        System.out.println("//!-> Hora entra: " + hora.toString());
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date myDate = formatter.parse(this.formatHora(hora));
        java.sql.Time sqlDate = new java.sql.Time(myDate.getTime());
        System.out.println("//!-> Hora Sale: " + sqlDate.toString());
        System.out.println("!------- Fin Formato --------!");
        return sqlDate;
    }

    public void enviarDescripcionResumen(int id, String descripcion, String resumen) {
        Statement stmt = null;
        Conexion conexion = Conexion.getInstance();
        try {

            conexion.conectar();
            stmt = conexion.conn.createStatement();
            String sql;

            sql = "UPDATE agilerepo.eventopublic SET Descripcion='" + descripcion + "',resumen='" + resumen
                    + "' WHERE idEventoPublic=" + id;

            stmt.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.desconectar();
        }

    }

    public void enviarImagenPrincipal(int id, String directorioImgPrincipal) {

        Statement stmt = null;
        Conexion conexion = Conexion.getInstance();
        try {

            conexion.conectar();
            stmt = conexion.conn.createStatement();
            String sql;

            sql = "UPDATE agilerepo.eventopublic SET portada='" + directorioImgPrincipal
                    + "' WHERE idEventoPublic=" + id;

            stmt.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.desconectar();
        }

    }

    public void enviarImagenSecundaria(int id, String directorioImg2) {
        Statement stmt = null;
        Conexion conexion = Conexion.getInstance();
        try {

            conexion.conectar();
            stmt = conexion.conn.createStatement();
            String sql;

            sql = "UPDATE agilerepo.eventopublic SET imgSecundaria='" + directorioImg2
                    + "' WHERE idEventoPublic=" + id;

            stmt.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.desconectar();
        }
    }

    public void updateUbiHora(UbiHoraConfig obj, int id) throws ParseException {
        System.out.println("Cont entrando: " + obj.getUbifisica());
     
        Conexion conne = Conexion.getInstance();
        conne.conectar();
        try {
            stm = conne.conn.createStatement();
            stm.execute(String.format("update confighoraubi set ubiFisica='" + obj.getUbifisica() + "',link='" + obj.getLink() + "',zonaHoraria='" + obj.getZonaHoraria() + "',hIni='" + obj.getHinicial() + "',hFin='" + obj.getHfinal() + "',presencial=" + obj.isFisico() + ",FIni='" + dateFormatSQL(obj.getFini()) + "',FFin='" + dateFormatSQL(obj.getFfin()) + "' where idEvento=" + id + ";"));
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
