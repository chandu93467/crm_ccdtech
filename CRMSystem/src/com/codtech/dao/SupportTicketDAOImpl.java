package com.codtech.dao;

import com.codtech.model.SupportTicket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupportTicketDAOImpl implements SupportTicketDAO {

    private Connection connection;

    public SupportTicketDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<SupportTicket> getAllSupportTickets() {
        List<SupportTicket> tickets = new ArrayList<>();
        String query = "SELECT id, clientId, title, description, status FROM support_tickets";

        try (PreparedStatement stmt = connection.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                int clientId = rs.getInt("clientId");
                String title = rs.getString("title");
                String description = rs.getString("description");
                String status = rs.getString("status");

                SupportTicket ticket = new SupportTicket(id, clientId, title, description, status);
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception as needed
        }

        return tickets;
    }

    @Override
    public SupportTicket getSupportTicketById(int id) {
        String query = "SELECT clientId, title, description, status FROM support_tickets WHERE id = ?";
        SupportTicket ticket = null;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int clientId = rs.getInt("clientId");
                String title = rs.getString("title");
                String description = rs.getString("description");
                String status = rs.getString("status");

                ticket = new SupportTicket(id, clientId, title, description, status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception as needed
        }

        return ticket;
    }

    @Override
    public void addSupportTicket(SupportTicket ticket) {
        String query = "INSERT INTO support_tickets (clientId, title, description) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, ticket.getClientId());
            stmt.setString(2, ticket.getTitle());
            stmt.setString(3, ticket.getDescription());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception as needed
        }
    }

    @Override
    public void updateSupportTicket(SupportTicket ticket) {
        String query = "UPDATE support_tickets SET clientId = ?, title = ?, description = ?, status = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, ticket.getClientId());
            stmt.setString(2, ticket.getTitle());
            stmt.setString(3, ticket.getDescription());
            stmt.setString(4, ticket.getStatus());
            stmt.setInt(5, ticket.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception as needed
        }
    }

    @Override
    public void deleteSupportTicket(int id) {
        String query = "DELETE FROM support_tickets WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception as needed
        }
    }
}
