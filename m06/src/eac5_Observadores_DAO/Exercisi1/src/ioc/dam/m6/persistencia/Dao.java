/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m6.persistencia;

import ioc.dam.m6.persistencia.excepcions.UtilitatPersistenciaException;
import java.util.List;

/**
 * Representa la funcionalitat minima 
 * d'acces a les dades persistents de qualsevol entitat del model de dades 
 * d'una aplicacio. Es tracta d'una classe parametrica on el primer parametre
 * representa el tipus d'entitat de la que es fara el manteniment i 
 * sincronitzacio amb la font de persistencia.
 * El segon parametre representa el tipus de dada que l'entitat utilitza com a
 * identificador (clau primaria) de les seves instancies. 
 * @param <T> tipus d'entitat de la qual es fara el manteniment
 * @param <I> tipus de dada de la clau de l'entitat T
 * @author josep
 */
public interface Dao<T, I> {    
    /**
     * Crea una instancia nova de l'entitat referenciada per 
     * aquest objecte DAO, la qual no es emmagatzemada de 
     * forma persistent sino que es crea amb l'objectiu de 
     * realitzar alguna gestio temporal.
     * @return instancia de l'entitat referenciada per 
     * aquest objecte DAO.
     */
    public T novaInstanciaTemporal();
    
    /**
     * Crea una instancia nova de l'entitat referenciada per 
     * aquest objecte DAO, la qual s'emmagatzemara de forma 
     * persistent abans de ser retornada. Aquest metode 
     * nomes es adequat en cas que l'entitat tingui un
     * identificador generat de forma automatica, doncs en 
     * cas contrari, la insercio generara un error.
     * @return instancia de l'entitat referenciada per aquest 
     * objecte DAO.
     * @throws UtilitatPersistenciaException Es llanca per 
     * qualsevol error provinent del sistema de persistencia.
     */
    public T novaInstancia() 
				throws UtilitatPersistenciaException;
    
    /**
     * Crea una instancia nova de l'entitat referenciada per 
     * aquest objecte DAO i s'inicialitza amb l'identificador
     * passat per parametre. La instancia no s'emmagatzemara,
     * per tant s'hauria de destinar a realitzar alguna 
     * gestio temporal.
     * @param id de la instancia (clau primaria).
     * @return instancia de l'entitat referenciada per aquest 
     * objecte DAO.
     */
    public T novaInstanciaTemporal(I id);
    
    /**
     * Crea una instancia nova de l'entitat referenciada per 
     * aquest objecte DAO i s'inicialitza amb l'identificador
     * passat per parametre. La instancia s'emmagatzemara de 
     * forma persistent abans de ser retornada. En cas que ja 
     * existis una entitat persistent amb el mateix 
     * identificador es llancaria una excepcio indicant que 
     * l'entitat que s'ha intentat crear no es pot inserir 
     * perque el seu identificador ja esta utilitzat.
     * @param id de la instancia (clau primaria).
     * @return instancia de l'entitat referenciada per aquest 
     * objecte DAO.
     * @throws UtilitatPersistenciaException Es llanca per 
     * qualsevol error provinent del sistema de persistencia.
     */
    public T novaInstancia(final I id) 
				throws UtilitatPersistenciaException;
    
    /**
     * Refresca els atributs de l'entitat passada per  
     * parametre amb les dades emmagatzemades.
     * @param entitat a refrescar
     * @return L'entitat refrescada.
     * @throws UtilitatPersistenciaException Es llanca per 
     * qualsevol error provinent del sistema de persistencia.
     */
    public T refrescar(final T entitat)  
				throws UtilitatPersistenciaException;
    
    /**
     * Emmagatzema les dades contingudes als atributs de 
     * l'entitat passada per parametre. Si l'entitat no 
     * fos persistent en el moment de la invocacio, es 
     * fara una insercio. En canvi si l'entitat ja fos 
     * persistent es realitzara una actualitzacio de les 
     * dades emmagatzemades.
     * @param entitat a emmagatzemar
     * @throws UtilitatPersistenciaException Es llanca per 
     * qualsevol error provinent del sistema de persistencia.
     */
    public void emmagatzemarDades(final T entitat)  
				throws UtilitatPersistenciaException;
    
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
    public boolean esPersistent(final T entitat) 
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
    public void modificar(final T entitat) 
				throws UtilitatPersistenciaException;
    
    /**
     * S'emmagatzema per primer cop l'entitat passada per 
     * parametre. Es a dir que per poder-la inserir amb exit, 
     * cal que no existeixi a la base de dades cap altra 
     * entitat emmagatzemada amb el mateix identificador. 
     * Quan aixo passi es produira una error i es llancara 
     * una excepcio de tipus UtilitatPersistenciaException.
     * @param entitat a inserir
     * @throws UtilitatPersistenciaException Es llanca per 
     * qualsevol error provinent del sistema de persistencia.
     */
    public void inserir(final T entitat) 
				throws UtilitatPersistenciaException;
    
    /**
     * Fa que la entitat identificada amb la clau que es 
     * passa per parametre deixi de ser persistent. Si no 
     * existeix cap entitat persistent identificada amb la 
     * clau, es produira es llancara una excepcio de tipus 
     * UtilitatPersistenciaException.
     * @param clau que identifica l'entitat candidata a 
     * deixar de ser persistent.
     * @throws UtilitatPersistenciaException Es llanca per 
     * qualsevol error provinent del sistema de persistencia.
     */
    public void eliminarPerClau(final I clau) 
				throws UtilitatPersistenciaException;   
    
    /**
     * Fa que l'entitat passada per parametre deixi de ser 
     * persistent. Si no existeix cap entitat persistent 
     * identificada amb el mateix valor que la instancia 
     * passada per parametre, es produira un error i es 
     * llancara una excepcio de tipus 
     * UtilitatPersistenciaException.
     * @param entitat es l'entitat candidata a deixar de ser 
     * persistent.
     * @throws UtilitatPersistenciaException Es llanca per 
     * qualsevol error provinent del sistema de persistencia.
     */
    public void eliminar(final T entitat) 
				throws UtilitatPersistenciaException;   
    
    /**
     * Obte una instancia persistent (emmagatzemada 
     * previament) identificada amb la clau que es passa per 
     * parametre. 
     * @param clau que identifica la entitat que es desitja 
     * recuperar.
     * @return Instancia de l'entitat recuperada a partir de 
     * les dades emmagatzemades.
     * @throws UtilitatPersistenciaException Es llanca per 
     * qualsevol error provinent del sistema de persistencia.
     */
    public T obtenirInstancia(final I clau) 
				throws UtilitatPersistenciaException;
    
    /**
     * Obte una llista de totes les entitats emmagatzemades 
     * del tipus referenciat per aquest DAO.
     * @return llista d'entitats persistents del tipus 
     * referenciat per aquest DAO.
     * @throws UtilitatPersistenciaException Es llanca per 
     * qualsevol error provinent del sistema de persistencia.
     */
    public List<T> obtenirTot() 
				throws UtilitatPersistenciaException;   
}
