package controleur;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sio.leo.weblafbs.modele.statsDAO;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

@WebServlet(name = "AbonnementServlet", urlPatterns = {"/AbonnementServlet"})
public class AbonnementServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        statsDAO dao = new statsDAO();
        
           String userRole = (String) request.getSession().getAttribute("role");
           if ("adherent".equals(request.getSession().getAttribute("role")))
       {
    response.sendRedirect("/webLaFBS/index.html");   
    return; }
        
        
        try {
            // Récupérer tous les abonnements
            List<String[]> abonnements = dao.getTousLesAbonnements();
            request.setAttribute("abonnements", abonnements);
            
            // Récupérer les types d'abonnement et les salles pour les formulaires
            List<String[]> types = dao.getTousLesTypesAbonnement();
            List<String[]> salles = dao.getToutesLesSalles();
            request.setAttribute("types", types);
            request.setAttribute("salles", salles);
        } catch (NamingException ex) {
            Logger.getLogger(AbonnementServlet.class.getName()).log(Level.SEVERE, "Erreur de connexion JNDI", ex);
            request.setAttribute("erreur", "Erreur de connexion à la base de données");
        }
        
        // Rediriger vers la JSP
        request.getRequestDispatcher("/WEB-INF/vue/abonnements.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if ("modifier".equals(action)) {
            try {
                int abonnementId = Integer.parseInt(request.getParameter("abonnementId"));
                String nouveauType = request.getParameter("nouveauType");
                String nouvelleSalle = request.getParameter("nouvelleSalle");
                
                statsDAO dao = new statsDAO();
                boolean misAJour = dao.mettreAJourAbonnement(abonnementId, nouveauType, nouvelleSalle);
                
                if (misAJour) {
                    request.setAttribute("message", "Abonnement mis à jour avec succès.");
                } else {
                    request.setAttribute("erreur", "Échec de la mise à jour de l'abonnement.");
                }
            } catch (NamingException ex) {
                Logger.getLogger(AbonnementServlet.class.getName()).log(Level.SEVERE, "Erreur de connexion JNDI", ex);
                request.setAttribute("erreur", "Erreur de connexion à la base de données");
            } catch (NumberFormatException ex) {
                request.setAttribute("erreur", "ID d'abonnement invalide.");
            }
        }
        
        // Recharger les abonnements après la mise à jour
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet pour gérer l'affichage et la modification des abonnements des abonnés.";
    }
}