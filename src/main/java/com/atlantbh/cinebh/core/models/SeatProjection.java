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
public class SeatProjection {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projectionId", nullable = false)
    private Projection projection;

    @ManyToMany
    @JoinTable(
            name = "seatProjectionSeats",
            joinColumns = @JoinColumn(name = "seatProjectionId"),
            inverseJoinColumns = @JoinColumn(name = "seatId")
    )
    private Set<Seat> seats;
}
