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

//Servlet per l'aggiunta di un nuovo evento al database da parte dell'admin. Riceve i dati dell'evento da una richiesta POST e li inserisce nella tabella 'Eventi'.

@WebServlet("/AddEventServlet")
public class AddEventServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)   //Metodo che gestisce le richieste POST per aggiungere un nuovo evento.
            throws ServletException, IOException {

        // Recupero dei parametri inviati dal form HTML
        String eventName = request.getParameter("event_name");
        String eventDescription = request.getParameter("event_description");
        String eventDate = request.getParameter("event_date");  // formato "YYYY-MM-DDTHH:mm"
        String eventLocation = request.getParameter("event_location");
        String eventPriceStr = request.getParameter("event_price"); // nuovo parametro

        double eventPrice = 0.0;
        
        try {
            // Conversione del prezzo da stringa a double
            eventPrice = Double.parseDouble(eventPriceStr);
        } catch (NumberFormatException e) {
            // Se il prezzo non è valido, stampa l'errore e reindirizza alla pagina degli admin con un messaggio di errore
            e.printStackTrace();
            response.sendRedirect("admin.html?error=Prezzo%20non%20valido");
            return;  // Interrompe l'esecuzione del metodo
        }

         // Verifica e formatta correttamente la data
        if (eventDate != null && !eventDate.isEmpty()) {
            // Sostituisce 'T' con spazio e aggiunge ":00" per formattare la data nel formato corretto per il database
            eventDate = eventDate.replace("T", " ") + ":00";  // Es: "2025-02-16 10:30:00"
        }

        // Log per verificare i parametri ricevuti(solo per eventuale verifica su Eclipse in caso di necessita')
        System.out.println("Nome evento: " + eventName);
        System.out.println("Descrizione evento: " + eventDescription);
        System.out.println("Data evento: " + eventDate);
        System.out.println("Luogo evento: " + eventLocation);
        System.out.println("Prezzo evento: " + eventPrice);

       // Connessione al database e inserimento dei dati
        try (Connection connection = DatabaseConnection.getConnection()) {
            // Query SQL per inserire un nuovo evento nella tabella 'Eventi'
            String query = "INSERT INTO Eventi (nome, descrizione, data_evento, luogo, prezzo) VALUES (?, ?, ?, ?, ?)";
            
            // Creazione del PreparedStatement per evitare SQL Injection
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                // Assegnazione dei valori ai placeholder della query
                statement.setString(1, eventName);
                statement.setString(2, eventDescription);
                statement.setString(3, eventDate);
                statement.setString(4, eventLocation);
                statement.setDouble(5, eventPrice);

                // Esecuzione dell'operazione di inserimento
                int rowsAffected = statement.executeUpdate();
                
                if (rowsAffected > 0) {
                    // Inserimento riuscito, reindirizza alla pagina di conferma
                    response.sendRedirect("evento_creato.html");
                } else {
                    // Se non è stato inserito alcun evento, reindirizza con un messaggio di errore
                    response.sendRedirect("admin.html?error=Errore%20nell'aggiunta%20dell'evento");
                }
            }
        } catch (SQLException e) {
            // Gestione di eventuali errori di connessione o query SQL
            e.printStackTrace();
            response.sendRedirect("admin.html?error=Errore%20di%20connessione%20al%20database");
        }
    }
}


