/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m6.persistencia.excepcions;

/**
 *
 * @author josep
 */
public class ObjetePersistenException extends UtilitatPersistenciaException {

    /**
     * Creates a new instance of <code>ObjetePersistenException</code> without detail message.
     */
    public ObjetePersistenException() {
    }

    /**
     * Constructs an instance of <code>ObjetePersistenException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public ObjetePersistenException(String msg) {
        super(msg);
    }

    public ObjetePersistenException(Throwable thrwbl) {
        super(thrwbl);
    }

    public ObjetePersistenException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }
}
