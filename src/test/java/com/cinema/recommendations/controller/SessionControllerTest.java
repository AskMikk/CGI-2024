package com.cinema.recommendations.controller;

import com.cinema.recommendations.model.SeatDTO;
import com.cinema.recommendations.model.SessionDTO;
import com.cinema.recommendations.service.BookingService;
import com.cinema.recommendations.service.SessionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SessionController.class)
public class SessionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SessionService sessionService;

    @MockBean
    private BookingService bookingService;

    @Test
    public void getAllUpcomingSessionsSuccess() throws Exception {
        given(sessionService.getAllUpcomingSessions()).willReturn(List.of(new SessionDTO()));

        mockMvc.perform(get("/api/session/upcoming"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void getAllBookingsForSessionSuccess() throws Exception {
        given(bookingService.getOccupiedSeatsForSession(anyLong())).willReturn(List.of(new SeatDTO()));

        mockMvc.perform(get("/api/session/{sessionId}/bookings", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void getSessionByIdSuccess() throws Exception {
        given(sessionService.getSessionById(anyLong())).willReturn(new SessionDTO());

        mockMvc.perform(get("/api/session/{sessionId}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }}
