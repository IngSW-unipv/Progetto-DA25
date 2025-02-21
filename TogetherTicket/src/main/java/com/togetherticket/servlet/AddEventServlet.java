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

@WebServlet("/AddEventServlet")
public class AddEventServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String eventName = request.getParameter("event_name");
        String eventDescription = request.getParameter("event_description");
        String eventDate = request.getParameter("event_date");  // formato "YYYY-MM-DDTHH:mm"
        String eventLocation = request.getParameter("event_location");
        String eventPriceStr = request.getParameter("event_price"); // nuovo parametro

        double eventPrice = 0.0;
        try {
            eventPrice = Double.parseDouble(eventPriceStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("admin.html?error=Prezzo%20non%20valido");
            return;
        }

        // Verifica e formatta correttamente la data
        if (eventDate != null && !eventDate.isEmpty()) {
            // Sostituisci 'T' con spazio e aggiungi i secondi
            eventDate = eventDate.replace("T", " ") + ":00";  // Es: "2025-02-16 10:30:00"
        }

        // Log per verificare i parametri ricevuti
        System.out.println("Nome evento: " + eventName);
        System.out.println("Descrizione evento: " + eventDescription);
        System.out.println("Data evento: " + eventDate);
        System.out.println("Luogo evento: " + eventLocation);
        System.out.println("Prezzo evento: " + eventPrice);

        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO Eventi (nome, descrizione, data_evento, luogo, prezzo) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, eventName);
                statement.setString(2, eventDescription);
                statement.setString(3, eventDate);
                statement.setString(4, eventLocation);
                statement.setDouble(5, eventPrice);

                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    // Successo, reindirizza alla pagina di conferma
                    response.sendRedirect("evento_creato.html");
                } else {
                    response.sendRedirect("admin.html?error=Errore%20nell'aggiunta%20dell'evento");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("admin.html?error=Errore%20di%20connessione%20al%20database");
        }
    }
}


