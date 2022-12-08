/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m6.uf4.eac5.aplicacio.dao;

import ioc.dam.m6.persistencia.AbstractGestorJdbc;
import ioc.dam.m6.persistencia.UtilitatJdbc;
import ioc.dam.m6.persistencia.excepcions.UtilitatPersistenciaException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author josep
 */
public class AplicacioBDJdbc extends AbstractGestorJdbc{
    final String PROP_FILE_FOLDER="cfg"+File.separatorChar;
    final String PROP_FILE_EXTENSION=".properties";
    final String DRIVER_PROP="driver";
    final String USER_PROP="user";
    final String PASSWORD_PROP="password";
    final String URL_PROP="url";
    
    @Override
    public void iniciar() throws UtilitatPersistenciaException {
        try {
            String nomFitxer = PROP_FILE_FOLDER 
                                    + AplicacioBDJdbc.class.getSimpleName()
                                    + PROP_FILE_EXTENSION;
            
            Properties properties = new Properties();
            properties.load(new FileReader(nomFitxer));

            driver = properties.getProperty(DRIVER_PROP);
            user = properties.getProperty(USER_PROP);
            password= properties.getProperty(PASSWORD_PROP);
            url = properties.getProperty(URL_PROP);
        } catch (IOException ex) {
            UtilitatJdbc.onError(ex);
        }
    }
}
