/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sio.leo.weblafbs.modele;

import java.sql.Time;

public class Seance {
    private int id;
    private Time horaire;
    private String jourSemaine;
    private String prestation;
    private String salle;

    // Constructeur vide
    public Seance() {
    }

    // Constructeur complet
    public Seance(int id, Time horaire, String jourSemaine, String prestation, String salle) {
        this.id = id;
        this.horaire = horaire;
        this.jourSemaine = jourSemaine;
        this.prestation = prestation;
        this.salle = salle;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Time getHoraire() {
        return horaire;
    }

    public void setHoraire(Time horaire) {
        this.horaire = horaire;
    }

    public String getJourSemaine() {
        return jourSemaine;
    }

    public void setJourSemaine(String jourSemaine) {
        this.jourSemaine = jourSemaine;
    }

    public String getPrestation() {
        return prestation;
    }

    public void setPrestation(String prestation) {
        this.prestation = prestation;
    }

    public String getSalle() {
        return salle;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }

    // toString pour debug rapide
    @Override
    public String toString() {
        return "Seance{" +
                "id=" + id +
                ", horaire=" + horaire +
                ", jourSemaine='" + jourSemaine + '\'' +
                ", prestation='" + prestation + '\'' +
                ", salle='" + salle + '\'' +
                '}';
    }
}
