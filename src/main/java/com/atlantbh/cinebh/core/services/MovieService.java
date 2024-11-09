package com.atlantbh.cinebh.core.services;

import com.atlantbh.cinebh.core.models.Movie;
import com.atlantbh.cinebh.core.repositories.MovieRepository;
import com.atlantbh.cinebh.core.spec.MovieSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

    public Page<Movie> getFilteredCurrentlyShowing(String title, String city, String cinema, List<String> genres,
                                                   String projectionTime, LocalDate date, Pageable pageable) {
        Specification<Movie> spec = MovieSpecification.filterCurrentlyShowing(title, city, cinema, genres, projectionTime, date);
        return movieRepository.findAll(spec, pageable);
    }

    public Page<Movie> getFilteredUpcomingMovies(String title, String city, String cinema, List<String> genres,
                                                 LocalDate startDate, LocalDate endDate, Pageable pageable) {
        if (startDate == null || endDate == null) {
            startDate = LocalDate.now();
            endDate = startDate.plusDays(UPCOMING_DAYS_RANGE);
        }
        Specification<Movie> spec = MovieSpecification.filterUpcomingMovies(title, city, cinema, genres, startDate, endDate);
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
}
