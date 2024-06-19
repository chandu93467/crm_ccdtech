package com.codtech.service;

import com.codtech.dao.ReportDAO;
import com.codtech.dao.ReportDAOImpl;
import com.codtech.model.Report;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class ReportService {

    private ReportDAO reportDAO;

    public ReportService() {
        try {
            // Establish database connection
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/codtech_crm", "root",
                    "chandu@93467");
            reportDAO = new ReportDAOImpl(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addReport(Report report) {
        reportDAO.addReport(report);
    }

    public void deleteReport(int reportId) {
        reportDAO.deleteReport(reportId);
    }

    public Report getReportById(int reportId) {
        return reportDAO.getReportById(reportId);
    }

    public List<Report> getAllReports() {
        return reportDAO.getAllReports();
    }
}
