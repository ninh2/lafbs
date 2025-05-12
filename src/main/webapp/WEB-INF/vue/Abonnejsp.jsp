<%@page import="sio.leo.weblafbs.modele.Abonne"%>
<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Liste des Abonnés</title>
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
        <h2>Liste des abonnés</h2>

        <div class="action-link-container">
            <a href="/webLaFBS/ajoutabonne" class="action-link">Ajouter un abonné</a>
        </div>

        <table>
            <thead>
                <tr>
                    <th>Matricule</th>
                    <th>Nom</th>
                    <th>Prénom</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="abonne" items="${abonnes}">
                    <tr>
                        <td>${abonne.matricule}</td>
                        <td>${abonne.nom}</td>
                        <td>${abonne.prenom}</td>
                        <td>
                            <a href="/webLaFBS/unabo?matricule=${abonne.matricule}">Détail</a> | 
                            <a href="/webLaFBS/modifierAbonne?matricule=${abonne.matricule}">Modifier</a> | 
                            <a href="${pageContext.request.contextPath}/supprimerAbonne?matricule=${abonne.matricule}"
                             onclick="return confirm('Voulez-vous vraiment supprimer cet abonné ?');">
                             Supprimer
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div class="subscriber-count">
            Nombre d'abonnés : ${abonnes.size()}
        </div>
    </div>
</body>
</html>