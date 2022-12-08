/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m6.persistencia.excepcions;

import java.sql.SQLException;

/**
 *
 * @author josep
 */
public class UtilitatJdbcSQLException extends UtilitatPersistenciaException {

    /**
     * Creates a new instance of <code>UtilitatJdbcSQLException</code> without detail message.
     */
    public UtilitatJdbcSQLException() {
    }

    /**
     * Constructs an instance of <code>UtilitatJdbcSQLException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public UtilitatJdbcSQLException(String msg) {
        super(msg);
    }

    public UtilitatJdbcSQLException(SQLException exc) {
        super(exc.getMessage(), exc);
    }

    public UtilitatJdbcSQLException(String string, SQLException exc) {
        super(string, exc);
    }
    
    public SQLException getSQLCause(){
        return (SQLException) getCause();
    }    
}
