package com.atlantbh.cinebh.core.models;

import java.util.Set;
import java.util.UUID;
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
    private String name;
    private String phone;
    private String street;
    private String streetNumber;
    private String city;
    private String imageURL;
    @OneToMany(mappedBy = "venue")
    private Set<Projection> projections;
}

