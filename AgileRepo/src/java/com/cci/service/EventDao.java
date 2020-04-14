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
 * @author wesli
 */
public class EventDao implements Dao<Evento> {

    private Statement stm;
    private ResultSet rset;

    public static List<Evento> eventos = new ArrayList<>();

    public EventDao() {
    }

    public EventDao(String filtro) {
        /*
        Este constructor trae los eventos de la base de datos y crea una lista de los objetos
        de tipo Evento cuyo nombre o uno de sus tags inicie con lo definido en el parámetro
        "filtro"(si está vacío, manda a llamar a todos los que están en la tabla.

        La segunda sentencia de SQL se encarga de asignar los tags correspondientes a cada 
        uno de los eventos que se encuentran en el ArrayList "eventos"
         */

        eventos = new ArrayList<>();
        ResultSet rs = null;
        Statement stmt = null;
        try {
            Conexion conexion = Conexion.getInstance();
            conexion.conectar();
            stmt = conexion.conn.createStatement();
            String sql;


          sql = "SELECT e.idEvento,e.nombre,e.descripcion,e.horas,e.dias FROM eventos e,tagseventos t WHERE e.idEvento=t.evento AND (e.nombre LIKE '" 
                    + filtro + "%' OR t.tag LIKE '" + filtro + "%') "
                    + "AND propietario = '" + UsuarioLoginController.UID + "'"
                    + " GROUP BY e.idEvento ORDER BY e.idEvento Desc";
/*
            sql = "WITH "
                    + "EventoInfo AS (SELECT e.idEvento,"
                    + "e.nombre,"
                    + "e.descripcion,"
                    + "e.dias "
                    + "FROM eventos e,tagseventos t "
                    + "WHERE e.idEvento=t.evento AND (e.nombre LIKE '"+filtro+"%' OR t.tag LIKE '"+filtro+"%') AND propietario = '"+UsuarioLoginController.UID+"'"
                    + " GROUP BY e.idEvento ORDER BY e.idEvento Desc),"
                    + " "
                    + "EventoTiempo AS (SELECT evento, FORMAT(SUM(duracion)/60,1) AS horas "
                    + "FROM detalleevento WHERE bloqueo = 0 GROUP BY evento),"
                    + " "
                    + "EventoInicio AS (SELECT evento, horaInicio FROM detalleevento WHERE indiceEvento = 1)"
                    + " "
                    + "SELECT EventoInfo.idEvento,"
                    + " EventoInfo.nombre,"
                    + " EventoInfo.descripcion,"
                    + " EventoInfo.dias,"
                    + " IF(EventoTiempo.horas IS NULL,0,EventoTiempo.horas) AS horas,"
                    + " IF(EventoInicio.horaInicio IS NULL,'00:00:00',EventoInicio.horaInicio) AS horaInicio FROM EventoInfo LEFT JOIN EventoTiempo ON"
                    + " EventoInfo.idEvento = EventoTiempo.evento LEFT JOIN EventoInicio ON"
                    + " EventoTiempo.evento = EventoInicio.evento;";
*/
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("idEvento");
                String nombre = rs.getString("nombre");
                String desc = rs.getString("descripcion");
                double horas = rs.getDouble("horas");
                int dias = rs.getInt("dias");
               // Date hora = rs.getTime("horaInicio");
               // String hora2 = horaAjustada(hora);
                    
                    String hora2="08:30";
                eventos.add(new Evento(nombre, desc, id, horas, dias, hora2));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        rs = null;
        stmt = null;
        try {
            Conexion conexion = Conexion.getInstance();
            conexion.conectar();
            stmt = conexion.conn.createStatement();
            String sql;

            sql = "SELECT idTag,tag,evento FROM tagseventos where evento in(select idEvento from eventos where propietario = '" + UsuarioLoginController.UID + "')";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                for (Evento e : eventos) {
                    if (e.getId() == rs.getInt("evento")) {

                        int idTag = rs.getInt("idTag");
                        String tag = rs.getString("tag");
                        Tag nuevo = new Tag(tag, idTag);
                        e.setLosTags(e.getLosTags() + " #" + tag);

                        e.agregarTag(nuevo);

                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        rs = null;
        stmt = null;
        try {
            Conexion conexion = Conexion.getInstance();
            conexion.conectar();
            stmt = conexion.conn.createStatement();
            String sql;

            sql = "SELECT indiceEvento, evento, duracion, horaInicio from detalleevento where borrado=b'0'";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                for (Evento e : eventos) {
                    if (e.getId() == rs.getInt("evento")) {
                        String horaAntes=e.getDuracion();
                        horaAntes=horaAntes.replace("h","");
                        double duracionMin=rs.getInt("duracion")+Double.parseDouble(horaAntes)*60;
                     
                        
                        
                        
                       int indiceActual=rs.getInt("indiceEvento");
                       if(indiceActual==1){
                           duracionMin=duracionMin-60;
                         Date hora = rs.getTime("horaInicio");
                         String hora2 = horaAjustada(hora);
                        e.setHoraInicio(hora2);
                           
                       }
                       
                          double horas=duracionMin/60;
                        
                        horas=Math.round(horas*100.0)/100.0;
                        e.setDuracion(Double.toString(horas)+"h");

                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }   
        
        
        

        
        
        
        
    }
    
    
    

    public Optional<Evento> get(long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Evento> getAll() {
        return eventos;
    }

    @Override
    public void save(Evento t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void update(Evento t) {
        String formmatedTags = t.getLosTags().replaceAll("\\s+", "");
        System.out.println("Tags formateados : " + formmatedTags);
        System.out.println("" + t.getNombre() + " Entrada al dao");
        Conexion conne = Conexion.getInstance();
        try {
            deleteTags(t.getId());
            String[] tags = formmatedTags.split("#");
            conne.conectar();
            for (String s : tags) {
                insertTag(s.replace("#", ""), t.getId());
                System.out.println("" + s.replace("#", ""));
            }
            stm = conne.conn.createStatement();
            stm.execute(String.format("Update eventos\n"
                    + "set nombre ='%1$s',\n"
                    + "descripcion = '%2$s'\n"
                    + "where idEvento  = %3$d;", t.getNombre(), t.getDesc(), t.getId()));
        } catch (SQLException e) {
            System.err.println("" + e.getMessage());
        }
    }

    public void deleteTags(int i) {
        Conexion connec = Conexion.getInstance();
        try {
            System.out.println("" + i + " Borrando del ID");
            stm = connec.conn.createStatement();
            stm.execute(String.format("delete from tagseventos \n"
                    + "where evento = %d", i));
        } catch (SQLException ex) {
            Logger.getLogger(EventDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertTag(String tag, int idEvento) {
        Conexion conne = Conexion.getInstance();
        if (!tag.equals("")) {
            try {
                stm = conne.conn.createStatement();
                stm.execute(String.format("Insert into tagseventos(tag,evento)\n"
                        + "values ('%s',%d)", tag, idEvento));
            } catch (SQLException ex) {
                Logger.getLogger(EventDao.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public void nuevoEvento() {
        ResultSet rs = null;
        Statement stmt = null;
        try {
            Conexion conexion = Conexion.getInstance();
            conexion.conectar();
            stmt = conexion.conn.createStatement();
            String sql;

            //Ingresa un Evento nuevo en la base de datos
            sql = "INSERT INTO `agilerepo`.`eventos` (`nombre`, `descripcion`, `horas`, `dias`,`propietario`) VALUES ('Nueva Agenda', 'Agregar Descripcion', 1, 1,'" + UsuarioLoginController.UID + "');";
            stmt.executeUpdate(sql);
            //Ingresa un tag default en la base de datos y toma el id del Evento ingresado en el query anterior
            sql = "INSERT INTO `agilerepo`.`tagseventos` (`tag`, `evento`) VALUES ('tag', (SELECT MAX(idEvento) FROM `agilerepo`.`eventos`));";
            stmt.executeUpdate(sql);
            System.out.println("listo sql");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Evento t) {
        Conexion conne = Conexion.getInstance();
        try {
            System.out.println("Evento eliminado : " + t.getId());

            //Eliminar los tags del evento para respetar la llave
            stm = conne.conn.createStatement();
            stm.execute(String.format("Delete from tagseventos where evento = %d", t.getId()));

            //Eliminar los slots de los eventos para respetar la llave
            stm = conne.conn.createStatement();
            stm.execute(String.format("Delete from detalleevento where evento =%d", t.getId()));

            //Eliminar evento despues de que todas sus llaves hayan sido eliminados
            stm = conne.conn.createStatement();
            stm.execute(String.format("Delete from eventos where idEvento = %d", t.getId()));
            System.out.println("Eliminacion correcta");
        } catch (SQLException e) {
            Logger.getLogger(EventDao.class.getName()).log(Level.SEVERE, null, e);
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

}
