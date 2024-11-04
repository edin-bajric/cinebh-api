package com.atlantbh.cinebh.core.models;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Performer {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(updatable = false, nullable = false)
    private UUID id;
    @Column(nullable = false, length = 255)
    private String name;
    @Column(length = 255)
    private String role;
    @ManyToMany(mappedBy = "performers", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Movie> movies;
}
