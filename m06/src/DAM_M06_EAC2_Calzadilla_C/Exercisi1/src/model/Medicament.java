/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAM_M06_EAC2_Calzadilla_C.Exercisi1.src.model;

/**
 *
 * @author Isabel Calzadilla M-06
 * @version  : 10-10-2022
 */
package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Classe que representa un Objecte
 * @author professor
 */
public class Medicament {

    private int id;
    private String nomMedicament;
    private String principiActiu;
    private int dosi;
    private List<String> contraindicacions = new ArrayList<>();
    private String nomEmpresa;
    private boolean activa;
    private String domicili;

    
    public Medicament() {
    }

    public Medicament(int id, String nomMedicament, String principiActiu, int dosi, String [] contraindicacions, String nom, boolean activa, String domicili) {
        this.id = id;
        this.nomMedicament = nomMedicament;
        this.principiActiu = principiActiu;
        this.dosi = dosi;
        this.contraindicacions.addAll(Arrays.asList(contraindicacions));
        this.nomEmpresa = nom;
        this.activa = activa;
        this.domicili = domicili;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nomMedicament
     */
    public String getNomMedicament() {
        return nomMedicament;
    }

    /**
     * @param nomMedicament the nomMedicament to set
     */
    public void setNomMedicament(String nomMedicament) {
        this.nomMedicament = nomMedicament;
    }

    /**
     * @return the principiActiu
     */
    public String getPrincipiActiu() {
        return principiActiu;
    }

    /**
     * @param principiActiu the principiActiu to set
     */
    public void setPrincipiActiu(String principiActiu) {
        this.principiActiu = principiActiu;
    }

    /**
     * @return the dosi
     */
    public int getDosi() {
        return dosi;
    }

    /**
     * @param dosi the dosi to set
     */
    public void setDosi(int dosi) {
        this.dosi = dosi;
    }

    /**
     * @return the contraindicacions
     */
    public List<String> getContraindicacions() {
        return contraindicacions;
    }

    /**
     * @param contraindicacions the contraindicacions to set
     */
    public void setContraindicacions(List<String> contraindicacions) {
        this.contraindicacions = contraindicacions;
    }

    /**
     * @return the nomEmpresa
     */
    public String getNomEmpresa() {
        return nomEmpresa;
    }

    /**
     * @param nomEmpresa the nomEmpresa to set
     */
    public void setNomEmpresa(String nomEmpresa) {
        this.nomEmpresa = nomEmpresa;
    }

    /**
     * @return the activa
     */
    public boolean isActiva() {
        return activa;
    }

    /**
     * @param activa the activa to set
     */
    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    /**
     * @return the domicili
     */
    public String getDomicili() {
        return domicili;
    }

    /**
     * @param domicili the domicili to set
     */
    public void setDomicili(String domicili) {
        this.domicili = domicili;
    }

 

}

