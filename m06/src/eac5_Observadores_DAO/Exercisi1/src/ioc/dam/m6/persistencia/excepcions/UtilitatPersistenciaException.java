/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m6.persistencia.excepcions;

/**
 *
 * @author josep
 */
public class UtilitatPersistenciaException extends Exception {

    /**
     * Creates a new instance of <code>UtilitatPersistenciaException</code> without detail message.
     */
    public UtilitatPersistenciaException() {
    }

    /**
     * Constructs an instance of <code>UtilitatPersistenciaException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public UtilitatPersistenciaException(String msg) {
        super(msg);
    }

    public UtilitatPersistenciaException(Throwable thrwbl) {
        super(thrwbl.getMessage(), thrwbl);
    }

    public UtilitatPersistenciaException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }
}
