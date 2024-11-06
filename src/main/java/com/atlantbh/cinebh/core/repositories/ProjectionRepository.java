package com.atlantbh.cinebh.core.repositories;

import com.atlantbh.cinebh.core.models.Projection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface ProjectionRepository extends JpaRepository<Projection, UUID> {
    @Query("SELECT p FROM Projection p JOIN p.movie m WHERE LOWER(m.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    Page<Projection> findByMovieTitleContainingIgnoreCase(@Param("title") String title, Pageable pageable);
}
