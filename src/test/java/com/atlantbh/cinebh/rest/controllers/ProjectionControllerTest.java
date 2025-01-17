package com.atlantbh.cinebh.rest.controllers;

import com.atlantbh.cinebh.core.services.ProjectionService;
import com.atlantbh.cinebh.rest.dto.ProjectionDetailsDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProjectionControllerTest {

    @InjectMocks
    private ProjectionController projectionController;

    @Mock
    private ProjectionService projectionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetProjectionDetails() {
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

        when(projectionService.getProjectionDetails(movieId)).thenReturn(projectionDetailsDTO);

        ResponseEntity<ProjectionDetailsDTO> response = projectionController.getProjectionDetails(movieId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(projectionDetailsDTO, response.getBody());
        assertEquals("City1", response.getBody().getCities().get(0));

        verify(projectionService, times(1)).getProjectionDetails(movieId);
    }

    @Test
    void testGetProjectionDetailsInvalidId() {
        UUID movieId = UUID.randomUUID();

        when(projectionService.getProjectionDetails(movieId)).thenReturn(null);

        ResponseEntity<ProjectionDetailsDTO> response = projectionController.getProjectionDetails(movieId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());

        verify(projectionService, times(1)).getProjectionDetails(movieId);
    }
}
