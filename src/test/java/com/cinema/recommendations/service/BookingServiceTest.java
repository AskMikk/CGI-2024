package com.cinema.recommendations.service;

import com.cinema.recommendations.entity.Booking;
import com.cinema.recommendations.entity.Session;
import com.cinema.recommendations.entity.Theater;
import com.cinema.recommendations.entity.User;
import com.cinema.recommendations.model.SeatDTO;
import com.cinema.recommendations.repository.BookingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingService bookingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBookSeats() {
        Session session = new Session();
        User user = new User();
        int row = 1;
        List<Integer> seats = Arrays.asList(1, 2);
        when(bookingRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
        List<Booking> result = bookingService.bookSeats(session, row, seats, user);
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(bookingRepository, times(2)).save(any(Booking.class));
    }

    @Test
    void testGetOccupiedSeatsForSession() {
        Long sessionId = 1L;
        List<Booking> bookings = Arrays.asList(
                new Booking(null, new Session(), new User(), 1, 1, new Date()),
                new Booking(null, new Session(), new User(), 1, 2, new Date())
        );
        when(bookingRepository.findBySessionId(sessionId)).thenReturn(bookings);
        List<SeatDTO> occupiedSeats = bookingService.getOccupiedSeatsForSession(sessionId);
        assertNotNull(occupiedSeats);
        assertEquals(2, occupiedSeats.size());
        assertEquals(1, occupiedSeats.get(0).getRow());
        assertEquals(1, occupiedSeats.get(0).getSeat());
        assertEquals(1, occupiedSeats.get(1).getRow());
        assertEquals(2, occupiedSeats.get(1).getSeat());
    }

    @Test
    void testRecommendSeats() {
        Session session = new Session();
        session.setId(1L);
        Theater theater = new Theater();
        theater.setNumberOfRows(5);
        theater.setSeatsPerRow(20);
        session.setTheater(theater);

        List<Booking> occupiedBookings = Arrays.asList(
                new Booking(null, session, null, 3, 9, new Date()),
                new Booking(null, session, null, 3, 10, new Date())
        );
        when(bookingRepository.findBySessionId(session.getId())).thenReturn(occupiedBookings);

        int numberOfTickets = 5;
        List<SeatDTO> recommendedSeats = bookingService.recommendSeats(session, numberOfTickets);

        assertEquals(5, recommendedSeats.size());
        assertEquals(new SeatDTO(3, 12), recommendedSeats.get(0));
        assertEquals(new SeatDTO(2, 12), recommendedSeats.get(1));
        assertEquals(new SeatDTO(3, 11), recommendedSeats.get(2));
        assertEquals(new SeatDTO(3, 13), recommendedSeats.get(3));
        assertEquals(new SeatDTO(4, 12), recommendedSeats.get(4));
    }

}
