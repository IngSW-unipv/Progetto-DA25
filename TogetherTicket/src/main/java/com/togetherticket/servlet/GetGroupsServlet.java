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

@WebServlet("/GetGroupsServlet")
public class GetGroupsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Recupera il parametro "evento_id" dalla URL
        String eventoNome = request.getParameter("evento_id");

        if (eventoNome == null || eventoNome.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Parametro evento_id mancante.\"}");
            return;
        }

        int eventoId = -1; // variabile per l'ID dell'evento
        List<Gruppo> gruppi = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection()) {
            // Ottieni l'evento_id a partire dal nome dell'evento
            String queryEventoId = "SELECT id FROM Eventi WHERE nome = ?";
            try (PreparedStatement stmtEvento = conn.prepareStatement(queryEventoId)) {
                stmtEvento.setString(1, eventoNome);
                try (ResultSet rsEvento = stmtEvento.executeQuery()) {
                    if (rsEvento.next()) {
                        eventoId = rsEvento.getInt("id");
                    }
                }
            }

            if (eventoId == -1) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("{\"error\": \"Evento non trovato.\"}");
                return;
            }

            // Recupera i gruppi per l'evento_id
            String queryGruppi = "SELECT id, nome_gruppo FROM Gruppi WHERE evento_id = ?";
            try (PreparedStatement stmtGruppi = conn.prepareStatement(queryGruppi)) {
                stmtGruppi.setInt(1, eventoId);
                try (ResultSet rsGruppi = stmtGruppi.executeQuery()) {
                    while (rsGruppi.next()) {
                        Gruppo gruppo = new Gruppo(
                            rsGruppi.getInt("id"),
                            rsGruppi.getString("nome_gruppo")
                        );
                        gruppi.add(gruppo);
                    }
                }
            }

            // Restituisci i gruppi in formato JSON
            String json = new Gson().toJson(gruppi);
            response.getWriter().write(json);

        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"Errore nel recupero dei gruppi\", \"details\": \"" + e.getMessage() + "\"}");
            e.printStackTrace();
        }
    }
}
