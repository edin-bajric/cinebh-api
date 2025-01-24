package com.atlantbh.cinebh.core.services;

import com.atlantbh.cinebh.core.models.Seat;
import com.atlantbh.cinebh.core.repositories.SeatRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

class SeatServiceTest {

    @Mock
    private SeatRepository seatRepository;

    @InjectMocks
    private SeatService seatService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnSeatsWhenHallIdExists() {
        UUID hallId = UUID.randomUUID();
        Seat seat1 = new Seat();
        Seat seat2 = new Seat();
        List<Seat> seats = Arrays.asList(seat1, seat2);

        when(seatRepository.findAllByHallId(hallId)).thenReturn(seats);

        List<Seat> result = seatService.findAllByHallId(hallId);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(seats, result);
        verify(seatRepository, times(1)).findAllByHallId(hallId);
    }

    @Test
    void shouldReturnEmptyListWhenNoSeatsForHallId() {
        UUID hallId = UUID.randomUUID();
        List<Seat> seats = Arrays.asList();

        when(seatRepository.findAllByHallId(hallId)).thenReturn(seats);

        List<Seat> result = seatService.findAllByHallId(hallId);

        assertNotNull(result);
        assertEquals(0, result.size());
        assertEquals(seats, result);
        verify(seatRepository, times(1)).findAllByHallId(hallId);
    }

    @Test
    void shouldReturnNullWhenInvalidHallId() {
        UUID hallId = UUID.randomUUID();

        when(seatRepository.findAllByHallId(hallId)).thenReturn(null);

        List<Seat> result = seatService.findAllByHallId(hallId);

        assertNull(result);
        verify(seatRepository, times(1)).findAllByHallId(hallId);
    }
}
