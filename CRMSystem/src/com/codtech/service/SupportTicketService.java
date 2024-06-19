package com.codtech.service;

import com.codtech.dao.SupportTicketDAO;
import com.codtech.dao.SupportTicketDAOImpl;
import com.codtech.model.SupportTicket;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class SupportTicketService {

    private SupportTicketDAO supportTicketDAO;

    public SupportTicketService() {
        try {
            // Establish database connection
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/codtech_crm", "root",
                    "chandu@93467");
            supportTicketDAO = new SupportTicketDAOImpl(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addSupportTicket(SupportTicket ticket) {
        supportTicketDAO.addSupportTicket(ticket);
    }

    public void updateSupportTicket(SupportTicket ticket) {
        supportTicketDAO.updateSupportTicket(ticket);
    }

    public void deleteSupportTicket(int ticketId) {
        supportTicketDAO.deleteSupportTicket(ticketId);
    }

    public SupportTicket getSupportTicketById(int ticketId) {
        return supportTicketDAO.getSupportTicketById(ticketId);
    }

    public List<SupportTicket> getAllSupportTickets() {
        return supportTicketDAO.getAllSupportTickets();
    }
}
