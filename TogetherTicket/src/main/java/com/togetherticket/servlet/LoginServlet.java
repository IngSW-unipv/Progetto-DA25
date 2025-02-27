package com.togetherticket.servlet;

//servlet per il corretto funzionamento del login sul sito web, dividendo login per utenti e login per admin

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.togetherticket.servlet.DatabaseConnection;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //recupero i parametri nel form HTML
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Controlla le credenziali nel database
        try {
            // Connessione al database
            Connection connection = DatabaseConnection.getConnection();
            
            // Query per recuperare le informazioni dell'utente
            String query = "SELECT * FROM Utenti WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);  // Qui dovresti usare una versione sicura della password, come bcrypt
            
            ResultSet resultSet = statement.executeQuery();
            System.out.println("Username: " + username);  //li mostro sulla console solo per comodita' e per vedere l'utente loggato in questo momento
            System.out.println("Password: " + password);

            if (resultSet.next()) {
                // Recupera l'ID dell'utente
                int userId = resultSet.getInt("id");
                // Recupera il ruolo dell'utente
                String ruolo = resultSet.getString("ruolo");

                // Imposta l'attributo nella sessione per memorizzare l'ID dell'utente
                request.getSession().setAttribute("user_id", userId);
                System.out.println("ID utente: " + userId);  // Debug

                // Imposta anche il username e il ruolo
                request.getSession().setAttribute("username", username);
                request.getSession().setAttribute("ruolo", ruolo);

                // Se l'utente è un amministratore, redirigilo alla pagina admin.html
                if ("admin".equalsIgnoreCase(ruolo)) {
                    response.sendRedirect("admin.html"); // Pagina per l'admin
                } else {
                    // Altrimenti reindirizza alla pagina eventi (events.html)
                    response.sendRedirect("events.html");
                }
            } else {
                // Credenziali errate: imposta un attributo e inoltra la richiesta al login.jsp
                request.setAttribute("error", "Credenziali errate");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("Errore di connessione al database.");
        }
    }
}



