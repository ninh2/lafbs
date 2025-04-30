/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sio.leo.weblafbs.modele;

/**
 *
 * @author Corinne
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class AbonneDAO2 {
    private static final String URL = "jdbc:mysql://localhost:3307/lafbs";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final Logger logger = Logger.getLogger(AbonneDAO.class.getName());

    public List<Abonne> getAllAbonnes() {
        List<Abonne> abonnes = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
//logger.info("Loading driver...");
            // Chargement du driver MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
//logger.info("Connecting to database...");
            // Établir la connexion
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
//logger.info("Creating statement...");
            // Préparer la requête SQL
            String sql = "SELECT abo_matricule, abo_nom, abo_prenom FROM abonne";
            preparedStatement = connection.prepareStatement(sql);
//logger.info("Executing query...");
            // Exécuter la requête
            resultSet = preparedStatement.executeQuery();

            // Traiter les résultats
            while (resultSet.next()) {
                int matricule = resultSet.getInt("abo_matricule");
                String nom = resultSet.getString("abo_nom");
                String prenom = resultSet.getString("abo_prenom");

                Abonne abonne = new Abonne();
                abonne.setMatricule(matricule);
                abonne.setNom(nom);
                abonne.setPrenom(prenom);
logger.info(abonne.toString());
                abonnes.add(abonne);
            }
//logger.info("Query executed successfully.");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // Fermeture des ressources
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return abonnes;
    }
}
