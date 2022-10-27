package gestor;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import logicaAplicacio.gestors.GestorException;
import logicaAplicacio.gestors.GestorProducte;
import logicaAplicacio.gestors.GestorLaboratori;
import logicaAplicacio.gestors.*;

import logicaAplicacio.model.*;

import logicaAplicacio.model.Laboratori;
import static clonador.Clonador.clona;
import comparador.Comparador;
import static comparador.Comparador.compara;
import static comparador.Comparador.comparaLlistes;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author professor
 */
public class TestGestors {
    
    private  EntityManagerFactory emf;
    private  EntityManager em;
    private  final String PU = "jpa2223s1";
    private  GestorProducte gestorProducte;
    private  GestorLaboratori gestorLaboratori;
    private  GestorQuimic gestorQ;
    private  GestorMedicament gestorM;
    
    private final ArrayList totesAltes=new ArrayList();
    
    private final ArrayList<Laboratori> totsCont = new ArrayList<>(),
    // Resultat de consultar els institus despres de fer diferents proves
                                      totsContExcepte7=new ArrayList<>();   // despres d'eliminar l'institut amb codi 7
    
    private final ArrayList<Producte> totsPrincipals = new ArrayList<>(),
    // Resultat de consultar els professors despres de fer diferents proves
                                    totsFillB=new ArrayList<>(),   // tots els professors tipus tècnic
                                    totsExcepte8 = new ArrayList<>(),  // despres d'eliminar el professor amb codi 8
                                    totsModificat3 = new ArrayList<>(),  // despres de modificar el professor amb codi 3
                                    totsIncrementatsEl50pc= new ArrayList<>(),   // depres d'incrementar el sou de tots els professors un 50%
                                    increment2= new ArrayList<>(),   // depres d'incrementar un 20% el sou als professors amb títol d'Enginyeria informàtica
                                    elsDeP7 = new ArrayList<>(),            // despres d'etiquetar els professors amb títol Enginyeria informàtica
                                    elsDeS=new ArrayList<>(),             // despres d'etiquetar els professors amb especialitat Sanitària
                                    elsDeSou1760=new ArrayList<>(),        //resultat de consultar els professors del cos de tècnics amb sou 1760
                                    elsDeE=new ArrayList<>(),        //resultat de consultar els tecnics assignats a l'Laboratori Cal Gravat
                                    elsDe2=new ArrayList<>();   // els professors l'institut amb codi 2              
    
    
    /**
     * Abans de cada test neteja la base de dades i les omplim amb les dades de prova
     * @throws GestorException 
     */
    @Before
    public void setUp() throws GestorException {
        obre();
        creaDadesPrincipals();  //omplim a memoria les llistes anteriors
        // ho esborrem tot
        
        List<Laboratori> auxLlista1=gestorLaboratori.obtenirLaboratoris();
        
        
        List<Producte> auxLlista2=gestorProducte.obtenirProductes();
        
        /*for(Laboratori b:auxLlista1){
            gestorLaboratori.eliminar(b.getId());
        }
        
        for(Producte b:auxLlista2){
            gestorProducte.eliminar(b.getId());
        }*/
        
        fesAltes();//fem l'alta de les dades de prova
        
    } 
    
    /**
     * En acabar els tests, tanca l'entityManager i la factoria d'entities manager
     */
    
    @After()
    public  void classEnds(){
        tanca();
    }
    
    
    // metodes que proven coses; abans de cadascun s'executa setUp i despres classEnds
    @Test
    public void provaConsultaTotsM(){
        assertTrue(Comparador.comparaLlistes(gestorM.obtenirMedicaments(),totsFillB,1));        
    }
    
    @Test
    public void provaConsultaTotsP(){
        assertTrue(Comparador.comparaLlistes(gestorProducte.obtenirProductes(),totsPrincipals,1));        
    }

    @Test
    public void provaEliminarP8() throws GestorException{
        gestorProducte.eliminar(8);
        assertTrue(comparaLlistes(gestorProducte.obtenirProductes(),totsExcepte8,1));
    }
    
    /**
     *
     * @throws GestorException
     */
    @Test(expected=GestorException.class)
    public void provaEliminarP88() throws GestorException{
        gestorProducte.eliminar(88);
    }

    @Test
    public void provaEliminarCont7() throws GestorException{
        gestorLaboratori.eliminar(7);    // es un institut sense cap professors assignat; amb un cap dona error d'integritat
        assertTrue(comparaLlistes(gestorLaboratori.obtenirLaboratoris(),totsContExcepte7,1));
    }
    
    @Test
    public void provaModificarP3() throws GestorException{
        Producte a=gestorProducte.obtenirProducte(3);
        a.setNom("###");  a.setPreu(1000);
        gestorProducte.modificar(a);
        assertTrue(comparaLlistes(gestorProducte.obtenirProductes(),totsModificat3,1));
    }

    /**
     *
     * @throws GestorException
     */
    @Test(expected=GestorException.class)
    public void provaModificarP88() throws GestorException{
        //Tecnics(int codi, String nom, float sou, Laboratori centre, String formacio)
        Producte a = (Producte) new Medicament(88,"###",1000, "ok", null);
        gestorProducte.modificar(a);
    }
    
    @Test
    public void provaConsultarP5() throws GestorException{
        Producte a=gestorProducte.obtenirProducte(5);
        assertTrue(compara(a,totsPrincipals.get(4),2));
    }

    @Test
    public void provaConsultarP88() throws GestorException{
        assertNull(gestorProducte.obtenirProducte(88));
    }

    @Test
    public void provaConsultarPL2() throws GestorException{
        List<Producte> d= gestorLaboratori.obtenirLaboratori(2).getProductes();
        assertTrue(comparaLlistes(d,elsDe2,2));
    }
    
    @Test
    public void provaIncrementar50() throws GestorException{
        gestorProducte.incrementarPreu(50);
          assertTrue(comparaLlistes(gestorProducte.obtenirProductes(),totsIncrementatsEl50pc,1));
    }    
    
    @Test
    public void provaConsultarPperAtribut() throws GestorException{
        assertTrue(comparaLlistes(gestorQ.obtenirQuimicPerPerillositat(7),elsDeP7,1));
    }      

    @Test
    public void provaConsultarPperAtribut2() throws GestorException{
        assertTrue(comparaLlistes(gestorM.obtenirMedicamentsPerPA("S"),elsDeS,1));
    } 
    
    @Test
    public void provaConsultarPerL() throws GestorException{
        assertTrue(comparaLlistes(gestorM.obtenirMedicamentsPerLab(5),elsDeE,1));
    } 

    @Test
    public void provaConsultarP1760() throws GestorException{
        assertTrue(comparaLlistes(gestorM.obtenirMedicamentsPerPreu(1760),elsDeSou1760,1));
    }
    
    @Test
    public void provaIncrementar720() throws GestorException{
        gestorQ.incrementarPreusSegonsPerillositat(7, 20);
        assertTrue(comparaLlistes(gestorProducte.obtenirProductes(),increment2,1));
    }
    
    
    private void fesAltes() throws GestorException {
        for(Object o:totesAltes){
            if(o instanceof Producte){
               if(gestorProducte.obtenirProducte(((Producte) o).getId())==null){
                   gestorProducte.inserir((Producte)o);
               }
            }
            if(o instanceof Laboratori){
               if(gestorLaboratori.obtenirLaboratori(((Laboratori) o).getId())==null){
                   gestorLaboratori.inserir((Laboratori)o);
               }
            }

            em.detach(o);

        }
    }
    
    //Institut (int codi, String nom)
    private  Laboratori creaA(int codi, String nom, String urbs){
          return new Laboratori(codi, nom, urbs);
    }
    
    //Secundaria(int codi, String nom, float sou, String titulacio)
    private  Quimic creaQ(int codi, String nom, float sou, int p, Laboratori centre){
        Quimic s = new Quimic(codi, nom, sou, p, centre);
        
        centre.getProductes().add(s);
        
        return s;
    }
    
    //Tecnics(int codi, String nom, float sou, String formacio)
    private  Medicament creaM(int codi, String nom, float sou, String pa, Laboratori centre){
        Medicament t = new Medicament(codi, nom, sou, pa, centre);
        
        centre.getProductes().add(t);
        
        return t;
        
    }

    private  void creaDadesPrincipals(){  // s'omplen les llistes amb les dades de prova i els resultats de les consultes
        
        //Instituts
	totsCont.add(creaA(1, "A","Tarragona"));
	totsCont.add(creaA(2, "B","Barcelona"));
	totsCont.add(creaA(3, "C","Cornellà de Llobregat"));
	totsCont.add(creaA(4, "D","Girona"));
	totsCont.add(creaA(5, "E","Manresa"));
	totsCont.add(creaA(6, "F","Lleida"));
        totsCont.add(creaA(7, "D","Tortosa"));


	//Tipus Tècnics
	totsPrincipals.add(creaM(1,"A",1760F,"I",totsCont.get(0)));
	totsPrincipals.add(creaM(2,"B",2250F,"D",totsCont.get(1)));
	totsPrincipals.add(creaM(3,"C",2050F,"P", totsCont.get(2)));
	totsPrincipals.add(creaM(4,"D",1900F,"S", totsCont.get(4)));


	//Tipus Secundaria
	totsPrincipals.add(creaQ(5,"A",1960F,1,totsCont.get(1)));
	totsPrincipals.add(creaQ(6,"B",1700F,2,totsCont.get(0)));
	totsPrincipals.add(creaQ(7,"C",1870F,3,totsCont.get(5)));
	totsPrincipals.add(creaQ(8,"D",1670F,4,totsCont.get(4)));
	totsPrincipals.add(creaQ(9,"E",1780F,5,totsCont.get(4)));
	totsPrincipals.add(creaQ(10,"F",2700F,6,totsCont.get(2)));
	totsPrincipals.add(creaQ(11,"G",2010F,7,totsCont.get(3)));
	totsPrincipals.add(creaQ(12,"H",2310F,7,totsCont.get(0)));
	totsPrincipals.add(creaQ(13,"I",1930F,8,totsCont.get(5)));
	totsPrincipals.add(creaQ(14,"J",1760F,9,totsCont.get(3)));
	totsPrincipals.add(creaQ(15,"K",2010F,10,totsCont.get(2)));
	totsPrincipals.add(creaQ(16,"L",1440F,11,totsCont.get(0)));
        
        totesAltes.addAll(totsCont);
        totesAltes.addAll(totsPrincipals);

        
        for(Producte a:totsPrincipals){
            if(a.getId()!=8){
                totsExcepte8.add((Producte) clona(a, 2));
            }
            
            if(a.getId()<=4){
                totsFillB.add((Producte) clona(a, 2));
                
            }
            if(a.getLaboratori().getId()==2){
                elsDe2.add((Producte) clona(a, 2));
                
            }
            Producte aux=(Producte) clona(a, 2);
            if(aux.getId()==3){
                aux.setNom("###");  aux.setPreu(1000);
                
            }
            
            totsModificat3.add(aux);
            totsIncrementatsEl50pc.add((Producte) clona(a, 2));
            increment2.add((Producte) clona(a, 2));
            
            if(a instanceof Quimic){
                Quimic p = (Quimic)a;
                if(p.getPerillositat()==7){
                    elsDeP7.add(p);
                }
                
            } else {
                Medicament e =(Medicament)a;
                
                if(e.getPreu()==1760) {
                    elsDeSou1760.add(e);
                    
                } else if(e.getPrincipiActiu().equals("S")) {
                    elsDeS.add(e);
                    
                }
                if (e.getLaboratori().getId()==5  ) {
                    elsDeE.add(e);

                }
            }
        } // fi del for que recorre tots els professors
        //Producte//for(Professor a:totsIncrementatsEl50pc)//for(Professor a:totsIncrementatsEl50pc)
        
                
        totsIncrementatsEl50pc.forEach((a) -> {
            a.setPreu((float)(a.getPreu()*1.5));
        });
        
        increment2.forEach((a) -> {
            if ((a instanceof Quimic)) {
                Quimic s = (Quimic)a;
                if (s.getPerillositat()==7){
                    a.setPreu((float)(a.getPreu()*1.2));
                    
                }
            }
        });
        
         for(Laboratori a:totsCont){
            if(a.getId()!=7){
                totsContExcepte7.add((Laboratori) clona(a, 2));
            }
         }
        

    }
        
        
    private void obre(){  // obrim l'EntityManager i creem els gestors
        try{
        emf = Persistence.createEntityManagerFactory(PU);
        em=emf.createEntityManager();
        
        gestorLaboratori= new GestorLaboratori(em);
        gestorProducte= new GestorProducte(em);
        gestorM= new GestorMedicament(em);
        gestorQ= new GestorQuimic(em);
        
         
        }catch(Exception e){
            System.out.println("VVVVVVVVVVVVVVVVVVVVVVVVVVVV");
            System.out.println(e.getMessage());
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        }
    }
    
    private void tanca(){  //tanquem l'EntityManager
        em.close();
        emf.close();
        
    }
    
}
