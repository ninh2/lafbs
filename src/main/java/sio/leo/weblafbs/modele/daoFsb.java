package sio.leo.weblafbs.modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class daoFsb {
    private static Connection cnxFsb = null;

    public static Connection getConnection() throws SQLException {
        if (cnxFsb == null || cnxFsb.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver"); // Charge le driver MySQL
            } catch (ClassNotFoundException e) {
                throw new SQLException("Driver JDBC non trouvé", e);
            }

            String url = "jdbc:mysql://localhost:3306/lafbs"; // change "nom_de_ta_base"
            String utilisateur = "root"; // change par ton user phpMyAdmin, ex: "root"
            String motDePasse = "";    // change par ton mot de passe

            cnxFsb = DriverManager.getConnection(url, utilisateur, motDePasse);
        }
        return cnxFsb;
    }
}

