/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m6.uf4.eac5.aplicacio.dao;

import ioc.dam.m6.uf4.eac5.aplicacio.Districte;
import ioc.dam.m6.persistencia.AbstractJdbcDaoSimplificat;
import ioc.dam.m6.persistencia.JdbcPreparedDao;
import ioc.dam.m6.persistencia.JdbcPreparedQueryDao;
import ioc.dam.m6.persistencia.JdbcQueryDao;
import ioc.dam.m6.persistencia.UtilitatJdbcPlus;
import ioc.dam.m6.persistencia.excepcions.UtilitatPersistenciaException;
import ioc.dam.m6.uf4.eac5.aplicacio.Barri;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ISABEL CALZADILLA
 * @since  06/12/2022
 * @version M-06 ACTIVIDAD UF-4 EAC 5
 */
public class DistricteDao extends AbstractJdbcDaoSimplificat<Districte> {

    /**
     * Constructor que rep una connexio per parametre.
     * @param connection la connexio amb la que aquest objecte treballara
     */ 
    
    //*******************************************************************************
                // DECARACIÓN DE VARIABLES GLOBALES PARA REUSAR EN LOS MÉTODOS
    
    //*******************************************************************************
    private JdbcPreparedQueryDao queryDao;
    private JdbcPreparedDao preDao;
    private JdbcQueryDao dao;
    private Districte district;
    private BarriDao barri;  // LLAMADO A LA CLASE QUE IMPLEMENTA LOS MÉTODOS DEL DAO BARRI PARA USO COMO ATRIBUTOS EN LA CLASE DISTRICTE
    
    public DistricteDao(Connection connection) {
        super(connection);
    }
    
    @Override
    protected boolean esPersistent(final Districte entitat) throws UtilitatPersistenciaException {
        
        boolean isPersist;
        queryDao = new JdbcPreparedQueryDao(){
            
            @Override
            public void setParameter(PreparedStatement pstm) throws SQLException {
                int field = 0;
                pstm.setInt(++field, entitat.getNumero());
            }

            @Override
            public String getStatement() {
                 return "SELECT COUNT(numero) FROM districte WHERE numero = ?";
            }

            @Override
            public Object writeObject(ResultSet rs) throws SQLException {
                return rs.getInt(1);
            }
            
        };
        
        // LLAMADO A LA INTERFAZ 'UtilitatJdbcPlus.obtenirObjecte' PARA IMPLEMENTAR LA SENTENCIA DECLARADA VERIFICA SI ES 1 O 0 PARA TRASPONER SU VALOR A FALSE O TRUE
        isPersist = ((Integer) UtilitatJdbcPlus.obtenirObjecte(con, queryDao)) >= 1;
        
        return isPersist;
    }

    @Override
    protected void modificar(final Districte entitat) throws UtilitatPersistenciaException {

        preDao = new JdbcPreparedDao(){    
            
            @Override
            public void setParameter(PreparedStatement pstm) throws SQLException {
               
                // PARÁMETROS A INSERTAR EN LA QUERY, SE INCREMENTARÁN SEGÚN LA EJECUCIÓN EN '+1' PARA ROTAR TODOS LOS PARÁMETROS DE LA CLASE
                int field = 0;
                
                pstm.setString(++field, entitat.getNom());
                pstm.setInt(++field, entitat.getHabitants());
                pstm.setInt(++field, entitat.getNumero());  
                
            }

            @Override
            public String getStatement() {
                return "UPDATE districte SET nom = ?, habitants = ? WHERE numero = ?";
            }
            
        };
        // LLAMADO A LA EJECUCIÓN DE LA SENTENCIA SQL MEDIANTE LA INTERFAZ Y EL MÉTODO DE LA MISMA
        UtilitatJdbcPlus.executar(con, preDao);
    }

    @Override
    protected void inserir(final Districte entitat) throws UtilitatPersistenciaException {

        preDao = new JdbcPreparedDao(){    
            
            @Override
            public void setParameter(PreparedStatement pstm) throws SQLException {
                  
                  int field = 0;
                  
                  pstm.setInt(++field, entitat.getNumero());
                  pstm.setString(++field, entitat.getNom());
                  pstm.setInt(++field, entitat.getHabitants());
             }

            @Override
            public String getStatement() {
                return "INSERT INTO districte (numero, nom, habitants) VALUES (?, ?, ?)";
            }            
        };
        // ORDEN DE LA QUERY MEDIANTE LA INTERFAZ
            UtilitatJdbcPlus.executar(con, preDao); 
    }

    @Override
    public Districte refrescar(final Districte entitat) throws UtilitatPersistenciaException {

        queryDao = new JdbcPreparedQueryDao() { 

            // LLAMADO 'JdbcPreparedQueryDao' PARA HACER ESCRITURA DEL OBJETO MEDIANTE EL RESULSET 
            @Override
            public Object writeObject(ResultSet rs) throws SQLException {
              
                int field = 0;
                district = new Districte();
                district.setNumero(entitat.getNumero());
                district.setNom(rs.getString(++field));
                district.setHabitants(rs.getInt(++field));
                district.getBarris().clear();
                
                try {
                    barri = new BarriDao(con);
                    district.getBarris().addAll(barri.obtenirBarrisPerDistricte(entitat.getNumero()));
                } catch (UtilitatPersistenciaException ex) {
                    //Logger.getLogger(DistricteDao.class.getName()).log(Level.SEVERE, null, ex);  --->   HICE EL INTENTO DE CAPTURA DEL ERROR MEDIANTE EL LOGGER PERO NO ME RECONOCÍA LAS BIBLIOTECAS
                    System.out.println(ex.getMessage());
                }
                
                
                return district; 
            }

            @Override
            public String getStatement() {
                return "SELECT nom, habitants FROM districte WHERE numero = ?";  
            }

            @Override
            public void setParameter(PreparedStatement pstm) throws SQLException {
                int field = 0;
                pstm.setInt(++field, entitat.getNumero());
            }
        };
        return (Districte) UtilitatJdbcPlus.obtenirObjecte(con, queryDao);
    }

    @Override
    public void eliminar(final Districte entitat) throws UtilitatPersistenciaException {
     
        preDao = new JdbcPreparedDao() {
            
            @Override
            public void setParameter(PreparedStatement pstm) throws SQLException {
                int field = 0;
                pstm.setInt(++field, entitat.getNumero());           
            }

            @Override
            public String getStatement() {
                return "DELETE FROM districte WHERE numero = ?";
            }
                
        };
        
        UtilitatJdbcPlus.executar(con, preDao);
    }

    @Override
    // Obte els districtes amb totes les dades dels seus barris
    public List<Districte> obtenirTot() throws UtilitatPersistenciaException {
        
        dao = new JdbcQueryDao(){
            
            // LLAMADO AL RESULSET PARA LA CONSTRUCCION DE LA COPIA DEL OBJETO MEDIANTE LA CONSULTA  SQL
            @Override
            public Object writeObject(ResultSet rs) throws SQLException {
                int field = 0;

                district = new Districte();
                district.setNumero(rs.getInt(++field));
                district.setNom(rs.getString(++field));
                district.setHabitants(rs.getInt(++field));
                
                district.getBarris().clear();
                
                try {
                    barri = new BarriDao(con);
                    district.getBarris().addAll(barri.obtenirBarrisPerDistricte(district.getNumero()));
                    
                    
                } catch (UtilitatPersistenciaException ex){
                    System.out.println(ex.getStackTrace());
                }
                    
                return district;
            }

            @Override
            public String getStatement() {
                return "SELECT numero, nom, habitants FROM districte";
            }
            
        };
        
        return UtilitatJdbcPlus.obtenirLlista(con, dao); 
       
    }

    /**
     * Permet obtenir una llista de tots els districtes del sistema de persistencia
     * amb, com a minim, el nombre d'habitants indicat pel parametre.
     * Inclou les dades de tots els barris de cada districte inclos al resultat.
     * @param minimHabitants nombre minim d'minimHabitants dels districtes inclosos a la solucio
     * @return llista amb tots els districtes del sistema de persistencia amb, com a minim,
 el nombre d'minimHabitants indicat pel parametre
     * @throws UtilitatPersistenciaException si es produeix un error 
     */   
    
    public List<Districte> obtenirDistrictesPerHabitants(int minimHabitants) throws UtilitatPersistenciaException {

        queryDao = new JdbcPreparedQueryDao() {    
            
            @Override
            public void setParameter(PreparedStatement pstm) throws SQLException {
                int field = 0;
                pstm.setInt(++field, minimHabitants);
            }

            @Override
            public String getStatement() {
                return "SELECT nom, habitants, numero FROM districte  WHERE habitants >= ?";
            }

            @Override
            public Object writeObject(ResultSet rs) throws SQLException {
                int field =0;
                
                district = new Districte ();
                
                district.setNom(rs.getString(++field));
                district.setHabitants(rs.getInt(++field));
                district.setNumero(rs.getInt(++field));
                
               
                district.getBarris().clear();
                
                try {
                    barri = new BarriDao(con);
                    district.getBarris().addAll(barri.obtenirBarrisPerDistricte(district.getNumero()));
                    
                    
                } catch (UtilitatPersistenciaException ex){
                    System.out.println(ex.getMessage());
                }
                    
                return district;
            }
        
        };
        return UtilitatJdbcPlus.obtenirLlista(con, queryDao);
        
    }
}
