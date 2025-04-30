<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Connexion</title>
        <style>
            body { font-family: Arial, sans-serif; background-color: #f4f4f4; }
            form { width: 300px; margin: 50px auto; padding: 20px; background-color: #fff; border-radius: 8px; box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); }
            label, input { width: 100%; margin-bottom: 10px; padding: 8px; }
            input { border: 1px solid #ccc; border-radius: 4px; }
            button { width: 100%; padding: 10px; background-color: #4CAF50; color: white; border: none; border-radius: 4px; }
            button:hover { background-color: #45a049; }
            .error { color: red; font-size: 14px; }
        </style>
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
