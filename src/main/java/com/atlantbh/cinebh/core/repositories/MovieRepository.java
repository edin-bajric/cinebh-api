package com.atlantbh.cinebh.core.repositories;

import com.atlantbh.cinebh.core.models.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface MovieRepository extends JpaRepository<Movie, UUID> {
    Page<Movie> findByStartDateLessThanEqualAndEndDateGreaterThanEqual(LocalDate dateToCheckStart, LocalDate dateToCheckEnd, Pageable pageable);
    Page<Movie> findByStartDateBetween(LocalDate currentDate, LocalDate upcomingDate, Pageable pageable);
}
