<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion des Abonnements</title>
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
        <h1>Gestion des Abonnements</h1>
        <c:if test="${not empty erreur}">
            <p class="error">${erreur}</p>
        </c:if>
        <c:if test="${not empty message}">
            <p class="success">${message}</p>
        </c:if>
        <c:choose>
            <c:when test="${empty abonnements}">
                <p>Aucun abonnement trouvé.</p>
            </c:when>
            <c:otherwise>
                <table>
                    <thead>
                        <tr>
                            <th>Nom</th>
                            <th>Prénom</th>
                            <th>Type d'Abonnement</th>
                            <th>Salle</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="abonnement" items="${abonnements}">
                            <tr>
                                <td>${abonnement[0]}</td>
                                <td>${abonnement[1]}</td>
                                <td>${abonnement[3]}</td>
                                <td>${abonnement[4]}</td>
                                <td>
                                    <form method="post" action="/webLaFBS/AbonnementServlet" class="table-form">
                                        <input type="hidden" name="action" value="modifier">
                                        <input type="hidden" name="abonnementId" value="${abonnement[2]}">
                                        <select name="nouveauType" required>
                                            <c:forEach var="type" items="${types}">
                                                <option value="${type[0]}" ${type[1] eq abonnement[3] ? 'selected' : ''}>${type[1]}</option>
                                            </c:forEach>
                                        </select>
                                        <select name="nouvelleSalle" required>
                                            <c:forEach var="salle" items="${salles}">
                                                <option value="${salle[0]}" ${salle[1] eq abonnement[4] ? 'selected' : ''}>${salle[1]}</option>
                                            </c:forEach>
                                        </select>
                                        <!-- Ajout de la classe table-button ici -->
                                        <button type="submit" class="table-button">Modifier</button>
                                    </form>
                                </td>

                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>