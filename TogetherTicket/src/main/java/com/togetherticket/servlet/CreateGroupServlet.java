package com.togetherticket.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/CreateGroupServlet")
public class CreateGroupServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("CreateGroupServlet: Ricevuta richiesta POST");  // Debug

        // Ottieni i parametri dal form
        String nomeGruppo = request.getParameter("group_name");
        String descrizione = request.getParameter("group_description");
        String eventoNome = request.getParameter("evento_nome");

        System.out.println("Nome gruppo: " + nomeGruppo);
        System.out.println("Descrizione: " + descrizione);
        System.out.println("Evento Nome: " + eventoNome);

        // Controlla se l'utente è autenticato verificando la sessione
        Integer userId = (Integer) request.getSession().getAttribute("user_id");
        if (userId == null) {
            // Se l'ID utente non è presente, significa che l'utente non è loggato
            response.sendRedirect("login.html?error=Utente%20non%20identificato");
            return;
        }

        // Controlla se i dati sono nulli
        if (nomeGruppo == null || descrizione == null || eventoNome == null) {
            System.out.println("Errore: uno o più parametri sono nulli.");
            response.getWriter().write("Errore nei dati inviati.");
            return;
        }

        // Ottieni l'ID dell'utente creatore dalla sessione
        HttpSession session = request.getSession();
        Integer creatoreId = (Integer) session.getAttribute("user_id");

        System.out.println("Creatore ID: " + creatoreId);

        if (creatoreId == null) {
            System.out.println("Errore: utente non autenticato.");
            response.sendRedirect("login.html");
            return;
        }

        // Connessione al database per ottenere l'ID dell'evento
        int eventoId = -1;
        try (Connection conn = DatabaseConnection.getConnection()) {
            // Query per ottenere l'ID dell'evento
            String eventoQuery = "SELECT id FROM Eventi WHERE nome = ?";
            try (PreparedStatement eventoStmt = conn.prepareStatement(eventoQuery)) {
                eventoStmt.setString(1, eventoNome);
                try (ResultSet rs = eventoStmt.executeQuery()) {
                    if (rs.next()) {
                        eventoId = rs.getInt("id");
                    } else {
                        System.out.println("Errore: evento non trovato.");
                        response.getWriter().write("Errore: evento non trovato.");
                        return;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("Errore nel recupero dell'evento: " + e.getMessage());
            return;
        }

        // Connessione al database per inserire il gruppo
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO Gruppi (nome_gruppo, descrizione, creatore_id, evento_id) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, nomeGruppo);
                stmt.setString(2, descrizione);
                stmt.setInt(3, creatoreId);
                stmt.setInt(4, eventoId);  // Usa l'ID dell'evento

                int rowsInserted = stmt.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Gruppo creato con successo!");
                    response.sendRedirect("gruppo_creato.html?evento_id=" + java.net.URLEncoder.encode(eventoNome, "UTF-8"));

                } else {
                    System.out.println("Errore nella creazione del gruppo.");
                    response.getWriter().write("Errore nella creazione del gruppo.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("Errore nel database: " + e.getMessage());
        }
    }
}





