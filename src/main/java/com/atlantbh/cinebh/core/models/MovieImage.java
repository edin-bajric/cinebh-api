package com.atlantbh.cinebh.core.models;

import java.util.UUID;
import jakarta.persistence.*;

@Entity
public class MovieImage {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(updatable = false, nullable = false)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "movieId")
    private Movie movie;
    private Boolean isCoverImage;
    private String url;

    public MovieImage() {
    }

    public MovieImage(UUID id, Movie movie, Boolean isCoverImage, String url) {
        this.id = id;
        this.movie = movie;
        this.isCoverImage = isCoverImage;
        this.url = url;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Boolean getCoverImage() {
        return isCoverImage;
    }

    public void setCoverImage(Boolean coverImage) {
        isCoverImage = coverImage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

