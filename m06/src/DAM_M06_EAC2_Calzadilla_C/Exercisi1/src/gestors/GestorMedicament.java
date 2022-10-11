/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAM_M06_EAC2_Calzadilla_C.Exercisi1.src.gestors;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import DAM_M06_EAC2_Calzadilla_C.Exercisi1.src.model.Medicament;
import java.util.ArrayList;
import java.util.Arrays;



/**
 * Classe que gestiona la persistencia dels objectes de la classe model
 *  @author Isabel Calzadilla M-06
 *  @version  : 10-10-2022
 *  @Resum : Activitat 1, M06 UF2
*/
public class GestorMedicament {

    private Connection conn = null;
    private PreparedStatement statementPre;
    private ResultSet result;
    
    // QUERY PARA INSERTAR VALORES EN LA TABLA 'MEDICAMENT', HACEMOS USO DEL AGRUPAMIENTO MEDIANTE PARÉTESIS PARA EL CASO 'TYPUS' EMPRESA
    private final String insertSQL = "INSERT INTO medicament VALUES (?,?,?,?,(?,?,?),?);";
    private final String deleteSQL = "DELETE FROM medicament WHERE id = ?";
    //private final String selectSQL = "SELECT * FROM medicament WHERE id = ?";
    private final String selectSQL = "SELECT id, nomMedicament, principiActiu, dosi, (laboratori).nomEmpresa, (laboratori).activa, (laboratori).domicili, contraindicacions FROM medicament WHERE id = ?";
    
    // LA QUERY RECIBIRÁ CUALQUIER 
    private final String selectContraSQL = "SELECT id, nomMedicament, principiActiu, dosi, (laboratori).nomEmpresa, (laboratori).activa, (laboratori).domicili, contraindicacions FROM medicament WHERE ? = ANY(contraindicacions)";
    /**
     * Crea un gestor de l'objecte que treballara amb la connexio conn
     * @param conn connexio a traves de la qual es fan persistents els objectes
     */
    public GestorMedicament(Connection conn) {
       this.conn = conn;
    }
    
    /**
     * Crea un Objecte en la base de dades. Si ja n'hi ha algun amb el seu mateix id, llenca una excepcio.
     * @param obj Objecte a crear
     * @throws gestors.GestorException en cas d'error a la base de dades que pot ser, entre altres, clau duplicada.
     */
    public void inserir(Medicament obj) throws GestorException{
        
        try {
            statementPre = conn.prepareStatement(insertSQL);
            statementPre.setInt(1, obj.getId());  // FALTA EL MÉTODO PARA VERIFICAR SI EXISTE EL ID EN LA TABLA
            statementPre.setString(2, obj.getNomMedicament());
            statementPre.setString(3, obj.getPrincipiActiu());
            statementPre.setInt(4, obj.getDosi());
            
            // CONVERSIÓN DEL OBJETO SQL LIST CON EL MÉTODO 'CREATEARRAY OF' Y LAS INDICACIONES A COMPONERLO PARA HACERLO LEGÍBLE EN LA TABLA
            statementPre.setArray(8, conn.createArrayOf("varchar", obj.getContraindicacions().toArray(new String[10])));
            statementPre.setString(5, obj.getNomEmpresa());
            statementPre.setBoolean(6, obj.isActiva());
            statementPre.setString(7, obj.getDomicili());
            
            statementPre.executeUpdate();
            System.out.println("Correctamente añadido");
            
        } catch (SQLException ex) {
            throw new GestorException("Error en la preparación del statement con la query de insertar, verifíque");
        }
    }
    
    /**
     * Esborra de la base de dades un objecte amb un id determinat
     * @param objId id de l'objecte a esborrar
     * @throws gestors.GestorException si el id no correspon a cap objecte de la base de dades
     * o hi ha un error en l'acces a la base de dades
     */
   
    public void eliminar(int objId) throws GestorException {
       
        try {
            statementPre = conn.prepareStatement(deleteSQL);
            statementPre.setInt(1, objId);
            
            if(statementPre.executeUpdate() == 0) {
                throw new GestorException(" ID Inexistente, verifíque");
            }
        } catch (SQLException ex) {
           throw new GestorException("Error en la preparación del statement con la query de Borrado, verifíque");
        }
    }


    
    /**
     * Obte l'objecte de la base de dades amb un determinat id.
     * @param objId id de l'objecte a obtenir
     * @return l'objecte que té l'id proporcionada o null si no hi ha cap objecte amb aquest id a la base de dades
     * @throws gestors.GestorException en cas d'error a la base de dades
     */
   
    public Medicament obtenirMedicament(int objId) throws GestorException  {
        try {
            
            statementPre = conn.prepareStatement(selectSQL);
            statementPre.setInt(1, objId);
            
            result = statementPre.executeQuery();
                      
            // DECLARACIÓN DEL RESULSET YA QUE NOS DEVOLVERÁ UN OBJETO
            result = statementPre.executeQuery();
            
            if(result.next()){
                return new Medicament(result.getInt("id"), result.getString("nomMedicament"), result.getString("principiActiu"), result.getInt("dosi"), (String[]) result.getArray("contraindicacions").getArray(), result.getString("nomEmpresa"), result.getBoolean("activa"), result.getString("domicili"));
            }
            return null;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new GestorException("Error en la preparación del statement con la query para obtener el Medicamento, verifíque");
        }
    }

    /**
     * Retorna una llista dels objectes que tenen la contraindicacio proporcionada
     * @param contraindicacio contraindicacio utilitzada per a obtenir la llista d'objectes
     * @return Llista amb els objectes de la base de dades que contenen l'esmentada contraindicacio al seu array.
     * @throws gestors.GestorException en cas d'error a la base de dades
     */
    
    public List<Medicament> obtenirMedicamentPerContraindicacio(String contraindicacio) throws GestorException  {
        
        try {
            List <Medicament> medicaments = new ArrayList<>();
            
            statementPre = conn.prepareStatement(selectContraSQL);
            statementPre.setString(1, contraindicacio);
            
            result = statementPre.executeQuery();
            
            while(result.next()){   
                medicaments.add(new Medicament(result.getInt("id"), result.getString("nomMedicament"), result.getString("principiActiu"), result.getInt("dosi"), (String[]) result.getArray("contraindicacions").getArray(), result.getString("nomEmpresa"), result.getBoolean("activa"), result.getString("domicili")));
            }
            
            /*for(var i : medicaments){
                System.out.println(i.getId());
            }*/
            
            return medicaments;
        } catch (SQLException ex) {
            throw new GestorException("Error en la preparación del statement con la query para obtener la contraindicación, verifíque");
        }
    }
    
}

