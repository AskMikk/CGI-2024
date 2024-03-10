package com.cinema.recommendations.controller;

import com.cinema.recommendations.model.SessionDTO;
import com.cinema.recommendations.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public List<SessionDTO> getSessionsByUserId(@PathVariable Long userId) {
        return userService.findSessionsByUserId(userId);
    }

    @GetMapping("/{userId}/recommendations")
    public List<SessionDTO> getRecommendationsBasedOnHistory(@PathVariable Long userId) {
        return userService.recommendSessionsBasedOnHistory(userId);
    }
}
