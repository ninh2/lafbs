package sio.leo.weblafbs.modele;


import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Corinne
 */
public class Abonne {
    private int matricule;
    private String nom;
    private String prenom;
    private Date ddn; // Date de naissance
    private String telephone;
    private String mail;

    // Constructeur
    public Abonne(int matricule, String nom, String prenom, Date ddn, String telephone, String mail) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.ddn = ddn;
        this.telephone = telephone;
        this.mail = mail;
    }
    public Abonne(){
        
    }
    public int getMatricule() {
        return matricule;
    }
    public void setMatricule(int matricule) {
        this.matricule = matricule;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public Date getDdn() {
        return ddn;
    }
    public void setDdn(Date ddn) {
        this.ddn = ddn;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public int getAge() {
        if (ddn == null) {
            return 0; // Retourner 0 si la date de naissance est nulle
        }

        // Conversion date de naissance (ddn) en LocalDate
        LocalDate birthDate = new java.sql.Date(ddn.getTime()).toLocalDate();
        // date actuelle
        LocalDate currentDate = LocalDate.now();
        // Calcul différence entre la date de naissance et la date actuelle
        Period period = Period.between(birthDate, currentDate);

        // Retourner l'âge en années
        return period.getYears();
    }
    
    @Override
    public String toString(){
        return this.matricule + "  " + this.nom + "  " + this.prenom;
    }
    
}
