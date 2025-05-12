package controleur;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sio.leo.weblafbs.modele.AbonneDAO;

@WebServlet(name = "AbonneSupprimerServlet", urlPatterns = {"/supprimerAbonne"})
public class AbonneSupprimerServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(AbonneSupprimerServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        if (!"admin".equals(request.getSession().getAttribute("role")))
       {
     response.sendRedirect("/webLaFBS/index.html");  
    return; }

        try {
            // 2) Récupération du matricule et suppression
            int matricule = Integer.parseInt(request.getParameter("matricule"));
            new AbonneDAO().supprimeAbonne(matricule);
            logger.log(Level.INFO, "Abonné {0} supprimé.", matricule);
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, "Matricule invalide", e);
        } catch (SQLException | NamingException e) {
            logger.log(Level.SEVERE, "Erreur lors de la suppression de l’abonné", e);
        }

        // 3) Redirection vers la liste des abonnés
        response.sendRedirect(request.getContextPath() + "/lesabonnes");
    }

    @Override
    public String getServletInfo() {
        return "Servlet pour supprimer un abonné sans page de confirmation";
    }
}
