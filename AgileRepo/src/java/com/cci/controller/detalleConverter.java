/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.controller;
import com.cci.model.DetalleEvento;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author wesli
 */
@ManagedBean(name = "detalleconverter")
@FacesConverter(value = "detalleconverter")
public class detalleConverter implements Converter {

    private final static String DELIMITER = "~";

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {

        /*Toma un string que tiene el formato definido en getAsString y lo parte
        en un array tomando como referencia al DELIMITER, luego envía los elementos
        del array como parámetros de un constructor de DetalleEvento para ensamblar el objeto
        
         */
        String[] partes = string.split(DELIMITER);

        DetalleEvento detallito = new DetalleEvento(partes[0], partes[1], Integer.parseInt(partes[2]), Integer.parseInt(partes[3]), Integer.parseInt(partes[4]), Integer.parseInt(partes[5]), Integer.parseInt(partes[6]), partes[7], partes[8], partes[9], partes[10], partes[11]);

        return detallito;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        /*
        Convierte un objeto en un String respectando un formato definido. le cae 
        encima al convertidor por defecto(para que sea compatible con 
        el método getAsObject y que así la clase DetalleController pueda trabajar 
        con la lista de slots sin mayor problema)
         */

        if (!(o instanceof DetalleEvento)) {
            throw new RuntimeException(String.format("No se pudo convertir: %s el objeto '%s'", o.getClass(), o));
        }
        DetalleEvento detalle = (DetalleEvento) o;
        StringBuilder sb = new StringBuilder();
        sb
                .append(detalle.getTitulo())
                .append(DELIMITER)
                .append(detalle.getDescripcion())
                .append(DELIMITER)
                .append(Integer.toString(detalle.getDuracion()))
                .append(DELIMITER)
                .append(Integer.toString(detalle.getBorrado()))
                .append(DELIMITER)
                .append(Integer.toString(detalle.getIndice()))
                .append(DELIMITER)
                .append(Integer.toString(detalle.getEvento()))
                .append(DELIMITER)
                .append(Integer.toString(detalle.getId()))
                .append(DELIMITER)
                .append(detalle.getObjetivo())
                .append(DELIMITER)
                .append(detalle.getCategoria())
                .append(DELIMITER)
                .append(detalle.getColorCategoria())
                .append(DELIMITER)
                .append(detalle.getPasos())
                .append(DELIMITER)
                .append(detalle.getMateriales());
                
        
        String detalleStr = new String(sb);

        return detalleStr;
    }

}
