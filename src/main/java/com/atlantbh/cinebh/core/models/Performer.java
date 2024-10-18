package com.atlantbh.cinebh.core.models;

import java.util.UUID;
import jakarta.persistence.*;

@Entity
public class Performer {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(updatable = false, nullable = false)
    private UUID id;
    private String name;
    private String role;
    @ManyToOne
    @JoinColumn(name = "movieId")
    private Movie movie;

    public Performer() {
    }

    public Performer(UUID id, String name, String role, Movie movie) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.movie = movie;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
