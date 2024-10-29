package com.atlantbh.cinebh.core.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Movie {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(updatable = false, nullable = false)
    private UUID id;
    private String title;
    private String rating;
    private String language;
    private String length;
    private String description;
    private String director;
    private LocalDate startDate;
    private LocalDate endDate;
    private String trailerUrl;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "movieWriter",
            joinColumns = @JoinColumn(name = "movieId"),
            inverseJoinColumns = @JoinColumn(name = "writerId")
    )
    @JsonManagedReference
    private Set<Writer> writers;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "movieGenre",
            joinColumns = @JoinColumn(name = "movieId"),
            inverseJoinColumns = @JoinColumn(name = "genreId")
    )
    @JsonManagedReference
    private Set<Genre> genres;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "moviePerformer",
            joinColumns = @JoinColumn(name = "movieId"),
            inverseJoinColumns = @JoinColumn(name = "performerId")
    )
    @JsonManagedReference
    private Set<Performer> performers;

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    private Set<MovieImage> images;

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    private Set<MovieRating> ratings;

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    private Set<Projection> projections;
}
