package com.atlantbh.cinebh.core.services;

import com.atlantbh.cinebh.core.models.Genre;
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
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MovieServiceTest {
    private static final int UPCOMING_DAYS_RANGE = 14;

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

    @Test
    void testGetFilteredUpcomingMoviesWithValidDates() {
        Movie movie = new Movie();
        movie.setTitle("Upcoming Movie");
        List<Movie> movies = Collections.singletonList(movie);
        Page<Movie> moviePage = new PageImpl<>(movies);
        Pageable pageable = PageRequest.of(0, 4);
        LocalDate startDate = LocalDate.now().plusDays(1);
        LocalDate endDate = LocalDate.now().plusDays(UPCOMING_DAYS_RANGE);

        when(movieRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(moviePage);

        Page<Movie> result = movieService.getFilteredUpcomingMovies(
                "Upcoming Movie", "City", "Cinema", List.of("Drama"), startDate, endDate, pageable);

        assertEquals(1, result.getTotalElements());
        assertEquals("Upcoming Movie", result.getContent().get(0).getTitle());
        verify(movieRepository, times(1)).findAll(any(Specification.class), any(Pageable.class));
    }

    @Test
    void testGetFilteredUpcomingMoviesWithNullDates() {
        Movie movie = new Movie();
        movie.setTitle("Default Upcoming Movie");
        List<Movie> movies = Collections.singletonList(movie);
        Page<Movie> moviePage = new PageImpl<>(movies);
        Pageable pageable = PageRequest.of(0, 4);

        when(movieRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(moviePage);

        Page<Movie> result = movieService.getFilteredUpcomingMovies(
                "Default Upcoming Movie", "City", "Cinema", List.of("Action"), null, null, pageable);

        assertEquals(1, result.getTotalElements());
        assertEquals("Default Upcoming Movie", result.getContent().get(0).getTitle());
        verify(movieRepository, times(1)).findAll(any(Specification.class), any(Pageable.class));
    }

    @Test
    void testGetFilteredUpcomingMoviesWithNoResults() {
        Page<Movie> emptyPage = new PageImpl<>(Collections.emptyList());
        Pageable pageable = PageRequest.of(0, 4);
        LocalDate startDate = LocalDate.now().plusDays(1);
        LocalDate endDate = LocalDate.now().plusDays(UPCOMING_DAYS_RANGE);

        when(movieRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(emptyPage);

        Page<Movie> result = movieService.getFilteredUpcomingMovies(
                "Nonexistent Movie", "Nonexistent City", "Nonexistent Cinema",
                List.of("Fantasy"), startDate, endDate, pageable);

        assertEquals(0, result.getTotalElements());
        verify(movieRepository, times(1)).findAll(any(Specification.class), any(Pageable.class));
    }

    @Test
    void getMovie_validId_returnsMovie() {
        UUID movieId = UUID.randomUUID();
        Movie mockMovie = new Movie();
        mockMovie.setId(movieId);

        when(movieRepository.findById(movieId)).thenReturn(Optional.of(mockMovie));

        Movie result = movieService.getMovie(movieId);

        assertNotNull(result);
        assertEquals(movieId, result.getId());
        verify(movieRepository, times(1)).findById(movieId);
    }

    @Test
    void getMovie_invalidId_returnsNull() {
        UUID movieId = UUID.randomUUID();

        when(movieRepository.findById(movieId)).thenReturn(Optional.empty());

        Movie result = movieService.getMovie(movieId);

        assertNull(result);
        verify(movieRepository, times(1)).findById(movieId);
    }

    @Test
    void getSimilarMovies_validMovieId_returnsPageOfMovies() {
        UUID movieId = UUID.randomUUID();
        Genre genre = new Genre();
        genre.setId(UUID.randomUUID());
        Movie mockMovie = new Movie();
        mockMovie.setId(movieId);
        mockMovie.setGenres(List.of(genre));

        List<Movie> similarMovies = List.of(new Movie(), new Movie());
        Page<Movie> mockPage = new PageImpl<>(similarMovies);
        Pageable pageable = Pageable.ofSize(4);

        when(movieRepository.findById(movieId)).thenReturn(Optional.of(mockMovie));
        when(movieRepository.findSimilarMovies(List.of(genre.getId()), movieId, pageable)).thenReturn(mockPage);

        Page<Movie> result = movieService.getSimilarMovies(movieId, pageable);

        assertNotNull(result);
        assertEquals(2, result.getContent().size());
        verify(movieRepository, times(1)).findById(movieId);
        verify(movieRepository, times(1)).findSimilarMovies(List.of(genre.getId()), movieId, pageable);
    }

    @Test
    void getSimilarMovies_movieWithoutGenres_returnsEmptyPage() {
        UUID movieId = UUID.randomUUID();
        Movie mockMovie = new Movie();
        mockMovie.setId(movieId);
        mockMovie.setGenres(Collections.emptyList());

        Pageable pageable = Pageable.ofSize(4);

        when(movieRepository.findById(movieId)).thenReturn(Optional.of(mockMovie));

        Page<Movie> result = movieService.getSimilarMovies(movieId, pageable);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(movieRepository, times(1)).findById(movieId);
        verify(movieRepository, never()).findSimilarMovies(anyList(), any(), any());
    }

    @Test
    void getSimilarMovies_invalidMovieId_throwsException() {
        UUID movieId = UUID.randomUUID();

        when(movieRepository.findById(movieId)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> movieService.getSimilarMovies(movieId, Pageable.ofSize(4)));

        assertEquals("Movie not found", exception.getMessage());
        verify(movieRepository, times(1)).findById(movieId);
        verify(movieRepository, never()).findSimilarMovies(anyList(), any(), any());
    }
}

