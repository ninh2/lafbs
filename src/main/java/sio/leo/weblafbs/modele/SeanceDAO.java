/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sio.leo.weblafbs.modele;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeanceDAO {

    public List<Seance> getSeancesÀVenir() {
        List<Seance> seances = new ArrayList<>();
        String sql = "SELECT s.SEA_ID, s.SEA_HORAIRE, s.SEA_JOURSEM, p.PRE_LIBELLE, sa.SAL_NOM " +
                     "FROM seance s " +
                     "JOIN prestation p ON s.SEA_PRE_CODE = p.PRE_CODE " +
                     "JOIN salle sa ON s.SEA_SAL_ID = sa.SAL_ID " +
                     "ORDER BY s.SEA_JOURSEM, s.SEA_HORAIRE";

        try (Connection conn = daoFsb.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Seance s = new Seance();
                s.setId(rs.getInt("SEA_ID"));
                s.setHoraire(rs.getTime("SEA_HORAIRE"));
                s.setJourSemaine(rs.getString("SEA_JOURSEM"));
                s.setPrestation(rs.getString("PRE_LIBELLE"));
                s.setSalle(rs.getString("SAL_NOM"));
                seances.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seances;
    }
}
