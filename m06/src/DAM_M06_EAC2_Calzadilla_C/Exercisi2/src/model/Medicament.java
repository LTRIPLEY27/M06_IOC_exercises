/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAM_M06_EAC2_Calzadilla_C.Exercisi2.src.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author professor
 */

@Entity
public class Medicament implements Serializable{
    
    //TODO: S'han de posar les anotacions corresponents a aquest arxiu
    
    private int nombreSerie;
    private String nomMedicament;
    private float preu;
    private String autoritatSanitaria;
    private String empresa;
    private int pesEnMg;
    private boolean aprovat;
 
    /**
     * Constructor per defecte Constructors parametritzats
     */
    public Medicament() {
        
    }

    /**
     * Constructor parametritzat
     */
    public Medicament(int nombreSerie, String nomMedicament, float preu, String autoritatSanitaria, String empresa, int pesEnMg, boolean aprovat) {
        this.nombreSerie = nombreSerie;
        this.nomMedicament = nomMedicament;
        this.preu = preu;
        this.autoritatSanitaria = autoritatSanitaria;
        this.empresa = empresa;
        this.pesEnMg = pesEnMg;
        this.aprovat = aprovat;
    }

    /**
     * @return the nombreSerie
     */

    @Id
    public int getNombreSerie() {
        return nombreSerie;
    }

    /**
     * @param nombreSerie the nombreSerie to set
     */
    public void setNombreSerie(int nombreSerie) {
        this.nombreSerie = nombreSerie;
    }

    /**
     * @return the nomMedicament
     */
    public String getNomMedicament() {
        return nomMedicament;
    }

    /**
     * @param nomMedicament the nomMedicament to set
     */
    public void setNomMedicament(String nomMedicament) {
        this.nomMedicament = nomMedicament;
    }

    /**
     * @return the preu
     */
    public float getPreu() {
        return preu;
    }

    /**
     * @param preu the preu to set
     */
    public void setPreu(float preu) {
        this.preu = preu;
    }

    /**
     * @return the autoritatSanitaria
     */
    public String getAutoritatSanitaria() {
        return autoritatSanitaria;
    }

    /**
     * @param autoritatSanitaria the autoritatSanitaria to set
     */
    public void setAutoritatSanitaria(String autoritatSanitaria) {
        this.autoritatSanitaria = autoritatSanitaria;
    }

    /**
     * @return the empresa
     */
    public String getEmpresa() {
        return empresa;
    }

    /**
     * @param empresa the empresa to set
     */
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    /**
     * @return the pesEnMg
     */
    public int getPesEnMg() {
        return pesEnMg;
    }

    /**
     * @param pesEnMg the pesEnMg to set
     */
    public void setPesEnMg(int pesEnMg) {
        this.pesEnMg = pesEnMg;
    }

    /**
     * @return the aprovat
     */
    public boolean isAprovat() {
        return aprovat;
    }

    /**
     * @param aprovat the aprovat to set
     */
    public void setAprovat(boolean aprovat) {
        this.aprovat = aprovat;
    }

    



}

