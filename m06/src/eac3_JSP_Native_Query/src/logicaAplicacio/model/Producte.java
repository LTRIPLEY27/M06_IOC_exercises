/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logicaAplicacio.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import static javax.persistence.DiscriminatorType.CHAR;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import static javax.persistence.InheritanceType.SINGLE_TABLE;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Classe Producte de la jerarquia de lògica de l'aplicació
 * @author alumne
 */
//TODO posar les anotacions necessaries a la classe
@Entity
@Inheritance(strategy = SINGLE_TABLE)
@DiscriminatorColumn(name = "tipusProducte",discriminatorType = CHAR, length = 1)
//@DiscriminatorValue("P")
@NamedQueries({
    @NamedQuery(name = "Producte.all", query = "SELECT x FROM Producte x"),
    @NamedQuery(name = "Producte.delete", query = "DELETE FROM Producte x WHERE x.id = :codi"),
    @NamedQuery(name = "Producte.getter", query = "SELECT x FROM Producte x WHERE x.id = :codi"),
    @NamedQuery(name = "Producte.up", query = "UPDATE Producte x SET x.preu = x.preu + (x.preu * :incre / 100)"),
    @NamedQuery(name = "Producte.update", query = "UPDATE Producte x SET x.nom = :nom, x.preu = :preu, x.laboratori = :laboratori WHERE x.id = :codi") //SET x.nom = :nom, x.preu = :preu, x.laboratori = :laboratori WHERE
})
@Table(name = "producte")
public class Producte implements Serializable {
  private int id;
  private String nom;
  private float preu;
  private Laboratori laboratori;

   /**
    * Constructor per defecte: construeix un nou professor amb els valors per defecte de Java
    * (es necessari perque pugui funcionar JPA correctament)
    */
   public Producte() {
   }   
   
   
   /**
    * Constructor parametritzat: construeix un nou objecte amb els paramtres especificats
    * @param codi identificador del objecte
    * @param nom nom del objecte
    * @param preu preu assigant al objecte
    * @param centre laboratori on està assigant el objecte
    */
   protected Producte(int codi, String nom, float preu, Laboratori centre) {
        this.id = codi;
        this.nom = nom;
        this.preu = preu;
        this.laboratori = centre;
   }
   
   /**
    * Obte el codi que identifica el objecte
    * @return id que identifica el objecte
    */
   
   @Id
   public int getId() {
        return id;
   }

   /**
    * Actualitza el codi que identifica el objecte
    * @param codi nou valor per al id que identifica el objecte
    */
   public void setId(int codi) {
        this.id = codi;
   }   

   /**
    * Obte el nom del objecte
    * @return nom del objecte
    */
   @Column(name = "nom", length = 50)
   public String getNom() {
        return nom;
   }

   /**
    * Actualitza el nom del objecte
    * @param nom nou valor per al nom del objecte
    */
   public void setNom(String nom) {
        this.nom = nom;
   }

   /**
    * Obte el preu del objecte
    * @return preu del objecte
    */
   
   public float getPreu() {
        return preu;
   }

   /**
    * Actualitza el preu del objecte
    * @param sou nou valor per al preu del objecte
    */
   public void setPreu(float p) {
        this.preu = p;
   }
   
   /**
     * Obté l'laboratori 
     * @return el laboratori
     */
    @ManyToOne
    public Laboratori getLaboratori() {
        return laboratori;
    }
    
    /**
    * Actualitza el laboratori
    * @param centre nou valor per al laboratori
    */
   public void setLaboratori(Laboratori centre) {
        this.laboratori = centre;
   }
    
}
