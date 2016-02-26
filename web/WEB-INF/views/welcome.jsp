<html>
<head>
    <title>Reservation Query</title>
</head>
<body>
<form method="post">
    Court Name
    <input type="text" name="courtName" value="${courtName}" />
    <input type="submit" value="Query" />
</form>
<table border="1">
    <tr>
        <th>Court Name</th>
        <th>Date</th>
        <th>Hour</th>
        <th>Player</th>
    </tr>
    <forEach items="${reservations}" var="reservation">
    <tr>
        <td>${reservation.courtName}</td>
        <td><formatDate value="${reservation.date}" pattern="yyyy-MM-dd" /></td>
        <td>${reservation.hour}</td>
        <td>${reservation.player.name}</td>
    </tr>
    </forEach>
</table>
</body>
</html>