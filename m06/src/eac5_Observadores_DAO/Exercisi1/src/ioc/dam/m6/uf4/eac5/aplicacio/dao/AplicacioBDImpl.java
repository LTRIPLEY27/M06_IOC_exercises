/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m6.uf4.eac5.aplicacio.dao;

import ioc.dam.m6.uf4.eac5.aplicacio.Districte;
import ioc.dam.m6.uf4.eac5.aplicacio.Barri;
import ioc.dam.m6.persistencia.excepcions.UtilitatPersistenciaException;
import java.util.List;
import ioc.dam.m6.uf4.eac5.aplicacio.AplicacioBD;

/**
 *
 * @author josep
 */
public class AplicacioBDImpl extends AplicacioBDJdbc     implements AplicacioBD{
    DistricteDao districteDao;
    
    BarriDao barriDao;
 
    
    @Override
    public void obrir() throws UtilitatPersistenciaException {
        super.obrir();
        districteDao = new DistricteDao(con);
 
        barriDao = new BarriDao(con);
     
    }
    
    @Override
    public void emmagatzemar(Districte establiment) 
                        throws UtilitatPersistenciaException {
        districteDao.emmagatzemar(establiment);
    }

    @Override
    public void emmagatzemar(Barri empleat) throws UtilitatPersistenciaException {
        barriDao.emmagatzemar(empleat);
    }


    @Override
    public void eliminar(Districte establiment) throws UtilitatPersistenciaException {
        districteDao.eliminar(establiment);
    }

    @Override
    public void eliminar(Barri empleat) throws UtilitatPersistenciaException {
        barriDao.eliminar(empleat);
    }


    @Override
    public Districte refrescar(Districte establiment) throws UtilitatPersistenciaException {
        return districteDao.refrescar(establiment);
    }

    @Override
    public Barri refrescar(Barri empleat) throws UtilitatPersistenciaException {
        return barriDao.refrescar(empleat);
    }


    @Override
    public List<Districte> obtenirDistrictes()  
                                throws UtilitatPersistenciaException {
        return districteDao.obtenirTot();
    }

    @Override
    public List<Barri> obtenirBarris()  
                                throws UtilitatPersistenciaException {
        return barriDao.obtenirTot();
    }


    @Override
    public List<Barri> obtenirBarrisPerDistricte(int numDistricte) throws UtilitatPersistenciaException {
        return barriDao.obtenirBarrisPerDistricte(numDistricte);
    }


    @Override
    public List<Barri> obtenirBarrisPerDensitat(float densInferior, float densSuperior) throws UtilitatPersistenciaException {
        return this.barriDao.obtenirBarrisPerDensitat(densInferior, densSuperior);
    }

    @Override
    public List<Districte> obtenirDistrictesPerHabitants(int habitants) throws UtilitatPersistenciaException {
        return districteDao.obtenirDistrictesPerHabitants(habitants);
    }




}
