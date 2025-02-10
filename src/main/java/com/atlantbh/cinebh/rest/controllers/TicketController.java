package com.atlantbh.cinebh.rest.controllers;

import com.atlantbh.cinebh.core.services.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/")
    public ResponseEntity<?> createTicket(@RequestBody Map<String, Object> ticketDetails) {
        String userEmail = (String) ticketDetails.get("userEmail");
        String projectionId = (String) ticketDetails.get("projectionId");
        List<String> selectedSeats = (List<String>) ticketDetails.get("selectedSeats");
        String totalPrice = (String) ticketDetails.get("totalPrice").toString();

        return ticketService.createTicket(userEmail, projectionId, selectedSeats, totalPrice);
    }
}