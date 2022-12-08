/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m6.uf4.eac5.aplicacio;


/**
 * Classe que representa un barri
 * @author Professor
 */
public class Barri  {

    private String nom;
    private float densitatPoblacio;
    private Districte districte;


    
    /**
     * Permet consultar el nom del barri
     * @return nom del barri
     */
    public String getNom() {
        return nom;
    }
    
    /**
     * Permet actualitzar el nom del barri
     * @param nom nom a assignar al barri
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Permet consultar la densitat de poblacio del barri
     * @return la densitat de població del barri
     */
    public float getDensitatPoblacio() {
        return densitatPoblacio;
    }
    
    /**
     * Permet actualitzar la densitat de poblacio del barri
     * @param densitatPoblacio densitatPoblacio a assignar al barri
     */
    public void setDensitatPoblacio(float densitatPoblacio) {
        this.densitatPoblacio = densitatPoblacio;
    }
    
   
    /**
     * Permet consultar el districte d'on es el barri
     * @return districte del barri
     */
    public Districte getDistricte() {
        return districte;
    }

    /**
     * Permet actualitzar el districte d'on es el barri
     * @param districte districte a assignar al barri
     */
    public void setDistricte(Districte districte) {
//        if(this.districte!=null){
//            districte.getBarris().remove(this);
//        }
        this.districte = (Districte)districte;
//        if(this.districte!=null){
//            districte.getBarris().add(this);
//        }
    }
    
    
}
