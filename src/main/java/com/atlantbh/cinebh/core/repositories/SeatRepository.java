package com.atlantbh.cinebh.core.repositories;

import com.atlantbh.cinebh.core.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SeatRepository extends JpaRepository<Seat, UUID> {
    @Query("SELECT s.id FROM SeatStatus s WHERE s.status = :status")
    UUID findStatusIdByStatus(@Param("status") String status);
    List<Seat> findAllByHallId(UUID hallId);
}
