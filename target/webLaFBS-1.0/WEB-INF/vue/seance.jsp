<%-- 
    Document   : seance
    Created on : 7 mai 2025, 18:02:47
    Author     : hadif
--%>
<%@ page contentType="text/html;charset=ISO-8859-1" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Liste des séances à venir | LaFBS</title>
    <link rel="stylesheet" href="/webLaFBS/styles.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap" rel="stylesheet">
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
        <h2>Liste des séances à venir</h2>

        <div class="action-link-container">
            <a href="${pageContext.request.contextPath}/AjoutSeanceServlet" class="action-link">Ajouter une séance</a>
        </div>

        <table>
            <thead>
                <tr>
                    <th>Jour</th>
                    <th>Horaire</th>
                    <th>Prestation</th>
                    <th>Salle</th>
                    <th>Abonnement nécessaire</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="seance" items="${seances}">
                    <tr>
                        <td>
                            <c:choose>
                                <c:when test="${seance.jourSemaine == '1'}">Lundi</c:when>
                                <c:when test="${seance.jourSemaine == '2'}">Mardi</c:when>
                                <c:when test="${seance.jourSemaine == '3'}">Mercredi</c:when>
                                <c:when test="${seance.jourSemaine == '4'}">Jeudi</c:when>
                                <c:when test="${seance.jourSemaine == '5'}">Vendredi</c:when>
                                <c:when test="${seance.jourSemaine == '6'}">Samedi</c:when>
                                <c:when test="${seance.jourSemaine == '7'}">Dimanche</c:when>
                                <c:otherwise>Inconnu</c:otherwise>
                            </c:choose>
                        </td>
                        <td><fmt:formatDate value="${seance.horaire}" pattern="HH:mm" /></td>
                        <td>${seance.prestation}</td>
                        <td>${seance.salle}</td>
                        <td>
                            <c:forEach var="abonnement" items="${seance.abonnementsRequis}" varStatus="status">
                                ${abonnement}<c:if test="${!status.last}">, </c:if>
                            </c:forEach>
                        </td>
                        <td>
                            <a href="/webLaFBS/modifierSeance?id=${seance.id}">Modifier</a>
                            <a href="/webLaFBS/seance/supprimer?id=${seance.id}" onclick="return confirm('Voulez-vous vraiment supprimer cette séance ?');">Supprimer</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div class="seance-count">
            Nombre de séances : ${seances.size()}
        </div>
    </div>
</body>
</html>