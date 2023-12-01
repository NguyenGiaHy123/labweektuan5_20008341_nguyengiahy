package com.example.labweektuan5_20008341_nguyengiahy.models;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "experience")
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exp_id")
    private long id;
    @ManyToOne
    @JoinColumn(name = "can_id")
    private Candidate candidate;
    @Column(name = "from_date",nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fromDate;
    @Column(name = "to_date",nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate toDate;
    @Column(name = "company",nullable = false, length = 120)
    private String companyName;
    @Column(name = "role",nullable = false, length = 100)
    private String role;
    @Column(name = "work_desc",nullable = false, length = 1000)
    private String workDescription;

    public Experience() {
    }

    public Experience(long id, Candidate candidate, LocalDate fromDate, LocalDate toDate, String companyName, String role, String workDescription) {
        this.id = id;
        this.candidate = candidate;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.companyName = companyName;
        this.role = role;
        this.workDescription = workDescription;
    }

    public Experience(Candidate candidate, LocalDate fromDate, LocalDate toDate, String companyName, String role, String workDescription) {
        this.candidate = candidate;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.companyName = companyName;
        this.role = role;
        this.workDescription = workDescription;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getWorkDescription() {
        return workDescription;
    }

    public void setWorkDescription(String workDescription) {
        this.workDescription = workDescription;
    }

    @Override
    public String toString() {
        return "Experience{" +
                "id=" + id +
                ", candidate=" + candidate +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", companyName='" + companyName + '\'' +
                ", role='" + role + '\'' +
                ", workDescription='" + workDescription + '\'' +
                '}';
    }
}
