package com.codtech.dao;

import com.codtech.model.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDAOImpl implements ClientDAO {

    private Connection connection;

    public ClientDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addClient(Client client) {
        String query = "INSERT INTO clients (firstName, lastName, email, phone, address) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, client.getFirstName());
            stmt.setString(2, client.getLastName());
            stmt.setString(3, client.getEmail());
            stmt.setString(4, client.getPhone());
            stmt.setString(5, client.getAddress());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception as needed
        }
    }

    @Override
    public void updateClient(Client client) {
        String query = "UPDATE clients SET firstName=?, lastName=?, email=?, phone=?, address=? WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, client.getFirstName());
            stmt.setString(2, client.getLastName());
            stmt.setString(3, client.getEmail());
            stmt.setString(4, client.getPhone());
            stmt.setString(5, client.getAddress());
            stmt.setInt(6, client.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception as needed
        }
    }

    @Override
    public void deleteClient(int clientId) {
        String query = "DELETE FROM clients WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, clientId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception as needed
        }
    }

    @Override
    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        String query = "SELECT id, firstName, lastName, email, phone, address FROM clients";
        try (PreparedStatement stmt = connection.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String address = rs.getString("address");

                Client client = new Client(firstName, lastName, email, phone, address);
                client.setId(id);
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception as needed
        }
        return clients;
    }

    @Override
    public Client getClientById(int clientId) {
        String query = "SELECT id, firstName, lastName, email, phone, address FROM clients WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, clientId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String firstName = rs.getString("firstName");
                    String lastName = rs.getString("lastName");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    String address = rs.getString("address");

                    Client client = new Client(firstName, lastName, email, phone, address);
                    client.setId(clientId);
                    return client;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception as needed
        }
        return null;
    }
}
