<%@page import="sio.leo.weblafbs.modele.Abonne"%>
<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <title>Accueil</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            background-color: #f5f7fa;
        }

        /* Header Styles */
        header {
            background-color: #007BFF;
            color: #fff;
            padding: 20px 0;
            text-align: center;
            font-size: 24px;
            position: sticky; /* Le rendre sticky */
            top: 0; /* Fixe en haut de la page */
            z-index: 1000; /* Assure que le header reste au-dessus des autres éléments */
            width: 100%;
        }

        h1 {
            color: #fff;
            text-align: center;
            margin-bottom: 30px;
        }

        /* Table Styles */
        table {
            margin: auto;
            border-collapse: collapse;
            width: 80%;
            background-color: white;
            box-shadow: 0px 0px 10px rgba(0,0,0,0.1);
        }
        th, td {
            border: 1px solid #ddd;
            padding: 12px 15px;
            text-align: center;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #e6f7ff;
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
    </style>
</head>
<body>

    <!-- Fixed Header Section -->


    <!-- Sticky Navigation Bar -->
    <nav>
        <a href="/webLaFBS/lesabonnes">Liste des abonnés</a>
        <a href="/webLaFBS/stats">Statistiques</a>
        <a href="/webLaFBS/accueil">Séances disponibles</a>
    </nav>

    <h1>Prochaines Séances</h1>

    <table>
        <thead>
            <tr>
                <th>Jour</th>
                <th>Horaire</th>
                <th>Prestation</th>
                <th>Salle</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="seance" items="${seances}">
                <tr>
                    <td>${seance.jourSemaine}</td>
                    <td>${seance.horaire}</td>
                    <td>${seance.prestation}</td>
                    <td>${seance.salle}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</body>
</html>
