package ioc.dam.m6.uf4.aplicacio.test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import clonador.Clonador;
import comparador.Comparador;
import ioc.dam.m6.persistencia.excepcions.UtilitatPersistenciaException;
import ioc.dam.m6.uf4.eac5.aplicacio.AplicacioBD;
import ioc.dam.m6.uf4.eac5.aplicacio.Barri;
import ioc.dam.m6.uf4.eac5.aplicacio.Districte;
import ioc.dam.m6.uf4.eac5.aplicacio.dao.AplicacioBDImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author professor
 */
public class TestComponentsDAO {
    
    private final  AplicacioBD aplicBD=new AplicacioBDImpl();

    
    
    private final ArrayList totesAltes=new ArrayList();
    private final ArrayList<Districte> totsDistrictes = new ArrayList<>(),
            
    // Resultat de consultar els districtes despres de fer diferents proves
                                    totsDistrictesExcepteEl_4 = new ArrayList<>(),  // despres d'eliminar el districte amb codi 4
                                    totsDistrictesCanviatEl_2 = new ArrayList<>(),  // despres de modificar el districte amb codi 2
                                    districtesAmbAlmenys20000Habitants=new ArrayList<>();                 // resultat de consultar els districtes amb 20000 habitants o mes

    
    private final ArrayList<Barri> totsBarris = new ArrayList<>(),
    // Resultat de consultar els barris despres de fer diferents proves
                                    totsBarrisExcepteColl = new ArrayList<>(),  // despres d'eliminar el barri de Campoamor
                                    totsBarrisCanviatLaTorre = new ArrayList<>(),  // despres de modificar el barri de Gracia
                                    barrisDelDistricte_3 = new ArrayList<>(),            // resultat de consultar els barris del districte que te codi 3
                                    barrisDelDistricte_1=new ArrayList<>(),             // resultat de consultar els barris del districte que te codi 1
                                    barrisAmbDensitat200_300=new ArrayList<>();                 // resultat de consultar els barris amb una densitat entre 200 i 300 (tots dos inclosos)

    private Barri barriLaTorre;
    private Districte districte_2, districte_4;
    
    
    
    
    

    /**
     * Abans de cada test neteja la base de dades
     * @throws ioc.dam.m6.persistencia.excepcions.UtilitatPersistenciaException en cas d'error
     */
    @Before
    public void setUp() throws UtilitatPersistenciaException  {
        obre();
        creaDadesPrincipals();
        
        List<Barri> auxLlistaBarris=aplicBD.obtenirBarris();

        List<Districte> auxLlistaDistrictes=aplicBD.obtenirDistrictes();
        
        for(Barri ba:auxLlistaBarris){
            aplicBD.eliminar(ba);
        }
        for(Districte d:auxLlistaDistrictes){
            aplicBD.eliminar(d);
        }
        
        fesAltes();
        
    } 
    
    /**
     * En acabar els tests, tanca la connexio amb la base de dades
     */
    
    @After()
    public  void classEnds(){
        tanca();
    }
    
    @Test 
    public void provaAltes(){
        //es fan als tractaments previs
    }
    
    @Test
    public void provaConsultaTotsDistrictes() throws UtilitatPersistenciaException{
        assertTrue(Comparador.compara(aplicBD.obtenirDistrictes(),totsDistrictes, 2));        
    }

    
    @Test
    public void provaConsultaTotsBrris() throws UtilitatPersistenciaException{
        assertTrue(Comparador.compara(aplicBD.obtenirBarris(),totsBarris,2));        
    }
    
    @Test
    public void provaConsultaBarrisPerDistricte() throws UtilitatPersistenciaException{
        assertTrue(Comparador.compara(aplicBD.obtenirBarrisPerDistricte(3),this.barrisDelDistricte_3, 2));   
        assertTrue(Comparador.compara(aplicBD.obtenirBarrisPerDistricte(1),this.barrisDelDistricte_1, 2)); 
    }


    @Test
    public void provaConsultaDistrictesPerMinHabitants() throws UtilitatPersistenciaException{
  
        assertTrue(Comparador.comparaLlistes(aplicBD.obtenirDistrictesPerHabitants(20000),this.districtesAmbAlmenys20000Habitants,2));  
    }
    
    @Test
    public void provaEliminarBarriColl() throws UtilitatPersistenciaException{
        Barri ba=new Barri();
        ba.setNom("Coll");
        aplicBD.eliminar(ba);
        assertTrue(Comparador.comparaLlistes(aplicBD.obtenirBarris(),totsBarrisExcepteColl, 2));
    }
    
    
    @Test
    public void provaEliminarBarriErroni() throws UtilitatPersistenciaException{
        Barri ba=new Barri();
        ba.setNom("Error");
        aplicBD.eliminar(ba);
        assertTrue(Comparador.comparaLlistes(aplicBD.obtenirBarris(),totsBarris, 2));
    }
    
    @Test
    public void provaEliminarDistricte4() throws UtilitatPersistenciaException{
        Districte d=new Districte();
        d.setNumero(4);
        aplicBD.eliminar(d);    // es el districte que no te cap barri associat; amb la resta dona error d'integritat
        assertTrue(Comparador.comparaLlistes(aplicBD.obtenirDistrictes(),totsDistrictesExcepteEl_4,2));
    }
    
    @Test  
    public void provaEliminarDistricteErroni() throws UtilitatPersistenciaException{
        Districte d=new Districte();
        d.setNumero(88);  // no existeix
        aplicBD.eliminar(d);    
        assertTrue(Comparador.comparaLlistes(aplicBD.obtenirDistrictes(),totsDistrictes,2));
    }
    
    @Test
    public void provaModificarBarriLaTorre() throws UtilitatPersistenciaException{
        Barri ba=new Barri();
        ba.setNom("La Torre");
        ba=aplicBD.refrescar(ba);
        ba.setDensitatPoblacio(1);
        aplicBD.emmagatzemar(ba);
        assertTrue(Comparador.comparaLlistes(aplicBD.obtenirBarris(),totsBarrisCanviatLaTorre, 2));
    }        
    
    @Test
    public void provaModificarDistricteErroni() throws UtilitatPersistenciaException{
        Districte d=new Districte();
        d.setNumero(88);  // no existeix
        d= aplicBD.refrescar(d);
        if(d!=null){
            d.setHabitants(1);
            aplicBD.emmagatzemar(d);
        }
        assertTrue(Comparador.comparaLlistes(aplicBD.obtenirDistrictes(),totsDistrictes,2));
    }

    public void provaModificarDistricte2() throws UtilitatPersistenciaException{
        Districte d=new Districte();
        d.setNumero(2);
        d=aplicBD.refrescar(d);
        d.setHabitants(1);
        aplicBD.emmagatzemar(d);
        assertTrue(Comparador.comparaLlistes(aplicBD.obtenirDistrictes(),totsDistrictesCanviatEl_2, 2));
    }

    
    @Test
    public void provaConsultarBarriLaTorre() throws UtilitatPersistenciaException{
        Barri ba=new Barri();
        ba.setNom("La Torre");
        ba=aplicBD.refrescar(ba);
        assertTrue(Comparador.compara(ba,barriLaTorre,2));
    }

    @Test
    public void provaConsultarBarriErroni() throws UtilitatPersistenciaException{
        Barri ba=new Barri();
        ba.setNom("Error");
        ba=aplicBD.refrescar(ba);
        assertNull(ba);
    }

    @Test
    public void provaConsultarDistricte2() throws UtilitatPersistenciaException{
        Districte d=new Districte();
        d.setNumero(2);
        d=aplicBD.refrescar(d);
        assertTrue(Comparador.compara(d,districte_2,2));
    }
    
    @Test
    public void provaConsultarDistricte4() throws UtilitatPersistenciaException{
        Districte d=new Districte();
        d.setNumero(4);
        d=aplicBD.refrescar(d);
        assertTrue(Comparador.compara(d,districte_4,2));
    }

    @Test
    public void provaConsultarDistribuidor88() throws UtilitatPersistenciaException{
        Districte d=new Districte();
        d.setNumero(88); // No es a la base de dades
        assertNull(aplicBD.refrescar(d));
    }
    
    @Test
    public void provaConsultarBarrisAmbDensitat_200_300() throws UtilitatPersistenciaException{
        assertTrue(Comparador.compara(aplicBD.obtenirBarrisPerDensitat(200,300),barrisAmbDensitat200_300,2));
    }      
    
     
    
    @Test
    public void provaConsultarDistrictesAmbAlmenys20000Habitants() throws UtilitatPersistenciaException{
        assertTrue(Comparador.comparaLlistes(aplicBD.obtenirDistrictesPerHabitants(20000),districtesAmbAlmenys20000Habitants,2));
    }      
    
    
    @Test
    public void provaConsultarBarrisDistricte3() throws UtilitatPersistenciaException{
        assertTrue(Comparador.comparaLlistes(aplicBD.obtenirBarrisPerDistricte(3),barrisDelDistricte_3,2));
    }      

    @Test
    public void provaConsultarBarrisDistricte1() throws UtilitatPersistenciaException{
        assertTrue(Comparador.comparaLlistes(aplicBD.obtenirBarrisPerDistricte(1),this.barrisDelDistricte_1,2));
    }          
    
    
    /**
     * Dona d'alta tots els objectes del vector <code>totesAltes</code>
     * @throws UtilitatPersistenciaException en cas d'error
     */

    private void fesAltes() throws UtilitatPersistenciaException{
        for(Object o:totesAltes){
            if(o instanceof Districte){
                aplicBD.emmagatzemar((Districte) o);
            }
            if(o instanceof Barri){
                aplicBD.emmagatzemar((Barri) o);
            }
        }
    }

    
   /**
    * Crea un barri amb les dades indicades per la resta dels parametres
    * @param nom nom del barri
    * @param densitat densitat del barri
    * @param districte districte on es el barri
    * @return barri creat amb les dades indicades pels parametres
    */
    private Barri creaBarri(String nom, float densitat,Districte districte){
        Barri barri=new Barri();
        
        barri.setNom(nom);
        barri.setDensitatPoblacio(densitat);
        barri.setDistricte(districte);
        
        return barri;
    }
    
    /**
     * Crea un districte amb les dades indicades pels parametres
     * @param numero numero que identifica al districte
     * @param nom nom del districte
     * @param habitants nombre d'habitants del districte
     * @return 
     */
    private  Districte creaDistricte(int numero, String nom, int habitants){
        Districte districte=new Districte();
        
        districte.setNumero(numero);
        districte.setNom(nom);
        districte.setHabitants(habitants);
        
        return districte;
    }
    
    /**
     * crea districtes i barris i els emmagatzema a les llistes on aniran els
 resultats esperats de les diferents proves
     */
    
    private  void creaDadesPrincipals(){  // s'omplen les llistes amb les dades de prova i els resultats de les consultes
        
        Districte d1, d2, d3;
        
        totsDistrictes.add(d1=creaDistricte(1,"Nord",5100));      
        totsDistrictes.add(d2=creaDistricte(2,"Sud",67000));
        totsDistrictes.add(d3=creaDistricte(3,"Est",15000));        
        totsDistrictes.add(creaDistricte(4,"Oest",80000)); 
        
        
        totsBarris.add(creaBarri("Can Pintxo",500,d1)); d1.getBarris().add(totsBarris.get(0));
        totsBarris.add(creaBarri("Tres Turons",600,d1)); d1.getBarris().add(totsBarris.get(1));  
        totsBarris.add(creaBarri("La Torre",700,d1)); d1.getBarris().add(totsBarris.get(2));
        totsBarris.add(creaBarri("La Pau",800,d2)); d2.getBarris().add(totsBarris.get(3));
        totsBarris.add(creaBarri("Font Nova",900,d3)); d3.getBarris().add(totsBarris.get(4));
        totsBarris.add(creaBarri("Coll",650,d3)); d3.getBarris().add(totsBarris.get(5));
        
        
        totesAltes.addAll(totsDistrictes);
        totesAltes.addAll(totsBarris);
        
        for(Districte d:totsDistrictes){
            Districte aux;
            
            aux=copia(d);
            if(d.getNumero()==2){
                districte_2=copia(d);
                aux.setHabitants(1);
                
            }
            totsDistrictesCanviatEl_2.add(aux);
            
            if(d.getNumero()!=4){
                totsDistrictesExcepteEl_4.add(copia(d));
            } 
            else districte_4=copia(d);
            
            if(d.getHabitants()>=20000)  districtesAmbAlmenys20000Habitants.add(copia(d));
                        
        }  // fi del for que recorre tots els districtes
        
        for(Barri b:totsBarris){
            Barri aux;
            
            aux=copia(b);
            
            if(b.getNom().equals("La Torre")){
                barriLaTorre=b;
                aux.setDensitatPoblacio(1);

            }
            
            totsBarrisCanviatLaTorre.add(aux);
            
            if(!b.getNom().equals("Coll")){
                totsBarrisExcepteColl.add(copia(b));
            }
            
            int auxDistricte= b.getDistricte().getNumero();
            
            if(auxDistricte==3)  barrisDelDistricte_3.add(copia(b));
            
            if(auxDistricte==1)  barrisDelDistricte_1.add(copia(b));
            
            float auxDensitat=b.getDensitatPoblacio();
            
            if(auxDensitat>=200 && auxDensitat<=300) barrisAmbDensitat200_300.add(copia(b));
            

        } // fi del for que recorre tots els barris
        
    }
    
    private Districte copia(Districte d){
        return (Districte) Clonador.clona(d,5);
    }
    
    private Barri copia(Barri b){
        return (Barri)Clonador.clona(b, 5);
    }
    

    private void obre(){
        try{
        
        aplicBD.iniciar();
        aplicBD.obrir();

        }catch(Exception e){
            System.out.println("@@@@@@@@@");
            System.out.println(e.getMessage());
            System.out.println("@@@@@@@@@");
        }
    }
    
    /**
     * tanca la connexio amb la base de dades
     */
    private void tanca(){
        aplicBD.tancar();
    }

}
