/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicaAplicacio.gestors;

import logicaAplicacio.model.Laboratori;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Classe per abstreure diferents accions sobre els objectes de tipus Laboratori
 * @author professor
 */
public class GestorLaboratori {

    private EntityManager em = null;
    private Query query = null;

    /**
     * Constructor que associa el gestor a un EntityManager
     * @param em EntityManager al qual s'associa el gestor
     */
    public GestorLaboratori(EntityManager em) {
        this.em = em;
    }
    
     /**
     * Consultar tots els laboratoris de la base de dades
     * @return Llista amb tots els laboratoris de la base de dades
     */
    public List<Laboratori> obtenirLaboratoris() /*throws GestorException*/ {
       //TODO completar el metode
       query = em.createNamedQuery("Laboratori.all");
       List <Laboratori> total = query.getResultList();
       
       //if(total.isEmpty()){
           //throw new GestorException("No se encuentran registros");
      // }
       return total; // nomes esta perque no doni error de compilacio; probablement s'haura d'eliminar o canviar
    }
    
       /**
     * Esborra laboratori de la base de dades que te determinat codi
     * @param id codi de laboratoris a esborrar
     * @throws logicaAplicacio.gestors.GestorException si el codi no correspon a cap laboratori de la base de dades
     * o hi ha un error en aquesta
     */
    public void eliminar(int id) throws GestorException {
        
        if(em.find(Laboratori.class, id) == null){
            throw new GestorException("El codi no correspon a cap laboratori de la base de dades o hi ha un error en aquesta");
        }
        query = em.createNamedQuery("Laboratori.delete");
        
        query.setParameter("codi", id);
        em.getTransaction().begin();
        
        query.executeUpdate();
        em.getTransaction().commit();
        
        System.out.println("OK DELETE");
    }
    
    /**
     * Obte laboratori de la base de dades amb un codi determinat
     * @param id codi de laboratori a obtenir
     * @return  laboratori o null en cas de no haver-hi cap laboratori persistent amb aquest codi
     */
    public Laboratori obtenirLaboratori(int id) {
      /* query = em.createNamedQuery("Laboratori.getter");
       query.setParameter("codi", id);
       
       Laboratori byQuery = (Laboratori) query.getSingleResult();
       
       if(byQuery == null){
           return null;
       }
       
       return byQuery;*/
       
       try{
            query = em.createNamedQuery("Laboratori.getter");
            query.setParameter("codi", id);
            //em.getTransaction().begin();
            //querys.executeUpdate();
            //em.getTransaction().commit();
            Laboratori byId = (Laboratori) query.getSingleResult();
            return byId;
        }catch(Exception e){
            return null;
        }
    }
    
    /**
     * Dona d'alta un laboratori en la base de dades. Si ja n'hi ha algun amb el seu mateix codi, llenca una excepcio.
     * @param lab Laboratori a inserir
     * @throws logicaAplicacio.gestors.GestorException en cas d'error a la base de dades que pot ser, entre altres, clau duplicada.
     */
    public void inserir(Laboratori lab) throws GestorException {
        if(em.find(Laboratori.class, lab.getId()) != null){
            throw new GestorException("Clau duplicada");
        }
        em.getTransaction().begin();
        em.merge(lab);
        em.getTransaction().commit();
    }
    
    /**
     * Modifica un laboratoris de la base de dades. Si no n'hi ha cap amb el seu codi, llenca una excepcio.
     * @param lab Laboratori a actualitzar
     * @throws logicaAplicacio.gestors.GestorException en cas d'error a la base de dades que pot ser, entre altres, clau inexistent.
    */
    public void modificar(Laboratori lab) throws GestorException{
        if(em.find(Laboratori.class, lab.getId()) == null){
            throw new GestorException("Clau inexistent.");
        }
        em.getTransaction().begin();
        em.merge(lab);
        em.getTransaction().commit();
    }
    
    
}
