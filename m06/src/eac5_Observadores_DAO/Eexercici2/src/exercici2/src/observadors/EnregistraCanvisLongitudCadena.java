/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package exercici2.src.observadors;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Observador que es informat de les actualitzacions d'una propietat de tipus
 * cadena i enregistra la longitud de la cadena mes llarga assignada a aquesta
 * propietat
 * @author professor
 */
public class EnregistraCanvisLongitudCadena implements PropertyChangeListener{
    
    private int maxLongitud;

    /**
     * Obte el valor de la propietat maxLongitud. Aquesta propietat conte la 
     * major longitud dels palindroms assignats a l'objecte observat
     * @return el valor de la propietat maxLongitud
     */
    public int getMaxLongitud() {
        return maxLongitud;
    }

    /**
     * Posa a zero la propietat maxLongitud
     */
    public void reinicia() {
        maxLongitud = 0;
    }
    
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        
        String longitude = (String) evt.getNewValue();
        
        
        maxLongitud = longitude.length(); 
  
    }

}
