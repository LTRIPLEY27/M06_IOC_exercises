/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m6.persistencia;

/**
 * Representa una sentencia SQL d'acces a dades via JDBC. Es una interficie 
 * que complementa la classe UtilitatJdbcPlus. Les classes que implementin 
 * aquesta interficie emmagatzemaran una sentencia SQL. La classe 
 * UtilitatJdbcPlus es capac de treballar amb instancies JdbcDao per executar 
 * les sentencies SQL que continguin.
 * @author josep
 */
public interface JdbcDao {
    /**
     * Obte la sentencia SQL emmagatzemada en aquest objecte.
     * @return una cadena amb la sentencia SQL
     */
    String getStatement();
}
