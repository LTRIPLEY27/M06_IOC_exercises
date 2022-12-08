/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package exercici2.src.observats;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;

/**
 *
 * @author professor
 */
public class Palindrom {
    private String palindrom;
    final String propertyName="Palindrom";
    
    
    
    private PropertyChangeSupport pcs=new PropertyChangeSupport(this);
    private VetoableChangeSupport vcs=new VetoableChangeSupport(this);
    
    /**
     * Obte el resultat de la propietat palindrom
     * @return valor de la propietat palindrom
     */
    public String getPalindrom() {
        return palindrom;
    }
    
    /**
     * Actualitza el valor de la propietat  palindrom
     *
     * @param palindrom nou valor de la propietat  palindrom    
     * @throws java.beans.PropertyVetoException  si el parametre no es un palindrom 
     */
    public void setPalindrom(final String palindrom) throws PropertyVetoException{
        //TODO afegir les instruccions que falten
        
        // CAPTA EL ÚLTIMO ESTADO PARA COMPARAR EN CASO DE CAMBIOS
        String auxiliarValue = this.palindrom;

        //TODO afegir les instruccions que falten       
        // LLAMAMOS AL CAMBIO DE PROPIEDAD A ACTUALIZAR (NOTIFICACIÓN AL VECTOR)
        this.vcs.fireVetoableChange(propertyName,  this.palindrom, palindrom);
        
        this.palindrom = palindrom;
        // UBICA EL VIEJO VALOR, NUEVO VALOR PARA COMPARAR
        this.pcs.firePropertyChange(propertyName, auxiliarValue, palindrom);
        
        // NOTIFICACIÓN POSTERIOR AL CAMBIO
        
        this.vcs.fireVetoableChange(propertyName, this.palindrom, palindrom);
        
        this.pcs.firePropertyChange(propertyName, this.palindrom, palindrom);
        
        this.palindrom = palindrom;
    }
    
    /**
     * Afegeix un observador (listener) del tipus PropertyChange als observadors de this
     * @param listener observador que s'afegeix
     */
    public void addPropertyChangeListener (PropertyChangeListener listener){
        //TODO completar el metode
        
        // ADICIÓN DEL LISTENER PARA NOTIFICAR EL CAMBIO
        this.pcs.addPropertyChangeListener(listener);
    }
    
    /**
     * Afegeix un observador (listener) del tipus VetoableChangeListener als observadors de this
     * @param listener observador que s'afegeix
     */
    
    public void addVetoablePropertyListener (VetoableChangeListener listener){
        
        // adición del propertyListener
        this.vcs.addVetoableChangeListener(listener);
    }    
 
    

}
