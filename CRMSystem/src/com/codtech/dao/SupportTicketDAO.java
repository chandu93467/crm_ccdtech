package com.codtech.dao;

import com.codtech.model.SupportTicket;

import java.util.List;

public interface SupportTicketDAO {
    void addSupportTicket(SupportTicket ticket);

    void updateSupportTicket(SupportTicket ticket);

    void deleteSupportTicket(int ticketId);

    SupportTicket getSupportTicketById(int ticketId);

    List<SupportTicket> getAllSupportTickets();
}
