package com.atlantbh.cinebh.core.models;

import java.util.Set;
import java.util.UUID;
import jakarta.persistence.*;

@Entity
public class Venue {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(updatable = false, nullable = false)
    private UUID id;
    private String name;
    private String phone;
    private String street;
    private String streetNumber;
    private String city;
    private String imageURL;
    @OneToMany(mappedBy = "venue")
    private Set<Projection> projections;

    public Venue() {
    }

    public Venue(UUID id, String name, String phone, String street, String streetNumber, String city, String imageURL, Set<Projection> projections) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.street = street;
        this.streetNumber = streetNumber;
        this.city = city;
        this.imageURL = imageURL;
        this.projections = projections;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Set<Projection> getProjections() {
        return projections;
    }

    public void setProjections(Set<Projection> projections) {
        this.projections = projections;
    }
}

