/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

import DAM_M06_EAC2_Calzadilla_C.Exercisi2.src.gestors.GestorException;
import DAM_M06_EAC2_Calzadilla_C.Exercisi2.src.gestors.GestorMedicamentOODB;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import DAM_M06_EAC2_Calzadilla_C.Exercisi2.src.model.Medicament;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author ISABE
 */
public class TestGestors {
    
    private final String PU = "ObjectDB";

   
    private GestorMedicamentOODB gestor;
    
    private EntityManagerFactory emFactory = Persistence.createEntityManagerFactory(PU);
    private EntityManager em = emFactory.createEntityManager();
    
    private final Medicament[] objs = {
        //dades reals
        new Medicament(1,"Aspirina", 2.5f, "SS", "Bayer", 500, true),
	new Medicament(2,"A0", 1.2f, "A0", "A0", 90, false),
        //dades falses per a obfuscar
	new Medicament(3,"A1", 1.506F, "A2", "A3", 1, false),
	new Medicament(4,"B1", 0.797F, "B2", "B3", 2, false),
	new Medicament(5,"C1", 1.489F, "C2", "C3", 6, true)
    };
    
    private final Medicament as4 = new Medicament(5, "Viagra", 15f, "Alemanya", "Pfizer", 50, false), as5 =  new Medicament(5, "Viagra", 15f, "Alemanya", "Pfizer", 50, false);
    
    @Before
    public void setUp() throws SQLException, GestorException {
        obrir();
        buidarBaseDades();
        ferAltes();
        
    }
    
    @After
    public void tearDown() throws SQLException {
        tancar();
    }
    
    
    private void tancarIObrir() throws SQLException{
        tancar();
        obrir();
        
    }
    
    
    private void obrir() throws SQLException{
        
        emFactory = Persistence.createEntityManagerFactory(PU);
        em = emFactory.createEntityManager();        
        
        gestor = new GestorMedicamentOODB(em);
        System.out.println(em);
     
    }
    
    private void tancar() throws SQLException{
       // System.out.println(em);
        em.close();
        emFactory.close();
    }
    
    private void buidarBaseDades() throws SQLException{
        
        
        Query query = em.createQuery("DELETE FROM Medicament");
        em.getTransaction().begin();
        query.executeUpdate();
        em.getTransaction().commit();
    }
    
    private void ferAltes() throws GestorException{
        for(Medicament a:objs){
            gestor.inserir(a);
        }
    }
  
    
    public boolean comparaObjectes(Medicament p1, Medicament p2){
        return  p1.getNombreSerie()==p2.getNombreSerie() &&
                p1.getNomMedicament().equals(p2.getNomMedicament()) &&
                p1.getEmpresa().equals(p2.getEmpresa()) &&
                p1.getAutoritatSanitaria().equals(p2.getAutoritatSanitaria()) &&
                p1.getPreu()==p2.getPreu() &&
                p1.getPesEnMg()==p2.getPesEnMg() &&
                p1.isAprovat()==p2.isAprovat();
    }
    
    // les altes es fan cada vegada. Si hi ha les altes correctes es comprova 
    // als mètodes provaConsultaPerCodiTotes i provaConsultaPerCodiCap
    @Test
    public void provaAltes(){
        
    }  
    //*****************************************
     @Test
    public void getObject(){
        Medicament a = gestor.obtenirMedicament(3);
        assertTrue(comparaObjectes(a,objs[2]));
    } 
    //*****************************************
    
    @Test 
    public void provaConsultaPerRefTotes() throws SQLException, GestorException{
        tancarIObrir();  //ens assegurem que s'han gravat els buffers
        
        Medicament a=gestor.obtenirMedicament(3);
        assertTrue(comparaObjectes(a,objs[2]));
        
        a=gestor.obtenirMedicament(1);
        assertTrue(comparaObjectes(a,objs[0]));
        
        a=gestor.obtenirMedicament(4);
        assertTrue(comparaObjectes(a,objs[3]));
        
        a=gestor.obtenirMedicament(2);
        assertTrue(comparaObjectes(a,objs[1]));
    }
    
    //provem a fer consultes per codis innexistents
    
    @Test 
    public void provaConsultaPerRefCap() throws SQLException, GestorException{
        tancarIObrir();  //ens assegurem que s'han gravat els buffers

        assertNull(gestor.obtenirMedicament(35));
        assertNull(gestor.obtenirMedicament(-1));
        assertNull(gestor.obtenirMedicament(8));

    }

    @Test 
    public void provaModificacio() throws SQLException, GestorException{
        System.out.println("aaaa");

        tancarIObrir();  //ens assegurem que s'han gravat els buffers
        
        Medicament a=gestor.obtenirMedicament(3);
        assertTrue(comparaObjectes(a,objs[2]));
        
        a=gestor.obtenirMedicament(1);
        assertTrue(comparaObjectes(a,objs[0]));
        
        gestor.modificar(as4);
        a=gestor.obtenirMedicament(5);
                
        assertTrue(comparaObjectes(a,as5));
        
        a=gestor.obtenirMedicament(2);
        assertTrue(comparaObjectes(a,objs[1]));
    }
    
    
    @Test
    public void provaEliminar() throws SQLException, GestorException {
        gestor.eliminar(3);
        tancarIObrir();
        
        assertNull(gestor.obtenirMedicament(3));
        
        Medicament a=gestor.obtenirMedicament(1);
        assertTrue(comparaObjectes(a,objs[0]));
        
        a=gestor.obtenirMedicament(4);
        assertTrue(comparaObjectes(a,objs[3]));
        
        a=gestor.obtenirMedicament(2);
        assertTrue(comparaObjectes(a,objs[1]));


    }
    
}
