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
 * @author wesli
 */
public class DetalleDao implements Dao<DetalleEvento> {

    public static List<DetalleEvento> detalles = new ArrayList<>();

    private int indiceEvento = 0;

    public DetalleDao() {
    }

    public DetalleDao(int evento) {
        /*
Este constructor trae los detalles de evento de la base de datos y crea una lista de los objetos
de tipo DetalleEvento que coincidan con el id que viene por parámetro

         */

        detalles = new ArrayList<>();
        ResultSet rs = null;
        Statement stmt = null;
        Conexion conexion = Conexion.getInstance();
        try {

            conexion.conectar();
            stmt = conexion.conn.createStatement();
            String sql;

            sql = "SELECT idDetalleEvento,indiceEvento,evento,duracion,titulo,descripcion,borrado,Objetivo,Categoria,ColorCategoria,Pasos,Materiales,bloqueo,horaInicio,primeroDeDia FROM detalleevento WHERE evento=" + evento + " AND borrado=0 ORDER BY indiceEvento";
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

                Date hora = rs.getTime("horaInicio");
                int primero = rs.getInt("primeroDeDia");

                //llama a un método que retorna un string con la hora en formato hh:mm en 24h 
                String hora2 = horaAjustada(hora);

                detalles.add(new DetalleEvento(titulo, desc, duracion, borrado, indice, evento, id, obj, cat, colorcat, pas, mat, bloqueo, primero, hora, hora2));
            }
            // Asigna el valor del tamano de la lista para el nuevo indice al final de la lista
            indiceEvento = detalles.size() + 1;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.desconectar();
        }

        /*
        Actualiza las horas de los slots y redefine cuales son considerados como 
        primeros del día para usarlos como base para esto.
         */
        int i = 1;
        int bloque = 0;
        String hora = "";
        int duracion = 0;
        int primero = 0;
        for (DetalleEvento det : detalles) {
            primero = 0;

            if (i == 1) {
                primero = 1;
            }

            if (bloque == 1) {
                primero = 1;
            }
            bloque = det.getBloequeo();
            det.setPrimeroDelDia(primero);

            if (primero == 0) {
                det.setHoraInicioStr(recalcularHora(hora, duracion));

            }

            hora = det.getHoraInicioStr();
            duracion = det.getDuracion();

            update(det);

            i++;
        }

    }

    public String recalcularHora(String hora2, int duracion) {

        /*
        Toma la hora del slot anterior y le agrega la cantidad de minutos de su 
        duración para calcular la hora inicial del nuevo slot. 
        
        Para ello obtiene los minutos de la hora del slot anterior, le suma 
        la duración y convierte eso a horas.
        
        Toma la cantidad entera de horas y la agrega a la cantidad de horas del 
        slot anterior, si hay un residuo, lo coloca en los minutos. 
        
        
         */
        String horaNueva = "";
        int horas = 0;
        int minutos = 0;

        String horasStr = hora2.substring(0, 2);
        String minutosStr = hora2.substring(3, 5);

        horas = Integer.parseInt(horasStr);
        minutos = Integer.parseInt(minutosStr);

        int sumaMin = duracion + minutos;
        float minutosModi = sumaMin / 60;

        if (minutosModi >= 1) {
            horas = horas + (int) minutosModi;
            sumaMin = sumaMin - ((int) minutosModi * 60);
        }
        //Para que empiece un nuevo ciclo una vez pase las 23 horas 
        horas = horas % 24;

        if (horas < 10) {
            horaNueva = "0" + horas + ":";
        } else {
            horaNueva = horas + ":";
        }

        if (sumaMin < 10) {
            horaNueva = horaNueva + "0" + sumaMin;
        } else {
            horaNueva = horaNueva + sumaMin;
        }

        return horaNueva;
    }

    public void updateDetalle(int idDetalle, String titulo, String desc, String obj, String cat, String colorcat, String pas, String mat) {
        ResultSet rs = null;
        Statement stmt = null;
        try {
            Conexion conexion = Conexion.getInstance();
            conexion.conectar();
            stmt = conexion.conn.createStatement();
            String sql;

            sql = "UPDATE detalleevento SET titulo='" + titulo + "', descripcion='" + desc + "', Objetivo='" + obj + "', Categoria='" + cat + "', ColorCategoria='" + colorcat + "', Pasos='" + pas + "', Materiales='" + mat + "' WHERE (idDetalleEvento=" + idDetalle + ")";
            stmt.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
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

    /**
     * Inserta un Bloque en el detalle del evento LLama al evento insertDetalle
     * con la bandera de bloqueo evaluando a True
     *
     * @param evento id del evento donde se inserta el Bloque
     */
    public void insertarBloque(int evento) {
        int bloqueo = 1; // Evalua a True
        insertDetalle(evento, bloqueo);
    }

    /**
     * Inserta un Slot en el detalle del evento Llama al evento insertDetalle
     * con la bandera de bloqueo evaluando a Falso
     *
     * @param evento id del evento donde se inserta el Slot
     */
    public void insertarSlot(int evento) {
        int bloqueo = 0; // Evalua a False
        insertDetalle(evento, bloqueo);
    }

    /**
     * Inserta un Detalle al evento especificado, evalua en runtime si es
     * bloqueo o slot normal
     *
     * @param evento id del evento donde se insertara un slot/bloque al detalle
     * @param bloqueo se usa como una bandera para llamar la sentencia de insert
     * correcta
     */
    public int esPrimeroDeDia(int evento) {
        /*
        Revisa si la lista de slots está vacía o si el último slot creado es un 
        bloque para saber si tiene que otorgarle la propiedad primeroDeDia o no 
        a un nuevo slot
         */
        int esPrimero = 0;
        if (detalles.isEmpty()) {
            esPrimero = 1;
        } else {
            ResultSet rs = null;
            Statement stmt = null;
            try {
                Conexion conexion = Conexion.getInstance();
                conexion.conectar();
                stmt = conexion.conn.createStatement();
                String sql;

                sql = "SELECT bloqueo FROM detalleevento WHERE evento=" + evento + " AND borrado=0 AND indiceEvento=" + detalles.size();
                rs = stmt.executeQuery(sql);

                while (rs.next()) {

                    int bloqueo = rs.getInt("bloqueo");

                    if (bloqueo == 1) {
                        esPrimero = 1;
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return esPrimero;
    }

    public String horaNuevo(int evento) {
        /*
       Toma la hora del último slot y le agrega su duración para crear la hora inicial
       del nuevo slot
         */
        ResultSet rs = null;
        Statement stmt = null;
        String horaNueva = "";
        int horas = 0;
        int minutos = 0;
        int duracion = 0;
        try {
            Conexion conexion = Conexion.getInstance();
            conexion.conectar();
            stmt = conexion.conn.createStatement();
            String sql;

            sql = "SELECT horaInicio,duracion FROM detalleevento WHERE evento=" + evento + " AND borrado=0 AND indiceEvento=" + getMaxIndex(evento);
            rs = stmt.executeQuery(sql);

            while (rs.next()) {

                Date hora = rs.getTime("horaInicio");

                duracion = rs.getInt("duracion");

                String hora2 = horaAjustada(hora);

                String horasStr = hora2.substring(0, 2);
                String minutosStr = hora2.substring(3, 5);

                horas = Integer.parseInt(horasStr);
                minutos = Integer.parseInt(minutosStr);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        int sumaMin = duracion + minutos;
        float minutosModi = sumaMin / 60;

        if (minutosModi >= 1) {
            horas = horas + (int) minutosModi;
            sumaMin = sumaMin - ((int) minutosModi * 60);
        }

        horas = horas % 24;

        if (horas < 10) {
            horaNueva = "0" + horas + ":";
        } else {
            horaNueva = horas + ":";
        }

        if (sumaMin < 10) {
            horaNueva = horaNueva + "0" + sumaMin;
        } else {
            horaNueva = horaNueva + sumaMin;
        }

        return horaNueva;
    }

    public void insertDetalle(int evento, int bloqueo) {
        Statement stmt = null;
        try {
            Conexion conexion = Conexion.getInstance();
            conexion.conectar();
            stmt = conexion.conn.createStatement();
            String sql;
            sql = generarSentencia(evento, bloqueo); // De forma dinamica determina que tipo de Slot se esta insertando
            stmt.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Este metodo retorna la sentencia adecuada para insertar condicionalmente
     * un bloque o slot en la tabla
     *
     * @param evento el id del evento
     * @param bloqueo 0 = false 1= true
     * @return
     */
    private int getMaxIndex(int evento) {
        int maxIndex = 0;

        ResultSet rs = null;
        Statement stmt = null;
        String horaNueva = "";
        int horas = 0;
        int minutos = 0;
        int duracion = 0;
        try {
            Conexion conexion = Conexion.getInstance();
            conexion.conectar();
            stmt = conexion.conn.createStatement();
            String sql;

            sql = "SELECT MAX(indiceEvento) FROM detalleevento WHERE evento=" + evento + " AND borrado=0";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                maxIndex = rs.getInt("MAX(indiceEvento)");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return maxIndex;
    }

    private String generarSentencia(int evento, int bloqueo) {

        String titulo;
        indiceEvento = getMaxIndex(evento) + 1;
        if (bloqueo == 1) {
            titulo = "Bloque";
        } else {
            titulo = "Slot" + indiceEvento;
        }

        /*
        Manda a llamar métodos para saber si el nuevo slot debe ser considerado 
        el primero del día; si no lo es, debe calcular su hora de inicio basado 
        en la hora y duración del slot anterior 
        
         */
        int primero = esPrimeroDeDia(evento);
        String nuevaHora = "";

        if (primero == 1) {
            nuevaHora = "08:30";
        } else {
            nuevaHora = horaNuevo(evento);
        }

        String sentencia = "INSERT INTO detalleevento ( indiceEvento, evento, titulo, bloqueo, horaInicio, primeroDeDia ) VALUES ('" + indiceEvento + "', '" + evento + "','" + titulo + "', '" + bloqueo + "','" + nuevaHora + "', b'" + primero + "')";

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
        Statement stmt = null;
        Conexion conexion = Conexion.getInstance();
        try {

            conexion.conectar();
            stmt = conexion.conn.createStatement();
            String sql;
            sql = "UPDATE agilerepo.detalleevento SET indiceEvento=" + t.getIndice() + ",duracion=" + t.getDuracion()
                    + ",titulo='" + t.getTitulo() + "',descripcion='" + t.getDescripcion() + "',horaInicio='"
                    + t.getHoraInicioStr() + "',primeroDeDia=b'" + t.getPrimeroDelDia()
                    + "' WHERE idDetalleEvento=" + t.getId();

            stmt.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.desconectar();
        }
//        
    }

    public void updateReorder(DetalleEvento t) {

        //Posición 0:Nombre Categoría
        Statement stmt = null;
        Conexion conexion = Conexion.getInstance();
        try {

            conexion.conectar();
            stmt = conexion.conn.createStatement();
            String sql;
            sql = "UPDATE agilerepo.detalleevento SET indiceEvento=" + t.getIndice() + ",duracion=" + t.getDuracion()
                    + ",titulo='" + t.getTitulo() + "',descripcion='" + t.getDescripcion()
                    + "' WHERE idDetalleEvento=" + t.getId();

            stmt.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.desconectar();
        }
    }

    public void updateHora(DetalleEvento t) {
        Statement stmt = null;
        Conexion conexion = Conexion.getInstance();
        try {

            conexion.conectar();
            stmt = conexion.conn.createStatement();
            String sql;
            sql = "UPDATE agilerepo.detalleevento SET horaInicio='" + t.getHoraInicioStr()
                    + "' WHERE idDetalleEvento=" + t.getId();

            stmt.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.desconectar();
        }
    }

    public void updateDuracion(DetalleEvento t) {
        Statement stmt = null;
        Conexion conexion = Conexion.getInstance();
        try {

            conexion.conectar();
            stmt = conexion.conn.createStatement();
            String sql;

            sql = "UPDATE agilerepo.detalleevento SET duracion=" + t.getDuracion()
                    + " WHERE idDetalleEvento=" + t.getId();

            stmt.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.desconectar();
        }
    }

    @Override
    public void delete(DetalleEvento t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /*  -> Funciones del Cronograma  <- */
 /* -> Retorna la lista con los detalles necesarios para levantar el cronograma <- */
    public List<DetalleEvento> todo() {
        List<DetalleEvento> tdetalles = new ArrayList<>();
        ResultSet rs = null;
        Statement stmt = null;
        try {
            Conexion conexion = Conexion.getInstance();
            conexion.conectar();
            stmt = conexion.conn.createStatement();
            String sql;

            sql = "SELECT idDetalleEvento,indiceEvento,evento,duracion,titulo,descripcion,borrado,Objetivo,Categoria,ColorCategoria,Pasos,Materiales,bloqueo,horaInicio,primeroDeDia FROM detalleevento";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {

                int id = rs.getInt("idDetalleEvento");
                int indice = rs.getInt("indiceEvento");
                int evento = rs.getInt("evento");
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
                int primeroDeDia = rs.getByte("primeroDeDia");
                Date horaInicio = rs.getTime("horaInicio");

                tdetalles.add(new DetalleEvento(titulo, desc, duracion, borrado, indice, evento, id, obj, cat, colorcat, pas, mat, bloqueo, primeroDeDia, horaInicio, horaAjustada(horaInicio)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return tdetalles;
    }

    public String propiedadEvento(int idEvento) {
        String returned = "";
        Statement stm;
        ResultSet rset;
        Conexion conne = Conexion.getInstance();
        conne.conectar();
        try {
            stm = conne.conn.createStatement();
            rset = stm.executeQuery(String.format("select e.propietario from eventos as e \n"
                    + "where e.idEvento = %d", idEvento));
            while (rset.next()) {
                returned = rset.getString("propietario");
            }

        } catch (SQLException ex) {
            Logger.getLogger(DetalleDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return returned;
    }

    public String propiedadSlot(int idSlot) {
        String returned = "";
        Statement stm;
        ResultSet rset;
        Conexion conne = Conexion.getInstance();
        conne.conectar();
        try {
            stm = conne.conn.createStatement();
            rset = stm.executeQuery(String.format("select e.propietario from eventos as e \n"
                    + "where e.idEvento = (select de.evento from  detalleevento as de where de.idDetalleEvento = %d);", idSlot));
            while (rset.next()) {
                returned = rset.getString("propietario");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DetalleDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return returned;
    }

}
