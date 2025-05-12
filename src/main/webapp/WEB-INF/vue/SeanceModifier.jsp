<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modification Séance</title>
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
        <h2>MODIFICATION SÉANCE</h2>
        <form action="modifierSeance" method="post">
            <input type="hidden" name="id" value="${seance.id}"/>

            <label for="jourSemaine">Jour de la semaine :</label>
            <select id="jourSemaine" name="jourSemaine" required>
                <option value="1" ${seance.jourSemaine == '1' ? 'selected' : ''}>Lundi</option>
                <option value="2" ${seance.jourSemaine == '2' ? 'selected' : ''}>Mardi</option>
                <option value="3" ${seance.jourSemaine == '3' ? 'selected' : ''}>Mercredi</option>
                <option value="4" ${seance.jourSemaine == '4' ? 'selected' : ''}>Jeudi</option>
                <option value="5" ${seance.jourSemaine == '5' ? 'selected' : ''}>Vendredi</option>
                <option value="6" ${seance.jourSemaine == '6' ? 'selected' : ''}>Samedi</option>
                <option value="7" ${seance.jourSemaine == '7' ? 'selected' : ''}>Dimanche</option>
            </select>

            <label for="horaire">Horaire :</label>
            <input type="time" id="horaire" name="horaire" value="${seance.horaire.toString().substring(0, 5)}" required/>

            <label for="prestationCode">Prestation :</label>
            <select id="prestationCode" name="prestationCode" required>
                <option value="" disabled>Choisir une prestation</option>
                <c:forEach var="prestation" items="${prestations}">
                    <option value="${prestation.code}" <c:if test="${prestation.code == seance.prestationCode}">selected</c:if>>
                        ${prestation.libelle}
                    </option>
                </c:forEach>
            </select>

            <label for="salleId">Salle :</label>
            <select id="salleId" name="salleId" required>
                <option value="" disabled>Choisir une salle</option>
                <c:forEach var="salle" items="${salles}">
                    <option value="${salle.id}" <c:if test="${salle.id == seance.salleId}">selected</c:if>>
                        ${salle.nom}
                    </option>
                </c:forEach>
            </select>

            <label for="nbPlaces">Nombre de places :</label>
            <input type="number" id="nbPlaces" name="nbPlaces" min="1" value="${seance.nbPlaces}" required/>

            <button type="submit">Modifier</button>
        </form>
    </div>
</body>
</html>
