package com.cinema.recommendations.service;

import com.cinema.recommendations.entity.Booking;
import com.cinema.recommendations.entity.Session;
import com.cinema.recommendations.entity.Genre;
import com.cinema.recommendations.model.SessionDTO;
import com.cinema.recommendations.repository.BookingRepository;
import com.cinema.recommendations.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final SessionRepository sessionRepository;
    private final BookingRepository bookingRepository;
    private final SessionService sessionService;

    public List<SessionDTO> findSessionsByUserId(Long userId) {
        List<Booking> bookings = bookingRepository.findByUserId(userId);
        Set<Long> sessionIds = bookings.stream()
                .map(booking -> booking.getSession().getId())
                .collect(Collectors.toSet());

        return sessionIds.stream()
                .map(sessionRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(sessionService::convertToDTO)
                .collect(Collectors.toList());
    }

    // Võib-olla oleks mõistlik lisada täiendavaid hindamisparameetreid.
    public List<SessionDTO> recommendSessionsBasedOnHistory(Long userId) {
        List<Booking> userBookings = bookingRepository.findByUserId(userId);
        Map<String, Long> genreFrequency = userBookings.stream()
                .flatMap(booking -> booking.getSession().getFilm().getGenres().stream())
                .collect(Collectors.groupingBy(Genre::getName, Collectors.counting()));

        List<String> preferredGenres = genreFrequency.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        List<Session> upcomingSessions = sessionRepository.findUpcomingSessions(new Date());
        return upcomingSessions.stream()
                .filter(session -> session.getFilm().getGenres().stream().anyMatch(genre -> preferredGenres.contains(genre.getName())))
                .sorted(Comparator.comparing(session -> calculateMatchScore(session, preferredGenres)))
                .map(sessionService::convertToDTO)
                .collect(Collectors.toList());
    }

    private int calculateMatchScore(Session session, List<String> preferredGenres) {
        int score = 0;
        for (String genre : preferredGenres) {
            if (session.getFilm().getGenres().stream().anyMatch(g -> g.getName().equals(genre))) {
                score++;
            }
        }
        return score;
    }
}
