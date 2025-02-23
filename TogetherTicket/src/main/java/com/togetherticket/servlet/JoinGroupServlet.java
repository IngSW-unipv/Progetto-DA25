package com.togetherticket.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/JoinGroupServlet")
public class JoinGroupServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Recupera la sessione senza crearne una nuova se non esiste
        HttpSession session = request.getSession(false);
        if (session == null) {
            System.out.println("JoinGroupServlet: Nessuna sessione trovata.");
            response.sendRedirect("login.html");
            return;
        }
        
        // Recupera l'attributo "user_id" dalla sessione
        Object userIdObj = session.getAttribute("user_id");
        if (userIdObj == null) {
            System.out.println("JoinGroupServlet: Attributo user_id non trovato nella sessione.");
            response.sendRedirect("login.html");
            return;
        }
        Integer userId = (Integer) userIdObj;
        System.out.println("JoinGroupServlet: user_id dalla sessione = " + userId);
        
        // Recupera i parametri inviati dal form
        String gruppoIdStr = request.getParameter("group"); // ID del gruppo selezionato
        String eventoId = request.getParameter("eventoId");    // Evento (ID o nome, a seconda della logica)
        
        if (gruppoIdStr == null || gruppoIdStr.isEmpty() || eventoId == null || eventoId.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Dati mancanti.");
            return;
        }
        
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO Membri (gruppo_id, utente_id, ruolo) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, Integer.parseInt(gruppoIdStr));
                stmt.setInt(2, userId);
                stmt.setString(3, "membro"); // Imposta il ruolo "membro"
                
                int result = stmt.executeUpdate();
                if (result > 0) {
                    // Inserimento riuscito: reindirizza alla pagina di conferma
                    response.sendRedirect("unito_gruppo.html?eventoId=" + eventoId + "&gruppoId=" + gruppoIdStr);
                } else {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore nell'unirsi al gruppo.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore nel database: " + e.getMessage());
        }
    }
}



