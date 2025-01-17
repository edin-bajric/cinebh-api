package com.atlantbh.cinebh.rest.controllers;

import com.atlantbh.cinebh.core.models.Seat;
import com.atlantbh.cinebh.core.services.SeatService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

class SeatControllerTest {

    @InjectMocks
    private SeatController seatController;

    @Mock
    private SeatService seatService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetSeatsByHallId() {
        UUID hallId = UUID.randomUUID();
        Seat seat1 = new Seat();
        Seat seat2 = new Seat();
        List<Seat> seats = Arrays.asList(seat1, seat2);

        when(seatService.findAllByHallId(hallId)).thenReturn(seats);

        ResponseEntity<List<Seat>> response = seatController.getSeatsByHallId(hallId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(seats, response.getBody());

        verify(seatService, times(1)).findAllByHallId(hallId);
    }

    @Test
    void testGetSeatsByHallIdWithNoSeats() {
        UUID hallId = UUID.randomUUID();
        List<Seat> seats = Arrays.asList();

        when(seatService.findAllByHallId(hallId)).thenReturn(seats);

        ResponseEntity<List<Seat>> response = seatController.getSeatsByHallId(hallId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(seats, response.getBody());
        assertNotNull(response.getBody());
        assertEquals(0, response.getBody().size());

        verify(seatService, times(1)).findAllByHallId(hallId);
    }

    @Test
    void testGetSeatsByInvalidHallId() {
        UUID hallId = UUID.randomUUID();

        when(seatService.findAllByHallId(hallId)).thenReturn(null);

        ResponseEntity<List<Seat>> response = seatController.getSeatsByHallId(hallId);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());

        verify(seatService, times(1)).findAllByHallId(hallId);
    }
}
