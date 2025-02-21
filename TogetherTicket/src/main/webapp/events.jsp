
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Eventi - TogetherTicket</title>
    <link rel="stylesheet" href="css/style.css">
    <!-- Altri link e script -->
</head>
<body>
    <header>
        <!-- Header con logo, menu, ecc. -->
    </header>
    <main>
        <h2>Eventi Disponibili</h2>
        <c:if test="${not empty eventiList}">
            <ul>
                <c:forEach var="evento" items="${eventiList}">
                    <li>
                        <strong>Nome:</strong> ${evento.nome}<br>
                        <strong>Descrizione:</strong> ${evento.descrizione}<br>
                        <strong>Data:</strong> ${evento.dataEvento}<br>
                        <strong>Luogo:</strong> ${evento.luogo}<br>
                    </li>
                </c:forEach>
            </ul>
        </c:if>
        <c:if test="${empty eventiList}">
            <p>Nessun evento trovato.</p>
        </c:if>
    </main>
    <footer>
        <!-- Footer -->
    </footer>
</body>
</html>

