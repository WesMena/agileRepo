/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.controller;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;  
import java.util.Optional;
import java.util.Scanner;
/**
 *
 * @author wesli
 */
public class MailController{
    
    
    
    void enviarCorreo(int idTransaccion,String nombreCompleto, String nombreEvento, 
            String fechaYHora, String tipoEntrada, Double precio, String correoUser,
            String telUser) throws FileNotFoundException{
       
        
        
        //Estos son unos .txt que contienen el html de las plantillas de los correos
    String ubicacionTemplateUsuario=Constantes.dirProyecto+"/src/java/com/OtherSource/templateCorreoUser.txt";
       
   String ubicacionTemplateAdmin=Constantes.dirProyecto+"/src/java/com/OtherSource/templateCorreoAdmin.txt";
        
   //Credenciales de la cuenta que envía los correos
    String cuenta=Constantes.cuentaSMTP;
           String contra=Constantes.contraSTMP;
      
           //Correo de la cuenta a la que se quiere enviar los reportes de compra 
           //Si el usuario adquiere una entrada de pago
    String correoAdmin=Constantes.correoReporte;       
           
               
   
   
   
        
         String precioStr;
         Boolean dePago=false;
         
        if(precio==0){
         precioStr="Gratis";
        }else{
            dePago=true;
         precioStr=Double.toString(precio);      
        }
       
        if(telUser.isEmpty()){
            telUser="-No disponible-";
        }
        
        
        
        /*Manda a llamar el template del correo que se envía al usuario
        y lo recorre línea por línea hasta tenerlo todo almacenado en un string.
        Luego usa el método String.replace para cambiar el texto colocado en el .txt
        por los valores de las variables correspondientes 
      
        */
             File templateUser=new File(ubicacionTemplateUsuario);
        Scanner scannerUser=new Scanner(templateUser);
 String templateStr="";
 
        while(scannerUser.hasNext()){
            templateStr=templateStr+""+scannerUser.nextLine();
        }
        
        scannerUser.close();
      templateStr=templateStr.replace("%1$s",nombreCompleto);
      templateStr=templateStr.replace("%2$s",nombreEvento);
      templateStr=templateStr.replace("%3$s",String.valueOf(idTransaccion));
      templateStr=templateStr.replace("%4$s",fechaYHora);
      templateStr=templateStr.replace("%5$s",tipoEntrada);
      templateStr=templateStr.replace("%6$s",precioStr);
     
      try{
          /*
          Define la configuración del servidor smtp para utilizar Gmail 
          */
          
          
            Properties propiedades=new Properties();
         propiedades.setProperty("mail.smtp.host","smtp.gmail.com");
           propiedades.setProperty("mail.smtp.starttls.enable", "true");
           propiedades.setProperty("mail.smtp.port", "587");
           propiedades.setProperty("mail.smtp.auth", "true");
           Session sesion=Session.getDefaultInstance(propiedades);
           
          
           
           String asunto="Entrada para el evento "+nombreEvento;
           
           
             MimeMessage mail=new MimeMessage(sesion);
           
           mail.setFrom(new InternetAddress(cuenta));
           mail.addRecipient(Message.RecipientType.TO,new InternetAddress(correoUser));
           mail.setSubject(asunto);
           mail.setContent(templateStr,"text/html");
           Transport transporte=sesion.getTransport("smtp");
           transporte.connect(cuenta,contra);
           transporte.sendMessage(mail,mail.getRecipients(Message.RecipientType.TO));
           transporte.close();
                   
          
          
      } catch(MessagingException ex){
         Logger.getLogger(MailController.class.getName()).log(Level.SEVERE, null, ex); 
      }
   
        
      if(dePago){
           /*Manda a llamar el template del correo que se envía al administrador
        y lo recorre línea por línea hasta tenerlo todo almacenado en un string.
        Luego usa el método String.replace para cambiar el texto colocado en el .txt
        por los valores de las variables correspondientes 
      
        */
          
          
         File templateAdmin=new File(ubicacionTemplateAdmin);
        Scanner scannerAdmin=new Scanner(templateAdmin);
 String templateAdminStr="";
 
        while(scannerAdmin.hasNext()){
            templateAdminStr=templateAdminStr+""+scannerAdmin.nextLine();
        }
        
        scannerAdmin.close();
          
         
        
      templateAdminStr=templateAdminStr.replace("%1$s",nombreCompleto);
      templateAdminStr=templateAdminStr.replace("%2$s",nombreEvento);
      templateAdminStr=templateAdminStr.replace("%3$s",String.valueOf(idTransaccion));
      templateAdminStr=templateAdminStr.replace("%4$s",fechaYHora);
      templateAdminStr=templateAdminStr.replace("%5$s",tipoEntrada);
      templateAdminStr=templateAdminStr.replace("%6$s",precioStr);
      templateAdminStr=templateAdminStr.replace("%7$s",telUser);
      templateAdminStr=templateAdminStr.replace("%8$s",correoUser);
      
       try{
            /*
          Define la configuración del servidor smtp para utilizar Gmail 
          */
           
            Properties propiedades=new Properties();
         propiedades.setProperty("mail.smtp.host","smtp.gmail.com");
           propiedades.setProperty("mail.smtp.starttls.enable", "true");
           propiedades.setProperty("mail.smtp.port", "587");
           propiedades.setProperty("mail.smtp.auth", "true");
           Session sesion=Session.getDefaultInstance(propiedades);
           
          
           String asunto="Reporte de compra hecha por el usuario "+nombreCompleto+" en el evento "+nombreEvento;
     
           
           
             MimeMessage mail=new MimeMessage(sesion);
           
           mail.setFrom(new InternetAddress(cuenta));
           mail.addRecipient(Message.RecipientType.TO,new InternetAddress(correoAdmin));
           mail.setSubject(asunto);
           mail.setContent(templateAdminStr,"text/html");
           Transport transporte=sesion.getTransport("smtp");
           transporte.connect(cuenta,contra);
           transporte.sendMessage(mail,mail.getRecipients(Message.RecipientType.TO));
           transporte.close();
                   
          
          
      } catch(MessagingException ex){
         Logger.getLogger(MailController.class.getName()).log(Level.SEVERE, null, ex); 
      }
      
      
      
      
      }
      
      
    }
    
    
  
}