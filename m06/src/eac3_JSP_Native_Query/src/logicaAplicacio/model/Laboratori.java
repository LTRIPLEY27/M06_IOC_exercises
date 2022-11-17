/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicaAplicacio.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Classe Laboratori de la jerarquia de lògica de l'aplicació
 * @author professor
 */
//TODO posar les anotacions necessaries a la classe
@Entity
@Table(name = "laboratori")
// inserción de las namedquerys
@NamedQueries({
        @NamedQuery(name = "Laboratori.all", query = "SELECT x FROM Laboratori x"),
        @NamedQuery(name = "Laboratori.delete", query = "DELETE FROM Laboratori WHERE x.id =:codi"),
        @NamedQuery(name = "Laboratori.getter", query = "SELECT x FROM Laboratori x WHERE x.id =:codi")       
})
public class Laboratori implements Serializable {
    
  private int id;
  private String nom;
  private String ciutat;
  private List<Producte> productes=new ArrayList<>();
  
  /**
    * Constructor per defecte: construeix un nou objecte amb els valors per defecte de Java
    * (es necessari perque pugui funcionar JPA correctament)
    */
   public Laboratori() {
       
   }   
   
   /**
    * Constructor parametritzat: construeix un nou objecte amb els paramtres especificats
    * @param codi identificador
    * @param nom nom
    * @param urbs ciutat 
    */
   public Laboratori (int codi, String nom, String urbs) {
        this.id = codi;
        this.nom = nom;
        this.ciutat = urbs;
   }
   
   /**
    * Obte el id que identifica 
    * @return id que identifica 
    */
   @Id
   public int getId() {
        return id;
   }
   
   /**
    * Actualitza el id que identifica 
    * @param id nou valor per al id que identifica 
    */
   public void setId(int id) {
        this.id = id;
   }   

   /**
    * Obte el nom 
    * @return nom 
    */
   @Column(name = "nom", length = 50)
   public String getNom() {
        return nom;
   }

   /**
    * Actualitza el nom de l
    * @param nom nou valor per al nom de 
    */
   public void setNom(String nom) {
        this.nom = nom;
   }
   
   /**
    * Obte la ciutat on es troba 
    * @return nom de 
    */
   @Column(name = "ciutat", length = 25)
   public String getCiutat() {
        return ciutat;
   }

   /**
    * Actualitza la ciutat on es troba l
    * @param urbs nou valor per al nom de 
    */
   public void setCiutat(String urbs) {
        this.ciutat = urbs;
   }
   
   /**
    * Llistat dels productes 
    * @return Llistat dels productes 
    */   
    
    @OneToMany(mappedBy = "laboratori")
    public List<Producte> getProductes() {
        return productes;
    }
    
    /**
    * Modifica el llistat dels productes 
     *@param llistat nou llistat dels productes     */   
    public void setProductes(List<Producte> llistat) {
        this.productes=llistat;
    }  
    
}
