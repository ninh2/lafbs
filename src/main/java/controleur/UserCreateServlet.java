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
import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import sio.leo.weblafbs.modele.Abonne;
import sio.leo.weblafbs.modele.AbonneDAO;
import sio.leo.weblafbs.modele.Hasher;
import sio.leo.weblafbs.modele.UserDAO;
import sio.leo.weblafbs.modele.User;

/**
 *
 * @author Corinne
 */
@WebServlet(name = "ajoutuserservlet", urlPatterns = {"/ajoutuser"})
public class UserCreateServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Juste afficher le formulaire d'ajout (pas de données à récupérer)
        request.getRequestDispatcher("/WEB-INF/vue/createuser.jsp").forward(request, response);
    }
    

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    try {
        processRequest(request, response);
        // Récupérer les données du formulaire sans l'ID
        String nom = request.getParameter("nom");
        String pass = request.getParameter("pass");
        String role = request.getParameter("role");

        // Hasher le mot de passe
        String hashedPass = Hasher.hashPassword(pass);

        // Créer un utilisateur sans ID (la base s'en charge)
        User user = new User(0, nom, hashedPass, role);  // Met l'ID à 0 car il sera généré par MySQL

        // Ajouter l'utilisateur à la base de données
        UserDAO userdao = new UserDAO();
        userdao.addUser(user);

        // Rediriger vers la page d'accueil après ajout
        response.sendRedirect("/webLaFBS/index.html");
    } catch (SQLException | NamingException ex) {
        Logger.getLogger(UserCreateServlet.class.getName()).log(Level.SEVERE, null, ex);
    } catch (Exception ex) {
        Logger.getLogger(UserCreateServlet.class.getName()).log(Level.SEVERE, null, ex);
    }
}


    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
