package com.atlantbh.cinebh.core.models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
public class Seat {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(updatable = false, nullable = false)
    private UUID id;
    @Column(nullable = false, length = 45)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hallId", nullable = true)
    @JsonIgnoreProperties({"seats"})
    private Hall hall;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "typeId", nullable = true)
    @JsonIgnoreProperties({"seats"})
    private SeatType type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "statusId", nullable = true)
    @JsonIgnoreProperties({"seats"})
    private SeatStatus status;

    @OneToMany(mappedBy = "seat")
    private List<SeatProjection> seatProjections;
}
