package com.cinema.recommendations.service;

import com.cinema.recommendations.model.SessionDTO;
import com.cinema.recommendations.entity.Session;
import com.cinema.recommendations.entity.Genre;
import com.cinema.recommendations.entity.Language;
import com.cinema.recommendations.repository.BookingRepository;
import com.cinema.recommendations.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;
    private final BookingRepository bookingRepository;
    private final ModelMapper modelMapper;

    public List<SessionDTO> getAllUpcomingSessions() {
        List<Session> sessions = sessionRepository.findUpcomingSessions(new Date());
        return sessions.stream()
                .sorted(Comparator.comparing(Session::getStartTime))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public SessionDTO convertToDTO(Session session) {
        SessionDTO dto = modelMapper.map(session, SessionDTO.class);
        dto.setFilmTitle(session.getFilm().getTitle());
        dto.setGenres(session.getFilm().getGenres().stream()
                .map(Genre::getName)
                .collect(Collectors.toList()));
        dto.setAgeRestriction(session.getFilm().getAgeRestriction() != null ? session.getFilm().getAgeRestriction().getDescription() : "Not specified");
        dto.setTheaterName(session.getTheater().getName());
        dto.setLanguage(session.getLanguage() != null ? session.getLanguage().getName() : "Not specified");
        dto.setSubtitles(session.getSubtitles().stream()
                .map(Language::getName)
                .collect(Collectors.toList()));
        int bookedSeats = bookingRepository.countBySession(session);
        int totalSeats = session.getTheater().getCapacity();
        dto.setAvailableSeats(totalSeats - bookedSeats);
        dto.setNumberOfRows(session.getTheater().getNumberOfRows());
        dto.setSeatsPerRow(session.getTheater().getSeatsPerRow());
        dto.setPosterUrl(session.getFilm().getPosterUrl());
        return dto;
    }

    public SessionDTO getSessionById(Long sessionId) {
        Optional<Session> sessionOptional = sessionRepository.findById(sessionId);
        if (sessionOptional.isPresent()) {
            return convertToDTO(sessionOptional.get());
        } else {
            throw new RuntimeException("Session not found for id: " + sessionId);
        }
    }
}