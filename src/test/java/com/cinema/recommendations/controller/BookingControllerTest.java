package com.cinema.recommendations.controller;

import com.cinema.recommendations.entity.Session;
import com.cinema.recommendations.entity.User;
import com.cinema.recommendations.model.SeatDTO;
import com.cinema.recommendations.repository.SessionRepository;
import com.cinema.recommendations.repository.UserRepository;
import com.cinema.recommendations.service.BookingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(BookingController.class)
public class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingService bookingService;

    @MockBean
    private SessionRepository sessionRepository;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void recommendSeatsSuccess() throws Exception {
        given(sessionRepository.findById(any())).willReturn(Optional.of(new Session()));
        given(bookingService.recommendSeats(any(Session.class), any(Integer.class))).willReturn(List.of(new SeatDTO()));

        mockMvc.perform(get("/api/booking/recommend-seats?sessionId=1&numberOfTickets=2"))
                .andExpect(status().isOk());
    }

    @Test
    public void bookSeatsSuccess() throws Exception {
        given(sessionRepository.findById(any())).willReturn(Optional.of(new Session()));
        given(userRepository.findById(any())).willReturn(Optional.of(new User()));
        given(bookingService.bookSeats(any(Session.class), any(Integer.class), any(List.class), any(User.class))).willReturn(List.of());

        mockMvc.perform(post("/api/booking/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"sessionId\": 1, \"userId\": 1, \"row\": 1, \"seats\": [1, 2, 3]}"))
                .andExpect(status().isOk());
    }
}
