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
import javax.persistence.Table;

/**
 *
 * @author professor
 */
//TODO posar les anotacions necessaries a la classe
@Entity
@Table(name = "medicament")
@DiscriminatorValue("M")
@NamedQueries({
    @NamedQuery(name = "Medicament.all", query = "SELECT x FROM Medicament x"),
    @NamedQuery(name = "Medicament.bylab", query = "SELECT x FROM Medicament x WHERE x.laboratori.id = :codi"),
    @NamedQuery(name = "Medicament.bypa", query = "SELECT x FROM Medicament x WHERE x.principiActiu = :pa"),
    @NamedQuery(name = "Medicament.byprice", query = "SELECT x FROM Medicament x WHERE x.preu = :price")
})
public class Medicament extends Producte {
    
    private String principiActiu;
    
    
    /**
     * Constructor per defecte: construeix un professor del cos de tècnics amb els valors per defecte de Java
     */
    public Medicament() {
    }

    /**
     * Constructor parametritzat: construeix  un professor del cos de tècnics amb els parametres especificats
     * @param codi codi que identifica
     * @param nom nom del objecte
     * @param preu preu
     * @param pa principiActiu  
     * @param centre laboratori
     */
    public Medicament(int codi, String nom, float preu, String pa, Laboratori centre) {
        super(codi, nom, preu, centre);
        this.principiActiu = pa;   
    }

    @Column(name = "principi_actiu", length = 100)
    public String getPrincipiActiu() {
        return principiActiu;
    }

    public void setPrincipiActiu(String espec) {
        this.principiActiu = espec;
    }
    
    
}
