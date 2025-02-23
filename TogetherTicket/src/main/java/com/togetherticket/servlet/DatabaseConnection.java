package com.togetherticket.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/TT";
    private static final String USER = "root"; // Cambia se usi un altro utente
    private static final String PASSWORD = "Ciao123!"; // Cambia se hai una password

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Assicura che il driver venga caricato
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver MySQL non trovato!", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
