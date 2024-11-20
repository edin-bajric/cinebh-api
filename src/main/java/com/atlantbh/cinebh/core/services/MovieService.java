package com.atlantbh.cinebh.core.services;

import com.atlantbh.cinebh.core.models.Genre;
import com.atlantbh.cinebh.core.models.Movie;
import com.atlantbh.cinebh.core.repositories.MovieRepository;
import com.atlantbh.cinebh.core.spec.MovieSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    private static final int UPCOMING_DAYS_RANGE = 14;
    private static final LocalDate TODAY = LocalDate.now();

    public Page<Movie> getMovies(Pageable pageable) {
        return movieRepository.findAll(pageable);
    }

    public Movie getMovie(UUID id) {
        Optional<Movie> movie = movieRepository.findById(id);
        return movie.orElse(null);
    }

    public Page<Movie> getFilteredCurrentlyShowing(String title, String city, String cinema, List<String> genres,
                                                   String projectionTime, LocalDate date, Pageable pageable) {
        Specification<Movie> spec = MovieSpecification.filterCurrentlyShowing(title, city, cinema, genres, projectionTime, date);
        return movieRepository.findAll(spec, pageable);
    }

    public Page<Movie> getFilteredUpcomingMovies(String title, String city, String cinema, List<String> genres,
                                                 LocalDate startDate, LocalDate endDate, Pageable pageable) {
        LocalDate finalStartDate = Optional.ofNullable(startDate).orElse(TODAY);
        LocalDate finalEndDate = Optional.ofNullable(endDate).orElse(finalStartDate.plusDays(UPCOMING_DAYS_RANGE));

        Specification<Movie> spec = MovieSpecification.filterUpcomingMovies(title, city, cinema, genres, finalStartDate, finalEndDate);
        return movieRepository.findAll(spec, pageable);
    }

    public Page<Movie> getCurrentlyShowing(Pageable pageable) {
        return movieRepository.findByStartDateLessThanEqualAndEndDateGreaterThanEqual(TODAY, TODAY, pageable);
    }

    public List<Movie> getFeatured() {
        Page<Movie> currentlyShowing = getCurrentlyShowing(Pageable.unpaged());
        List<Movie> moviesList = new ArrayList<>(currentlyShowing.getContent());

        Collections.shuffle(moviesList);

        return moviesList.stream()
                .limit(3)
                .collect(Collectors.toList());
    }

    public Page<Movie> getSimilarMovies(UUID movieId, Pageable pageable) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new IllegalArgumentException("Movie not found"));

        List<UUID> genreIds = movie.getGenres().stream()
                .map(Genre::getId)
                .collect(Collectors.toList());

        if (genreIds.isEmpty()) {
            return Page.empty();
        }

        return movieRepository.findSimilarMovies(genreIds, movieId, pageable);
    }

}
