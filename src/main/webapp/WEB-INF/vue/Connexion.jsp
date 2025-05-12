<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Connexion</title>
          <link rel="stylesheet" href="/webLaFBS/styles.css">
    </head>
    <body>
        <h2 style="text-align: center;">Connexion</h2>

        <!-- Formulaire de connexion -->
        <form action="connexion" method="post">
            <label for="username">Nom d'utilisateur:</label>
            <input type="text" id="username" name="username" required />

            <label for="password">Mot de passe:</label>
            <input type="password" id="password" name="password" required />

            <button type="submit">Se connecter</button>
        </form>

        <!-- Affichage des erreurs s'il y en a -->
        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>
    </body>
</html>
