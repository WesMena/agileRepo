/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.service;

import com.cci.controller.UsuarioLoginController;
import com.cci.model.Entrada;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public List<Entrada> getAllByIdEvt(int idEvt) {
        List<Entrada> returnedOpt = new ArrayList<>();
        Entrada returned = new Entrada();
        Conexion conne = Conexion.getInstance();

        try {
            stm = conne.conn.createStatement();
            rset = stm.executeQuery(String.format("select * from entrada where EventoId = %1$d", idEvt));
            while (rset.next()) {
                returned.setNombre(rset.getString("nombreEntrada"));
                returned.setPrecio(rset.getDouble("precio"));
                returned.setFechaFin(rset.getDate("fechaFin").toString());
                returned.setFechaInicio(rset.getDate("fechaInicio").toString());
                returned.setHoraFin(rset.getTime("horaFin").toString());
                returned.setHoraInicio(rset.getTime("horaInicio").toString());
                returned.setTipo(rset.getInt("Tipo"));
                returned.setCantidad(rset.getInt("cantidad"));
                returnedOpt.add(returned);
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            //Ingresa un tag default en la base de datos y toma el id del Evento ingresado en el query anterior

            System.out.println("listo sql Entrada");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
