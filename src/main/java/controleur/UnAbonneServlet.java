/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controleur;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import sio.leo.weblafbs.modele.Abonne;
import sio.leo.weblafbs.modele.AbonneDAO;

/**
 *
 * @author Corinne
 */
@WebServlet(name = "srvUnAbonne", urlPatterns = {"/unabo"})
public class UnAbonneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Integer matricule =null; 
        try {
            // Récupérer le matricule de la requête et le convertir en int pour rechercher l'abonné correspondant
            Integer matricule = Integer.valueOf(request.getParameter("matricule"));
            
            AbonneDAO abonneDao = new AbonneDAO();
            Abonne UnAbonne = abonneDao.getAbonneByMatricule(matricule); 
            // Ajoute l'abonné aux attributs de la requête
            request.setAttribute("abonne", UnAbonne);
            //Ajout de de l'âge
            request.setAttribute("age", UnAbonne.getAge());
            // Rediriger vers une page JSP pour afficher les informations détaillées
            request.getRequestDispatcher("WEB-INF/vue/jspUnAbonne.jsp").forward(request, response);
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(UnAbonneServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
    }

}

