package com.togetherticket.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/MyGroupsServlet")
public class MyGroupsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Recupera l'ID dell'utente dalla sessione
    	Integer userId = (Integer) request.getSession().getAttribute("user_id");
    	if (userId == null) {
    	    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    	    response.getWriter().write("{\"error\": \"Utente non identificato. Effettua il login.\"}");
    	    return;
    	}
    	System.out.println("ID utente dalla sessione: " + userId);
    	System.out.println("Session user_id: " + request.getSession().getAttribute("user_id"));



        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        List<Map<String, Object>> gruppiUtente = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection()) {
            // Query per ottenere i gruppi a cui l'utente è iscritto
        	String query = "SELECT G.id AS gruppo_id, G.nome_gruppo, E.nome AS evento_nome "
                    + "FROM Membri M "
                    + "JOIN Gruppi G ON M.gruppo_id = G.id "
                    + "JOIN Eventi E ON G.evento_id = E.id "
                    + "WHERE M.utente_id = ?";

            
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, userId);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        Map<String, Object> gruppo = new HashMap<>();
                        gruppo.put("gruppo_id", rs.getInt("gruppo_id"));
                        gruppo.put("nome_gruppo", rs.getString("nome_gruppo"));
                        gruppo.put("evento_nome", rs.getString("evento_nome"));
                        
                        // Ottieni i membri del gruppo
                        List<String> membri = new ArrayList<>();
                        String membriQuery = "SELECT U.username FROM Membri M JOIN Utenti U ON M.utente_id = U.id WHERE M.gruppo_id = ?";
                        try (PreparedStatement membriStmt = conn.prepareStatement(membriQuery)) {
                            membriStmt.setInt(1, rs.getInt("gruppo_id"));
                            try (ResultSet rsMembri = membriStmt.executeQuery()) {
                                while (rsMembri.next()) {
                                    membri.add(rsMembri.getString("username"));
                                }
                            }
                        }
                        gruppo.put("partecipanti", membri);
                        gruppiUtente.add(gruppo);
                    }
                }
            }

            // Invia la lista come JSON
            String json = new Gson().toJson(gruppiUtente);
            response.getWriter().write(json);

        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"Errore nel recupero dei gruppi\"}");
        }
    }
}
