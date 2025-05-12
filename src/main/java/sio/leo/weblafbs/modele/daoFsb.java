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
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author corinne
 */
public class daoFsb {
    //Pour connecter mariadb et la base lardon
    private static Connection cnxFsb=null;
    
    private static InitialContext initialcontext ;
    private static DataSource datasource ;
    private static Connection cnxFbs ;
    
    public static Connection getConnection() throws NamingException, SQLException{
        // Récupérer le contexte JNDI
        initialcontext = new InitialContext();
        
        // Rechercher la ressource DataSource configurée dans context.xml
         datasource = (DataSource) initialcontext.lookup("java:/comp/env/jdbc/MaSourceDeDonnees");
        //effectue la conexion à la base 
        cnxFbs=datasource.getConnection();
        // Retourner la connexion à la base de données
        return cnxFbs;
    }   
}

