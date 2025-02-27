package com.togetherticket.servlet;

//Servlet per il corretto funzionamento della registrazione sul sito

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

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Recupero i parametri compilati nel form HTML da inserire all'interno del database
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");

        if (username == null || email == null || password == null || nome == null || cognome == null) {
            request.setAttribute("error", "Errore nei dati inviati.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            
            // Controlla se esiste già un utente con lo stesso username o email
            String checkSql = "SELECT id FROM Utenti WHERE username = ? OR email = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                checkStmt.setString(1, username);
                checkStmt.setString(2, email);
                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (rs.next()) {
                        request.setAttribute("error", "Username o email già esistenti.");
                        request.getRequestDispatcher("register.jsp").forward(request, response);
                        return;
                    }
                }
            }
            
            // Inserisci i dati nel database
            String sql = "INSERT INTO Utenti (username, email, password, nome, cognome) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                stmt.setString(2, email);
                stmt.setString(3, password); 
                stmt.setString(4, nome);
                stmt.setString(5, cognome);

                int rowsInserted = stmt.executeUpdate();
                if (rowsInserted > 0) {
                    response.sendRedirect("login.html");
                } else {
                    request.setAttribute("error", "Errore nella registrazione.");
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Errore nel database: " + e.getMessage());
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}

