package com.cinema.recommendations.service;

import com.cinema.recommendations.entity.Booking;
import com.cinema.recommendations.entity.Film;
import com.cinema.recommendations.entity.Genre;
import com.cinema.recommendations.entity.Session;
import com.cinema.recommendations.model.SessionDTO;
import com.cinema.recommendations.repository.BookingRepository;
import com.cinema.recommendations.repository.SessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private SessionService sessionService;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindSessionsByUserId() {
        Long userId = 1L;
        Booking booking = new Booking();
        Session session = new Session();
        session.setId(1L);
        booking.setSession(session);

        List<Booking> bookings = Collections.singletonList(booking);
        when(bookingRepository.findByUserId(userId)).thenReturn(bookings);
        when(sessionRepository.findById(session.getId())).thenReturn(Optional.of(session));

        SessionDTO sessionDTO = new SessionDTO();
        when(sessionService.convertToDTO(any(Session.class))).thenReturn(sessionDTO);

        List<SessionDTO> result = userService.findSessionsByUserId(userId);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(bookingRepository).findByUserId(userId);
        verify(sessionRepository).findById(session.getId());
    }

    @Test
    void testRecommendSessionsBasedOnHistory() {
        Long userId = 1L;
        Booking booking = new Booking();
        Session session = new Session();
        session.setId(1L);
        Film film = new Film();
        film.setGenres(Set.of(new Genre("Seiklus"), new Genre("Draama")));
        session.setFilm(film);
        booking.setSession(session);

        List<Booking> userBookings = Collections.singletonList(booking);
        when(bookingRepository.findByUserId(userId)).thenReturn(userBookings);

        List<Session> upcomingSessions = Collections.singletonList(session);
        when(sessionRepository.findUpcomingSessions(any(Date.class))).thenReturn(upcomingSessions);

        SessionDTO sessionDTO = new SessionDTO();
        when(sessionService.convertToDTO(any(Session.class))).thenReturn(sessionDTO);

        List<SessionDTO> result = userService.recommendSessionsBasedOnHistory(userId);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(bookingRepository).findByUserId(userId);
        verify(sessionRepository).findUpcomingSessions(any(Date.class));
    }

    @Test
    void deleteAllBookingsBySessionAndUser_NoBookingsFound_ThrowsException() {
        Long userId = 2L;
        Long sessionId = 2L;
        when(bookingRepository.findByUserIdAndSessionId(userId, sessionId)).thenReturn(Collections.emptyList());

        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> userService.deleteAllBookingsBySessionAndUser(sessionId, userId));

        assertTrue(thrown.getMessage().contains("No bookings found for user id: " + userId + " and session id: " + sessionId));

        verify(bookingRepository, never()).deleteAll(any());
    }
}
