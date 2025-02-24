<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Seleziona Gruppo - TogetherTicket</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <header>
        <h1>TogetherTicket - Seleziona un gruppo per l'evento ${evento_nome}</h1>
    </header>

    <main>
        <h2>Gruppi disponibili per l'evento: ${evento_nome}</h2>

        <c:if test="${not empty gruppi}">
            <form action="join_group.jsp" method="GET">
                <label for="gruppo">Seleziona un gruppo:</label>
                <select name="gruppo_id" id="gruppo">
                    <c:forEach var="gruppo" items="${gruppi}">
                        <option value="${gruppo.id}">${gruppo.nomeGruppo}</option>
                    </c:forEach>
                </select>
                <button type="submit">Unisciti al gruppo</button>
            </form>
        </c:if>

        <c:if test="${empty gruppi}">
            <p>Non ci sono gruppi disponibili per questo evento.</p>
        </c:if>
    </main>

    <footer>
        <p>&copy; 2025 TogetherTicket. Tutti i diritti riservati.</p>
    </footer>
</body>
</html>

