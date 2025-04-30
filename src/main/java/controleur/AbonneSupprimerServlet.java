
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

/**
 *
 * @author Corinne
 */
@WebServlet(name = "AbonneSupprimerServlet", urlPatterns = {"/supprimerAbonne"})
public class AbonneSupprimerServlet extends HttpServlet {

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
        
         if ("consult".equals(request.getSession().getAttribute("role"))) {
    request.getSession().setAttribute("errorMessage", "Vous n'avez pas les permissions nécessaires.");
    response.sendRedirect("/webLaFBS/lesabonnes");
    return;
}

         
        try {
            processRequest(request, response);
            //Récupération du matricule en int
            Integer matricule = Integer.valueOf(request.getParameter("matricule"));
            //Récupérer l'abonné pour pouvoir le modifier
            AbonneDAO abonneDao = new AbonneDAO(); 
            Abonne UnAbonne = abonneDao.getAbonneByMatricule(matricule);
            // Ajoute l'abonné aux attributs de la requête
            
            request.setAttribute("abonne", UnAbonne);
            request.getRequestDispatcher("/WEB-INF/vue/abonneSupprimer.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AbonneSupprimerServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(AbonneSupprimerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    
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
       int matricule = Integer.parseInt(request.getParameter("matricule"));       
     
     try {
        AbonneDAO abonneDao = new AbonneDAO(); 
        abonneDao.supprimeAbonne(matricule);
         
        }
     catch (SQLException ex) {
         
         Logger.getLogger(AbonneSupprimerServlet.class.getName()).log(Level.SEVERE, null, ex);     
    }   
     catch (NamingException ex) {
            Logger.getLogger(AbonneSupprimerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
