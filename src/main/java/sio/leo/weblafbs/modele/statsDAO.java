package sio.leo.weblafbs.modele;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

public class statsDAO {

    // Statistiques sur les inscriptions par activité
    public List<String[]> getInscriptionsParActivite(String dateDebut, String dateFin) throws NamingException {
        List<String[]> resultats = new ArrayList<>();
        String sql = "SELECT p.PRE_LIBELLE AS nom_activite, COUNT(*) AS nb_inscriptions " +
                     "FROM inscription i " +
                     "JOIN seance s ON i.INS_SEA_ID = s.SEA_ID " +
                     "JOIN prestation p ON s.SEA_PRE_CODE = p.PRE_CODE " +
                     "WHERE i.INS_DATE BETWEEN ? AND ? " +
                     "GROUP BY p.PRE_LIBELLE";

        try (Connection conn = daoFsb.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dateDebut);
            ps.setString(2, dateFin);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    resultats.add(new String[]{
                        rs.getString("nom_activite"),
                        String.valueOf(rs.getInt("nb_inscriptions"))
                    });
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(statsDAO.class.getName()).log(Level.SEVERE, "Erreur SQL dans getInscriptionsParActivite", e);
        }
        return resultats;
    }

    // Statistiques sur les abonnements par type et salle
    public List<String[]> getAbonnementsParTypeEtSalle(String dateDebut, String dateFin) throws NamingException {
        List<String[]> resultats = new ArrayList<>();
        String sql = "SELECT t.TAB_LIBELLE AS libelle_type, s.SAL_NOM AS nom_salle, COUNT(*) AS nb_abonnes " +
                     "FROM abonnement a " +
                     "JOIN typeabonnement t ON a.AB_TAB_CODE = t.TAB_CODE " +
                     "JOIN salle s ON a.AB_SAL_ID = s.SAL_ID " +
                     "WHERE a.AD_DATE BETWEEN ? AND ? " +
                     "GROUP BY t.TAB_LIBELLE, s.SAL_NOM";

        try (Connection conn = daoFsb.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dateDebut);
            ps.setString(2, dateFin);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    resultats.add(new String[]{
                        rs.getString("libelle_type"),
                        rs.getString("nom_salle"),
                        String.valueOf(rs.getInt("nb_abonnes"))
                    });
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(statsDAO.class.getName()).log(Level.SEVERE, "Erreur SQL dans getAbonnementsParTypeEtSalle", e);
        }
        return resultats;
    }

    // Statistiques sur les présences aux activités
    public List<String[]> getPresencesActivites(String dateDebut, String dateFin) throws NamingException {
        List<String[]> resultats = new ArrayList<>();
        String sql = "SELECT p.PRE_LIBELLE AS nom_activite, COUNT(*) AS nb_presences " +
                     "FROM inscription i " +
                     "JOIN seance s ON i.INS_SEA_ID = s.SEA_ID " +
                     "JOIN prestation p ON s.SEA_PRE_CODE = p.PRE_CODE " +
                     "WHERE i.INS_PRESENT = 1 AND i.INS_DATE BETWEEN ? AND ? " +
                     "GROUP BY p.PRE_LIBELLE";

        try (Connection conn = daoFsb.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dateDebut);
            ps.setString(2, dateFin);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    resultats.add(new String[]{
                        rs.getString("nom_activite"),
                        String.valueOf(rs.getInt("nb_presences"))
                    });
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(statsDAO.class.getName()).log(Level.SEVERE, "Erreur SQL dans getPresencesActivites", e);
        }
        return resultats;
    }

    // Statistiques sur la fréquentation par salle
    public List<String[]> getFrequentationParSalle(String dateDebut, String dateFin) throws NamingException {
        List<String[]> resultats = new ArrayList<>();
        String sql = "SELECT s.SAL_NOM AS nom_salle, COUNT(*) AS nb_frequentation " +
                     "FROM inscription i " +
                     "JOIN seance se ON i.INS_SEA_ID = se.SEA_ID " +
                     "JOIN salle s ON se.SEA_SAL_ID = s.SAL_ID " +
                     "WHERE i.INS_PRESENT = 1 AND i.INS_DATE BETWEEN ? AND ? " +
                     "GROUP BY s.SAL_NOM";

        try (Connection conn = daoFsb.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dateDebut);
            ps.setString(2, dateFin);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    resultats.add(new String[]{
                        rs.getString("nom_salle"),
                        String.valueOf(rs.getInt("nb_frequentation"))
                    });
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(statsDAO.class.getName()).log(Level.SEVERE, "Erreur SQL dans getFrequentationParSalle", e);
        }
        return resultats;
    }

    // Statistiques sur les absences aux activités
    public List<String[]> getTauxAbsenceActivites(String dateDebut, String dateFin) throws NamingException {
        List<String[]> resultats = new ArrayList<>();
        String sql = "SELECT p.PRE_LIBELLE AS nom_activite, " +
                     "SUM(CASE WHEN i.INS_PRESENT = 0 THEN 1 ELSE 0 END) AS nb_absences, " +
                     "COUNT(*) AS total_inscriptions, " +
                     "ROUND((SUM(CASE WHEN i.INS_PRESENT = 0 THEN 1 ELSE 0 END) * 100.0) / COUNT(*), 2) AS taux_absence " +
                     "FROM inscription i " +
                     "JOIN seance s ON i.INS_SEA_ID = s.SEA_ID " +
                     "JOIN prestation p ON s.SEA_PRE_CODE = p.PRE_CODE " +
                     "WHERE i.INS_DATE BETWEEN ? AND ? " +
                     "GROUP BY p.PRE_LIBELLE";

        try (Connection conn = daoFsb.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dateDebut);
            ps.setString(2, dateFin);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    resultats.add(new String[]{
                        rs.getString("nom_activite"),
                        rs.getString("nb_absences"),
                        rs.getString("total_inscriptions"),
                        rs.getString("taux_absence") + "%"
                    });
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(statsDAO.class.getName()).log(Level.SEVERE, "Erreur SQL dans getTauxAbsenceActivites", e);
        }
        return resultats;
    }
    
    
    
    // Méthode pour récupérer tous les abonnements avec détails
    public List<String[]> getTousLesAbonnements() throws NamingException {
        List<String[]> abonnements = new ArrayList<>();
        String sql = "SELECT a.ABO_NOM, a.ABO_PRENOM, ab.AB_ID, t.TAB_LIBELLE, s.SAL_NOM " +
                     "FROM abonnement ab " +
                     "JOIN abonne a ON ab.AB_ABO_MATRICULE = a.ABO_MATRICULE " +
                     "JOIN typeabonnement t ON ab.AB_TAB_CODE = t.TAB_CODE " +
                     "JOIN salle s ON ab.AB_SAL_ID = s.SAL_ID";

        try (Connection conn = daoFsb.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                abonnements.add(new String[]{
                    rs.getString("ABO_NOM"),
                    rs.getString("ABO_PRENOM"),
                    String.valueOf(rs.getInt("AB_ID")),
                    rs.getString("TAB_LIBELLE"),
                    rs.getString("SAL_NOM")
                });
            }
        } catch (SQLException e) {
            Logger.getLogger(statsDAO.class.getName()).log(Level.SEVERE, "Erreur SQL dans getTousLesAbonnements", e);
        }
        return abonnements;
    }

    // Méthode pour récupérer tous les types d'abonnement
    public List<String[]> getTousLesTypesAbonnement() throws NamingException {
        List<String[]> types = new ArrayList<>();
        String sql = "SELECT TAB_CODE, TAB_LIBELLE FROM typeabonnement";

        try (Connection conn = daoFsb.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                types.add(new String[]{
                    rs.getString("TAB_CODE"),
                    rs.getString("TAB_LIBELLE")
                });
            }
        } catch (SQLException e) {
            Logger.getLogger(statsDAO.class.getName()).log(Level.SEVERE, "Erreur SQL dans getTousLesTypesAbonnement", e);
        }
        return types;
    }

    // Méthode pour récupérer toutes les salles
    public List<String[]> getToutesLesSalles() throws NamingException {
        List<String[]> salles = new ArrayList<>();
        String sql = "SELECT SAL_ID, SAL_NOM FROM salle";

        try (Connection conn = daoFsb.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                salles.add(new String[]{
                    rs.getString("SAL_ID"),
                    rs.getString("SAL_NOM")
                });
            }
        } catch (SQLException e) {
            Logger.getLogger(statsDAO.class.getName()).log(Level.SEVERE, "Erreur SQL dans getToutesLesSalles", e);
        }
        return salles;
    }

    // Méthode pour mettre à jour un abonnement
    public boolean mettreAJourAbonnement(int abonnementId, String nouveauType, String nouvelleSalle) throws NamingException {
        String sql = "UPDATE abonnement SET AB_TAB_CODE = ?, AB_SAL_ID = ? WHERE AB_ID = ?";
        try (Connection conn = daoFsb.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nouveauType);
            ps.setString(2, nouvelleSalle);
            ps.setInt(3, abonnementId);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            Logger.getLogger(statsDAO.class.getName()).log(Level.SEVERE, "Erreur SQL dans mettreAJourAbonnement pour AB_ID " + abonnementId, e);
            return false;
        }
    }
    
    
    
    
}