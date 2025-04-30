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
