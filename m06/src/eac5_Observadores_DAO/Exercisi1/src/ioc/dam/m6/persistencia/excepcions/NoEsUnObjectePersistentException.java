/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m6.persistencia.excepcions;

/**
 *
 * @author josep
 */
public class NoEsUnObjectePersistentException extends ObjetePersistenException {

    /**
     * Creates a new instance of <code>NoEsUnObjectePersistentException</code> without detail message.
     */
    public NoEsUnObjectePersistentException() {
    }

    /**
     * Constructs an instance of <code>NoEsUnObjectePersistentException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public NoEsUnObjectePersistentException(String msg) {
        super(msg);
    }

    public NoEsUnObjectePersistentException(Throwable thrwbl) {
        super(thrwbl);
    }

    public NoEsUnObjectePersistentException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }    
}
