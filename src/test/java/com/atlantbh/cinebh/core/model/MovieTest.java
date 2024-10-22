package com.atlantbh.cinebh.core.model;

import com.atlantbh.cinebh.core.models.Movie;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class MovieTest {
    @Test
    void shouldCreateNewMovie() {
        Movie movie = new Movie(UUID.randomUUID(), "Title", "Rating", "Language", "Length", "Description", "Director", LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 1), "link", null, null, null, null, null, null);

        assertEquals("Title", movie.getTitle());
        assertEquals("Rating", movie.getRating());
        assertEquals("Language", movie.getLanguage());
        assertEquals("Length", movie.getLength());
        assertEquals("Description", movie.getDescription());
        assertEquals("Director", movie.getDirector());
        assertEquals(LocalDate.of(2023, 1, 1), movie.getStartDate());
        assertEquals(LocalDate.of(2023, 1, 1), movie.getEndDate());
        assertEquals("link", movie.getTrailerUrl());
    }
}
