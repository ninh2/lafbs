package sio.leo.weblafbs.modele;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

public class SeanceDAO {
    private static final Logger logger = Logger.getLogger(SeanceDAO.class.getName());

    // Nouvelle méthode pour récupérer les abonnements associés à une prestation
    public List<String> getAbonnementsForPrestation(String preCode) throws SQLException, NamingException {
        List<String> abonnements = new ArrayList<>();
        String sql = "SELECT t.TAB_LIBELLE " +
                     "FROM typeabonnement t " +
                     "JOIN prestationabonnement pa ON t.TAB_CODE = pa.APR_TAB_CODE " +
                     "WHERE pa.APR_PRE_CODE = ?";
        try (Connection conn = daoFsb.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, preCode);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    abonnements.add(rs.getString("TAB_LIBELLE"));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erreur SQL lors de la récupération des abonnements", e);
            throw e;
        }
        return abonnements;
    }

    // Mise à jour de getSeancesÀVenir pour inclure les abonnements
    public List<Seance> getSeancesÀVenir() {
        List<Seance> seances = new ArrayList<>();
        String sql = "SELECT s.SEA_ID, s.SEA_HORAIRE, s.SEA_JOURSEM, s.SEA_PRE_CODE, s.SEA_SAL_ID, s.SEA_NBPLACE, " +
                     "p.PRE_LIBELLE, sa.SAL_NOM " +
                     "FROM seance s " +
                     "JOIN prestation p ON s.SEA_PRE_CODE = p.PRE_CODE " +
                     "JOIN salle sa ON s.SEA_SAL_ID = sa.SAL_ID " +
                     "ORDER BY s.SEA_JOURSEM, s.SEA_HORAIRE";

        try (Connection conn = daoFsb.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            logger.log(Level.INFO, "Exécution de la requête SQL : {0}", sql);
            int count = 0;
            while (rs.next()) {
                Seance s = new Seance();
                s.setId(rs.getInt("SEA_ID"));
                s.setHoraire(rs.getTime("SEA_HORAIRE"));
                s.setJourSemaine(rs.getString("SEA_JOURSEM"));
                s.setPrestation(rs.getString("PRE_LIBELLE"));
                s.setSalle(rs.getString("SAL_NOM"));
                s.setPrestationCode(rs.getString("SEA_PRE_CODE"));
                s.setSalleId(rs.getString("SEA_SAL_ID"));
                s.setNbPlaces(rs.getInt("SEA_NBPLACE"));
                // Ajout des abonnements requis
                s.setAbonnementsRequis(getAbonnementsForPrestation(s.getPrestationCode()));
                seances.add(s);
                count++;
                logger.log(Level.FINE, "Séance chargée : {0}", s.toString());
            }
            logger.log(Level.INFO, "Nombre de séances récupérées : {0}", count);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erreur SQL lors de l'exécution de la requête", e);
            throw new RuntimeException("Erreur lors de la récupération des séances", e);
        } catch (NamingException e) {
            logger.log(Level.SEVERE, "Erreur de configuration JNDI pour la connexion", e);
            throw new RuntimeException("Erreur de connexion à la base de données", e);
        }
        return seances;
    }

    // Mise à jour de getSeanceById pour inclure les abonnements
    public Seance getSeanceById(int id) {
        Seance seance = null;
        String sql = "SELECT s.SEA_ID, s.SEA_HORAIRE, s.SEA_JOURSEM, s.SEA_PRE_CODE, s.SEA_SAL_ID, s.SEA_NBPLACE, " +
                     "p.PRE_LIBELLE, sa.SAL_NOM " +
                     "FROM seance s " +
                     "JOIN prestation p ON s.SEA_PRE_CODE = p.PRE_CODE " +
                     "JOIN salle sa ON s.SEA_SAL_ID = sa.SAL_ID " +
                     "WHERE s.SEA_ID = ?";

        try (Connection conn = daoFsb.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    seance = new Seance();
                    seance.setId(rs.getInt("SEA_ID"));
                    seance.setHoraire(rs.getTime("SEA_HORAIRE"));
                    seance.setJourSemaine(rs.getString("SEA_JOURSEM"));
                    seance.setPrestation(rs.getString("PRE_LIBELLE"));
                    seance.setSalle(rs.getString("SAL_NOM"));
                    seance.setPrestationCode(rs.getString("SEA_PRE_CODE"));
                    seance.setSalleId(rs.getString("SEA_SAL_ID"));
                    seance.setNbPlaces(rs.getInt("SEA_NBPLACE"));
                    // Ajout des abonnements requis
                    seance.setAbonnementsRequis(getAbonnementsForPrestation(seance.getPrestationCode()));
                    logger.log(Level.FINE, "Séance récupérée : {0}", seance.toString());
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erreur SQL lors de la récupération de la séance", e);
            throw new RuntimeException("Erreur lors de la récupération de la séance", e);
        } catch (NamingException e) {
            logger.log(Level.SEVERE, "Erreur de configuration JNDI pour la connexion", e);
            throw new RuntimeException("Erreur de connexion à la base de données", e);
        }
        return seance;
    }

    // Les autres méthodes (updateSeance, getAllPrestations, getAllSalles, supprimerSeance, ajouterSeance) restent inchangées
  public void updateSeance(Seance seance) throws NamingException {
    String sql = "UPDATE seance SET SEA_HORAIRE = ?, SEA_JOURSEM = ?, SEA_PRE_CODE = ?, SEA_SAL_ID = ?, SEA_NBPLACE = ? WHERE SEA_ID = ?";

    try (Connection conn = daoFsb.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setTime(1, seance.getHoraire());
        ps.setString(2, seance.getJourSemaine());
        ps.setString(3, seance.getPrestationCode());
        ps.setString(4, seance.getSalleId());
        ps.setInt(5, seance.getNbPlaces());
        ps.setInt(6, seance.getId());
        
        int rowsAffected = ps.executeUpdate();
        if (rowsAffected > 0) {
            logger.log(Level.INFO, "Séance mise à jour avec succès.");
        } else {
            logger.log(Level.WARNING, "Aucune séance mise à jour.");
        }
    } catch (SQLException e) {
        logger.log(Level.SEVERE, "Erreur lors de la mise à jour de la séance", e);
        throw new RuntimeException("Erreur lors de la mise à jour de la séance", e);
    }
}


    public List<Prestation> getAllPrestations() throws SQLException, NamingException {
        List<Prestation> prestations = new ArrayList<>();
        String sql = "SELECT PRE_CODE, PRE_LIBELLE FROM prestation";
        try (Connection conn = daoFsb.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Prestation prestation = new Prestation();
                prestation.setCode(rs.getString("PRE_CODE"));
                prestation.setLibelle(rs.getString("PRE_LIBELLE"));
                prestations.add(prestation);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur récupération prestations", e);
        }
        return prestations;
    }

    public List<Salle> getAllSalles() throws SQLException, NamingException {
        List<Salle> salles = new ArrayList<>();
        String sql = "SELECT SAL_ID, SAL_NOM FROM salle";
        try (Connection conn = daoFsb.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Salle salle = new Salle();
                salle.setId(rs.getString("SAL_ID"));
                salle.setNom(rs.getString("SAL_NOM"));
                salles.add(salle);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur récupération salles", e);
        }
        return salles;
    }

    public void supprimerSeance(int id) throws SQLException, NamingException {
        String sql = "DELETE FROM seance WHERE SEA_ID = ?";

        try (Connection conn = daoFsb.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            logger.log(Level.INFO, "Séance supprimée, lignes affectées : {0}", rowsAffected);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erreur SQL lors de la suppression de la séance", e);
            throw e;
        } catch (NamingException e) {
            logger.log(Level.SEVERE, "Erreur de configuration JNDI pour la suppression", e);
            throw e;
        }
    }

    public void ajouterSeance(Seance seance) throws SQLException, NamingException {
        String sql = "INSERT INTO seance (SEA_HORAIRE, SEA_JOURSEM, SEA_PRE_CODE, SEA_SAL_ID, SEA_NBPLACE) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = daoFsb.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setTime(1, seance.getHoraire());
            ps.setString(2, seance.getJourSemaine());
            ps.setString(3, seance.getPrestationCode());
            ps.setString(4, seance.getSalleId());
            ps.setInt(5, seance.getNbPlaces());

            int rows = ps.executeUpdate();
            logger.log(Level.INFO, "Séance ajoutée, lignes affectées : {0}", rows);
        }
    }
}