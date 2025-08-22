package com.eurafric.entreprise.service;

import com.eurafric.entreprise.entity.Report;
import com.eurafric.entreprise.repository.ReportRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReportService {

    private final ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public Report createReport(Report report) {
        return reportRepository.save(report);
    }

    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    public Report getReportById(Long id) {
        return reportRepository.findById(id).orElse(null);
    }

    public List<Report> getReportsByStatus(String status) {
        return reportRepository.findByStatus(status);
    }

    public List<Report> getReportsByType(String type) {
        return reportRepository.findByType(type);
    }

    public List<Report> getReportsByAgency(String agency) {
        return reportRepository.findByAgency(agency);
    }

    public void deleteReport(Long id) {
        reportRepository.deleteById(id);
    }

    public Report updateReport(Report report) {
        return reportRepository.save(report);
    }
}
