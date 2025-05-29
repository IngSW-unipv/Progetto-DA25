package application;

import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;

public class MainApp {
    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            if (conn != null) {
                System.out.println("Connessione al database riuscita!");
            } else {
                System.out.println("Connessione al database fallita.");
            }
        } catch (SQLException e) {
            System.out.println("Errore durante la connessione al database.");
            e.printStackTrace();
        }
    }
}
