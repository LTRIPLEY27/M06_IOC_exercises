/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m6.persistencia;

import ioc.dam.m6.persistencia.excepcions.UtilitatPersistenciaException;
import java.sql.Connection;

/**
 *
 * @author josep
 */
public abstract class AbstractGestorJdbc implements GestorPersistencia {
    protected String driver;
    protected String user;
    protected String password;
    protected String url;
    protected Connection con;

    @Override
    public void obrir() throws UtilitatPersistenciaException{
        con = UtilitatJdbc.obrir(driver, url, user, password);
    }

    @Override
    public void tancar(){
        UtilitatJdbc.tancarConnexio(getConnexio());
    }

    /**
     * Obte el driver JDBC
     * @return el driver
     */
    public String getDriver() {
        return driver;
    }

    /**
     * * Obte l'usuari per realitzar la connexio al SGDB
     * @return l'usuari
     */
    public String getUser() {
        return user;
    }

    /**
     * Obte la contrasenya per realitzar la connexio
     * @return la contrasenya
     */
    public String getPassword() {
        return password;
    }

    /**
     * Obte la URL del SGBD per realitzar la connexio
     * @return la url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Obte la connexio JDBC
     * @return la connexio
     */
    public Connection getConnexio() {
        return con;
    }
}
