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
public class User {
    private int id;
    private String nom;
    private String pass;
    private String  role; 
   

    // Constructeur
    public User(int id, String nom, String pass,String role) {
        this.id = id;
        this.nom = nom;
        this.pass = pass;
        this.role = role;      
    }
    public User(){
        
    }
    public int getID() {
        return id;
    }
    public void setId(int matricule) {
        this.id = matricule;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getpass() {
        return pass;
    }
    public void setpass(String pass) {
        this.pass = pass;
    }
 
    public String getrole() {
        return role;
    }
    public void setrole(String role) {
        this.role = role;
    }
    
    

    
}
