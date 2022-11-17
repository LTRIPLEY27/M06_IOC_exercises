/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logicaAplicacio.gestors;

import logicaAplicacio.model.Producte;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Classe per abstreure diferents accions sobre els objectes de tipus Producte
 * @author professor
 */
public class GestorProducte {

    private EntityManager em = null;
    private Query querys;

    /**
     * Constructor que associa el gestor a un EntityManager
     * @param em EntityManager al qual s'associa el gestor
     */
    public GestorProducte(EntityManager em) {
        this.em = em;
    }

    /**
     * Consultar tots els producte de la base de dades
     * @return Llista amb tots els producte de la base de dades
     */
    public List<Producte> obtenirProductes() {
       
        querys = em.createNamedQuery("Producte.all");
  
        return querys.getResultList();
    }
     /**
     * Esborra el producte de la base de dades que te un determinat codi
     * @param id codi que identifica el producte a esborrar
     * @throws logicaAplicacio.gestors.GestorException si el codi no correspon a cap producte de la base de dades
     * o hi ha un error en aquesta
     */
    public void eliminar(int id) throws GestorException {
        
        if(obtenirProducte(id) == null){
            throw new GestorException("ID inexistente");
        }
        querys = em.createNamedQuery("Producte.delete");
        // EL SETTEO DE LA QUERY HA DE REALIZARSE FUERA DE LA INVOCACIÓN DEL TRANSACTION
        querys.setParameter("codi", id);
        em.getTransaction().begin();
        querys.executeUpdate();
        
        em.getTransaction().commit();
        
    }

    /**
     * Obte el producte de la base de dades amb un codi determinat
     * @param id codi del producte a obtenir
     * @return objecte o null en cas de no haver-hi cap producte persistent amb aquest codi
     */
    public Producte obtenirProducte(int id) {
        try{
            querys = em.createNamedQuery("Producte.getter");
            querys.setParameter("codi", id);

            return (Producte) querys.getSingleResult();
        } catch(Exception e){
            return null;
        }  
    }

    /**
     * Dona d'alta un producte en la base de dades. Si ja n'hi ha algun amb el seu mateix codi, llenca una excepcio.
     * @param p producte a inserir
     * @throws logicaAplicacio.gestors.GestorException en cas d'error a la base de dades que pot ser, entre altres, clau duplicada.
     */
    public void inserir(Producte p) throws GestorException {
       
        if(em.find(Producte.class, p.getId()) != null){
            throw new GestorException("Id registrado");
        }
        
        em.getTransaction().begin();
        em.merge(p);
        em.getTransaction().commit();   
    }

   /**
     * Modifica un producte de la base de dades. Si no n'hi ha cap amb el seu codi, llenca una excepcio.
     * @param p objecte a actualitzar
     * @throws logicaAplicacio.gestors.GestorException en cas d'error a la base de dades que pot ser, entre altres, clau inexistent.
    */
    public void modificar(Producte p) throws GestorException{
       
       if(em.find(Producte.class, p.getId()) == null){
           throw new GestorException("Clau inexistent.");
       }
       querys = em.createNamedQuery("Producte.update");
       querys.setParameter("nom", p.getNom());
       querys.setParameter("preu", p.getPreu());
       querys.setParameter("laboratori", p.getLaboratori());
       querys.setParameter("codi", p.getId());
       
       
       em.getTransaction().begin();
       querys.executeUpdate();
       //em.merge(p);
       em.getTransaction().commit();
    }
    
   /**
     * Incrementa el preu de tots els productes de la base de dades en un percentatge determinat
     * @param percentantge tant per cent (%) d'increment 
     */
    public void incrementarPreu(float percentantge){
        /*querys = em.createNamedQuery("Producte.all");
        
        List <Producte> totals = querys.getResultList();
        for(var i : totals){
            i.setPreu(i.getPreu() + (i.getPreu() * percentantge / 100));
            em.getTransaction().begin();
            em.merge(i);
            em.getTransaction().commit();
        }*/
        
        //**************************************************
        
        querys = em.createNamedQuery("Producte.up");
        querys.setParameter("incre", percentantge);
        em.getTransaction().begin();
        querys.executeUpdate();
        em.getTransaction().commit();
    }
    
}
