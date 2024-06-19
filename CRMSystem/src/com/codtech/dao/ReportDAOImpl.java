package com.codtech.dao;

import com.codtech.model.Report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReportDAOImpl implements ReportDAO {

    private Connection connection;

    public ReportDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Report> getAllReports() {
        List<Report> reports = new ArrayList<>();
        String query = "SELECT id, title, content, generated_at FROM reports";

        try (PreparedStatement stmt = connection.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                LocalDateTime generatedAt = rs.getTimestamp("generated_at").toLocalDateTime();

                Report report = new Report(id, title, content, generatedAt);
                reports.add(report);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception as needed
        }

        return reports;
    }

    @Override
    public Report getReportById(int id) {
        Report report = null;
        String query = "SELECT title, content, generated_at FROM reports WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String title = rs.getString("title");
                String content = rs.getString("content");
                LocalDateTime generatedAt = rs.getTimestamp("generated_at").toLocalDateTime();

                report = new Report(id, title, content, generatedAt);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception as needed
        }

        return report;
    }

    @Override
    public void addReport(Report report) {
        String query = "INSERT INTO reports (title, content, generated_at) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, report.getTitle());
            stmt.setString(2, report.getContent());
            stmt.setTimestamp(3, java.sql.Timestamp.valueOf(report.getGeneratedAt()));

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Report added successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception as needed
        }
    }

    @Override
    public void updateReport(Report report) {
        String query = "UPDATE reports SET title = ?, content = ?, generated_at = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, report.getTitle());
            stmt.setString(2, report.getContent());
            stmt.setTimestamp(3, java.sql.Timestamp.valueOf(report.getGeneratedAt()));
            stmt.setInt(4, report.getId());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Report updated successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception as needed
        }
    }

    @Override
    public void deleteReport(int id) {
        String query = "DELETE FROM reports WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Report deleted successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception as needed
        }
    }
}
