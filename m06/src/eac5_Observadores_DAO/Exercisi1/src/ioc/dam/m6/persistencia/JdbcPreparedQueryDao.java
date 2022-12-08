/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m6.persistencia;

/**
 * Representa una sentencia SQL de tipus consulta via JDBC. Es una interficie
 * que complementa la classe UtilitatJdbcPlus. El format de la sentencia SQL 
 * seguira la sintaxi JDBC en referencia a la seva parametritzacio. Les classes 
 * que implementin aquesta interficie emmagatzemaran una sentencia SQL de tipus 
 * consulta. La classe UtilitatJdbcPlus es capac de treballar amb instancies 
 * JdbcPreparedQueryDao per crear les intancies PreparedStatement 
 * corresponents, incoporar-hi les dades dels parametres, executar-les,
 * obtenir els resultats i instanciar entitats amb les dades recollides 
 * @author josep
 */
public interface JdbcPreparedQueryDao extends JdbcPreparedDao, JdbcQueryDao {

}
