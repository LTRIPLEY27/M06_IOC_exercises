/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eac4.src.gestors;

import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import model.Medicament;

/**
 * @author Isabel Calzadilla M-06
 * @version  : 17-11-2022
 * @Resum : Activitat 2, M06 UF3
 */
public class Utilitats {

    static JAXBContext jaxbContext;
    static Marshaller jaxbMarshaller;
    static Unmarshaller jaxbUnmarshaller;    

    static  {
        try {
            jaxbContext = JAXBContext.newInstance(Medicament.class);
            jaxbMarshaller = jaxbContext.createMarshaller();    
            jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        } catch (JAXBException ex) {
            System.err.println(ex.getMessage());
        }
    }
  


    
    
/**
 * Obte la representacio XML d'un objecte en forma d'String.
 * S'assumeix que la representacio es correcta.
 * @param obj objecte a representar en XML
 * @return representacio XML de l'objecte indicat pel parametre
 * @throws gestors.GestorException si es produeix algun error en la conversio
 */
    
    public static String formaObjecteXML(Medicament obj) throws GestorException {
        
        try {
            StringWriter sw = new StringWriter();
            
            jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
           
            jaxbMarshaller.marshal(obj, sw);
            
            return sw.toString();
            
        } catch (JAXBException ex) {
            throw new GestorException(ex.getMessage());
        }

    }
 /**
  * Obte un objecte a partir de la seva representacio en XML continguda en un String.
  * @param dades representacio XML d'un objecte
  * @return objecte dades corresponent a la representacio XML continguda al parametre
  * @throws gestors.GestorException si es produeix algun error en la conversio
  */   
    public static Medicament obteObjecte(String dades) throws GestorException {
        
        try {
            StringReader reader = new StringReader(dades);
            jaxbUnmarshaller.setEventHandler(new javax.xml.bind.helpers.DefaultValidationEventHandler());
            Medicament c = (Medicament) jaxbUnmarshaller.unmarshal(reader);
            return c;
            
        } catch (JAXBException ex) {
            ex.printStackTrace();
            throw new GestorException(ex.getMessage());
        }
    
    }

}