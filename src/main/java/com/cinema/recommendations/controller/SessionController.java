package com.cinema.recommendations.controller;

import com.cinema.recommendations.model.SeatDTO;
import com.cinema.recommendations.model.SessionDTO;
import com.cinema.recommendations.service.BookingService;
import com.cinema.recommendations.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/session")
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;
    private final BookingService bookingService;

    @GetMapping("/upcoming")
    public List<SessionDTO> getAllUpcomingSessions() {
        return sessionService.getAllUpcomingSessions();
    }

    @GetMapping("/{sessionId}/bookings")
    public List<SeatDTO> getAllBookingsForSession(@PathVariable Long sessionId) {
        return bookingService.getOccupiedSeatsForSession(sessionId);
    }

    @GetMapping("/{sessionId}")
    public SessionDTO getSessionById(@PathVariable Long sessionId) {
        return sessionService.getSessionById(sessionId);
    }
}
