package com.codtech.dao;

import com.codtech.model.Report;
import java.util.List;

public interface ReportDAO {
    List<Report> getAllReports();

    Report getReportById(int id);

    void addReport(Report report);

    void updateReport(Report report);

    void deleteReport(int id);
}
