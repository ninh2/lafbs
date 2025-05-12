<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="sio.leo.weblafbs.modele.Abonne" %>

<html>
<head>
    <title>Détails de l'abonné</title>
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
    
    <h1>Détails de l'abonné</h1>

    <table>
        <tr>
            <th>Matricule</th>
            <td>${abonne.matricule}</td>
        </tr>
        <tr>
            <th>Nom</th>
            <td>${abonne.nom}</td>
        </tr>
        <tr>
            <th>Prénom</th>
            <td>${abonne.prenom}</td>
        </tr>
        <tr>
            <th>Age</th>
            <td>${age}</td>
        </tr>
        <tr>
            <th>Téléphone</th>
            <td>${abonne.telephone}</td>
        </tr>
        <tr>
            <th>Email</th>
            <td>${abonne.mail}</td>
        </tr>
        <!-- Add other details as needed -->
    </table>

    <a href="/webLaFBS/lesabonnes">Retour à la liste des abonnés</a>
</body>
</html>
