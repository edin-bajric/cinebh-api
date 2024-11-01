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
import java.util.List;
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
    @Column(nullable = false, length = 255)
    private String title;
    @Column(length = 45)
    private String rating;
    @Column(length = 45)
    private String language;
    @Column(length = 45)
    private String length;
    @Column
    private String description;
    @Column(length = 255)
    private String director;
    @Column
    private LocalDate startDate;
    @Column
    private LocalDate endDate;
    @Column(length = 255)
    private String trailerUrl;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "movieWriter",
            joinColumns = @JoinColumn(name = "movieId"),
            inverseJoinColumns = @JoinColumn(name = "writerId")
    )
    @JsonManagedReference
    private List<Writer> writers;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "movieGenre",
            joinColumns = @JoinColumn(name = "movieId"),
            inverseJoinColumns = @JoinColumn(name = "genreId")
    )
    @JsonManagedReference
    private List<Genre> genres;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "moviePerformer",
            joinColumns = @JoinColumn(name = "movieId"),
            inverseJoinColumns = @JoinColumn(name = "performerId")
    )
    @JsonManagedReference
    private List<Performer> performers;

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    private List<MovieImage> images;

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    private List<MovieRating> ratings;

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    private List<Projection> projections;
}
