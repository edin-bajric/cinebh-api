package com.atlantbh.cinebh.core.models;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.UUID;

@Entity
public class Movie {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(nullable = false)
    private UUID id;
    private String title;
    private String rating;
    private String language;
    private String length;
    @Lob
    private String description;
    private String director;
    private java.sql.Date startDate;
    private java.sql.Date endDate;
    private String trailerUrl;

    @ManyToMany
    @JoinTable(
            name = "movieWriter",
            joinColumns = @JoinColumn(name = "movieId"),
            inverseJoinColumns = @JoinColumn(name = "writerId")
    )
    private Set<Writer> writers;

    @ManyToMany
    @JoinTable(
            name = "movieGenre",
            joinColumns = @JoinColumn(name = "movieId"),
            inverseJoinColumns = @JoinColumn(name = "genreId")
    )
    private Set<Genre> genres;

    @OneToMany(mappedBy = "movie")
    private Set<Performer> performers;

    @OneToMany(mappedBy = "movie")
    private Set<MovieImage> images;

    @OneToMany(mappedBy = "movie")
    private Set<MovieRating> ratings;

    @OneToMany(mappedBy = "movie")
    private Set<Projection> projections;

    public Movie() {
    }

    public Movie(UUID id, String title, String rating, String language, String length, String description, String director, Date startDate, Date endDate, String trailerUrl, Set<Writer> writers, Set<Genre> genres, Set<Performer> performers, Set<MovieImage> images, Set<MovieRating> ratings, Set<Projection> projections) {
        this.id = id;
        this.title = title;
        this.rating = rating;
        this.language = language;
        this.length = length;
        this.description = description;
        this.director = director;
        this.startDate = startDate;
        this.endDate = endDate;
        this.trailerUrl = trailerUrl;
        this.writers = writers;
        this.genres = genres;
        this.performers = performers;
        this.images = images;
        this.ratings = ratings;
        this.projections = projections;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }

    public Set<Writer> getWriters() {
        return writers;
    }

    public void setWriters(Set<Writer> writers) {
        this.writers = writers;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Set<Performer> getPerformers() {
        return performers;
    }

    public void setPerformers(Set<Performer> performers) {
        this.performers = performers;
    }

    public Set<MovieImage> getImages() {
        return images;
    }

    public void setImages(Set<MovieImage> images) {
        this.images = images;
    }

    public Set<MovieRating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<MovieRating> ratings) {
        this.ratings = ratings;
    }

    public Set<Projection> getProjections() {
        return projections;
    }

    public void setProjections(Set<Projection> projections) {
        this.projections = projections;
    }
}
