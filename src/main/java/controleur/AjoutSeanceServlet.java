package controleur;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sio.leo.weblafbs.modele.Prestation;
import sio.leo.weblafbs.modele.Salle;
import sio.leo.weblafbs.modele.Seance;
import sio.leo.weblafbs.modele.SeanceDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

@WebServlet(name = "AjoutSeanceServlet", urlPatterns = {"/AjoutSeanceServlet"})
public class AjoutSeanceServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(AjoutSeanceServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
                String userRole = (String) request.getSession().getAttribute("role");

        if (!"admin".equals(request.getSession().getAttribute("role")))
       {
    response.sendRedirect("/webLaFBS/index.html");  
    return; }
        
        
        try {
            SeanceDAO dao = new SeanceDAO();
            List<Prestation> prestations = dao.getAllPrestations();
            List<Salle> salles = dao.getAllSalles();

            request.setAttribute("prestations", prestations);
            request.setAttribute("salles", salles);

            request.getRequestDispatcher("/WEB-INF/vue/AjoutSeance.jsp").forward(request, response);
        } catch (SQLException | NamingException ex) {
            logger.log(Level.SEVERE, "Erreur lors du chargement des données pour le formulaire", ex);
            request.setAttribute("error", "Impossible de charger les données : " + ex.getMessage());
            request.getRequestDispatcher("/WEB-INF/vue/erreur.jsp").forward(request, response);
        }
    }

 @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String horaireStr = request.getParameter("horaire");       // format: HH:mm
    String jour = request.getParameter("jour");
    String prestationCode = request.getParameter("prestation");
    String salleId = request.getParameter("salle");
    String nbPlacesStr = request.getParameter("nbPlaces");

    try {
        // Validation des valeurs reçues
        if (horaireStr == null || jour == null || prestationCode == null || salleId == null || nbPlacesStr == null) {
            request.setAttribute("error", "Tous les champs sont obligatoires.");
            throw new IllegalArgumentException("Tous les champs sont obligatoires.");
        }

        // Convertir horaire et nombre de places
        Time horaire = Time.valueOf(horaireStr + ":00");
        int nbPlaces = Integer.parseInt(nbPlacesStr);

        Seance seance = new Seance();
        seance.setHoraire(horaire);
        seance.setJourSemaine(jour);
        seance.setPrestationCode(prestationCode);
        seance.setSalleId(salleId);
        seance.setNbPlaces(nbPlaces);

        SeanceDAO dao = new SeanceDAO();
        dao.ajouterSeance(seance);  // Appel à la méthode d'ajout

        // Si l'ajout se passe bien, on redirige vers la page de gestion des séances
        response.sendRedirect("/webLaFBS/seance");
        return;  // Il est important de retourner après la redirection pour arrêter l'exécution du code

    } catch (IllegalArgumentException e) {
        // Capturer les erreurs de validation
        request.setAttribute("error", "Erreur de validation des paramètres : " + e.getMessage());
    } catch (SQLException e) {
        // Capturer les erreurs SQL
        request.setAttribute("error", "Erreur lors de l'ajout de la séance : " + e.getMessage());
    } catch (NamingException e) {
        // Capturer les erreurs liées à la configuration JNDI
        request.setAttribute("error", "Erreur de connexion à la base de données : " + e.getMessage());
    } catch (Exception e) {
        // Capturer toute autre exception
        request.setAttribute("error", "Une erreur inattendue est survenue : " + e.getMessage());
    }

    // Charger les listes déroulantes si une erreur se produit
    try {
        SeanceDAO dao = new SeanceDAO();
        request.setAttribute("prestations", dao.getAllPrestations());
        request.setAttribute("salles", dao.getAllSalles());
    } catch (SQLException | NamingException ex) {
        request.setAttribute("error", "Impossible de charger les données : " + ex.getMessage());
    }

    // Renvoyer à la page JSP pour afficher les erreurs et recharger les données
    request.getRequestDispatcher("/WEB-INF/vue/AjoutSeance.jsp").forward(request, response);
}


    @Override
    public String getServletInfo() {
        return "Servlet pour ajouter une séance";
    }
}
