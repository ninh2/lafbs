<%@page import="sio.leo.weblafbs.modele.Abonne"%>
<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <title>Liste des Abonnés</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        /* Fixed Header Styles */
        header {
            background-color: #007BFF;
            color: #fff;
            padding: 20px 0;
            text-align: center;
            font-size: 24px;
            position: sticky;
            top: 0;
            z-index: 1000;
            width: 100%;
        }

        /* Navigation Bar */
        nav {
            background-color: #007BFF;
            display: flex;
            justify-content: center;
            padding: 15px 0;
            position: sticky;
            top: 0;
            z-index: 1000;
        }

        nav a {
            color: #fff;
            text-decoration: none;
            padding: 10px 20px;
            margin: 0 15px;
            font-size: 16px;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        nav a:hover {
            background-color: #0056b3;
        }

        /* Table Styles */
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
            background-color: white;
            box-shadow: 0px 0px 10px rgba(0,0,0,0.1);
        }

        th, td {
            border: 1px solid #ddd;
            padding: 12px 15px;
            text-align: left;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        a {
            color: #4CAF50;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }

        .ajouter-link {
            display: inline-block;
            margin: 20px;
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }

        .ajouter-link:hover {
            background-color: #45a049;
        }

        /* Subscriber count display */
        .subscriber-count {
            text-align: center;
            font-weight: bold;
            font-size: 18px;
        }

        .error-message {
            color: red;
            font-weight: bold;
            margin: 10px;
            padding: 10px;
            border: 1px solid red;
            background-color: #f8d7da;
        }
    </style>
</head>
<body>



    <!-- Sticky Navigation Bar -->
    <nav>
        <a href="/webLaFBS/lesabonnes">Liste des abonnés</a>
        <a href="/webLaFBS/stats">Statistiques</a>
        <a href="/webLaFBS/accueil">Séances disponibles</a>
    </nav>

    <!-- Affichage du message d'erreur si présent -->
    <% 
    String errorMessage = (String) session.getAttribute("errorMessage");
    if (errorMessage != null) {
        out.println("<div class='error-message'>" + errorMessage + "</div>");
        session.removeAttribute("errorMessage"); // Supprimez le message après l'affichage
    }
    %>

    <!-- Ajouter link styled as a button -->
    <a href="/webLaFBS/ajoutabonne" class="ajouter-link">Ajouter un abonné</a>

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
                    <!-- Renvoie vers la servlet srvUnAbonne avec le matricule de l'abonné -->
                    <td>
                        <a href="/webLaFBS/unabo?matricule=${abonne.matricule}">Détail</a> | 
                        <a href="/webLaFBS/modifierAbonne?matricule=${abonne.matricule}">Modifier</a> | 
                        <a href="/webLaFBS/supprimerAbonne?matricule=${abonne.matricule}">Supprimer</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <div class="subscriber-count">
        Nombre d'abonnés : ${abonnes.size()}
    </div>

</body>
</html>
