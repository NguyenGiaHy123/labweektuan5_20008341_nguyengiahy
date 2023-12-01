package com.example.labweektuan5_20008341_nguyengiahy.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "com_id")
    private long id;
    @Column(name = "comp_name", nullable = false)
    private String name;
    @Column(length = 1000)
    private String about;
    @OneToOne
    @JoinColumn(name = "address", nullable = false)
    private Address address;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String email;
    @Column(name = "web_url")
    private String webURL;
    @OneToMany(mappedBy = "company")
    private List<Job> jobs;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Company() {
    }

    public Company(long id, String name, String about, Address address, String phone, String email, String webURL, List<Job> jobs, User user) {
        this.id = id;
        this.name = name;
        this.about = about;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.webURL = webURL;
        this.jobs = jobs;
        this.user = user;
    }

    public Company(String name, String about, Address address, String phone, String email, String webURL, List<Job> jobs, User user) {
        this.name = name;
        this.about = about;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.webURL = webURL;
        this.jobs = jobs;
        this.user = user;
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

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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

    public String getWebURL() {
        return webURL;
    }

    public void setWebURL(String webURL) {
        this.webURL = webURL;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", about='" + about + '\'' +
                ", address=" + address +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", webURL='" + webURL + '\'' +
                ", jobs=" + jobs +
                ", user=" + user +
                '}';
    }
}
