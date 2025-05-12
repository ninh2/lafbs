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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import sio.leo.weblafbs.modele.Abonne;
import sio.leo.weblafbs.modele.AbonneDAO;

/**
 *
 * @author Corinne
 */
@WebServlet(name = "AbonneServlet", urlPatterns = {"/lesabonnes"})
public class AbonneServlet extends HttpServlet {
    //pour avoir des entrées dans les logs
    //private static final Logger logger = Logger.getLogger(AbonneDAO.class.getName());
    
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
    
        if ("adherent".equals(request.getSession().getAttribute("role")))
       {
    response.sendRedirect("/webLaFBS/index.html");   
    return; }
        
        
        try {
            AbonneDAO abonneDao =new AbonneDAO();
            List<Abonne> listeAbonnes = abonneDao.getAllAbonnes();
//abonnes.forEach(e->{
//logger.info(e.toString());
//});
//Envoie la liste des abonnés à la jsp
        request.setAttribute("abonnes", listeAbonnes);
        request.getRequestDispatcher("WEB-INF/vue/Abonnejsp.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AbonneServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(AbonneServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }        
}


 