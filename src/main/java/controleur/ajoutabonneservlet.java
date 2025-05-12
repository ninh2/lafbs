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
import controleur.ConnexionS;
import static java.lang.System.out;
/**
 *
 * @author Corinne
 */

@WebServlet(name = "ajoutabonneservlet", urlPatterns = {"/ajoutabonne"})
public class ajoutabonneservlet extends HttpServlet {

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

       if (!"admin".equals(request.getSession().getAttribute("role")))
       {
    response.sendRedirect("/webLaFBS/index.html");  
    return;  
}
       
  
     
 
        request.getRequestDispatcher("/WEB-INF/vue/ajoutabonne.jsp").forward(request, response);
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
        processRequest(request, response);
        // Récupérer les données du formulaire
        int matricule = Integer.valueOf(request.getParameter("matricule"));
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String String_ddn = request.getParameter("ddn");
        String telephone =request.getParameter("telephone"); 
        String mail =request.getParameter("mail");
        

        // Valider et mettre à jour les informations de l'abonné
        //convertion String en Date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date ddn;
        try {
            ddn = sdf.parse(String_ddn);
        
        Abonne abonne = new Abonne(matricule,nom, prenom,ddn,telephone,mail);
        AbonneDAO abonneDao = new AbonneDAO(); 
        try {
            abonneDao.ajouteAbonne(abonne);
        } catch (SQLException ex) {
            Logger.getLogger(ajoutabonneservlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        } catch (ParseException | SQLException | NamingException ex) {
            Logger.getLogger(ajoutabonneservlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        //renvoie vers la liste
         response.sendRedirect("/webLaFBS/lesabonnes");
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
