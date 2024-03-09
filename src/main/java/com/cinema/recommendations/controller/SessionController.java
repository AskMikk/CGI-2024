package com.cinema.recommendations.controller;

import com.cinema.recommendations.model.SessionDTO;
import com.cinema.recommendations.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/session")
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;

    @GetMapping("/upcoming")
    public List<SessionDTO> getAllUpcomingSessions() {
        return sessionService.getAllUpcomingSessions();
    }
}
