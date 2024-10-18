package com.atlantbh.cinebh.core.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Movie {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(updatable = false, nullable = false)
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
}
