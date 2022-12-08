/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m6.uf4.eac5.aplicacio;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa un districte urba
 * @author Professor
 */
public class Districte {
    private int numero;
    private String nom;
    private int habitants;
    private final List<Barri> barris = new ArrayList();


    /**
     * Permet consultar el numero que identifica el districte
     * @return numero que identifica el districte
     */    

    public int getNumero() {
        return numero;
    }

    /**
     * Permet actualitzar el numero que identifica el districte
     * @param numero valor a assignar al numero que identifica el districte
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * Permet consultar el nom del districte
     * @return  nom del districte
     */
    public String getNom() {
        return nom;
    }

    /**
     * Permet actualitzar el nom del districte
     * @param nom nom a donar al districte
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
   
    /**
     * Permet actualitzar el nombre d'habitants del districte
     * @param habitants nombre d'habitants a assignar al districte
     */
    public void setHabitants(int habitants) {
        this.habitants = habitants;
    }

    /**
     * Permet consultar el nombre d'habitants del districte
     * @return nombre d'habitants del districte
     */
    public int getHabitants() {
        return habitants;
    }    
    /**
     * Permet consultar la referencia a la llista amb tots els barris del districte
     * @return llista amb els barris del districte
     */
    public List<Barri> getBarris() {
        return (List<Barri>)barris;
    }

    
}
