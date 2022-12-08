/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package exercici2.src.observadors;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

/**
 * Observador que es informat de les actualitzacions d'una propietat de tipus
 * cadena i llenca una excepcio si aquesta no es un palindrom.
 * A mes, comptabilitza els intents de canvi erronis i, tambe, els exitosos
 * 
 * @author professor
 */
public class VerificaPalindrom implements VetoableChangeListener {

    private int comptaErrors, comptaEncerts;

    /**
     * Obte el nombre d'errors comptabilitzats
     * @return el nombre d'errors comptabilitzats
     */
    public int getComptaErrors() {
        return comptaErrors;
    }

    /**
     * Obte el nombre d'errors comptabilitzats
     * @return el nombre d'errors comptabilitzats
     */

    public int getComptaEncerts() {
        return comptaEncerts;
    }

    /**
     * Posa a zero els comptadors d'errors i encerts
     */
    public void reinicia() {
        comptaEncerts = comptaErrors = 0;
    }
    
    @Override
    public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
        //TODO escriure el cos del metode
        
        String word = (String) evt.getNewValue();
        String empty = "";
        
        // TRANSPONEMOS LA STRING PARA EVALUAR
        for(int i = word.length() - 1; i >= 0; i--){
            empty = empty + word.charAt(i);
        }
        
         // VERIFICAMOS SI LA PALABRA SE LEE DE IGUAL MANERA HACIA ATRAS Y ADELANTE
        boolean isPalindrome = word.equalsIgnoreCase(empty) ? true : false;

        if(isPalindrome){
            comptaEncerts++;
        } else {
            comptaErrors++;
            throw new PropertyVetoException("No es un palindrom.", evt);
        }
            
    }    


}
