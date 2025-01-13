package com.atlantbh.cinebh.rest.controllers;

import com.atlantbh.cinebh.core.models.Seat;
import com.atlantbh.cinebh.core.services.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/seats")
@RequiredArgsConstructor
public class SeatController {
    private final SeatService seatService;

    @GetMapping("/{hallId}")
    public ResponseEntity<List<Seat>> getSeatsByHallId(@PathVariable("hallId") UUID hallId) {
        return ResponseEntity.ok(seatService.findAllByHallId(hallId));
    }
}
