/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eac4.src.gestors;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.xquery.*;
import model.Medicament;



/**
 * @author Isabel Calzadilla M-06
 * @version  : 17-11-2022
 * @Resum : Activitat 2, M06 UF3
 */
public class GestorMedicament {


    protected static final String CLAU_DUPLICADA = "CLAU DUPLICADA";
    protected static final String CLAU_INEXISTENT = "CLAU INEXISTENT";
    public static final String ARREL = "doc(\"medicaments/medicaments.xml\")/collection(\"medicaments\")//"; //arrel del document (permet simplificar les expressions)
    
    // VARIABLES GLOBALES A REUSAR EN CADA MÉTODO
    protected String consult;
    protected XQExpression expression;
    protected XQResultSequence querys;
    private XQConnection con=null;

    /**
     * Crea un gestor d'objecte que treballara amb la connexio connex
     * @param con connexio a traves de la qual es fan persistents els objectes
     */
    public GestorMedicament(XQConnection con) {
       this.con = con;
    }
    
    
    /**
     * Dona d'alta un Medicament en la base de dades.Si ja n'hi ha alguna amb el seu mateix id, llenca una excepcio.
     * @param nou medicament a donar d'alta
     * @throws gestors.GestorException en cas d'error a la base de dades que pot ser, entre altres, clau duplicada.
     * @throws javax.xml.xquery.XQException
     */
    public void inserir(Medicament nou) throws GestorException, XQException  {
        
        // ÉSTE CONDIICONAL SOLO VERIFICA SI EL ID DADO ES DUPLICADO O NO
        if(obtenirMedicament(nou.getId()) != null){
            expression.cancel();
            throw new GestorException(CLAU_DUPLICADA);
        }
        
        try{
                // GUARDAMOS EL XML NUEVO ADHERIDO DEL OBJETO CON EL MÉTODO DE LA CLASE 'Utilitats'
            String declaration = Utilitats.formaObjecteXML(nou);

            // DECLARACION CON EL ARREL PARA AGREGAR DENTRO DE LA COLECCIÓN EL NUEVO VALOR
            consult = "insert node " + declaration + " into " + ARREL + "medicaments";

            // CREAMOS LA EXPRESIÓN
            expression = con.createExpression();
            expression.executeQuery(consult);
            expression.close();          
        } catch (XQException ex) {
            expression.cancel();
            throw new GestorException(ex.getMessage());
        } finally {
            expression.close();
        }
    }

    
  
    /**
     * Esborra de la base de dades un medicament amb un id determinat
     * @param id id de l'medicament  a esborrar
     * @throws gestors.GestorException si el codi no correspon a cap medicament de la base de dades
     * o hi ha un error en l'acces a la base de dades
     */
    public void eliminar(int id) throws GestorException {
        
        // ÉSTE CONDICIONAL SOLO VERIFICA SI EL ID DADO ES INEXISTENTE O NO
        if(obtenirMedicament(id) == null){
            throw new GestorException(CLAU_INEXISTENT);
        }
       
        try {
            // LA CONSULTA SOLO ELIMINA EL NODE CON EL ARREL AL MEDICAMENTO CON ESTE ID
            consult = "delete node " + ARREL + "medicaments/medicament[id = \"" + id + "\" ]";
            expression = con.createExpression();
            //EJECUTA LA CONSULTA
            expression.executeQuery(consult);
            expression.close(); 
        } catch (XQException ex) {
            throw new GestorException(ex.getMessage());
        }     
        
    }


    
    /**
     * Obte l'objecte de la base de dades amb un determinat id.
     * @param id identificador de l'objecte a obtenir
     * @return objecte amb id o bé null si no hi ha cap objecte a la base de dades
     * @throws gestors.GestorException en cas d'error a la base de dades
     */
    public Medicament obtenirMedicament(int id) throws GestorException  {
        
        try {
            expression = con.createExpression();
            // XQResultSequence = Obtiene una secuencia, literal de los resultados que devuelva el for de la  
            querys = expression.executeQuery("for $x in " + ARREL + "medicaments/medicament[id = \"" + id + "\"] return $x");
            
            // DECLARAMOS LA VARIABLE AUXILIAR QUE ALMACENARÁ EL RESULTADO DE LA CONSULTA Y QUE VERIFICARÁ SI HA SIDO NULO O NO
            String value = null;
            
            // LA OBTENCIÓN DEL ID ES DE UN RESULTADO ÚNICO, POR LO CUAL ES INDISTINTO EL USO DEL IF O EL 'WHILE', EN ESTE CASO AL TENER LA CERTEZA DE QUE SOLO ALMACENA Y TENDRÁ 1 RESULTADO, USARÉ EL IF
            if (querys.next()){
                value = querys.getItemAsString(null);
            }
            expression.close();
            
            // VALIDACIÓN DE LA VARIABLE AUXILIAR, EN CASO NULO, O EL CASO DE RETORNO DE VALOR
            if(value == null){
                return null;
            }else{
                return Utilitats.obteObjecte(value);
            }    
        } catch (XQException ex) {
            throw new GestorException (ex.getMessage());
        } 
    }

    
    /**
     * Obté una llista amb els medicaments que compten amb aquesta contraindicacio al seu array
     * @param elem contraindicacio per a obtenir la llista
     * @return Llista amb els medicaments de la base de dades que contenen l'element demanat
     * @throws gestors.GestorException en cas d'error a la base de dades
     */
    public List<Medicament> obtenirMedicamentPerContraindicacio(String elem) throws GestorException  {
        
        try {
            expression = con.createExpression();
            
            // al ser una consulta  sobre una coleccion contenida, debemos de realizar un casting de esa consulta a una (XQResultSequence) para que lo logre recorrer
            querys = (XQResultSequence) expression.executeQuery("for $x in " + ARREL + "medicaments/medicament[contraindicacions/contraindicacio = \"" + elem + "\"] return $x");

            // DECLARACIÓN DE UN NUEVO ARRAY QUE ALMACENARÁ LAS COINCIDENCIAS DE LA CONSULTA
            List<Medicament> results = new ArrayList<>();
            
            // HACEMOS USO DE UN WHILE PUES EL RESULTADO POTENCIALMENTE SERÁ DE +1
            while(querys.next()){
                                                    //OBTIENE CADA ITEM DEL NODO CHILD DENTRO DE LA COLECCIÓN
                results.add(Utilitats.obteObjecte(querys.getItemAsString(null)));
            }
             expression.close();
             
            return results;
        } catch (XQException ex) {
            throw new GestorException(ex.getMessage());
        }
    }

}