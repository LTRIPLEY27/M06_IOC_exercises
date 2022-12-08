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
public abstract class AbstractJdbcDao<T, I> implements Dao<T, I>{
    protected Connection con;

    /**
     * Constructor que rep una connexio per parametre.
     * @param connection es la connexio amb la que aquest objecte treballara
     */
    public AbstractJdbcDao(Connection connection) {
        this.con = connection;
    }
    
    @Override
    public T novaInstancia() throws UtilitatPersistenciaException{
        T ret = novaInstanciaTemporal();
        inserir(ret);
        return ret;
    }

    @Override
    public T novaInstancia(final I id) throws UtilitatPersistenciaException{
        T ret = novaInstanciaTemporal(id);
        inserir(ret);
        return ret;
    }

    @Override
    public void emmagatzemarDades(final T entitat) throws UtilitatPersistenciaException {
        if(esPersistent(entitat)){
            modificar(entitat);
        }else{
            inserir(entitat);
        }
    }
        
    /**
     * Obte la connexio que es fa servir per executar les sentencies SQL
     * contra el SGDB on s'emmagatzemaran les entitats controlades per aquest 
     * DAO.
     * @return 
     */
    public Connection getConnexio(){
        return con;
    }
}
