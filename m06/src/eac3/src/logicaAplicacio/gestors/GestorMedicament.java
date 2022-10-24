/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicaAplicacio.gestors;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import logicaAplicacio.model.Medicament;

/**
 * Classe que gestiona la persistencia dels objectes de la classe logicaAplicacio.model.Medicament
 * @author professor
 */
public class GestorMedicament {
    private EntityManager em = null;

    /**
     * Crea un gestor d'objecte que treballara amb l'EntityManager em
     * @param em context on es fan persistents els medicaments
     */
    public GestorMedicament (EntityManager em) {
       this.em = em;
    }

    /**
     * Obte una llista amb tots els medicaments
     * @return llista amb els medicaments de la base de dades
     */
    public List<Medicament> obtenirMedicaments() {
       //TODO completar el metode
       return null; // nomes esta perque no doni error de compilacio; probablement s'haura d'eliminar o canviar
    }
    
    /**
     * Obté una llista amb tots els medicaments produits en un laboratori
     * @param codiLab identificador del laboratori
     * @return llistat dels medicaments produits en aquest lab
     */
    public List<Medicament> obtenirMedicamentsPerLab(int codiLab) {
       //TODO completar el metode
       return null; // nomes esta perque no doni error de compilacio; probablement s'haura d'eliminar o canviar
    }
    
    /**
     * Obté una llista amb tots els medicaments amb un principi actiu proporcionat de la BD
     * @param principiActiu 
     * @return llistat dels tècnics amb un principi actiu especific de la base de dades
     */
    public List<Medicament> obtenirMedicamentsPerPA(String principiActiu) {
       //TODO completar el metode
       return null; // nomes esta perque no doni error de compilacio; probablement s'haura d'eliminar o canviar
    }
    
    /**
     * Obté una llista amb tots els medicaments amb un preu determinat
     * @param preu preu del medicaments
     * @return llistat dels medicaments amb un preu determinat
     */
    public List<Medicament> obtenirMedicamentsPerPreu(float preu) {
       //TODO completar el metode
       return null; // nomes esta perque no doni error de compilacio; probablement s'haura d'eliminar o canviar
    }

}
