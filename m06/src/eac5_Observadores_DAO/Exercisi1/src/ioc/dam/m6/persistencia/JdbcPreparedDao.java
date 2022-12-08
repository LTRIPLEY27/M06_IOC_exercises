/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m6.persistencia;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Representa una sentencia SQL d'acces a dades via JDBC. Es una interficie 
 * que complementa la classe UtilitatJdbcPlus. El format de la sentencia SQL 
 * seguira la sintaxi JDBC en referencia a la seva parametritzacio. Les classes 
 * que implementin aquesta interficie emmagatzemaran la sentencia SQL. 
 * La classe UtilitatJdbcPlus es capac de treballar amb instancies 
 * JdbcPreparedDao per crear les intancies PreparedStatement, incoportarar-hi 
 * les dades dels parametres i executar-les. 
 * @author josep
 */
public interface JdbcPreparedDao extends JdbcDao{
    
    
    /**
     * Aquest metode te per objectiu fer l'assignacio de valors als parametres 
     * de l'objecte PreparedStatement. El parametre s'ha creat amb la cadena SQL
     * que encapsula aquest objecte JdbcPreparedDao. 
     * @param pstm. Aquest parametre es un objecte de tipus PreparedStatement 
     * i s'ha creat amb la cadena SQL que encapsula aquest objecte JdbcPreparedDao. 
     * @throws SQLException 
     */
    void setParameter(PreparedStatement pstm) throws SQLException;
}
