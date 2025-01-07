package com.atlantbh.cinebh.core.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Hall {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(updatable = false, nullable = false)
    private UUID id;
    @Column(nullable = false, length = 45)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venueId", nullable = true)
    @JsonBackReference
    private Venue venue;

    @OneToMany(mappedBy = "hall", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Seat> seats;
}
