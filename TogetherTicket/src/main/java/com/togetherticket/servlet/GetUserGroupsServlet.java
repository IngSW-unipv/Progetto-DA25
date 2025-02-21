package com.togetherticket.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/GetUserGroupsServlet")
public class GetUserGroupsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Verifica se l'utente è loggato
        Integer userId = (Integer) request.getSession().getAttribute("user_id");
        if (userId == null) {
            response.sendRedirect("login.html");
            return;
        }

        // Recupera i gruppi dell'utente
        List<Group> groups = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT g.nome_gruppo, g.descrizione, e.nome AS evento_nome " +
                         "FROM Gruppi g " +
                         "JOIN Eventi e ON g.evento_id = e.id_evento " +
                         "WHERE g.creatore_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, userId);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    Group group = new Group();
                    group.setNomeGruppo(rs.getString("nome_gruppo"));
                    group.setDescrizione(rs.getString("descrizione"));
                    group.setEventoNome(rs.getString("evento_nome"));
                    groups.add(group);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("Errore nel database.");
            return;
        }

        // Restituisce i gruppi come JSON
        response.setContentType("application/json");
        response.getWriter().write(new com.google.gson.Gson().toJson(groups));
    }

    // Classe interna per rappresentare i gruppi
    private static class Group {
        private String nomeGruppo;
        private String descrizione;
        private String eventoNome;

        public String getNomeGruppo() {
            return nomeGruppo;
        }

        public void setNomeGruppo(String nomeGruppo) {
            this.nomeGruppo = nomeGruppo;
        }

        public String getDescrizione() {
            return descrizione;
        }

        public void setDescrizione(String descrizione) {
            this.descrizione = descrizione;
        }

        public String getEventoNome() {
            return eventoNome;
        }

        public void setEventoNome(String eventoNome) {
            this.eventoNome = eventoNome;
        }
    }
}
