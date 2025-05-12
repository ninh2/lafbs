package controleur;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import sio.leo.weblafbs.modele.SeanceDAO;

@WebServlet(name = "SupprimerSeanceServlet", urlPatterns = {"/seance/supprimer"})
public class SupprimerSeanceServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Vérification du rôle, optionnel
         if (!"admin".equals(request.getSession().getAttribute("role")))
       {
    response.sendRedirect("/webLaFBS/index.html");    
    return; }

        try {
            // Récupération de l'ID de la séance depuis l'URL
            int id = Integer.parseInt(request.getParameter("id"));

            // Suppression via DAO
            SeanceDAO seanceDao = new SeanceDAO();
            seanceDao.supprimerSeance(id);

        } catch (NumberFormatException e) {
            Logger.getLogger(SupprimerSeanceServlet.class.getName()).log(Level.SEVERE, "ID de séance invalide", e);
        } catch (SQLException | NamingException e) {
            Logger.getLogger(SupprimerSeanceServlet.class.getName()).log(Level.SEVERE, null, e);
        }

        // Redirection vers la liste des séances après suppression
        response.sendRedirect("/webLaFBS/seance");
    }

    @Override
    public String getServletInfo() {
        return "Servlet de suppression d'une séance";
    }
}
