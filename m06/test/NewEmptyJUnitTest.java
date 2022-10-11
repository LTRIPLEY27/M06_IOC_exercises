/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

import DAM_M06_EAC2_Calzadilla_C.Exercisi1.src.gestors.GestorException;
import DAM_M06_EAC2_Calzadilla_C.Exercisi1.src.gestors.GestorMedicament;
import DAM_M06_EAC2_Calzadilla_C.Exercisi1.src.model.Medicament;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ISABE
 */
public class NewEmptyJUnitTest {
    
    private final String TAULA="medicament";
    
    private final String URL="jdbc:postgresql://localhost:5432/ioc";
    private final String USER="postgres";
    private final String PSW="root";
   
    private GestorMedicament gestor;
    
    
    private Connection connexio;
    
    
    private final Medicament[] llistat = {
            //exemples reals
            new Medicament(1, "Aspirina", "AAS", 20, new String[]{"Nausees, Ulceres"}, "Bayer", true, "Alemanya"),
            new Medicament(2, "Viagra", "Sildenafil", 2, new String[]{"Nausees","Priapisme"}, "Pfizer", false, "Alemanya"),
            //dades random per a obfuscar una mica
            new Medicament(3, "A1", "A2", 1, new String[]{"llista1"}, "A3",  true, "A5"),
            new Medicament(4, "B1", "B2", 2, new String[]{"llista1","llista2"}, "B3",  true, "B5"),
            new Medicament(5, "C1", "C2", 3, new String[]{"llista1","llista2","llista3","llista4","llista5","llista6"}, "C3", true, "C5"),
    };
    
    
    @Before
    public void setUp() throws SQLException, GestorException {
        obrir();
        //buidarTaula();
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
        connexio = DriverManager.getConnection(URL, USER, PSW);
        connexio.setAutoCommit(true);
        gestor = new GestorMedicament(connexio);
     
    }
    
    private void tancar() throws SQLException{
        connexio.close();
    }
    
    /*private void buidarTaula() throws SQLException{
        PreparedStatement ps=connexio.prepareStatement("DELETE FROM "+TAULA);
        
        ps.executeUpdate();
    }*/
    
    private void ferAltes() throws GestorException{
        for(Medicament c : llistat){
            gestor.inserir(c);
        }
    }
    
    
    public boolean comparaObjectes(Medicament p1, Medicament p2){
        return  p1.getId()==p2.getId() &&
                p1.getNomMedicament().equals(p2.getNomMedicament()) &&
                p1.getPrincipiActiu().equals(p2.getPrincipiActiu()) &&
                p1.getDosi()== p2.getDosi() &&
                p1.getNomEmpresa().equals(p2.getNomEmpresa()) &&
                p1.getDomicili().equals(p2.getDomicili()) &&
                p1.isActiva()==p2.isActiva() &&
                comparaArraysCadena(p1.getContraindicacions(),p2.getContraindicacions());
    }
    
    public boolean comparaArraysCadena(List<String> l1, List<String> l2){
        
        if(l1.size()!=l2.size()){
            return false;
        }
        
        for(String s:l1){
            if(!l2.contains(s)){
                return false;
            }
        }
        return true;
    }
    
    // les altes es fan cada vegada en el Before, amb el que no és necessari aquest mètode. Si hi ha les altes correctes es comprova 
    // als metodes provaConsultaPerCodiTots i provaConsultaPerCodiCap
    @Test
    public void provaAltes() throws GestorException{
    }
    
    /*@Test 
    public void provaConsultaPerCodiTots() throws SQLException, GestorException{
        tancarIObrir();  //ens assegurem que s'han gravat els buffers
        
        Medicament a=gestor.obtenirMedicament(3);
        assertTrue(comparaObjectes(a,llistat[2]));
        
        a=gestor.obtenirMedicament(1);
        assertTrue(comparaObjectes(a,llistat[0]));
        
        a=gestor.obtenirMedicament(4);
        assertTrue(comparaObjectes(a,llistat[3]));
        
        a=gestor.obtenirMedicament(2);
        assertTrue(comparaObjectes(a,llistat[1]));
        
        a=gestor.obtenirMedicament(5);
        assertTrue(comparaObjectes(a,llistat[4]));
    }
    
    //provem a fer consultes per codis innexistents
    
    @Test 
    public void provaConsultaPerIdCap() throws SQLException, GestorException {
        tancarIObrir();  //ens assegurem que s'han gravat els buffers

        assertNull(gestor.obtenirMedicament(40));
        assertNull(gestor.obtenirMedicament(-3));
        assertNull(gestor.obtenirMedicament(70));

    }
    
    @Test
    public void provaObtenirObjectePerElementArray() throws SQLException, GestorException {
        tancarIObrir();
        
        List<Medicament> l=gestor.obtenirMedicamentPerContraindicacio("Nausees");
        
        
        assertTrue(l.size()==2 && comprovaObjecte(l,1)&& comprovaObjecte(l,2));
        
        l=gestor.obtenirMedicamentPerContraindicacio("llista1");
        
        assertTrue(l.size()==3 && comprovaObjecte(l,3)&& comprovaObjecte(l,4)&& comprovaObjecte(l,5));
        
        l=gestor.obtenirMedicamentPerContraindicacio("llista2");
        
        assertTrue(l.size()==2 && comprovaObjecte(l,4)&& comprovaObjecte(l,5));
        
        l=gestor.obtenirMedicamentPerContraindicacio("llista3");
        
        assertTrue(l.size()==1 && comprovaObjecte(l,5));

    }
    
    private boolean comprovaObjecte(List<Medicament> l, int id){
        for(Medicament p:l){
            if(p.getId()==id && comparaObjectes(p,llistat[id-1])){ // aprofitem que posicio = id - 1
                return true;
            }
        }
        return false;
    }
    
    @Test
    public void provaEliminar() throws SQLException, GestorException {
        gestor.eliminar(3);
        tancarIObrir();
        
        assertNull(gestor.obtenirMedicament(3));
        
        Medicament a=gestor.obtenirMedicament(1);
        assertTrue(this.comparaObjectes(a,llistat[0]));
        
        a=gestor.obtenirMedicament(4);
        assertTrue(comparaObjectes(a,llistat[3]));
        
        a=gestor.obtenirMedicament(2);
        assertTrue(comparaObjectes(a,llistat[1]));


    }*/
    
    
    
}
