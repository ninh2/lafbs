import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import sio.leo.weblafbs.modele.Seance;
import sio.leo.weblafbs.modele.SeanceDAO;

@WebServlet("/accueil")
public class accServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SeanceDAO seanceDAO = new SeanceDAO();
        List<Seance> seances = seanceDAO.getSeancesÀVenir();
        
        request.setAttribute("seances", seances);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/vue/accueil.jsp");
        dispatcher.forward(request, response);
    }
}
