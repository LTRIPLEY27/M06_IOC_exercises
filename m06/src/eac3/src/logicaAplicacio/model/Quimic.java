/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logicaAplicacio.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Producte que treballa com a professor de secundària a un institut
 * @author professor
 */
//TODO posar les anotacions necessaries a la classe
public class Quimic extends Producte {
    
    private int perillositat;
       
    
    /**
     * Constructor per defecte: construeix un objecte amb els valors per defecte de Java
    */
    public Quimic() {
    }

    /**
     * Constructor parametritzat: construeix un objecte amb els parametres especificats
     * @param codi codi que identifica a l'objecte
     * @param nom nom del obbjecte
     * @param preu preu
     * @param perillositat del professor de secundària
     * @param centre institut on està assigant el professor de secundària
     */
    public Quimic(int codi, String nom, float preu, int perillositat, Laboratori centre) {
        super(codi, nom, preu, centre);
        this.perillositat=perillositat;
    }

    /**
     * Obte la perillositat
     * @return perillositat
     */
    public int getPerillositat() {
        return perillositat;
    }

    public void setPerillositat(int p) {
        this.perillositat = p;
    }

   
}
