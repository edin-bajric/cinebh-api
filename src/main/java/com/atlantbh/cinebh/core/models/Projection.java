package com.atlantbh.cinebh.core.models;

import java.sql.Time;
import java.util.UUID;
import jakarta.persistence.*;

@Entity
public class Projection {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(updatable = false, nullable = false)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "venueId")
    private Venue venue;
    @ManyToOne
    @JoinColumn(name = "movieId")
    private Movie movie;
    private java.sql.Time projectionTime;

    public Projection() {
    }

    public Projection(UUID id, Venue venue, Movie movie, Time projectionTime) {
        this.id = id;
        this.venue = venue;
        this.movie = movie;
        this.projectionTime = projectionTime;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Time getProjectionTime() {
        return projectionTime;
    }

    public void setProjectionTime(Time projectionTime) {
        this.projectionTime = projectionTime;
    }
}

