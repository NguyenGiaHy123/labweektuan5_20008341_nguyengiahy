package com.example.labweektuan5_20008341_nguyengiahy.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "job")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private long id;
    @Column(name = "job_name", nullable = false)
    private String name;
    @Column(name = "job_desc", length = 2000, nullable = false)
    private String description;
    @ManyToOne
    @JoinColumn(name = "company")
    private Company company;
    @OneToMany(mappedBy = "job")
    private List<JobSkill> jobSkills;

    public Job() {
    }

    public Job(String name, String description, Company company, List<JobSkill> jobSkills) {
        this.name = name;
        this.description = description;
        this.company = company;
        this.jobSkills = jobSkills;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<JobSkill> getJobSkills() {
        return jobSkills;
    }

    public void setJobSkills(List<JobSkill> jobSkills) {
        this.jobSkills = jobSkills;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
//                ", company=" + company +
                ", jobSkills=" + jobSkills +
                '}';
    }
}
