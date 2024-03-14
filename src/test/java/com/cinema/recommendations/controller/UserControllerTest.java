package com.cinema.recommendations.controller;

import com.cinema.recommendations.model.SessionDTO;
import com.cinema.recommendations.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Aja säästmiseks pärast esimese kirjutamist tehti ülejäänud tehisintilekti abil koopiana.
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void getSessionsByUserIdSuccess() throws Exception {
        given(userService.findSessionsByUserId(anyLong())).willReturn(List.of(new SessionDTO()));

        mockMvc.perform(get("/api/user/{userId}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void getRecommendationsBasedOnHistorySuccess() throws Exception {
        given(userService.recommendSessionsBasedOnHistory(anyLong())).willReturn(List.of(new SessionDTO()));

        mockMvc.perform(get("/api/user/{userId}/recommendations", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
    @Test
    public void deleteAllBookingsBySessionAndUserSuccess() throws Exception {
        doNothing().when(userService).deleteAllBookingsBySessionAndUser(anyLong(), anyLong());
        mockMvc.perform(delete("/api/user/{userId}/sessions/{sessionId}", 2L, 1L))
                .andExpect(status().isOk());
    }
}
