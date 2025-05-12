<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>AJOUT SÉANCE</title>
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
        <h2>AJOUT SÉANCE</h2>

        <c:if test="${not empty error}">
            <div class="error">
                ${error}
            </div>
        </c:if>

        <form action="AjoutSeanceServlet" method="post">
            <label for="horaire">Heure :</label>
            <input type="time" id="horaire" name="horaire" required/>

            <label for="jour">Jour de la semaine :</label>
            <select id="jour" name="jour" required>
                <option value="1">Lundi</option>
                <option value="2">Mardi</option>
                <option value="3">Mercredi</option>
                <option value="4">Jeudi</option>
                <option value="5">Vendredi</option>
                <option value="6">Samedi</option>
                <option value="7">Dimanche</option>
            </select>

            <label for="prestation">Prestation :</label>
            <select id="prestation" name="prestation" required>
                <option value="" disabled selected>Choisir une prestation</option>
                <c:forEach var="prestation" items="${prestations}">
                    <option value="${prestation.code}">${prestation.libelle}</option>
                </c:forEach>
            </select>

            <label for="salle">Salle :</label>
            <select id="salle" name="salle" required>
                <option value="" disabled selected>Choisir une salle</option>
                <c:forEach var="salle" items="${salles}">
                    <option value="${salle.id}">${salle.nom}</option>
                </c:forEach>
            </select>

            <label for="nbPlaces">Nombre de places :</label>
            <input type="number" id="nbPlaces" name="nbPlaces" min="1" required/>

            <button type="submit">Ajouter</button>
        </form>
    </div>
</body>
</html>