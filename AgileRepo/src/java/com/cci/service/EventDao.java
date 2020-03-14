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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

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

            sql = "SELECT e.idEvento,e.nombre,e.descripcion,e.horas,e.dias FROM eventos e,tagseventos t WHERE e.idEvento=t.evento AND (e.nombre LIKE '" + filtro + "%' OR t.tag LIKE '" + filtro + "%') "
                    + "AND propietario = '" + UsuarioLoginController.UID + "'"
                    + " GROUP BY e.idEvento ORDER BY e.idEvento Desc";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("idEvento");
                String nombre = rs.getString("nombre");
                String desc = rs.getString("descripcion");
                int horas = rs.getInt("horas");
                int dias = rs.getInt("dias");
                eventos.add(new Evento(nombre, desc, id, horas, dias));

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
            sql = "INSERT INTO `agilerepo`.`eventos` (`nombre`, `descripcion`, `horas`, `dias`,`propietario`) VALUES ('Nuevo Evento', 'Agregar Descripcion', 1, 1,'" + UsuarioLoginController.UID + "');";
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

}
