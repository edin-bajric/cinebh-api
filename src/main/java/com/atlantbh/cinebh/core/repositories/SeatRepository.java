package com.atlantbh.cinebh.core.repositories;

import com.atlantbh.cinebh.core.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SeatRepository extends JpaRepository<Seat, UUID> {
    List<Seat> findAllByHallId(UUID hallId);
}
