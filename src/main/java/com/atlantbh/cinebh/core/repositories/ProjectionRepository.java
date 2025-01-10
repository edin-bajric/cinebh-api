package com.atlantbh.cinebh.core.repositories;

import com.atlantbh.cinebh.core.models.Projection;
import com.atlantbh.cinebh.rest.dto.ProjectionDetailsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface ProjectionRepository extends JpaRepository<Projection, UUID> {

    @Query("""
        SELECT DISTINCT v.city
        FROM Projection p
        JOIN p.venue v
        WHERE p.movie.id = :movieId
    """)
    List<String> findCitiesByMovieId(@Param("movieId") UUID movieId);

    @Query("""
        SELECT DISTINCT v.name
        FROM Projection p
        JOIN p.venue v
        WHERE p.movie.id = :movieId
    """)
    List<String> findCinemasByMovieId(@Param("movieId") UUID movieId);

    @Query("""
        SELECT m.startDate
        FROM Movie m
        WHERE m.id = :movieId
    """)
    LocalDate findStartDateByMovieId(@Param("movieId") UUID movieId);

    @Query("""
        SELECT m.endDate
        FROM Movie m
        WHERE m.id = :movieId
    """)
    LocalDate findEndDateByMovieId(@Param("movieId") UUID movieId);

    @Query("""
        SELECT DISTINCT pt.time
        FROM Projection p
        JOIN p.projectionTimes pt
        WHERE p.movie.id = :movieId
    """)
    List<Time> findProjectionTimesByMovieId(@Param("movieId") UUID movieId);

    default ProjectionDetailsDTO getProjectionDetails(UUID movieId) {
        return new ProjectionDetailsDTO(
                findCitiesByMovieId(movieId),
                findCinemasByMovieId(movieId),
                findStartDateByMovieId(movieId),
                findEndDateByMovieId(movieId),
                findProjectionTimesByMovieId(movieId)
        );
    }
}
