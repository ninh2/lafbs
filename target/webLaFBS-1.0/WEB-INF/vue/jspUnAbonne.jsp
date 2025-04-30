<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="sio.leo.weblafbs.modele.Abonne" %>

<html>
<head>
    <title>Détails de l'abonné</title>
    <style>
        /* General body styling */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }

        /* Heading style */
        h1 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }

        /* Table styling */
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: #fff;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }

        th, td {
            padding: 12px;
            text-align: left;
            border: 1px solid #ddd;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        /* Link styling */
        a {
            display: block;
            text-align: center;
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }

        a:hover {
            background-color: #45a049;
        }

        /* Responsive styling for small screens */
        @media (max-width: 600px) {
            table {
                width: 100%;
                padding: 10px;
            }

            h1 {
                font-size: 18px;
            }
        }
    </style>
</head>
<body>
    <h1>Détails de l'abonné</h1>

    <table>
        <tr>
            <th>Matricule</th>
            <td>${abonne.matricule}</td>
        </tr>
        <tr>
            <th>Nom</th>
            <td>${abonne.nom}</td>
        </tr>
        <tr>
            <th>Prénom</th>
            <td>${abonne.prenom}</td>
        </tr>
        <tr>
            <th>Age</th>
            <td>${age}</td>
        </tr>
        <tr>
            <th>Téléphone</th>
            <td>${abonne.telephone}</td>
        </tr>
        <tr>
            <th>Email</th>
            <td>${abonne.mail}</td>
        </tr>
        <!-- Add other details as needed -->
    </table>

    <a href="/webLaFBS/lesabonnes">Retour à la liste des abonnés</a>
</body>
</html>
