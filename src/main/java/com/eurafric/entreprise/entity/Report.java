package com.eurafric.entreprise.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reports")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reports_seq_gen")
    @SequenceGenerator(name = "reports_seq_gen", sequenceName = "reports_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String type;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    private String agency;
    private String statistics;
    private String comments;
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public LocalDateTime getDateCreated() { return dateCreated; }
    public void setDateCreated(LocalDateTime dateCreated) { this.dateCreated = dateCreated; }
    public String getAgency() { return agency; }
    public void setAgency(String agency) { this.agency = agency; }
    public String getStatistics() { return statistics; }
    public void setStatistics(String statistics) { this.statistics = statistics; }
    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public User getAuthor() { return author; }
    public void setAuthor(User author) { this.author = author; }
}
