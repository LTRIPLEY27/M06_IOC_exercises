package ioc.dam.m6.persistencia;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Representa una sentencia SQL parametrica d'acces a dades via JDBC. Es una 
 * interficie que complementa la classe UtilitatJdbcPlus. El format de la sentencia 
 * SQL seguira la sintaxi JDBC en referencia a la seva parametritzacio. 
 * Les classes que implementin aquesta interficie emmagatzemaran la sentencia 
 * SQL. EL metode setParameter, es capac de treballar amb dades indexades de 
 * manera que la mateixa sentencia s'executi per cada index en una unica 
 * transaccio.
 * La classe UtilitatJdbcPlus es capac de treballar amb instancies 
 * JdbcPreparedDaoList creant la instancia PreparedStatement, incoporant-hi 
 * les dades dels parametres d'acord amb l'index corresponent i executant-les. 
 * @author josep
 */
public interface JdbcPreparedDaoList extends JdbcDao{
    
    /**
     * Aquest metode te per objectiu fer l'assignacio de valors als parametres 
     * des d'una llista de dades indexades. El parametre pstm s'ha creat 
     * a partir de la cadena SQL i el valor id indica l'index de la llista 
     * de dades al que toca executar. Totes les execucions es realitzaran com
     * una unica transaccio.
     * @param id es l'index corresponent a un conjunt de dades amb les que 
     * assignar el parametres d'una de les execucions de la sentencia SQL 
     * retornada per getStatemet().
     * @param pstm es el PreparedStatement corresponent a la cadena SQL.
     * @throws SQLException 
     */
    void setParameter(int id, PreparedStatement pstm) throws SQLException;
    
    /**
     * Obte el nombre de dades per a les quals caldra executar la sentencia SQL
     * @return enter nombre de dades per a les que caldra executar la sentencia 
     * SQL
     */
    int sizeList();
}
