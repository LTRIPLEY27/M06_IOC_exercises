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
    public List<Laboratori> obtenirLaboratoris() {
       //TODO completar el metode
       return null; // nomes esta perque no doni error de compilacio; probablement s'haura d'eliminar o canviar
    }
    
       /**
     * Esborra laboratori de la base de dades que te determinat codi
     * @param id codi de laboratoris a esborrar
     * @throws logicaAplicacio.gestors.GestorException si el codi no correspon a cap laboratori de la base de dades
     * o hi ha un error en aquesta
     */
    public void eliminar(int id) throws GestorException {
        //TODO completar el metode
       
    }
    
    /**
     * Obte laboratori de la base de dades amb un codi determinat
     * @param id codi de laboratori a obtenir
     * @return  laboratori o null en cas de no haver-hi cap laboratori persistent amb aquest codi
     */
    public Laboratori obtenirLaboratori(int id) {
        //TODO completar el metode
       return null; // nomes esta perque no doni error de compilacio; probablement s'haura d'eliminar o canviar

    }
    
    /**
     * Dona d'alta un laboratori en la base de dades. Si ja n'hi ha algun amb el seu mateix codi, llenca una excepcio.
     * @param lab Laboratori a inserir
     * @throws logicaAplicacio.gestors.GestorException en cas d'error a la base de dades que pot ser, entre altres, clau duplicada.
     */
    public void inserir(Laboratori lab) throws GestorException {
        //TODO completar el metode

    }
    
    /**
     * Modifica un laboratoris de la base de dades. Si no n'hi ha cap amb el seu codi, llenca una excepcio.
     * @param lab Laboratori a actualitzar
     * @throws logicaAplicacio.gestors.GestorException en cas d'error a la base de dades que pot ser, entre altres, clau inexistent.
    */
    public void modificar(Laboratori lab) throws GestorException{
        //TODO completar el metode

    }
    
    
}
