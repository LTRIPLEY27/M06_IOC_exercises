

package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Isabel Calzadilla M-06
 * @version  : 17-11-2022
 * @Resum : Activitat 2, M06 UF3
 */
@XmlRootElement
public class Medicament {

    private int id;
    private String nomMedicament;
    private String principiActiu;
    private int dosi;
    private final ArrayList<String> contraindicacions = new ArrayList<>();
    private String laboratori;

    
    public Medicament() {
    }

    public Medicament(int id, String n, String c, int num, String [] llista, String t1) {
        this.id = id;
        this.nomMedicament = n;
        this.principiActiu = c;
        this.dosi = num;
        this.contraindicacions.addAll(Arrays.asList(llista));
        this.laboratori = t1;
    }

    @XmlElement
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement
    public String getNomMedicament() {
        return nomMedicament;
    }

    public void setNomMedicament(String n) {
        this.nomMedicament = n;
    }

    @XmlElement
    public String getPrincipiActiu() {
        return principiActiu;
    }

    public void setPrincipiActiu(String principiActiu) {
        this.principiActiu = principiActiu;
    }

    @XmlElement
    public int getDosi() {
        return dosi;
    }

    public void setDosi(int dosi) {
        this.dosi = dosi;
    }

    @XmlElement
    public String getLaboratori() {
        return laboratori;
    }

    public void setLaboratori(String nom) {
        this.laboratori = nom;
    }

    @XmlElementWrapper(name = "contraindicacions")
    @XmlElement(name = "contraindicacio")
    public ArrayList<String> getContraindicacions() {
            return contraindicacions;
    }

    public void setContraindicacions(List<String> pc) {
            this.contraindicacions.clear();
            this.contraindicacions.addAll(pc);
    }

    
    

}
