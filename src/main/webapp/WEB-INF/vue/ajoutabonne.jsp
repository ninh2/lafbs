<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AJOUT ABONNE</title>
        <style>
            /* Styles généraux pour la page */
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f9;
                margin: 0;
                padding: 0;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }

            h1 {
                text-align: center;
                color: #333;
                margin-bottom: 20px;
            }

            /* Style du formulaire */
            form {
                background-color: white;
                padding: 30px;
                border-radius: 8px;
                box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
                width: 400px;
                box-sizing: border-box;
            }

            label {
                display: block;
                margin: 10px 0 5px;
                font-weight: bold;
                color: #555;
            }

            input[type="text"],
            input[type="date"],
            input[type="email"] {
                width: 100%;
                padding: 10px;
                margin-bottom: 15px;
                border: 1px solid #ddd;
                border-radius: 5px;
                box-sizing: border-box;
                font-size: 16px;
            }

            button[type="submit"] {
                background-color: #4CAF50;
                color: white;
                padding: 12px 20px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                width: 100%;
                font-size: 16px;
            }

            button[type="submit"]:hover {
                background-color: #45a049;
            }

            /* Désactivation du bouton si l'utilisateur n'est pas admin */
            button[disabled] {
                background-color: #ccc;
                cursor: not-allowed;
            }

            /* Styles pour l'élément de notification d'erreur */
            .error {
                color: red;
                text-align: center;
                margin-top: 20px;
            }
        </style>
    </head>
    <body>
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
