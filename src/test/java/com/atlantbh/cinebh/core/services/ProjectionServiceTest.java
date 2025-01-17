package com.atlantbh.cinebh.core.services;

import com.atlantbh.cinebh.core.repositories.ProjectionRepository;
import com.atlantbh.cinebh.rest.dto.ProjectionDetailsDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

class ProjectionServiceTest {

    @Mock
    private ProjectionRepository projectionRepository;

    @InjectMocks
    private ProjectionService projectionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnProjectionDetailsWhenMovieIdExists() {
        UUID movieId = UUID.randomUUID();
        ProjectionDetailsDTO projectionDetailsDTO = new ProjectionDetailsDTO(
                Arrays.asList("City1", "City2"),
                Arrays.asList("Cinema1", "Cinema2"),
                Arrays.asList("Street1", "Street2"),
                Arrays.asList("12345", "67890"),
                Arrays.asList("10", "20"),
                Arrays.asList("Hall1", "Hall2"),
                Arrays.asList(UUID.randomUUID(), UUID.randomUUID()),
                LocalDate.now(),
                LocalDate.now().plusDays(7),
                Arrays.asList(Time.valueOf("12:00:00"), Time.valueOf("15:00:00")),
                Arrays.asList(UUID.randomUUID(), UUID.randomUUID())
        );

        when(projectionRepository.getProjectionDetails(movieId)).thenReturn(projectionDetailsDTO);

        ProjectionDetailsDTO result = projectionService.getProjectionDetails(movieId);

        assertNotNull(result);
        assertEquals(projectionDetailsDTO, result);
        assertEquals("City1", result.getCities().get(0));
        verify(projectionRepository, times(1)).getProjectionDetails(movieId);
    }

    @Test
    void shouldReturnNullWhenMovieIdDoesNotExist() {
        UUID movieId = UUID.randomUUID();

        when(projectionRepository.getProjectionDetails(movieId)).thenReturn(null);

        ProjectionDetailsDTO result = projectionService.getProjectionDetails(movieId);

        assertNull(result);
        verify(projectionRepository, times(1)).getProjectionDetails(movieId);
    }
}
