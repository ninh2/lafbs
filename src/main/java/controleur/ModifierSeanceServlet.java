package controleur;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sio.leo.weblafbs.modele.Seance;
import sio.leo.weblafbs.modele.SeanceDAO;
import sio.leo.weblafbs.modele.Prestation;
import sio.leo.weblafbs.modele.Salle;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "ModifierSeanceServlet", urlPatterns = {"/modifierSeance"})
public class ModifierSeanceServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(ModifierSeanceServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         if ("adherent".equals(request.getSession().getAttribute("role")))
       {
    response.sendRedirect("/webLaFBS/index.html");     
    return; }
        
        
        int id = Integer.parseInt(request.getParameter("id"));
        SeanceDAO seanceDAO = new SeanceDAO();
        Seance seance = seanceDAO.getSeanceById(id);

        // Récupération des listes pour les <select>
        List<Prestation> prestations = null;
        try {
            prestations = Prestation.getAllPrestations();
        } catch (SQLException | NamingException ex) {
            logger.log(Level.SEVERE, "Erreur lors de la récupération des prestations", ex);
        }

        List<Salle> salles = null;
        try {
            salles = Salle.getAllSalles();
        } catch (SQLException | NamingException ex) {
            logger.log(Level.SEVERE, "Erreur lors de la récupération des salles", ex);
        }

        // Passer l'objet 'seance', la liste des prestations et la liste des salles à la JSP
        request.setAttribute("seance", seance);
        request.setAttribute("prestations", prestations);
        request.setAttribute("salles", salles);

        // Affichage de la page de modification
        request.getRequestDispatcher("/WEB-INF/vue/SeanceModifier.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String horaireStr = request.getParameter("horaire");
            // Convertir l'heure (assurez-vous qu'elle soit au format HH:mm)
            Time horaire = Time.valueOf(horaireStr + ":00"); // format "HH:mm:ss"
            String jourSemaine = request.getParameter("jourSemaine");
            String prestationCode = request.getParameter("prestationCode");
            String salleId = request.getParameter("salleId");
            int nbPlaces = Integer.parseInt(request.getParameter("nbPlaces"));

            // Validation des paramètres (vérification que les valeurs ne sont pas nulles ou incorrectes)
            if (horaire == null || jourSemaine == null || prestationCode == null || salleId == null || nbPlaces <= 0) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Données invalides.");
                return;
            }

            Seance seance = new Seance();
            seance.setId(id);
            seance.setHoraire(horaire);
            seance.setJourSemaine(jourSemaine);
            seance.setPrestationCode(prestationCode);
            seance.setSalleId(salleId);
            seance.setNbPlaces(nbPlaces);

            // Mise à jour des abonnements nécessaires en fonction de la prestation
            SeanceDAO seanceDAO = new SeanceDAO();
            List<String> abonnementsRequis = seanceDAO.getAbonnementsForPrestation(prestationCode);
            seance.setAbonnementsRequis(abonnementsRequis); // Associer les abonnements à la séance

            seanceDAO.updateSeance(seance);  // Mettre à jour la séance avec les nouveaux abonnements

            // Rediriger vers la liste des séances après la mise à jour
            response.sendRedirect("/webLaFBS/seance");
        } catch (IllegalArgumentException ex) {
            logger.log(Level.WARNING, "Erreur de format de données", ex);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Données invalides.");
        } catch (NamingException ex) {
            logger.log(Level.SEVERE, "Erreur de base de données", ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la mise à jour de la séance.");
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Erreur SQL lors de la mise à jour", ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur SQL lors de la mise à jour de la séance.");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet pour modifier une séance";
    }
}
