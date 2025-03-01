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

//Servlet per la rimozione di un evento dal database. Gestisce le richieste DELETE per eliminare un evento e le sue relazioni.
    
@WebServlet("/DeleteEventServlet")
public class DeleteEventServlet extends HttpServlet {

    @Override  //Override per utilizzare il metodo doDelete della classe HttpServlet
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Recupero il nome dell'evento da rimuovere(quello su cui ho premuto rimuovi evento)
        String eventName = request.getParameter("event_name");

        //if per gestire il caso in cui non dovessi avere un nome per l'evento o fosse una stringa vuota
        if (eventName == null || eventName.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Nome evento non valido\"}");
            return;
        }

        //la servlet stabilisce la connessione al database sql tramite il metodo .getConnection proprio della classe DatabaseConnection
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false); // Inizio transazione  //disabilito il commit automatico perche' stiamo eseguendo piu' operazioni di scrittura(cancellazione membri, poi gruppi e infine evento) e vogliamo 
            //che tutte vengano eseguite correttamente prima di confermare i cambiamenti nel database

            // Step 1: Ottenere l'ID dell'evento
            String getEventIdQuery = "SELECT id FROM Eventi WHERE nome = ?";
            int eventId = -1;

            try (PreparedStatement stmt = conn.prepareStatement(getEventIdQuery)) {
                stmt.setString(1, eventName);
                var rs = stmt.executeQuery();
                if (rs.next()) {
                    eventId = rs.getInt("id");
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().write("{\"error\": \"Evento non trovato\"}");
                    return;
                }
            }

            // Step 2: Eliminare i membri dei gruppi legati all'evento
            String deleteMembriQuery = "DELETE FROM Membri WHERE gruppo_id IN (SELECT id FROM Gruppi WHERE evento_id = ?)";
            try (PreparedStatement stmt = conn.prepareStatement(deleteMembriQuery)) {
                stmt.setInt(1, eventId);
                stmt.executeUpdate();
            }

            // Step 3: Eliminare i gruppi dell'evento
            String deleteGruppiQuery = "DELETE FROM Gruppi WHERE evento_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(deleteGruppiQuery)) {
                stmt.setInt(1, eventId);
                stmt.executeUpdate();
            }

            // Step 4: Eliminare l'evento
            String deleteEventoQuery = "DELETE FROM Eventi WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(deleteEventoQuery)) {
                stmt.setInt(1, eventId);
                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    conn.commit(); // Conferma transazione
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.getWriter().write("{\"message\": \"Evento rimosso con successo\"}");
                } else {
                    conn.rollback(); // Annulla operazione in caso di problemi
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().write("{\"error\": \"Errore nella rimozione dell'evento\"}");
                }
            }
        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"Errore nel database\", \"details\": \"" + e.getMessage() + "\"}");
            e.printStackTrace();
        }
    }
}



