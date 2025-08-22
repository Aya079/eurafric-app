package com.eurafric.entreprise.repository;

import com.eurafric.entreprise.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findByStatus(String status);
    List<Report> findByType(String type);
    List<Report> findByAgency(String agency);
    List<Report> findByAuthorId(Long userId);
}
