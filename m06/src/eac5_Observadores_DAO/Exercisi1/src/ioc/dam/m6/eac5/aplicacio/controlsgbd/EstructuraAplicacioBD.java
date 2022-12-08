/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m6.eac5.aplicacio.controlsgbd;

import ioc.dam.m6.persistencia.UtilitatJdbc;
import ioc.dam.m6.eac5.aplicacio.cfg.Constants;
import ioc.dam.m6.persistencia.excepcions.UtilitatPersistenciaException;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 *
 * @author josep
 */
public class EstructuraAplicacioBD extends AplicacioBDJdbc{
    String sqlDeCreacio;
    String sqlDEliminacio;
    String sqlDeModificacio;
    String sqlDImportacio;
   
    
    public static void main(String[] args) {
        EstructuraAplicacioBD estructuraAplicacioBD = new EstructuraAplicacioBD();
        try {            
            estructuraAplicacioBD.iniciar();
            estructuraAplicacioBD.obrir();

            if(args.length==0){
                estructuraAplicacioBD.executaOrdre("crea");
            }else{
                for(String ordre: args){
                    estructuraAplicacioBD.executaOrdre(ordre);
                }
            }            
        } catch (UtilitatPersistenciaException ex) {
            Logger.getLogger(EstructuraAplicacioBD.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            estructuraAplicacioBD.tancar();           
        }

    }
    
    @Override
    public void iniciar() throws UtilitatPersistenciaException{
        super.iniciar();
        try {
            //nom del fitxer de propietats per configurar aquesta classe
            String nomFitxer = Constants.PROP_FILE_FOLDER 
                                    + EstructuraAplicacioBD.class.getSimpleName()
                                    + Constants.PROP_FILE_EXTENSION;

            //LLegeix i carrega les propietats
            Properties properties = new Properties();
            properties.load(new FileReader(nomFitxer));

            //configura el sistema d'enregistrament (Logger)
            LogManager.getLogManager().readConfiguration(
                            new FileInputStream(properties.getProperty(
                                                    Constants.FITXER_LOG_CFG)));

            //assigna els scripts
            sqlDeCreacio = properties.getProperty(
                                        Constants.FITXER_CREACIO_ESTRUCTURA);
            sqlDEliminacio = properties.getProperty(
                                        Constants.FITXER_ELIMINACIO_ESTRUCTURA);  
            sqlDeModificacio = properties.getProperty(
                                        Constants.FITXER_MODIFICACIO_ESTRUCTURA);  
            sqlDImportacio = properties.getProperty(
                                        Constants.FITXER_IMPORTACIO_DADES);  
        } catch (IOException ex) {
            UtilitatJdbc.onError(ex);
        }
    }

    public void executaOrdre(String ordre) throws UtilitatPersistenciaException{
        if(ordre.toLowerCase().startsWith("crea") 
                || ordre.toLowerCase().startsWith("insta")){
            crearEstructura();
        }else if(ordre.toLowerCase().startsWith("elim")){
            eliminarEstructura();
        }else if(ordre.toLowerCase().startsWith("modif")){
            crearEstructura();
        }else if(ordre.toLowerCase().startsWith("import")){
            importarDades();                
        }else{
            System.out.println("Argument " + ordre + " desconnegut");
        }
    }
    
    public void crearEstructura() throws UtilitatPersistenciaException{
        llegirFitxerSQLIExecutarLo(sqlDeCreacio);
    }

    public void eliminarEstructura() throws UtilitatPersistenciaException{
        llegirFitxerSQLIExecutarLo(sqlDEliminacio);
    }

    public void importarDades() throws UtilitatPersistenciaException{
        llegirFitxerSQLIExecutarLo(sqlDImportacio);
    }

    public void modificarEstructura() throws UtilitatPersistenciaException{
        llegirFitxerSQLIExecutarLo(sqlDeModificacio);
    }

    private void llegirFitxerSQLIExecutarLo( String script) 
                                                    throws UtilitatPersistenciaException {
        FileReader reader = null;
        StringBuilder stringBuilder = new StringBuilder();
        int charsLlegits=0;
        char[] buffer = new char[512];
        try{
            reader = new FileReader(script);
            while(charsLlegits!=-1){    
                stringBuilder.append(buffer, 0, charsLlegits);
                charsLlegits=reader.read(buffer);
            }

            UtilitatJdbc.executar(getConnexio(), 
                                    stringBuilder.toString());
        } catch (IOException ex) {
            UtilitatJdbc.onError(ex);
        }
    }        
}
