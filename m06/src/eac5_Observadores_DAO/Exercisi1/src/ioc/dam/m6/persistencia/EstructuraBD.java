/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m6.persistencia;

import ioc.dam.m6.persistencia.excepcions.UtilitatPersistenciaException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;

/**
 *
 * @author josep
 */
public abstract class EstructuraBD{
    protected String sqlDeCreacio=null;
    protected String sqlDEliminacio=null;
    protected String sqlDeModificacio=null;
    protected String sqlDImportacio=null;
    protected Connection connection = null;
    
    public abstract void iniciar() throws UtilitatPersistenciaException;

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
        FileReader reader;
        StringBuilder stringBuilder = new StringBuilder();
        int charsLlegits=0;
        char[] buffer = new char[512];
        try{
            reader = new FileReader(script);
            while(charsLlegits!=-1){    
                stringBuilder.append(buffer, 0, charsLlegits);
                charsLlegits=reader.read(buffer);
            }

            UtilitatJdbc.executar(connection, 
                                    stringBuilder.toString());
        } catch (IOException ex) {
            UtilitatJdbc.onError(ex);
        }
    }     
    
    public void tancar(){
        UtilitatJdbc.tancarConnexio(connection);
    }
}
