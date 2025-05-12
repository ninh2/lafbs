<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modification Abonné</title>
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
        
        <h1>MODIFICATION ABONNE</h1>
        <form action="modifierAbonne" method="post">
            <input type="hidden" name="matricule" value="${abonne.matricule}"/>

            <label for="nom">Nom:</label>
            <input type="text" id="nom" name="nom" value="${abonne.nom}" required/>
            
            <label for="prenom">Prénom:</label>
            <input type="text" id="prenom" name="prenom" value="${abonne.prenom}" required/>
            
            <label for="ddn">Date de Naissance:</label>
            <input type="date" id="ddn" name="ddn" value="${abonne.ddn}" required/>
            
            <label for="telephone">Téléphone:</label>
            <input type="text" id="telephone" name="telephone" value="${abonne.telephone}" required/>
            
            <label for="mail">Email:</label>
            <input type="text" id="mail" name="mail" value="${abonne.mail}" required/>
            
            <button type="submit">Modifier</button>
        </form>
    </body>
</html>
