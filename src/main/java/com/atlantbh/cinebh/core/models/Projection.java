package com.atlantbh.cinebh.core.models;

import java.util.UUID;
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
}

