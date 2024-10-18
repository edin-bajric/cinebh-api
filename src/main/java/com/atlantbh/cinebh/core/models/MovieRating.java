package com.atlantbh.cinebh.core.models;

import java.util.UUID;
import jakarta.persistence.*;

@Entity
public class MovieRating {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(updatable = false, nullable = false)
    private UUID id;
    private String name;
    private String rating;
    @ManyToOne
    @JoinColumn(name = "movieId")
    private Movie movie;

    public MovieRating() {
    }

    public MovieRating(UUID id, String name, String rating, Movie movie) {
        this.id = id;
        this.name = name;
        this.rating = rating;
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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}

