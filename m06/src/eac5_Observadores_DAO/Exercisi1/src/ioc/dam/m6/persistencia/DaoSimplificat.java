/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m6.persistencia;

import ioc.dam.m6.persistencia.excepcions.UtilitatPersistenciaException;
import java.util.List;

/**
 *
 * @author josep
 */

/**
 * Representa la funcionalitat minima 
 * d'acces a les dades persistents de qualsevol entitat del model de dades 
 * d'una aplicacio. Es tracta d'una classe parametrica, en la que el parametre
 * representa el tipus d'entitat de la que es fara el manteniment i 
 * sincronitzacio amb la font de persistencia.
 * @param <T> tipus de l'entitat de la qual es fara el manteniment
 * @author josep
 * 
 */
public interface DaoSimplificat<T> {

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
    public void emmagatzemar(final T entitat)  
				throws UtilitatPersistenciaException;
    
    /**
     * Fa que l'entitat passada per parametre deixi de ser 
     * persistent.
     * @param entitat es l'entitat candidata a deixar de ser 
     * persistent.
     * @throws UtilitatPersistenciaException Es llanca per 
     * qualsevol error provinent del sistema de persistencia.
     */
    public void eliminar(final T entitat) 
				throws UtilitatPersistenciaException;
    
    /**
     * Obte una llista de totes les entitats emmagatzemades 
     * del tipus referenciat per aquest DAO.
     * @return llista d'entitats persistents del tipus 
     * referenciat per aquest DAO.
     * @throws UtilitatPersistenciaException Es llanca per 
     * qualsevol error provinent del sistema de persist?ncia.
     */
    public List<T> obtenirTot() 
				throws UtilitatPersistenciaException;   
}
