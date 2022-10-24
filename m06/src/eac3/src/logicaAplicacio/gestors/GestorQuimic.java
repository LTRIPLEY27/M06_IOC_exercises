/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicaAplicacio.gestors;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import logicaAplicacio.model.Quimic;

/**
 * Classe que gestiona la persistencia dels objectes de la classe logicaAplicacio.model.Quimic
 * @author professor
 */
public class GestorQuimic {
    private EntityManager em = null;

    /**
     * Crea un gestor de productes quimics que treballara amb l'EntityManager em
     * @param em context on es fan persistents 
     */
    public GestorQuimic(EntityManager em) {
       this.em = em;
    }

    /**
     * Consultar tots els productes quimics de la base de dades
     * @return Llista amb tots els productes quimics de la base de dades
     */
    public List<Quimic> obtenirProductesQuimics() {
       //TODO completar el metode
       return null; // nomes esta perque no doni error de compilacio; probablement s'haura d'eliminar o canviar
    }
   
    /**
     * Obte una llista amb tots els productes quimics de la base de dades amb una `perillositat determinada
     * @param perillositat 
     * @return llistat amb els productes quimics de la base de dades amb una `perillositat determinada
     */
    public List<Quimic> obtenirQuimicPerPerillositat(int perillositat) {
       //TODO completar el metode
       return null; // nomes esta perque no doni error de compilacio; probablement s'haura d'eliminar o canviar
    }
    
     /**
     * Incrementa el preu de tots els objectes que tenen una determinada perillositat en un percentatge conccret
     * @param p perillositat dels objectes dels quals incrementarem els sous
     * @param percentantge tant per cent (%) d'increment 
     */
    public void incrementarPreusSegonsPerillositat(int p, float percentantge){
        //TODO completar el metode
      
    }

}
