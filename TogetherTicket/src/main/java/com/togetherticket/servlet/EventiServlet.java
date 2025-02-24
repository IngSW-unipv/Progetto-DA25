package com.togetherticket.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;

@WebServlet("/eventi")
public class EventiServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        List<Evento> eventi = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT nome, descrizione, data_evento, luogo, prezzo FROM Eventi";
            try (PreparedStatement stmt = conn.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    Evento evento = new Evento(
                        rs.getString("nome"),
                        rs.getString("descrizione"),
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp("data_evento")),
                        rs.getString("luogo"),
                        rs.getDouble("prezzo")
                    );
                    eventi.add(evento);
                }

                String json = new Gson().toJson(eventi);
                response.getWriter().write(json);
            }
        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"Errore nel recupero degli eventi\", \"details\": \"" + e.getMessage() + "\"}");
            e.printStackTrace();
        }
    }
}



