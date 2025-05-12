<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Creer user</title>
         <link rel="stylesheet" href="/webLaFBS/styles.css">
    </head>
    <body>
        <nav>
    <ul class="nav-links">
        <li><a href="/webLaFBS/lesabonnes">Consulter la liste des abonnés</a></li>
        <li><a href="/webLaFBS/stats">Consulter les statistiques</a></li>
        <li><a href="/webLaFBS/seance">Consulter les séances</a></li>
        <li><a href="/webLaFBS/AbonnementServlet">Gérer les abonnements</a></li>
    </ul>
</nav>
        
        <h1>Creer un user</h1>
        <form action="ajoutuser" method="post">
            <label for="id">User ID:</label>
            <input type="number" id="id" name="id" value="${user.id}" required />
            <label for="nom">Nom:</label>
            <input type="text" id="nom" name="nom" value="${user.nom}" required />
            <label for="pass">Password:</label>
            <input type="password" id="pass" name="pass" value="${user.pass}" required />
            <label for="role">Role:</label>
            <select id="role" name="role" required>
                <option value="admin" ${user.role != null && user.role.equalsIgnoreCase('admin') ? 'selected' : ''}>Admin</option>
                <option value="consult" ${user.role != null && user.role.equalsIgnoreCase('consult') ? 'selected' : ''}>Consult</option>
                <option value="adherent" ${user.role != null && user.role.equalsIgnoreCase('adherent') ? 'selected' : ''}>Adherent</option>
            </select>
            <button type="submit">Creer User</button>
        </form>
    </body>
</html>
