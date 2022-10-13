/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAM_M06_EAC2_Calzadilla_C.Exercisi2.src.gestors;

import DAM_M06_EAC2_Calzadilla_C.Exercisi2.src.model.Medicament;
import javax.persistence.EntityManager;

/**
 * Situacio excepcional produida en el sistema de persistencia
 * @author Isabel Calzadilla M-06
 * @version  : 12-10-2022
 * @Resum : Activitat 2, M06 UF2
 */

public class GestorMedicamentOODB {
     private EntityManager em = null;
     private Medicament entityDB;
    
    /**
     * Crea un gestor de persistencia que treballara amb l'EntityManager Medicament
     * @param obj context on es fan persistentents els objectes
     */
    public GestorMedicamentOODB(EntityManager obj) {
        
       this.em = obj;
       
    }
    
    
    /**
     * Dona d'alta un objecte en la base de dades. Si ja n'hi ha algun amb el seu mateix nombre de serie, llenca una excepcio.
     * @param obj objecte a donar d'alta
     * @throws gestors.GestorException en cas d'error a la base de dades que pot ser, entre altres, clau duplicada.
     */
    public void inserir(Medicament obj) throws GestorException {
       
        //LLAMADA AL MÉTODO DE CLASE 'obtenirMedicament'PARA VERIFICAR LA EXISTENCIA EN LA DATABASE LA EXISTENCIA O NO DEL ID, E CASO DE EXISITIR, INVOCAMOS A LA EXCEPCION
        if(obtenirMedicament(obj.getNombreSerie()) != null){
             throw new GestorException("Error, ID existente en la database");
        }
      
        // DECLARAMOS EL OBJETO
        entityDB = new Medicament(obj.getNombreSerie(), obj.getNomMedicament(), obj.getPreu(), obj.getAutoritatSanitaria(), obj.getEmpresa(), obj.getPesEnMg(), obj.isAprovat());

        //LLAMADO AL ENTITY MANAGER PARA DECLARAR LA PERSISTENCIA EN BASE DE DATOS
        em.getTransaction().begin();
        // CON EL 'MERGE' AGREGAMOS U EDITAMOS EN DATABASE
        em.merge(entityDB);
        //DETACH LIMPIAR el objeto enviado a la database
        em.detach(entityDB);
        // CONFIRMAMOS TODO LO PREVIO CON EL MÉTODO 'COMMIT'
        em.getTransaction().commit();
    }

    
    /**
     * Modifica un objecte de la base de dades. Si no n'hi ha cap amb el seu nombre de serie, llenca una excepcio.
     * @param obj objecte actualitzat
     * @throws gestors.GestorException en cas d'error a la base de dades que pot ser, entre altres, clau duplicada.
     */
    public void modificar(Medicament obj) throws GestorException {
      //TODO codificar el metode modificar

        // LLAMA EL MÉTODO DE OBTENER EL OBJETO
        if(obtenirMedicament(obj.getNombreSerie()) == null){
            throw new GestorException("Error, ID Inexistente en la database");
        }
        // CASO CONTRARIO, INVOCAMOS EL MERGE YA QUE 'EDITA' U 'AGREGA' EL OBJETO
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();
    }    
    
    
    /**
     * Esborra un objecte amb un determinat numero de serie
     * @param nombreSerie nombre serie de l'objecte a esborrar
     * @throws gestors.GestorException si el nombre de serie no correspon a cap objecte de la base de dades
     */
    public void eliminar(int nombreSerie) throws GestorException {
      // LLAMA EL MÉTODO DE OBTENER EL OBJETO
        if(obtenirMedicament(nombreSerie) == null){
            throw new GestorException("Error, ID Inexistente en la database");
        }
        entityDB = em.find(Medicament.class, nombreSerie);
        em.getTransaction().begin();
        em.remove(entityDB);
        em.getTransaction().commit();
    }

   
    /**
     * Obte l'objecte de la base de dades amb un numero de serie determinat
     * @param nombreSerie nombre serie de l'objecte a obtenir
     * @return objecte amb aquest nombre serie o null si no hi ha cap objecte a la base de dades amb aquest numero de serie
     */
    public Medicament obtenirMedicament(int nombreSerie) {
        //TODO codificar el metode obtenirMedicament
        //RECIBIMOS EL VALOR DEL OBJETO CON EL ID DESDE LA BASE DE DATOS
        entityDB = em.find(Medicament.class, nombreSerie);

        if(entityDB == null){
            System.err.println("Error, ID inexistente en la Base de Datos");
            return null;
        }
        //DETACH LIMPIAR el objeto enviado a la database
        em.detach(entityDB);
        return entityDB;     
    }
}
