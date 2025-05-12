<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Statistiques</title>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="/webLaFBS/styles.css">
    </head>
    <body>
        <nav>
        <ul class="nav-links">
            <!-- Ces liens sont visibles uniquement si le rôle n?est pas "adherent" -->
            <c:if test="${sessionScope.role ne 'adherent'}">
                <li><a href="/webLaFBS/lesabonnes">Consulter la liste des abonnés</a></li>
            </c:if>
            
            <c:if test="${sessionScope.role ne 'adherent'}">
                <li><a href="/webLaFBS/AbonnementServlet">Gérer les abonnements</a></li>
            </c:if>

            <!-- Toujours visibles -->
            <li><a href="/webLaFBS/stats">Consulter les statistiques</a></li>
            <li><a href="/webLaFBS/seance">Consulter les séances</a></li>
        </ul>
    </nav>
                
        <div class="content">
            <h1>Statistiques des Activités</h1>
            <% if (request.getAttribute("error") != null) { %>
                <p class="error"><%= request.getAttribute("error") %></p>
            <% } %>
            <form action="/webLaFBS/stats" method="post">
                <label for="statType">Type de statistique :</label>
                <select name="statType" id="statType" required>
                    <option value="inscriptions">Inscriptions par activité</option>
                    <option value="abonnements">Abonnements par type et salle</option>
                    <option value="presence">Présence aux activités</option>
                    <option value="frequentation">Fréquentation par salle</option>
                    <option value="absences">Taux d'absences</option>
                </select>

                <label for="dateDebut">Du :</label>
                <input type="date" name="dateDebut" id="dateDebut" required>

                <label for="dateFin">Au :</label>
                <input type="date" name="dateFin" id="dateFin" required>

                <button type="submit">Consulter</button>
            </form>
        </div>
    </body>
</html>