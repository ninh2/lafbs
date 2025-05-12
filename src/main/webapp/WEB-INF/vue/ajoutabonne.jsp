<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AJOUT ABONNE</title>
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
        
        <div>
            <h1>AJOUT ABONNE</h1>
            <form action="ajoutabonne" method="post">
                <label for="matricule">Matricule:</label>
                <input type="text" id="matricule" name="matricule" required/>

                <label for="nom">Nom:</label>
                <input type="text" id="nom" name="nom" required/>

                <label for="prenom">Prénom:</label>
                <input type="text" id="prenom" name="prenom" required/>

                <label for="ddn">Date de Naissance:</label>
                <input type="date" id="ddn" name="ddn" required/>

                <label for="telephone">Téléphone:</label>
                <input type="text" id="telephone" name="telephone" required/>

                <label for="mail">Email:</label>
                <input type="email" id="mail" name="mail" required/>

                  <button type="submit">ajouter</button>            
              
            </form>
        </div>
    </body>
</html>
