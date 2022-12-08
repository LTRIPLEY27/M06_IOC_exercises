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
public abstract class AbstractJdbcDaoSimplificat<T> 
                                                implements DaoSimplificat<T>{
    protected Connection con;

    /**
     * Constructor que rep una connexio per parametre.
     * @param connection es la connexio amb la que aquest objecte treballara
     */
    public AbstractJdbcDaoSimplificat(Connection connection) {
        this.con = connection;
    }
    
    @Override
    public void emmagatzemar(T entitat) throws UtilitatPersistenciaException {
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

    /**
     * Indica si l'objecte passat per parametre es 
     * persistent.  La comprovacio de l'existencia es basa 
     * exclusivament amb el valor dels atributs 
     * identificadors de la instancia.
     * @param entitat a comprovar.
     * @return cert si l'entitat a comprovar es persistent. 
     * Fals en cas contrari.
     * @throws UtilitatPersistenciaException Es llanca per 
     * qualsevol error provinent del sistema de persistencia.
     */
    
    
    
    abstract protected boolean esPersistent(T entitat) 
				throws UtilitatPersistenciaException;
    
    /**
     * Actualitza les dades emmagatzemades amb les 
     * contingudes a l'estat de la instancia passada per 
     * parametre. L'entitat ha d'haver estat emmagatzemada 
     * amb anterioritat. Contrariament es produira un error.
     * @param entitat des d'on fer la actualitzacio.
     * @throws UtilitatPersistenciaException Es llanca per 
     * qualsevol error provinent del sistema de persistencia.
     */
    abstract protected void modificar(T entitat) 
				throws UtilitatPersistenciaException;
    
    /**
     * S'emmagatzema per primer cop l'entitat passada per 
     * parametre. Es a dir que per poder-la inserir amb exit, 
     * cal que no existeixi a la base de dades cap altra 
     * entitat emmagatzemada amb el mateix identificador. 
     * Quan aixo passi es produira un error i es llancara 
     * una excepcio de tipus UtilitatPersistenciaException.
     * @param entitat a inserir
     * @throws UtilitatPersistenciaException Es llanca per 
     * qualsevol error provinent del sistema de persistencia.
     */
    abstract protected void inserir(T entitat) 
				throws UtilitatPersistenciaException;
}
