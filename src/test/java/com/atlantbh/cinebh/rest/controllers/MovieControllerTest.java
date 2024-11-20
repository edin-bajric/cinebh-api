package com.atlantbh.cinebh.rest.controllers;

import com.atlantbh.cinebh.core.models.Movie;
import com.atlantbh.cinebh.core.services.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MovieControllerTest {

    @InjectMocks
    private MovieController movieController;

    @Mock
    private MovieService movieService;

    private final int DEFAULT_PAGE = 0;
    private final int DEFAULT_SIZE = 4;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCurrentlyShowing() {
        Movie movie = new Movie();
        movie.setTitle("Currently Showing Movie");
        Page<Movie> moviePage = new PageImpl<>(Collections.singletonList(movie));

        when(movieService.getFilteredCurrentlyShowing(any(), any(), any(), any(), any(), any(LocalDate.class), any()))
                .thenReturn(moviePage);

        ResponseEntity<Page<Movie>> response = movieController.currentlyShowing(
                "Some title", "Some city", "Some cinema", List.of("Genre"), "12:00", LocalDate.now(),
                DEFAULT_PAGE, DEFAULT_SIZE
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, Objects.requireNonNull(response.getBody()).getTotalElements());
        assertEquals("Currently Showing Movie", response.getBody().getContent().get(0).getTitle());

        verify(movieService, times(1))
                .getFilteredCurrentlyShowing(any(), any(), any(), any(), any(), any(LocalDate.class), any());
    }

    @Test
    void testGetMovies() {
        Movie movie = new Movie();
        movie.setTitle("Test Movie");
        Page<Movie> moviePage = new PageImpl<>(Collections.singletonList(movie));

        when(movieService.getMovies(any())).thenReturn(moviePage);

        ResponseEntity<Page<Movie>> response = movieController.getMovies(DEFAULT_PAGE, DEFAULT_SIZE);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Test Movie", Objects.requireNonNull(response.getBody()).getContent().get(0).getTitle());
        verify(movieService, times(1)).getMovies(any());
    }
}

