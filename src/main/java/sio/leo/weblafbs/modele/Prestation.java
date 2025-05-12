package sio.leo.weblafbs.modele;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

public class Prestation {
    private String code;
    private String libelle;

    // Getters et setters
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }

    // Méthode pour obtenir toutes les prestations
    public static List<Prestation> getAllPrestations() throws SQLException, NamingException {
        List<Prestation> prestations = new ArrayList<>();
        String query = "SELECT * FROM PRESTATION";

        try (Connection conn = daoFsb.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Prestation prestation = new Prestation();
                prestation.setCode(rs.getString("PRE_CODE"));
                prestation.setLibelle(rs.getString("PRE_LIBELLE"));
                prestations.add(prestation);
            }
        }

        return prestations;
    }

    // Méthode pour obtenir une prestation par son code
    public static Prestation getPrestationByCode(String code) throws SQLException, NamingException {
        String query = "SELECT * FROM PRESTATION WHERE PRE_CODE = ?";
        try (Connection conn = daoFsb.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, code);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Prestation prestation = new Prestation();
                    prestation.setCode(rs.getString("PRE_CODE"));
                    prestation.setLibelle(rs.getString("PRE_LIBELLE"));
                    return prestation;
                }
            }
        }
        return null; // Retourne null si aucune prestation n'a été trouvée
    }
}