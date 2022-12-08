/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m6.uf4.eac5.aplicacio.dao;

import ioc.dam.m6.persistencia.EstructuraBD;
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
public class AplicacioBDEstructura extends EstructuraBD{
    final String FITXER_ELIMINACIO_ESTRUCTURA = "fitxer_eliminacio_estructura";
    final String FITXER_CREACIO_ESTRUCTURA = "fitxer_creacio_estructura";
    final String FITXER_MODIFICACIO_ESTRUCTURA= "fitxer_modificacio_estructura";
    final String FITXER_IMPORTACIO_DADES= "fitxer_importacio_dades";
    final String FITXER_LOG_CFG = "fitxer_logger_cfg";
    final String PROP_FILE_FOLDER="cfg"+File.separatorChar;
    final String PROP_FILE_EXTENSION=".properties";
    final String DRIVER_PROP="driver";
    final String USER_PROP="user";
    final String PASSWORD_PROP="password";
    final String URL_PROP="url";

    @Override
    public void iniciar() throws UtilitatPersistenciaException{
        try {
            //nom del fitxer de propietats per configurar aquesta classe
            String nomFitxer = PROP_FILE_FOLDER 
                                    + AplicacioBDEstructura.class.getSimpleName()
                                    + PROP_FILE_EXTENSION;

            //LLegeix i carrega les propietats
            Properties properties = new Properties();
            properties.load(new FileReader(nomFitxer));

            //assigna els scripts
            sqlDeCreacio = properties.getProperty(
                                        FITXER_CREACIO_ESTRUCTURA);
            sqlDEliminacio = properties.getProperty(
                                        FITXER_ELIMINACIO_ESTRUCTURA);  
            sqlDeModificacio = properties.getProperty(
                                        FITXER_MODIFICACIO_ESTRUCTURA);  
            sqlDImportacio = properties.getProperty(
                                        FITXER_IMPORTACIO_DADES);  
            String driver = properties.getProperty(DRIVER_PROP);
            String user = properties.getProperty(USER_PROP);
            String password = properties.getProperty(PASSWORD_PROP);
            String url = properties.getProperty(URL_PROP);
            connection = UtilitatJdbc.obrir(driver, url, user, password);

        } catch (IOException ex) {
            UtilitatJdbc.onError(ex);
        }
    }
    
}
