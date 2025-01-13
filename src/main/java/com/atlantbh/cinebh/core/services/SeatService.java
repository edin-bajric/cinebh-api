package com.atlantbh.cinebh.core.services;

import com.atlantbh.cinebh.core.models.Seat;
import com.atlantbh.cinebh.core.repositories.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SeatService {
    private final SeatRepository seatRepository;

    public List<Seat> findAllByHallId(UUID hallId) {
        return seatRepository.findAllByHallId(hallId);
    }
}
