package com.atlantbh.cinebh.core.models;

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
public class SeatType {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(updatable = false, nullable = false)
    private UUID id;
    @Column(nullable = false, length = 45)
    private String type;
    @Column(nullable = false)
    private int price;

    @OneToMany(mappedBy = "type", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Seat> seats;
}
