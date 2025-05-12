<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Résultats des Statistiques</title>
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
            <h1>Résultats des Statistiques</h1>
            <% 
                String statType = (String) request.getAttribute("statType");
                String dateDebut = (String) request.getAttribute("dateDebut");
                String dateFin = (String) request.getAttribute("dateFin");
                List<String[]> resultats = (List<String[]>) request.getAttribute("resultats");
                
                if (request.getAttribute("error") != null) {
            %>
                <p class="error"><%= request.getAttribute("error") %></p>
            <% } else if (resultats == null || resultats.isEmpty()) { %>
                <p class="error">Aucun résultat trouvé pour la période sélectionnée.</p>
            <% } else { %>
                <p>Période : du <%= dateDebut %> au <%= dateFin %></p>
                <table>
                    <thead>
                        <tr>
                            <% 
                                if (statType.equals("inscriptions") || statType.equals("presence")) {
                            %>
                                <th>Activité</th>
                                <th>Nombre</th>
                            <% } else if (statType.equals("abonnements")) { %>
                                <th>Type d'abonnement</th>
                                <th>Salle</th>
                                <th>Nombre d'abonnés</th>
                            <% } else if (statType.equals("frequentation")) { %>
                                <th>Salle</th>
                                <th>Fréquentation</th>
                            <% } else if (statType.equals("absences")) { %>
                                <th>Activité</th>
                                <th>Absences</th>
                                <th>Inscriptions totales</th>
                                <th>Taux d'absence</th>
                            <% } %>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (String[] resultat : resultats) { %>
                            <tr>
                                <% for (String valeur : resultat) { %>
                                    <td><%= valeur %></td>
                                <% } %>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            <% } %>
            <a href="/webLaFBS/stats" class="retour-link">Retour</a>
        </div>
    </body>
</html>