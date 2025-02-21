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

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");

        if (username == null || email == null || password == null || nome == null || cognome == null) {
            response.getWriter().write("Errore nei dati inviati.");
            return;
        }

        // Inserisci i dati nel database
        try (Connection conn = DatabaseConnection.getConnection()) {
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
                    response.getWriter().write("Errore nella registrazione.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("Errore nel database: " + e.getMessage());
        }
    }
}



