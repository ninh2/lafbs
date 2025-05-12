/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controleur;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import sio.leo.weblafbs.modele.Hasher;
import sio.leo.weblafbs.modele.User;
import sio.leo.weblafbs.modele.UserDAO;


/**
 *
 * @author fchouch
 */
@WebServlet(name = "ConnexionS", urlPatterns = {"/connexion"})
public class ConnexionS extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDAO userDAO = null;
        User user = null;
      

        try {
            // Création de l'objet UserDAO pour interroger la base de données
            userDAO = new UserDAO();
            // Recherche de l'utilisateur avec le nom d'utilisateur
            user = userDAO.getUserByUsername(username); 
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }

        // Vérification si l'utilisateur existe et si le mot de passe est correct
        if (user != null) {
            try {
                // Utilisation de la méthode de vérification de mot de passe haché
                if (Hasher.verifyPassword(password, user.getpass())) {  // user.getPassword() est le mot de passe stocké dans la base
                    // Si les mots de passe correspondent, l'utilisateur est authentifié
                    request.getSession().setAttribute("role", user.getrole());
                    request.getSession().setAttribute("user", user);
                    
             
                    
                    
                    // Redirection vers la page d'accueil après connexion
                   request.getRequestDispatcher("/WEB-INF/vue/roleBridge.jsp").forward(request, response);

                } else {
                    // Si les mots de passe ne correspondent pas, afficher un message d'erreur
                    request.setAttribute("error", "Identifiants incorrects");
                    request.getRequestDispatcher("/WEB-INF/vue/Connexion.jsp").forward(request, response);
                }
            } catch (Exception e) {
                e.printStackTrace();
                // En cas d'erreur pendant la vérification du mot de passe, afficher une erreur
                request.setAttribute("error", "Erreur lors de la vérification du mot de passe");
                request.getRequestDispatcher("/WEB-INF/vue/Connexion.jsp").forward(request, response);
            }
        } else {
            // Si l'utilisateur n'est pas trouvé, afficher un message d'erreur
            request.setAttribute("error", "Identifiants incorrects");
            request.getRequestDispatcher("/WEB-INF/vue/Connexion.jsp").forward(request, response);
        }
        
        // Lorsque l'utilisateur se connecte avec succès :
   

        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lorsque la page de connexion est demandée par GET, afficher la page de connexion
        request.getRequestDispatcher("/WEB-INF/vue/Connexion.jsp").forward(request, response);
    }
}


