/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAM_M06_EAC2_Calzadilla_C.Exercisi1.src.gestors;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import DAM_M06_EAC2_Calzadilla_C.Exercisi1.src.model.Medicament;

/**
 * Classe que gestiona la persistencia dels objectes de la classe model
 *  @author Isabel Calzadilla M-06
 *  @version  : 10-10-2022
*/
public class GestorMedicament {

    private Connection conn = null;

    /**
     * Crea un gestor de l'objecte que treballara amb la connexio conn
     * @param conn connexio a traves de la qual es fan persistents els objectes
     */
    public GestorMedicament(Connection conn) {
       this.conn = conn;
    }
    
    /**
     * Crea un Objecte en la base de dades. Si ja n'hi ha algun amb el seu mateix id, llenca una excepcio.
     * @param obj Objecte a crear
     * @throws gestors.GestorException en cas d'error a la base de dades que pot ser, entre altres, clau duplicada.
     */
    public void inserir(Medicament obj) throws GestorException  {
        //TODO codificar el metode inserir
     
        
    }

    
    /**
     * Esborra de la base de dades un objecte amb un id determinat
     * @param objId id de l'objecte a esborrar
     * @throws gestors.GestorException si el id no correspon a cap objecte de la base de dades
     * o hi ha un error en l'acces a la base de dades
     */
   
    public void eliminar(int objId) throws GestorException {
       //TODO codificar el metode eliminar
       
       
    }


    
    /**
     * Obte l'objecte de la base de dades amb un determinat id.
     * @param objId id de l'objecte a obtenir
     * @return l'objecte que té l'id proporcionada o null si no hi ha cap objecte amb aquest id a la base de dades
     * @throws gestors.GestorException en cas d'error a la base de dades
     */
   
    public Medicament obtenirMedicament(int objId) throws GestorException  {
        //TODO codificar el metode obtenirMedicament
        
        
        //només per evitar errors de compilació. Canviar-ho
        return null;
        
    }

    /**
     * Retorna una llista dels objectes que tenen la contraindicacio proporcionada
     * @param contraindicacio contraindicacio utilitzada per a obtenir la llista d'objectes
     * @return Llista amb els objectes de la base de dades que contenen l'esmentada contraindicacio al seu array.
     * @throws gestors.GestorException en cas d'error a la base de dades
     */
    
    public List<Medicament> obtenirMedicamentPerContraindicacio(String contraindicacio) throws GestorException  {
        //TODO codificar el metode obtenirMedicamentPerContraindicacio
        
        //només per evitar errors de compilació. Canviar-ho
        return null;
        
        	
    }
    

}

