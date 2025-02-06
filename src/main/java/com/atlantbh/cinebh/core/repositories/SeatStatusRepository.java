package com.atlantbh.cinebh.core.repositories;

import com.atlantbh.cinebh.core.models.SeatStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SeatStatusRepository extends JpaRepository<SeatStatus, UUID> {
    Optional<SeatStatus> findByStatus(String status);
}