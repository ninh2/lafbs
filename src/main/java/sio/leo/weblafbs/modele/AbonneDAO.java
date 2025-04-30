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





public class AbonneDAO {
    //pour suivre les log dans apache_tomcat/log 
    private static final Logger logger = Logger.getLogger(AbonneDAO.class.getName());
    private Connection cnx ;
    private Statement stmt;
    //ResultSet rs = stmt.executeQuery(requeteSql);
       
    public AbonneDAO() throws SQLException, NamingException{
    logger.info("**********************AbonneDAO*****************");
    cnx =  daoFsb.getConnection();
    stmt=cnx.createStatement();
    }
   


    public ArrayList<Abonne> getAllAbonnes() throws SQLException {
        ArrayList<Abonne> lesAbonnes = new ArrayList<>();
        String requeteSql = "select * from abonne";
        Integer matricule;
        String nom ;
        String prenom ;
        Date ddn ;
        String telephone ;
        String mail;
        
//            Connection cnx = daoFsb.getConnection();
//            Statement stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery(requeteSql);
       
        
         
            
        try {
            while (rs.next()) {
                matricule = rs.getInt("ABO_MATRICULE");
                nom = rs.getString("ABO_NOM");
                prenom = rs.getString("ABO_PRENOM");
                ddn = rs.getDate("ABO_DDN");
                telephone = rs.getString("ABO_TELEPHONE");
                mail = rs.getString("ABO_MAIL");

                Abonne abonne = new Abonne(matricule, nom, prenom, ddn, telephone, mail);
                lesAbonnes.add(abonne);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(AbonneDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("pbm sur rs");
        }
        return lesAbonnes;
    }
    private void close(Connection c) throws SQLException{
        c.close();
    }
    
    public Abonne getAbonneByMatricule(int matricule) throws NamingException {
        Abonne abonne = null;
        String sql = "SELECT * FROM ABONNE WHERE ABO_MATRICULE = ?";
        
        try (
             //Connection conn = daoFsb.getConnection();
            PreparedStatement pstmt = cnx.prepareStatement(sql)) {
            
            pstmt.setInt(1, matricule);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    abonne = new Abonne();
                    abonne.setMatricule(rs.getInt("ABO_MATRICULE"));
                    abonne.setNom(rs.getString("ABO_NOM"));
                    abonne.setPrenom(rs.getString("ABO_PRENOM"));
                    abonne.setDdn(rs.getDate("ABO_DDN"));
                    abonne.setTelephone(rs.getString("ABO_TELEPHONE"));
                    abonne.setMail(rs.getString("ABO_MAIL"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return abonne;
    }
    public void supprimeAbonne(int id) throws SQLException{
       
        String sql = "delete FROM ABONNE WHERE ABO_MATRICULE = ?";
       
        try (
            //Connection conn = daoFsb.getConnection();
            PreparedStatement pstmt = cnx.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate(); 
        }   
    }
    
    
    
    public void updateAbonne(Abonne AboNew) throws SQLException{
        
                String sql = "UPDATE abonne SET "
        + "ABO_MATRICULE = " + AboNew.getMatricule() + ", "
        + "ABO_NOM = '" + AboNew.getNom() + "', "
        + "ABO_PRENOM = '" + AboNew.getPrenom() + "', "
        + "ABO_DDN = '" + new java.sql.Date(AboNew.getDdn().getTime()) + "', "
        + "ABO_TELEPHONE = '" + AboNew.getTelephone() + "', "
        + "ABO_MAIL = '" + AboNew.getMail() + "' "
        + "WHERE ABO_MATRICULE = " + AboNew.getMatricule();
     
        
        logger.info(sql);
        cnx.createStatement().executeUpdate(sql);
         
    }
    
    
    
  public void ajouteAbonne(Abonne AboNew) throws SQLException {
    String sql = "INSERT INTO abonne (ABO_MATRICULE, ABO_NOM, ABO_PRENOM, ABO_DDN, ABO_TELEPHONE, ABO_MAIL) "
               + "VALUES (?, ?, ?, ?, ?, ?)";
    
    
    try (PreparedStatement stmt = cnx.prepareStatement(sql)) {
        stmt.setInt(1, AboNew.getMatricule());
        stmt.setString(2, AboNew.getNom());
        stmt.setString(3, AboNew.getPrenom());
        stmt.setDate(4, new java.sql.Date(AboNew.getDdn().getTime()));  
        stmt.setString(5, AboNew.getTelephone());
        stmt.setString(6, AboNew.getMail());
        
   
        stmt.executeUpdate();
    }
}

 

    


}

    


  
  
  

    



 
 

