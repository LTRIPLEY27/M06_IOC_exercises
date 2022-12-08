/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m6.uf4.eac5.aplicacio.dao;

import ioc.dam.m6.uf4.eac5.aplicacio.Districte;
import ioc.dam.m6.uf4.eac5.aplicacio.Barri;
import ioc.dam.m6.persistencia.AbstractJdbcDaoSimplificat;
import ioc.dam.m6.persistencia.JdbcPreparedDao;
import ioc.dam.m6.persistencia.JdbcPreparedQueryDao;
import ioc.dam.m6.persistencia.JdbcQueryDao;
import ioc.dam.m6.persistencia.UtilitatJdbcPlus;
import ioc.dam.m6.persistencia.excepcions.UtilitatPersistenciaException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

/**
 *
 * @author ISABEL CALZADILLA
 * @since  06/12/2022
 * @version M-06 ACTIVIDAD UF-4 EAC 5
 */
public class BarriDao extends AbstractJdbcDaoSimplificat<Barri> {

   /**
     * Constructor que rep una connexio per parametre.
     * @param connection connexio amb la que aquest objecte treballara
     */
    
    public BarriDao(Connection connection) {
        super(connection);

    }

    @Override
    protected boolean esPersistent(final Barri entitat) throws UtilitatPersistenciaException {
        boolean ret;
        
        JdbcPreparedQueryDao jdbcDao = new JdbcPreparedQueryDao() {

            @Override
            public Object writeObject(ResultSet rs) throws SQLException {
                return rs.getInt(1);
            }

            @Override
            public String getStatement() {
                return "select count(nom) from barri where nom=?";
            }

            @Override
            public void setParameter(PreparedStatement pstm) throws SQLException {
                int field=0;
                pstm.setString(++field, entitat.getNom());
            }
        };
        ret = ((Integer)UtilitatJdbcPlus.obtenirObjecte(con, jdbcDao))>=1;
        
        return ret;
    }

    @Override
    protected void modificar(final Barri entitat) throws UtilitatPersistenciaException {
        JdbcPreparedDao jdbcPreparedDao = new JdbcPreparedDao() {

            @Override
            public void setParameter(PreparedStatement pstm) throws SQLException {
                int field=0;
                pstm.setFloat(++field, entitat.getDensitatPoblacio());

                if(entitat.getDistricte()==null){
                    pstm.setNull(++field, Types.INTEGER);                    
                }else{
                    pstm.setInt(++field, entitat.getDistricte().getNumero());
                }

                pstm.setString(++field, entitat.getNom());
                
            }

            @Override
            public String getStatement() {
                return "update barri set densitat=?, num_districte=? where nom=?";
            }
        };
        UtilitatJdbcPlus.executar(con, jdbcPreparedDao);
    }

    @Override
    protected void inserir(final Barri entitat) throws UtilitatPersistenciaException {
        JdbcPreparedDao jdbcPreparedDao = new JdbcPreparedDao() {

            @Override
            public void setParameter(PreparedStatement pstm) throws SQLException {
                int field=0;
                pstm.setString(++field, entitat.getNom());
                pstm.setFloat(++field, entitat.getDensitatPoblacio());
                if(entitat.getDistricte()==null){
                    pstm.setNull(++field, Types.INTEGER);                    
                }else{
                    pstm.setInt(++field, entitat.getDistricte().getNumero());
                }
            }

            @Override
            public String getStatement() {
                return "insert into barri(nom, densitat, num_districte) values(?,?, ?)";
            }
        };
        UtilitatJdbcPlus.executar(con, jdbcPreparedDao);
    }

    @Override
    public Barri refrescar(final Barri entitat) throws UtilitatPersistenciaException {
        Barri ret;
        JdbcPreparedQueryDao jdbcDao = new JdbcPreparedQueryDao() {

            @Override
            public Object writeObject(ResultSet rs) throws SQLException {
                int field=0;
                
                entitat.setDensitatPoblacio(rs.getFloat(++field));
                
                Districte pertany=new Districte();
                pertany.setNumero(rs.getInt(++field));
                if(!rs.wasNull()){
                    pertany.setNom(rs.getString(++field));
                    pertany.setHabitants(rs.getInt(++field));
                }else{
                    pertany=null;
                }
                entitat.setDistricte(pertany);
                

                return entitat;
            }

            @Override
            public String getStatement() {
                return "SELECT ba.densitat, ba.num_districte, dis.nom, dis.habitants"
                        + " FROM barri ba LEFT JOIN districte dis ON ba.num_districte=dis.numero WHERE ba.nom=?";
            }

            @Override
            public void setParameter(PreparedStatement pstm) throws SQLException {
                int field=0;
                pstm.setString(++field, entitat.getNom());
            }
        };
        ret = (Barri) UtilitatJdbcPlus.obtenirObjecte(con, jdbcDao);
        
        return ret;
    }

    @Override
    public void eliminar(final Barri entitat) throws UtilitatPersistenciaException {
        JdbcPreparedDao jdbcDao = new JdbcPreparedDao() {

            @Override
            public void setParameter(PreparedStatement pstm) throws SQLException {
                int field=0;
                pstm.setString(++field, entitat.getNom());
            }

            @Override
            public String getStatement() {
                return "delete from barri where nom = ?";
            }
        };
        UtilitatJdbcPlus.executar(con, jdbcDao);    
    }

    @Override
    public List<Barri> obtenirTot() throws UtilitatPersistenciaException {
        
        JdbcQueryDao jdbcDao = new JdbcQueryDao() {

            @Override
            public Object writeObject(ResultSet rs) throws SQLException {
                int field=0;
                
                Barri entitat = new Barri();
                
                entitat.setNom(rs.getString(++field));
                entitat.setDensitatPoblacio(rs.getFloat(++field));
                
                Districte pertany=new Districte();
                pertany.setNumero(rs.getInt(++field));
                if(!rs.wasNull()){
                    pertany.setNom(rs.getString(++field));
                    pertany.setHabitants(rs.getInt(++field));
                }else{
                    pertany=null;
                }
                entitat.setDistricte(pertany);
                

                return entitat;
            }

            @Override
            public String getStatement() {
                return "SELECT ba.nom, ba.densitat, ba.num_districte, dis.nom, dis.habitants"
                        + " FROM barri ba LEFT JOIN districte dis ON ba.num_districte=dis.numero";
            }


        };
        return UtilitatJdbcPlus.obtenirLlista(con, jdbcDao);        

    }   

    /**
     * Permet obtenir una llista de tots els barris de la base de dades
     * amb una denistat entre dos valors determinats (ambdos inclosos).Inclou les dades del seu districte.
     * @param densInferior valor minim que pot tenir la densitat dels barris del resultat
     * @param densSuperior valor maxim que pot tenir la densitat dels barris del resultat
     * @return llista amb tots els barris de la base de dades que tenen
     * la seva densitat compresa entre els dos parametres (ambdos inclosos)
     * @throws UtilitatPersistenciaException si es produeix un error 
     */ 
    
    public List<Barri> obtenirBarrisPerDensitat(float densInferior, float densSuperior) throws UtilitatPersistenciaException{
        
        JdbcPreparedQueryDao queryDao = new JdbcPreparedQueryDao() {
            
            @Override
            public void setParameter(PreparedStatement pstm) throws SQLException {
                 
                 int field = 0;
                 pstm.setFloat(++field, densInferior);
                 pstm.setFloat(++field, densSuperior);
            }

            @Override
            public String getStatement() {
               
                return "SELECT b.nom, b.densitat, b.num_districte, d.nom, d.habitants"
                        + " FROM barri b LEFT JOIN districte d ON b.num_districte = d.numero"
                        + " WHERE b.densitat BETWEEN ? AND ?";
            }

            @Override
            public Object writeObject(ResultSet rs) throws SQLException {
                int field =0;
                
                Barri fav = new Barri();
                fav.setNom(rs.getString(++field));
                fav.setDensitatPoblacio(rs.getFloat(++field));
                
                Districte district = new Districte();
                district.setNumero(rs.getInt(++field));
                if(!rs.wasNull()){
                    district.setNom(rs.getString(++field));
                    district.setHabitants(rs.getInt(++field));
                }
                else{
                    district = null;
                }
                
                fav.setDistricte(district);
                
                return fav;
            }
            
        };
        
        return UtilitatJdbcPlus.obtenirLlista(con, queryDao);  
    }
    /**
     * Permet obtenir una llista de tots els barris de la base de dades
     * que pertanyin a un determinat districte. Inclou les dades dels 
     * districtes de cada barri.
     * @param numDistricte enter que identifica al districte al qual han 
     * de pertanyer els barris resultants de la consulta
     * @return llista de tots els barris de la base de dades
     * que pertanyin al districte amb igual numero que el parametre
     * @throws UtilitatPersistenciaException si es produeix un error 
     */    
    
    public List<Barri> obtenirBarrisPerDistricte(int numDistricte) throws UtilitatPersistenciaException{
        
        // LLAMADO DE LA INTERFICIE DAO E IMPLEMENTACIÓN DE LOS MÉTODOS
        JdbcPreparedQueryDao queryDao = new JdbcPreparedQueryDao() {
            @Override
            public void setParameter(PreparedStatement pstm) throws SQLException {
                int field = 0; 
                // setteamos el parámetro a consultar
                pstm.setInt(++field, numDistricte);
                
            }

            // QUERY A RETORNAR
            @Override
            public String getStatement() {
                 
                 return "SELECT b.nom, b.densitat, b.num_districte, d.nom, d.habitants"
                        + " FROM barri b LEFT JOIN districte d ON b.num_districte = d.numero"
                        + " WHERE b.num_districte = ? ";
            }

            @Override
            public Object writeObject(ResultSet rs) throws SQLException {
                
                int field = 0;
                
                Barri fav = new Barri();
                fav.setNom(rs.getString(++field));
                fav.setDensitatPoblacio(rs.getFloat(++field));
                
                // definimos el tipo distrito que contiene el barrio
                Districte district = new Districte();
                district.setNumero(rs.getInt(++field));
                
                // VERIFICAMOS LA CLAVE
                if(!rs.wasNull()){
                    district.setNom(rs.getString(++field));
                    district.setHabitants(rs.getInt(++field));
                } 
                else {
                    district = null;
                }
                
                fav.setDistricte(district);
                
                return fav;
            }
        
    };
        
        // RECIBIMOS LA RESPUESTA DE LA CONSULTA
        
        List <Barri> results = UtilitatJdbcPlus.obtenirLlista(con, queryDao);
        
        return results; 

    }

}
