package com.atlantbh.cinebh.core.services;

import com.atlantbh.cinebh.core.models.Movie;
import com.atlantbh.cinebh.core.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public Page<Movie> getMovies(Pageable pageable) {
        return movieRepository.findAll(pageable);
    }

    public Page<Movie> getCurrentlyShowing(Pageable pageable) {
        LocalDate today = LocalDate.now();
        return movieRepository.findByStartDateLessThanEqualAndEndDateGreaterThanEqual(today, today, pageable);
    }

    public Page<Movie> getUpcomingMovies(Pageable pageable) {
        LocalDate today = LocalDate.now().plusDays(1);
        LocalDate upcomingDate = today.plusDays(14);
        return movieRepository.findByStartDateBetween(today, upcomingDate, pageable);
    }
}
