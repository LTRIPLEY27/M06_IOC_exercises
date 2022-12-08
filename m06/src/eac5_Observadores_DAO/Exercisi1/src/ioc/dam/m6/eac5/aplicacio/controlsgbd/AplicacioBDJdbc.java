/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m6.eac5.aplicacio.controlsgbd;

import ioc.dam.m6.persistencia.AbstractGestorJdbc;
import ioc.dam.m6.persistencia.UtilitatJdbc;
import ioc.dam.m6.eac5.aplicacio.cfg.Constants;
import ioc.dam.m6.persistencia.excepcions.UtilitatPersistenciaException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author josep
 */
public class AplicacioBDJdbc extends AbstractGestorJdbc{

    @Override
    public void iniciar() throws UtilitatPersistenciaException {
        try {
            String nomFitxer = Constants.PROP_FILE_FOLDER 
                                    + AplicacioBDJdbc.class.getSimpleName()
                                    + Constants.PROP_FILE_EXTENSION;
            
            Properties properties = new Properties();
            
            
            properties.load(new FileReader(nomFitxer));



            driver = properties.getProperty(Constants.DRIVER_PROP);
            user = properties.getProperty(Constants.USER_PROP);
            password= properties.getProperty(Constants.PASSWORD_PROP);
            url = properties.getProperty(Constants.URL_PROP);
        } catch (IOException ex) {
            UtilitatJdbc.onError(ex);
        }
    }
}
