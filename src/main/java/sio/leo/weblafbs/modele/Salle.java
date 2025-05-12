package sio.leo.weblafbs.modele;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

public class Salle {
    private String id;
    private String nom;
    private String adrRue;
    private String adrVille;
    private String telephone;

    // Getters et setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getAdrRue() { return adrRue; }
    public void setAdrRue(String adrRue) { this.adrRue = adrRue; }

    public String getAdrVille() { return adrVille; }
    public void setAdrVille(String adrVille) { this.adrVille = adrVille; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    // Méthode pour obtenir toutes les salles
    public static List<Salle> getAllSalles() throws SQLException, NamingException {
        List<Salle> salles = new ArrayList<>();
        String query = "SELECT * FROM SALLE";

        try (Connection conn = daoFsb.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Salle salle = new Salle();
                salle.setId(rs.getString("SAL_ID"));
                salle.setNom(rs.getString("SAL_NOM"));
                salle.setAdrRue(rs.getString("SAL_ADRRUE"));
                salle.setAdrVille(rs.getString("SAL_ADVILLE"));
                salle.setTelephone(rs.getString("SAL_TELEPHONE"));
                salles.add(salle);
            }
        }

        return salles;
    }

    // Méthode pour obtenir une salle par son ID
    public static Salle getSalleById(String id) throws SQLException, NamingException {
        String query = "SELECT * FROM SALLE WHERE SAL_ID = ?";
        try (Connection conn = daoFsb.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Salle salle = new Salle();
                    salle.setId(rs.getString("SAL_ID"));
                    salle.setNom(rs.getString("SAL_NOM"));
                    salle.setAdrRue(rs.getString("SAL_ADRRUE"));
                    salle.setAdrVille(rs.getString("SAL_ADVILLE"));
                    salle.setTelephone(rs.getString("SAL_TELEPHONE"));
                    return salle;
                }
            }
        }
        return null; // Return null if no salle is found
    }
}