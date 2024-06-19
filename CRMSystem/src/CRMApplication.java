import com.codtech.model.Client;
import com.codtech.model.SupportTicket;
import com.codtech.model.Report;
import com.codtech.service.ClientService;
import com.codtech.service.SupportTicketService;
import com.codtech.service.ReportService;

import java.util.List;
import java.util.Scanner;

public class CRMApplication {
    private static ClientService clientService = new ClientService();
    private static SupportTicketService supportTicketService = new SupportTicketService();
    private static ReportService reportService = new ReportService();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nSelect operation:");
            System.out.println("1. Add Client");
            System.out.println("2. Update Client");
            System.out.println("3. Delete Client");
            System.out.println("4. View All Clients");
            System.out.println("5. Add Support Ticket");
            System.out.println("6. Update Support Ticket");
            System.out.println("7. Delete Support Ticket");
            System.out.println("8. View All Support Tickets");
            System.out.println("9. Add Report");
            System.out.println("10. Delete Report");
            System.out.println("11. View All Reports");
            System.out.println("12. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character after nextInt()

            switch (choice) {
                case 1:
                    addClient(scanner);
                    break;
                case 2:
                    updateClient(scanner);
                    break;
                case 3:
                    deleteClient(scanner);
                    break;
                case 4:
                    viewAllClients();
                    break;
                case 5:
                    addSupportTicket(scanner);
                    break;
                case 6:
                    updateSupportTicket(scanner);
                    break;
                case 7:
                    deleteSupportTicket(scanner);
                    break;
                case 8:
                    viewAllSupportTickets();
                    break;
                case 9:
                    addReport(scanner);
                    break;
                case 10:
                    deleteReport(scanner);
                    break;
                case 11:
                    viewAllReports();
                    break;
                case 12:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private static void addClient(Scanner scanner) {
        System.out.println("Enter first name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter last name:");
        String lastName = scanner.nextLine();
        System.out.println("Enter email:");
        String email = scanner.nextLine();
        System.out.println("Enter phone:");
        String phone = scanner.nextLine();
        System.out.println("Enter address:");
        String address = scanner.nextLine();

        Client client = new Client(firstName, lastName, email, phone, address);
        clientService.addClient(client);
        System.out.println("Client added successfully.");
    }

    private static void updateClient(Scanner scanner) {
        System.out.println("Enter client ID to update:");
        int clientId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character after nextInt()

        Client client = clientService.getClientById(clientId);
        if (client == null) {
            System.out.println("Client not found with ID: " + clientId);
            return;
        }

        System.out.println("Enter new first name (leave blank to keep unchanged):");
        String firstName = scanner.nextLine();
        if (!firstName.isBlank()) {
            client.setFirstName(firstName);
        }

        System.out.println("Enter new last name (leave blank to keep unchanged):");
        String lastName = scanner.nextLine();
        if (!lastName.isBlank()) {
            client.setLastName(lastName);
        }

        System.out.println("Enter new email (leave blank to keep unchanged):");
        String email = scanner.nextLine();
        if (!email.isBlank()) {
            client.setEmail(email);
        }

        System.out.println("Enter new phone (leave blank to keep unchanged):");
        String phone = scanner.nextLine();
        if (!phone.isBlank()) {
            client.setPhone(phone);
        }

        System.out.println("Enter new address (leave blank to keep unchanged):");
        String address = scanner.nextLine();
        if (!address.isBlank()) {
            client.setAddress(address);
        }

        clientService.updateClient(client);
        System.out.println("Client updated successfully.");
    }

    private static void deleteClient(Scanner scanner) {
        System.out.println("Enter client ID to delete:");
        int clientId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character after nextInt()

        clientService.deleteClient(clientId);
        System.out.println("Client deleted successfully.");
    }

    private static void viewAllClients() {
        List<Client> clients = clientService.getAllClients();
        if (clients.isEmpty()) {
            System.out.println("No clients found.");
        } else {
            System.out.println("List of Clients:");
            for (Client client : clients) {
                System.out.println(client.getId() + ": " + client.getFirstName() + " " + client.getLastName() +
                        ", " + client.getEmail() + ", " + client.getPhone() + ", " + client.getAddress());
            }
        }
    }

    private static void addSupportTicket(Scanner scanner) {
        System.out.println("Enter client ID:");
        int clientId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character after nextInt()
        System.out.println("Enter description:");
        String description = scanner.nextLine();
        System.out.println("Enter status:");
        String status = scanner.nextLine();

        SupportTicket ticket = new SupportTicket(clientId, description, status);
        supportTicketService.addSupportTicket(ticket);
        System.out.println("Support ticket added successfully.");
    }

    private static void updateSupportTicket(Scanner scanner) {
        System.out.println("Enter support ticket ID to update:");
        int ticketId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character after nextInt()

        SupportTicket ticket = supportTicketService.getSupportTicketById(ticketId);
        if (ticket == null) {
            System.out.println("Support ticket not found with ID: " + ticketId);
            return;
        }

        System.out.println("Enter new client ID (leave blank to keep unchanged):");
        String clientIdInput = scanner.nextLine();
        if (!clientIdInput.isBlank()) {
            int clientId = Integer.parseInt(clientIdInput);
            ticket.setClientId(clientId);
        }

        System.out.println("Enter new description (leave blank to keep unchanged):");
        String description = scanner.nextLine();
        if (!description.isBlank()) {
            ticket.setDescription(description);
        }

        System.out.println("Enter new status (leave blank to keep unchanged):");
        String status = scanner.nextLine();
        if (!status.isBlank()) {
            ticket.setStatus(status);
        }

        supportTicketService.updateSupportTicket(ticket);
        System.out.println("Support ticket updated successfully.");
    }

    private static void deleteSupportTicket(Scanner scanner) {
        System.out.println("Enter support ticket ID to delete:");
        int ticketId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character after nextInt()

        supportTicketService.deleteSupportTicket(ticketId);
        System.out.println("Support ticket deleted successfully.");
    }

    private static void viewAllSupportTickets() {
        List<SupportTicket> tickets = supportTicketService.getAllSupportTickets();
        if (tickets.isEmpty()) {
            System.out.println("No support tickets found.");
        } else {
            System.out.println("List of Support Tickets:");
            for (SupportTicket ticket : tickets) {
                System.out.println(ticket.getId() + ": Client ID - " + ticket.getClientId() + ", Description - " +
                        ticket.getDescription() + ", Status - " + ticket.getStatus());
            }
        }
    }

    private static void addReport(Scanner scanner) {
        System.out.println("Enter report title:");
        String title = scanner.nextLine();
        System.out.println("Enter report content:");
        String content = scanner.nextLine();

        Report report = new Report(title, content);
        reportService.addReport(report);
        System.out.println("Report added successfully.");
    }

    private static void deleteReport(Scanner scanner) {
        System.out.println("Enter report ID to delete:");
        int reportId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character after nextInt()

        reportService.deleteReport(reportId);
        System.out.println("Report deleted successfully.");
    }

    private static void viewAllReports() {
        List<Report> reports = reportService.getAllReports();
        if (reports.isEmpty()) {
            System.out.println("No reports found.");
        } else {
            System.out.println("List of Reports:");
            for (Report report : reports) {
                System.out.println(
                        report.getId() + ": " + report.getTitle() + ", Generated At: " + report.getGeneratedAt() +
                                "\nContent:\n" + report.getContent());
            }
        }
    }
}