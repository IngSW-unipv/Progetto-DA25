package com.togetherticket.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/bacheca")
public class BachecaServlet extends HttpServlet {

    // Metodo per gestire le richieste HTTP GET (recupera i messaggi della bacheca)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json"); // Imposta la risposta come JSON
        response.setCharacterEncoding("UTF-8"); // Imposta la codifica dei caratteri

        // Recupera l'ID del gruppo dalla query string dell'URL
        int gruppoId;
        try {
            gruppoId = Integer.parseInt(request.getParameter("gruppo_id"));
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"ID gruppo non valido\"}");
            return;
        }

        // Ottieni l'ID dell'utente dalla sessione
        Integer utenteId = (Integer) request.getSession().getAttribute("user_id");

        if (utenteId == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"error\": \"Utente non autenticato\"}");
            return;
        }

        List<Bacheca> messaggi = new ArrayList<>();   // Lista per memorizzare i messaggi della bacheca

        try (Connection conn = DatabaseConnection.getConnection()) {
            // Query per recuperare i messaggi della bacheca di un gruppo specifico
            String query = "SELECT B.utente_id, U.username, B.messaggio, B.data_pubblicazione "
                         + "FROM Bacheca B "
                         + "JOIN Utenti U ON B.utente_id = U.id "
                         + "WHERE B.gruppo_id = ? ORDER BY B.data_pubblicazione ASC";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, gruppoId);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        String dataPubblicazione = rs.getString("data_pubblicazione");
                        Bacheca messaggio = new Bacheca(
                            gruppoId,
                            rs.getInt("utente_id"),
                            rs.getString("username"),
                            rs.getString("messaggio"),
                            dataPubblicazione
                        );
                        messaggi.add(messaggio);
                    }
                }
            }

            // Converte la lista di messaggi in formato JSON e la invia al client
            String json = new Gson().toJson(messaggi);
            response.getWriter().write(json);

        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"Errore nel recupero dei messaggi\", \"details\": \"" + e.getMessage() + "\"}");
            e.printStackTrace();
        }
    }

    // Metodo per gestire le richieste HTTP POST (inserisce un nuovo messaggio nella bacheca)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Recupera l'ID del gruppo e il messaggio dalla richiesta
        int gruppoId;
        String messaggio = request.getParameter("messaggio");

        try {
            gruppoId = Integer.parseInt(request.getParameter("gruppo_id"));
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"ID gruppo non valido\"}");
            return;
        }

        // Ottieni l'ID utente dalla sessione
        Integer utenteId = (Integer) request.getSession().getAttribute("user_id");

        if (utenteId == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"error\": \"Utente non autenticato\"}");
            return;
        }

        if (messaggio == null || messaggio.trim().isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Messaggio non valido\"}");
            return;
        }

        // Formatta la data di pubblicazione
        String dataPubblicazione = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new java.util.Date());

        try (Connection conn = DatabaseConnection.getConnection()) {
            // Query per inserire un nuovo messaggio nella bacheca
            String query = "INSERT INTO Bacheca (gruppo_id, utente_id, messaggio, data_pubblicazione) VALUES (?, ?, ?, ?)";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, gruppoId);
                stmt.setInt(2, utenteId);  // Usa l'ID utente dalla sessione
                stmt.setString(3, messaggio);
                stmt.setString(4, dataPubblicazione);

                int rowsAffected = stmt.executeUpdate();  // Esegue l'inserimento
                if (rowsAffected > 0) {
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.getWriter().write("{\"message\": \"Messaggio aggiunto con successo\"}");
                } else {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.getWriter().write("{\"error\": \"Errore nell'inserimento del messaggio\"}");
                }
            }
        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"Errore nel database\", \"details\": \"" + e.getMessage() + "\"}");
            e.printStackTrace();
        }
    }
}




