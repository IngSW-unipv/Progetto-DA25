package com.togetherticket.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/GetMessaggiServlet")
public class GetMessaggiServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int gruppoId = Integer.parseInt(request.getParameter("gruppo_id"));

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print("[");

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT m.testo, m.data_invio, u.username FROM Messaggi m JOIN Utenti u ON m.utente_id = u.id WHERE m.gruppo_id = ? ORDER BY m.data_invio ASC";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, gruppoId);
                try (ResultSet rs = stmt.executeQuery()) {
                    boolean first = true;
                    while (rs.next()) {
                        if (!first) out.print(",");
                        first = false;
                        out.printf("{\"testo\": \"%s\", \"data\": \"%s\", \"utente\": \"%s\"}", rs.getString("testo"), rs.getString("data_invio"), rs.getString("username"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        out.print("]");
        out.flush();
    }
}
