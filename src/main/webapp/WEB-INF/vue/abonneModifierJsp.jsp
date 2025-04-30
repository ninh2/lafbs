<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modification Abonné</title>
        <style>
            /* General body styling */
            body {
                font-family: Arial, sans-serif;
                background-color: #f9f9f9;
                margin: 0;
                padding: 20px;
            }

            /* Heading style */
            h1 {
                text-align: center;
                color: #333;
                margin-bottom: 20px;
            }

            /* Form container */
            form {
                background-color: #fff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
                max-width: 600px;
                margin: 0 auto;
            }

            /* Label styling */
            label {
                font-size: 16px;
                color: #333;
                display: block;
                margin-bottom: 8px;
            }

            /* Input field styling */
            input[type="text"],
            input[type="date"],
            input[type="email"] {
                width: 100%;
                padding: 10px;
                margin-bottom: 12px;
                border: 1px solid #ccc;
                border-radius: 5px;
                font-size: 14px;
            }

            input[type="text"]:focus,
            input[type="date"]:focus,
            input[type="email"]:focus {
                border-color: #4CAF50;
                outline: none;
            }

            /* Submit button styling */
            button[type="submit"] {
                background-color: #4CAF50;
                color: white;
                padding: 12px 20px;
                border: none;
                border-radius: 5px;
                font-size: 16px;
                cursor: pointer;
                width: 100%;
            }

            button[type="submit"]:hover {
                background-color: #45a049;
            }

            /* Responsive layout for smaller screens */
            @media (max-width: 600px) {
                form {
                    padding: 15px;
                    width: 90%;
                }

                h1 {
                    font-size: 18px;
                }
            }
        </style>
    </head>
    <body>
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
