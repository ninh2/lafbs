package controleur;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sio.leo.weblafbs.modele.Seance;
import sio.leo.weblafbs.modele.SeanceDAO;

@WebServlet(name = "SeanceServlet", urlPatterns = {"/seance"})
public class SeanceServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(SeanceServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            SeanceDAO seanceDao = new SeanceDAO();
            List<Seance> listeSeances = seanceDao.getSeancesÀVenir();
            logger.log(Level.INFO, "Nombre de séances envoyées à la JSP : {0}", listeSeances.size());
            request.setAttribute("seances", listeSeances);
            request.getRequestDispatcher("/WEB-INF/vue/seance.jsp").forward(request, response);
        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, "Erreur lors de la récupération des séances", e);
            request.setAttribute("errorMessage", "Une erreur est survenue lors du chargement des séances : " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/vue/erreur.jsp").forward(request, response);
        }
    }
}