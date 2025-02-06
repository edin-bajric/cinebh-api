package com.atlantbh.cinebh.core.services;

import com.atlantbh.cinebh.api.impl.mailsender.MailgunSender;
import com.atlantbh.cinebh.core.models.AppUser;
import com.atlantbh.cinebh.core.models.Projection;
import com.atlantbh.cinebh.core.models.Seat;
import com.atlantbh.cinebh.core.models.SeatStatus;
import com.atlantbh.cinebh.core.models.Ticket;
import com.atlantbh.cinebh.core.repositories.SeatRepository;
import com.atlantbh.cinebh.core.repositories.SeatStatusRepository;
import com.atlantbh.cinebh.core.repositories.TicketRepository;
import com.atlantbh.cinebh.core.repositories.AppUserRepository;
import com.atlantbh.cinebh.core.repositories.ProjectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final SeatRepository seatRepository;
    private final SeatStatusRepository seatStatusRepository;
    private final AppUserRepository appUserRepository;
    private final ProjectionRepository projectionRepository;
    private final MailgunSender mailgunSender;

    @Transactional
    public ResponseEntity<?> createTicket(String userEmail, String projectionId, List<String> selectedSeats, String totalPrice) {
        try {
            AppUser user = appUserRepository.findByEmail(userEmail)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            Projection projection = projectionRepository.findById(UUID.fromString(projectionId))
                    .orElseThrow(() -> new RuntimeException("Projection not found"));

            SeatStatus reservedStatus = seatStatusRepository.findByStatus("reserved")
                    .orElseThrow(() -> new RuntimeException("Reserved status not found"));

            Ticket ticket = new Ticket();
            ticket.setProjection(projection);
            ticket.setAppUser(user);

            List<String> seatNames = selectedSeats.stream()
                    .map(seatId -> seatRepository.findById(UUID.fromString(seatId))
                            .orElseThrow(() -> new RuntimeException("Seat not found"))
                            .getName())
                    .collect(Collectors.toList());

            for (String seatId : selectedSeats) {
                Seat seat = seatRepository.findById(UUID.fromString(seatId))
                        .orElseThrow(() -> new RuntimeException("Seat not found"));

                seat.setStatus(reservedStatus);
                seatRepository.save(seat);

                ticket.getSeats().add(seat);
            }

            ticketRepository.save(ticket);

            String seatNamesString = String.join(", ", seatNames);
            mailgunSender.send(userEmail, "Your ticket has been created \nTotal price is: " + totalPrice + "KM" + " \nSelected seat(s): " + seatNamesString, "Ticket created");

            Map<String, String> response = new HashMap<>();
            response.put("message", "Ticket created successfully");
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}