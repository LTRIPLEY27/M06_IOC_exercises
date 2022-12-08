/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m6.persistencia;

import ioc.dam.m6.persistencia.excepcions.UtilitatPersistenciaException;

/**
 *
 * @author josep
 */
public interface GestorPersistencia {
    /**
     * Assigna els valors de configuracio de la connexio a partir d'un sistema
     * d'inicialitzacio concretat a cada instancia d'aquesta interficie. 
     * @throws UtilitatPersistenciaException 
     */
    void iniciar() throws UtilitatPersistenciaException;

    /**
     * Obte una connexio a partir de les dades configurades en invocar el 
     * metode <code>iniciar</code>. Un cop obtinguda la connexio aquesta 
     * romandra oberta i es podra reciclar fins que no s'invoqui el metode 
     * <code>tancar</code>. Abans d'invocar aquest metode, cal haver invocat 
     * el metode <code>iniciar</code>. En cas que s'invoqui obrir sense cap 
     * invocacio previa del metode iniciar produira un error que es 
     * materialitzara en el llancament de <code>UtilitatPersistenciaException</code>.
     * El metode iniciar nomes s'ha d'invocar una unica vegada. Un cop invocat, 
     * si les dades de configuracio son correctes i el SGBD es accessible, 
     * no hauria de produir-se cap error.
     * @throws UtilitatPersistenciaException 
     */
    void obrir() throws UtilitatPersistenciaException;

    /**
     * Tanca la connexio oberta des de la invocacio del metode <i>obrir</i>. 
     * La base de dades ha d'estar aixecada i ha de mantenir oberta la connexio.
     * En cas d'error, aquest sera enregistrat en un fitxer, pero no es 
     * llancara cap excepcio per evitar una excessiva imbrincacio de sentencies 
     * try-catch.
     */
    void tancar();    
}
