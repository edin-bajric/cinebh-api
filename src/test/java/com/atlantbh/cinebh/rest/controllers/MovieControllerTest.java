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
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

    @Test
    void testUpcomingMovies() {
        Movie movie = new Movie();
        movie.setTitle("Upcoming Movie");
        Page<Movie> moviePage = new PageImpl<>(Collections.singletonList(movie));

        when(movieService.getFilteredUpcomingMovies(any(), any(), any(), any(), any(LocalDate.class), any(LocalDate.class), any()))
                .thenReturn(moviePage);

        ResponseEntity<Page<Movie>> response = movieController.upcomingMovies(
                "Some title", "Some city", "Some cinema", List.of("Genre"),
                LocalDate.now(), LocalDate.now().plusDays(7), DEFAULT_PAGE, DEFAULT_SIZE
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, Objects.requireNonNull(response.getBody()).getTotalElements());
        assertEquals("Upcoming Movie", response.getBody().getContent().get(0).getTitle());

        verify(movieService, times(1))
                .getFilteredUpcomingMovies(any(), any(), any(), any(), any(LocalDate.class), any(LocalDate.class), any());
    }

    @Test
    void validIdShouldReturnMovie() {
        UUID movieId = UUID.randomUUID();
        Movie mockMovie = new Movie();
        mockMovie.setId(movieId);

        when(movieService.getMovie(movieId)).thenReturn(mockMovie);

        ResponseEntity<Movie> response = movieController.getMovie(movieId);

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertEquals(movieId, response.getBody().getId());
        verify(movieService, times(1)).getMovie(movieId);
    }

    @Test
    void invalidIdShouldReturnNull() {
        UUID movieId = UUID.randomUUID();

        when(movieService.getMovie(movieId)).thenReturn(null);

        ResponseEntity<Movie> response = movieController.getMovie(movieId);

        assertNotNull(response);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertNull(response.getBody());
        verify(movieService, times(1)).getMovie(movieId);
    }

    @Test
    void validMovieIdShouldReturnPageOfMovies() {
        UUID movieId = UUID.randomUUID();
        Movie similarMovie1 = new Movie();
        Movie similarMovie2 = new Movie();
        Page<Movie> mockPage = new PageImpl<>(List.of(similarMovie1, similarMovie2));
        Pageable pageable = Pageable.ofSize(4);

        when(movieService.getSimilarMovies(movieId, pageable)).thenReturn(mockPage);

        ResponseEntity<Page<Movie>> response = movieController.similarMovies(movieId, DEFAULT_PAGE, DEFAULT_SIZE);

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertEquals(2, response.getBody().getContent().size());
        verify(movieService, times(1)).getSimilarMovies(movieId, pageable);
    }

    @Test
    void noSimilarMoviesShouldReturnsEmptyPage() {
        UUID movieId = UUID.randomUUID();
        Page<Movie> mockPage = Page.empty(Pageable.ofSize(4));

        when(movieService.getSimilarMovies(movieId, Pageable.ofSize(4))).thenReturn(mockPage);

        ResponseEntity<Page<Movie>> response = movieController.similarMovies(movieId, DEFAULT_PAGE, DEFAULT_SIZE);

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertTrue(response.getBody().isEmpty());
        verify(movieService, times(1)).getSimilarMovies(movieId, Pageable.ofSize(4));
    }
}

