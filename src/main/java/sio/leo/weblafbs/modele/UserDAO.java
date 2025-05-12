package sio.leo.weblafbs.modele;

/**
 *
 * @author Corinne
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import sio.leo.weblafbs.modele.User;

public class UserDAO {
    //pour suivre les log dans apache_tomcat/log 
    private static final Logger logger = Logger.getLogger(UserDAO.class.getName());
    private Connection cnx;
    private Statement stmt;

    public UserDAO() throws SQLException, NamingException {
        logger.info("**********************UserDAO*****************");
        cnx = daoFsb.getConnection();
        stmt = cnx.createStatement();
    }

    public ArrayList<User> getAllUsers() throws SQLException {
        ArrayList<User> users = new ArrayList<>();
        String requeteSql = "select * from user"; // Table name is now "user"
        Integer id;
        String nom;
        String pass;
        String role;

        ResultSet rs = stmt.executeQuery(requeteSql);

        try {
            while (rs.next()) {
                id = rs.getInt("use_id");
                nom = rs.getString("use_nom");
                pass = rs.getString("use_pass");
                role = rs.getString("use_role");

                User user = new User(id, nom, pass, role);
                users.add(user);
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
            System.out.println("pbm sur rs");
        }
        return users;
    }

    private void close(Connection c) throws SQLException {
        c.close();
    }

    public User getUserById(int id) throws NamingException {
        User user = null;
        String sql = "SELECT * FROM user WHERE use_id = ?"; // Table name is now "user"

        try (PreparedStatement pstmt = cnx.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("use_id"));
                    user.setNom(rs.getString("use_nom"));
                    user.setpass(rs.getString("use_pass"));
                    user.setrole(rs.getString("use_role"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void deleteUser(int id) throws SQLException {
        String sql = "DELETE FROM user WHERE use_id = ?"; // Table name is now "user"

        try (PreparedStatement pstmt = cnx.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    public void updateUser(User user) throws SQLException {
        String sql = "UPDATE user SET "  // Table name is now "user"
                + "use_id = " + user.getID() + ", "
                + "use_nom = '" + user.getNom() + "', "
                + "use_pass = '" + user.getpass() + "', "
                + "use_role = '" + user.getrole() + "' "
                + "WHERE use_id = " + user.getID();

        logger.info(sql);
        cnx.createStatement().executeUpdate(sql);
    }

    public void addUser(User newUser) throws SQLException {
        String sql = "INSERT INTO user (use_id, use_nom, use_pass, use_role) " // Table name is now "user"
                + "VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = cnx.prepareStatement(sql)) {
            stmt.setInt(1, newUser.getID());
            stmt.setString(2, newUser.getNom());
            stmt.setString(3, newUser.getpass());
            stmt.setString(4, newUser.getrole());

            stmt.executeUpdate();
        }
    }

   

public User getUserByUsername(String username) throws SQLException {
    User user = null;
    String sql = "SELECT * FROM user WHERE use_nom = ?"; 

    try (PreparedStatement pstmt = cnx.prepareStatement(sql)) {
        pstmt.setString(1, username);  
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                // Créez un nouvel utilisateur avec les données récupérées
                user = new User();
                user.setId(rs.getInt("use_id"));
                user.setNom(rs.getString("use_nom"));
                user.setpass(rs.getString("use_pass"));
                user.setrole(rs.getString("use_role"));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return user;
}

    

    
}
