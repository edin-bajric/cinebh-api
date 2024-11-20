package com.atlantbh.cinebh.core.repositories;

import com.atlantbh.cinebh.core.models.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface MovieRepository extends JpaRepository<Movie, UUID>, JpaSpecificationExecutor<Movie> {
    Page<Movie> findByStartDateLessThanEqualAndEndDateGreaterThanEqual(LocalDate dateToCheckStart, LocalDate dateToCheckEnd, Pageable pageable);
    Page<Movie> findByStartDateBetween(LocalDate currentDate, LocalDate upcomingDate, Pageable pageable);
    @Query("SELECT m FROM Movie m JOIN m.genres g WHERE g.id IN :genreIds AND m.id <> :movieId GROUP BY m.id")
    Page<Movie> findSimilarMovies(@Param("genreIds") List<UUID> genreIds, @Param("movieId") UUID movieId, Pageable pageable);
}
