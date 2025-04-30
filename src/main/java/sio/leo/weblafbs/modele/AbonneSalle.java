/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sio.leo.weblafbs.modele;

/**
 *
 * @author fchouch
 */

public class AbonneSalle {
    
    private int sab_ABO_MATRICULE;
    private String sab_SAL_ID;

/** /*
 * 
 * @param sab_ABO_MATRICULE
 * @param sab_SAL_ID 
 */
    public AbonneSalle(int sab_ABO_MATRICULE, String sab_SAL_ID) {
        this.sab_ABO_MATRICULE = sab_ABO_MATRICULE;
        this.sab_SAL_ID = sab_SAL_ID;
    };

    public int getSab_ABO_MATRICULE() {
        return sab_ABO_MATRICULE;
    }

    public String getSab_SAL_ID() {
        return sab_SAL_ID;
    }

    public void setSab_ABO_MATRICULE(int sab_ABO_MATRICULE) {
        this.sab_ABO_MATRICULE = sab_ABO_MATRICULE;
    }

    public void setSab_SAL_ID(String sab_SAL_ID) {
        this.sab_SAL_ID = sab_SAL_ID;
    }
    
}