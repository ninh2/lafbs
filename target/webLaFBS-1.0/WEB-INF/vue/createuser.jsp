<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Creer user</title>
        <style>
            body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 0; }
            h1 { text-align: center; color: #333; }
            form { width: 300px; margin: 30px auto; padding: 15px; background-color: #fff; border-radius: 8px; box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); }
            label, input, select { width: 100%; margin-bottom: 10px; padding: 8px; font-size: 14px; }
            input, select { border: 1px solid #ccc; border-radius: 4px; }
            button { width: 100%; padding: 10px; background-color: #4CAF50; color: white; border: none; border-radius: 4px; cursor: pointer; }
            button:hover { background-color: #45a049; }
        </style>
    </head>
    <body>
        <h1>Créer un user</h1>
        <form action="ajoutuser" method="post">
          
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
