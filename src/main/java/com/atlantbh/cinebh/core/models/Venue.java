package com.atlantbh.cinebh.core.models;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Venue {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(updatable = false, nullable = false)
    private UUID id;
    @Column(nullable = false, length = 45)
    private String name;
    @Column(length = 45)
    private String phone;
    @Column(length = 255)
    private String street;
    @Column(length = 45)
    private String streetNumber;
    @Column(length = 45)
    private String city;
    @Column(length = 45)
    private String postcode;
    @Column(length = 255)
    private String imageURL;
    @OneToMany(mappedBy = "venue", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Projection> projections;
}

