package com.example.labweektuan5_20008341_nguyengiahy.models;

import com.neovisionaries.i18n.CountryCode;
import jakarta.persistence.*;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "add_id")
    private long id;
    @Column(length = 7)
    private String zipcode;
    @Column(length = 30)
    private String number;
    @Column(length = 150)
    private String street;
    @Column(length = 50)
    private String city;
    @Column(length = 30)
    private CountryCode country = CountryCode.VN;

    public Address() {
    }

    public Address(long id, String zipcode, String number, String street, String city, CountryCode country) {
        this.id = id;
        this.zipcode = zipcode;
        this.number = number;
        this.street = street;
        this.city = city;
        this.country = country;
    }

    public Address(String zipcode, String number, String street, String city, CountryCode country) {
        this.zipcode = zipcode;
        this.number = number;
        this.street = street;
        this.city = city;
        this.country = country;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public CountryCode getCountry() {
        return country;
    }

    public void setCountry(CountryCode country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", zipcode='" + zipcode + '\'' +
                ", number='" + number + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", country=" + country.getName() +
                '}';
    }
}
