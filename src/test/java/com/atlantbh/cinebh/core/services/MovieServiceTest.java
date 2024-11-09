package com.atlantbh.cinebh.core.services;

import com.atlantbh.cinebh.core.models.Movie;
import com.atlantbh.cinebh.core.repositories.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MovieServiceTest {

    @InjectMocks
    private MovieService movieService;

    @Mock
    private MovieRepository movieRepository;

    private Pageable pageable;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pageable = PageRequest.of(0, 4);
    }

    @Test
    void testGetCurrentlyShowing() {
        LocalDate today = LocalDate.now();
        Movie movie = new Movie();
        movie.setTitle("Test Movie");
        Page<Movie> moviePage = new PageImpl<>(Collections.singletonList(movie));

        when(movieRepository.findByStartDateLessThanEqualAndEndDateGreaterThanEqual(today, today, pageable))
                .thenReturn(moviePage);

        Page<Movie> result = movieService.getCurrentlyShowing(pageable);

        assertEquals(1, result.getTotalElements());
        assertEquals("Test Movie", result.getContent().get(0).getTitle());
        verify(movieRepository, times(1))
                .findByStartDateLessThanEqualAndEndDateGreaterThanEqual(today, today, pageable);
    }

    @Test
    void testGetFeatured() {
        Movie movie = new Movie();
        movie.setTitle("Featured Movie");
        Page<Movie> moviePage = new PageImpl<>(Collections.singletonList(movie));

        when(movieRepository.findByStartDateLessThanEqualAndEndDateGreaterThanEqual(any(LocalDate.class), any(LocalDate.class), any(Pageable.class)))
                .thenReturn(moviePage);

        var featuredMovies = movieService.getFeatured();

        assertEquals(1, featuredMovies.size());
        assertEquals("Featured Movie", featuredMovies.get(0).getTitle());
        verify(movieRepository, times(1))
                .findByStartDateLessThanEqualAndEndDateGreaterThanEqual(any(LocalDate.class), any(LocalDate.class), eq(Pageable.unpaged()));
    }

    @Test
    void testGetFilteredCurrentlyShowing() {
        Movie movie = new Movie();
        movie.setTitle("Sample Movie");
        List<Movie> movies = Collections.singletonList(movie);
        Page<Movie> moviePage = new PageImpl<>(movies);
        Pageable pageable = PageRequest.of(0, 4);

        when(movieRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(moviePage);

        Page<Movie> result = movieService.getFilteredCurrentlyShowing(
                "Sample Movie", "Sample City", "Sample Cinema",
                List.of("Action"), "18:00", LocalDate.now(), pageable);

        assertEquals(1, result.getTotalElements());
        assertEquals("Sample Movie", result.getContent().get(0).getTitle());
    }

    @Test
    void testGetFilteredCurrentlyShowingWithNoResults() {
        Page<Movie> emptyPage = new PageImpl<>(Collections.emptyList());
        Pageable pageable = PageRequest.of(0, 4);

        when(movieRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(emptyPage);

        Page<Movie> result = movieService.getFilteredCurrentlyShowing(
                "Nonexistent Movie", "Nonexistent City", "Nonexistent Cinema",
                List.of("Nonexistent Genre"), "00:00", LocalDate.now(), pageable);

        assertEquals(0, result.getTotalElements());
    }
}

