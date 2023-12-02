package com.example.labweektuan5_20008341_nguyengiahy.models;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "candidate")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "can_id")
    private long id;
    @Column(name = "full_name", nullable = false)
    private String fullName;
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;
    @Column(length = 15, nullable = false, unique = true)
    private String phone;
    @Column(nullable = false, unique = true)
    private String email;
    @OneToOne
    @JoinColumn(name = "address", nullable = false)
    private Address address;
    @OneToMany(mappedBy = "candidate")
    private List<CandidateSkill> candidateSkills;
    @OneToMany(mappedBy = "candidate")
    private List<Experience> experiences;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Candidate() {
    }

    public Candidate(long id, String fullName, LocalDate dob, String phone, String email, Address address, List<CandidateSkill> candidateSkills, List<Experience> experiences, User user) {
        this.id = id;
        this.fullName = fullName;
        this.dob = dob;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.candidateSkills = candidateSkills;
        this.experiences = experiences;
        this.user = user;
    }

    public Candidate(String fullName, LocalDate dob, String phone, String email, Address address, List<CandidateSkill> candidateSkills, List<Experience> experiences, User user) {
        this.fullName = fullName;
        this.dob = dob;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.candidateSkills = candidateSkills;
        this.experiences = experiences;
        this.user = user;
    }

    public Candidate(String fullName, LocalDate dob, String phone, String email, Address address) {
        this.fullName = fullName;
        this.dob = dob;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }
    public Candidate getCandidate() {
        return this;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<CandidateSkill> getCandidateSkills() {
        return candidateSkills;
    }

    public void setCandidateSkills(List<CandidateSkill> candidateSkills) {
        this.candidateSkills = candidateSkills;
    }

    public List<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<Experience> experiences) {
        this.experiences = experiences;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", dob=" + dob +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                ", candidateSkills=" + candidateSkills +
                ", experiences=" + experiences +
                ", user=" + user +
                '}';
    }
}
