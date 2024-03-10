package com.cinema.recommendations.controller;

import com.cinema.recommendations.entity.Booking;
import com.cinema.recommendations.entity.Session;
import com.cinema.recommendations.entity.User;
import com.cinema.recommendations.model.BookingDTO;
import com.cinema.recommendations.model.SeatDTO;
import com.cinema.recommendations.repository.SessionRepository;
import com.cinema.recommendations.repository.UserRepository;
import com.cinema.recommendations.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;
    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;

    @GetMapping("/recommend-seats")
    public List<SeatDTO> recommendSeats(@RequestParam Long sessionId, @RequestParam int numberOfTickets) {
        Session session = sessionRepository.findById(sessionId).orElseThrow(() -> new RuntimeException("Session not found"));
        return bookingService.recommendSeats(session, numberOfTickets);
    }

    @PostMapping("/book")
    public ResponseEntity<List<Booking>> bookSeats(@RequestBody @Valid BookingDTO request) {
        Session session = sessionRepository.findById(request.getSessionId()).orElseThrow(() -> new RuntimeException("Session not found"));
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        List<Booking> bookings = bookingService.bookSeats(session, request.getRow(), request.getSeats(), user);
        return ResponseEntity.ok(bookings);
    }
}
