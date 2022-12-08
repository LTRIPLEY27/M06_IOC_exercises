/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m6.persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Representa una sentencia SQL de tipus consulta via JDBC. Es una interficie 
 * que complementa la classe UtilitatJdbcPlus. Les classes que implementin 
 * aquesta interficie emmagatzemaran una sentencia SQL de tipus consulta. La 
 * classe UtilitatJdbcPlus es capac de treballar amb instancies JdbcQueryDao
 * per executar les sentencies SQL que continguin, obtenir els resultats i 
 * instanciar entitats amb les dades recollides 
 * @author josep
 */
public interface JdbcQueryDao extends JdbcDao{

    
    /**
     * Instancia un objecte amb les dades extretes del ResultSet passat per 
     * parametre, el qual conte els resultats de l'execucio de la sentencia 
     * SQL que aquest objecte JdbcQueryDao representa.
     * @param rs es el ResultSet que conte els resultats de l'execucio de la 
     * sentencia SQL que aquest objecte JdbcQueryDao representa.
     * @return La instancia construida.
     * @throws SQLException 
     */
    Object writeObject(ResultSet rs) throws SQLException;    
}
