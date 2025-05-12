package controleur;

import sio.leo.weblafbs.modele.statsDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javax.naming.NamingException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "statsServlet", urlPatterns = {"/stats"})
public class statsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Récupérer les paramètres du formulaire
        String statType = request.getParameter("statType");
        String dateDebut = request.getParameter("dateDebut");
        String dateFin = request.getParameter("dateFin");

        // Vérifier que les paramètres sont valides
        if (statType == null || dateDebut == null || dateFin == null) {
            request.setAttribute("error", "Veuillez sélectionner un type de statistique et une période valide.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/vue/stats.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // Appeler le DAO pour récupérer les statistiques
        statsDAO dao = new statsDAO();
        List<String[]> resultats = null;

        try {
            switch (statType) {
                case "inscriptions":
                    resultats = dao.getInscriptionsParActivite(dateDebut, dateFin);
                    break;
                case "abonnements":
                    resultats = dao.getAbonnementsParTypeEtSalle(dateDebut, dateFin);
                    break;
                case "presence":
                    resultats = dao.getPresencesActivites(dateDebut, dateFin);
                    break;
                case "frequentation":
                    resultats = dao.getFrequentationParSalle(dateDebut, dateFin);
                    break;
                case "absences":
                    resultats = dao.getTauxAbsenceActivites(dateDebut, dateFin);
                    break;
                default:
                    request.setAttribute("error", "Type de statistique inconnu.");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/vue/stats.jsp");
                    dispatcher.forward(request, response);
                    return;
            }
        } catch (NamingException ex) {
            Logger.getLogger(statsServlet.class.getName()).log(Level.SEVERE, "Erreur de connexion JNDI", ex);
            request.setAttribute("error", "Erreur de connexion à la base de données.");
        }

        // Passer les données à la JSP
        request.setAttribute("resultats", resultats);
        request.setAttribute("statType", statType);
        request.setAttribute("dateDebut", dateDebut);
        request.setAttribute("dateFin", dateFin);

        // Rediriger vers la JSP d'affichage
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/vue/resultats.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        
        // Rediriger vers la page du formulaire pour les requêtes GET
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/vue/stats.jsp");
        dispatcher.forward(request, response);
    }
}