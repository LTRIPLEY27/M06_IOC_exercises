/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m6.uf4.eac5.aplicacio;

import ioc.dam.m6.persistencia.excepcions.UtilitatPersistenciaException;
import java.util.List;

/**
 *
 * @author josep + professor
 */
public interface AplicacioBD {
    /**
     * Obte una connexio a partir de les dades configurades en invocar el 
     * metode <code>iniciar</code>. Un cop obtinguda la connexio, aquesta 
     * romandra oberta i es podra reciclar fins que no s'invoqui el metode 
     * <code>tancar</code>. Abans d'invocar aquest metode, cal haver invocat 
     * el metode <code>iniciar</code>. En cas que s'invoqui obrir sense cap 
     * invocacio previa del metode <code>iniciar</code> produira un error que es 
     * materialitzara en el llancament de <code>UtilitatPersistenciaException</code>.
     * El metode iniciar nomes s'ha d'invocar una unica vegada. Un cop invocat, 
     * si les dades de configuracio son correctes i el SGBD es accessible, 
     * no hauria de produir-se cap error.
     * @throws UtilitatPersistenciaException 
     */    
    void obrir() throws UtilitatPersistenciaException;

    /**
     * Tanca la connexio oberta des de la invocacio del metode <i>obrir</i>. 
     * La base de dades ha d'estar aixecada i ha de mantenir oberta la connexio.
     * En cas d'error, aquest sera enregistrat en un fitxer, pero no es 
     * llancara cap excepcio per evitar una excessiva imbrincacio de sentencies 
     * try-catch.
     */    
    void tancar();
    

    /**
     * Prepara la base de dades per treballar amb ell; 
     * cal invocar-lo nomes un cop i abans de qualsevol 
     * de les altres operacions.
     * @throws UtilitatPersistenciaException si es produeix un error
     */
    void iniciar() throws UtilitatPersistenciaException;
    
    /**
     * Emmagatzema un districte a la base de dades. Si ja hi existeix
     * un altre amb la mateixa clau, l'actualitza amb les dades del parametre.
     * No actualitza els articles que distribueix.
     * @param districte
     * @throws UtilitatPersistenciaException si es produeix un error 
     */
    void emmagatzemar(final Districte districte) throws UtilitatPersistenciaException ;

    /**
     * Emmagatzema un barri a la base de dades. Si ja hi existeix
     * un altre amb la mateixa clau, l'actualitza amb les dades del parametre.
     * Actualitza el districte que te assignat, pero no les dades d'aquest.
     * @param barri
     * @throws UtilitatPersistenciaException si es produeix un error 
     */
    void emmagatzemar(final Barri barri) throws UtilitatPersistenciaException ;

    /**
     * Elimina un districte de la base de dades. El localitza per la
     * clau. Si no hi existeix cap amb la mateixa clau, no fa res. Si té barris,
     * es produeix un error i no es realitza l'eliminacio
     * @param districte districte que volem eliminar
     * @throws UtilitatPersistenciaException si es produeix un error 
     */
    void eliminar(final Districte districte) throws UtilitatPersistenciaException ;
    
    /**
     * Elimina un barri de la base de dades. El localitza per la clau.
     * Si no hi existeix cap amb la mateixa clau, no fa res.
     * @param barri
     * @throws UtilitatPersistenciaException  si es produeix un error
     */
    void eliminar(final Barri barri) throws UtilitatPersistenciaException ;

    /**
     * Actualitza les dades a memoria d'un districte amb les que te a la base de
     * dades. El localitza per la clau. Si la base de dades no conte cap 
     * districte amb la mateixa clau, retorna null i no modifica el parametre. 
     * Si el troba, pero, actualitza tambe la llista dels seus barris
     * @param districte districte que volem actualitzar
     * @return referencia al districte actualitzat; null si no existeix a la base de dades
     * @throws UtilitatPersistenciaException si es produeix un error 
     */
    Districte refrescar(final Districte districte) throws UtilitatPersistenciaException ;
    
    /**
     * Actualitza les dades a memoria d'un barri amb les que hi ha a la base de
     * dades. El localitza per la clau. Si la base de dades no conte cap barri 
     * amb la mateixa clau, retorna null i no modifica el parametre. Si el troba,
     * però, actualitza tambe les dades del districte on es, pero no les dels 
     * seus barris.
     * @param barri barri que volem actualitzar
     * @return referencia a l'barri actualitzat; null si no existeix a la
 base de dades.
     * @throws UtilitatPersistenciaException  si es produeix un error
     */
    Barri refrescar(final Barri barri) throws UtilitatPersistenciaException ;

    /**
     * Permet obtenir una llista de tots els districtes de la base de dades.
     * Inclou les dades dels barris que distribueixen.
     * @return llista amb tots els establiments de la base de dades
     * @throws UtilitatPersistenciaException si es produeix un error 
     */
    List<Districte> obtenirDistrictes() throws UtilitatPersistenciaException ;
    
    /**
     * Permet obtenir una llista de tots els barris de la base de dades.
     * Inclou les dades del seu districte, pero no els seus barris
     * @return llista amb tots els barris de la base de dades
     * @throws UtilitatPersistenciaException si es produeix un error 
     */
    List<Barri> obtenirBarris() throws UtilitatPersistenciaException ;
    

    
    /**
     * Permet obtenir una llista de tots els barris de la base de dades
     * amb un determinat districte. Inclou les dades del seu districte, pero
     * no els seus barris
     * @param numDistricte districte dels barris que cal recuperar
     * @return llista amb tots els barris amb de la base de dades del districte
     * indicat pel parametre.
     * @throws UtilitatPersistenciaException si es produeix un error 
     */    
    List<Barri> obtenirBarrisPerDistricte(final int numDistricte)
                        throws UtilitatPersistenciaException ;  


    /**
     * Permet obtenir una llista de tots els barris de la base de dades
     * amb una denistat entre dos valors determinats (ambdos inclosos).
     * Inclou les dades del seu districte, pero no els barris d'aquests.
     * @param densInferior valor minim que pot tenir la densitat dels barris del resultat
     * @param densSuperior valor maxim que pot tenir la densitat dels barris del resultat
     * @return llista amb tots els barris de la base de dades que tenen
     * la seva densitat compresa entre els dos parametres (ambdos inclosos)
     * @throws UtilitatPersistenciaException si es produeix un error 
     */ 
    
    public List<Barri> obtenirBarrisPerDensitat(float densInferior, float densSuperior)
                            throws UtilitatPersistenciaException;
    
    
    
    

    /**
     * Permet obtenir una llista de tots els districtes del sistema de persistencia
     * amb, com a minim, el nombre d'habitants indicat pel parametre.
     * Inclou les dades de tots els barris de cada districte inclos al resultat.
     * @param habitants nombre minim d'habitants dels districtes inclosos a la solucio
     * @return llista amb tots els districtes del sistema de persistencia amb, com a minim,
     * el nombre d'habitants indicat pel parametre
     * @throws UtilitatPersistenciaException si es produeix un error 
     */   
    
    public List<Districte> obtenirDistrictesPerHabitants(int habitants)
                                            throws UtilitatPersistenciaException;

}
