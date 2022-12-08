/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m6.persistencia;


import ioc.dam.m6.eac5.aplicacio.controlsgbd.EstructuraAplicacioBD;
import ioc.dam.m6.persistencia.excepcions.UtilitatPersistenciaException;
import ioc.dam.m6.persistencia.excepcions.UtilitatJdbcRollbackException;
import ioc.dam.m6.persistencia.excepcions.UtilitatJdbcSQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Conjunt d'utilitats per facilitar el treball amb connectors JDBC.
 * @author josep
 */
public class UtilitatJdbc {

    /**
     * Crea i retorna una connexio configurara amb les dades passades per 
     * parametre.
     * @param driver es el nom complert de la classe Driver que usarem per 
     * connectar via JDBC amb el SGBD que fem servir com a magatzem de dades.
     * @param url es l'adreca que permet connectar amb la base de dades
     * @param user es el nom de l'usuari amb els permisos adequats per 
     * gestionar la persistencia de les nostres entitats.
     * @param password es la contrasenya de l'usuari indicat al parametre 
     * anterior.
     * @return Connection amb la connexio instanciada.
     * @throws UtilitatPersistenciaException 
     */
    public static Connection obrir(String driver, String url, String user, 
                                 String password) throws UtilitatPersistenciaException{
        Connection con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            onError(ex);
        } catch (ClassNotFoundException ex) {
            onError(ex);
        }        
        return con;
    }

    /**
     * Executa un conjunt de sentenciaes SQL en una unica transaccio.
     * @param con es la connexio contra la que excutar les sentencies SQL
     * @param sentenciesSql es una array amb totes els sentencies SQL a 
     * executar. Les sentencies es troben expressades com a cadenes de 
     * caracters.
     * @throws UtilitatJdbcSQLException 
     */
    public static void executar(Connection con, String[] sentenciesSql) 
                                                throws UtilitatJdbcSQLException{
        boolean autocommit;
        Statement stm = null;
        try {
            autocommit = con.getAutoCommit();
            con.setAutoCommit(false);
            stm = con.createStatement();

            for(String sent: sentenciesSql){
                stm.executeUpdate(sent);
            }
            con.commit();
            con.setAutoCommit(autocommit);
        } catch (SQLException ex) {
            desfer(con, ex);
            onError(ex);            
        }finally{
            tancaStatement(stm);
        }
    }    

    /**
     * Executa una sentencia SQL.
     * @param con es la connexio contra la que excutar la sentencia SQL
     * @param sentenciaSql es una cadena de caracters representant una
     * sentencia SQL.
     * @throws UtilitatJdbcSQLException 
     */
    public static void executar(Connection con, String sentenciaSql) 
                                                throws UtilitatJdbcSQLException{
        boolean autocommit;
        Statement stm = null;
        try {
            autocommit=con.getAutoCommit();
            con.setAutoCommit(true);
            stm = con.createStatement();
            stm.executeUpdate(sentenciaSql);
            con.setAutoCommit(autocommit);
        } catch (SQLException ex) {
            onError(ex);            
        }finally{
            tancaStatement(stm);
        }
    }    
    
    /**
     * Obte un valor numeric, el qual es el resultat de demanar el seguent 
     * valor d'una sequencia anomeanda com indica el parametre nomSequencia, al
     * SGBD connectat per mitja de la connexio con. 
     * @param con es la connexio utilitzada per demanar el seguent valor de la 
     * sequencia
     * @param nomSequencia es el nom de la sequencia de la que es demanara el
     * seguent valor.
     * @return el valor numeric obtingut.
     * @throws SQLException 
     */
    public static Long getNextValueId(Connection con, String nomSequencia) 
                                                throws SQLException{
        Long ret = null;
        boolean autocommit;
        Statement stm = null;
        ResultSet rs = null;
        try {
            autocommit=con.getAutoCommit();
            con.setAutoCommit(true);
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT nextval('" + nomSequencia + "')");
            if(rs.next()){
                ret = rs.getLong(1);
            }

            con.setAutoCommit(autocommit);
        }finally{
            tancaStatement(stm, rs);
        }
        return ret;
    }
    

    /**
     * Desfa les accions fetes en la transaccio activa mitjancant una 
     * sentencia rollback. El metode pot rebre una intancia no nul.la d'una 
     * excepcio en cas que la invocacio d'aquest metode sigui degut a un error
     * anterior que requerrexi desfer les accions de la transaccio activa.
     * @param con es la connexio on es troba activa la transaccio a desfer.
     * @param sqlEx es l'exepcio que provoca la invocacio d'aquest metode per 
     * tal de desfer les accions de la transaccio.
     * @throws UtilitatJdbcRollbackException 
     */
    public static void desfer(Connection con, SQLException sqlEx) 
                                           throws UtilitatJdbcRollbackException{
        try {
            con.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(EstructuraAplicacioBD.class.getName())
                    .log(Level.SEVERE, null, ex);
            throw new UtilitatJdbcRollbackException(ex, sqlEx);
        }
    }
    
    /**
     * Tanca la connexio passada per parametre. Un cop tancada la connexio 
     * restara inactiva.
     * @param con es la connexio a tancar.
     */
    public static void tancarConnexio(Connection con){
        try {
            if(con!=null && !con.isClosed()){
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilitatJdbc.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Tanca l'Statement i el ResultSet passats per parametre.
     * @param stm es l'Statement a tancar
     * @param rs es el ResultSet a tancar.
     */
    public static void tancaStatement(Statement stm, ResultSet rs){
        try {
            if(rs!=null && !rs.isClosed()){
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(EstructuraAplicacioBD.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        try {
            if(stm!=null && !stm.isClosed()){
                stm.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(EstructuraAplicacioBD.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    public static void tancaStatement(Statement stm){
        tancaStatement(stm, null);
    }

    public static void tancaResultSet(ResultSet rs){
        tancaStatement(null, rs);
    }

    public static void onError(Exception ex) throws UtilitatPersistenciaException{
        Logger.getLogger(EstructuraAplicacioBD.class.getName()).log(
                Level.SEVERE, null, ex);
        throw new UtilitatPersistenciaException(ex.getMessage(), ex);
    }

    public static void onError(SQLException ex) throws UtilitatJdbcSQLException{
        Logger.getLogger(EstructuraAplicacioBD.class.getName()).log(
                Level.SEVERE, null, ex);
        throw new UtilitatJdbcSQLException(ex.getMessage(), ex);
    }
}
