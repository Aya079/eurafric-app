package com.eurafric.entreprise.controller;

import com.eurafric.entreprise.entity.Report;
import com.eurafric.entreprise.service.ReportService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    public Report createReport(@RequestBody Report report) {
        return reportService.createReport(report);
    }

    @GetMapping
    public List<Report> getAllReports() {
        return reportService.getAllReports();
    }

    @GetMapping("/{id}")
    public Report getReportById(@PathVariable Long id) {
        return reportService.getReportById(id);
    }

    @GetMapping("/status/{status}")
    public List<Report> getReportsByStatus(@PathVariable String status) {
        return reportService.getReportsByStatus(status);
    }

    @GetMapping("/type/{type}")
    public List<Report> getReportsByType(@PathVariable String type) {
        return reportService.getReportsByType(type);
    }

    @GetMapping("/agency/{agency}")
    public List<Report> getReportsByAgency(@PathVariable String agency) {
        return reportService.getReportsByAgency(agency);
    }

    @PutMapping("/{id}")
    public Report updateReport(@PathVariable Long id, @RequestBody Report report) {
        report.setId(id);
        return reportService.updateReport(report);
    }

    @DeleteMapping("/{id}")
    public void deleteReport(@PathVariable Long id) {
        reportService.deleteReport(id);
    }
}
