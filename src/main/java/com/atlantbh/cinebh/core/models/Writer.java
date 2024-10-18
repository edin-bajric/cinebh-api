package com.atlantbh.cinebh.core.models;

import java.util.Set;
import java.util.UUID;
import jakarta.persistence.*;

@Entity
public class Writer {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(updatable = false, nullable = false)
    private UUID id;

    private String name;

    @ManyToMany(mappedBy = "writers")
    private Set<Movie> movies;

    public Writer() {
    }

    public Writer(UUID id, String name, Set<Movie> movies) {
        this.id = id;
        this.name = name;
        this.movies = movies;
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

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }
}

