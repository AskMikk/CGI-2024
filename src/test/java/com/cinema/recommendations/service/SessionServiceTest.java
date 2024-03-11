package com.cinema.recommendations.service;

import com.cinema.recommendations.entity.*;
import com.cinema.recommendations.model.SessionDTO;
import com.cinema.recommendations.repository.BookingRepository;
import com.cinema.recommendations.repository.SessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SessionServiceTest {

    @Mock
    private SessionRepository sessionRepository;
    @Mock
    private BookingRepository bookingRepository;
    @InjectMocks
    private SessionService sessionService;
    private final ModelMapper modelMapper = new ModelMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sessionService = new SessionService(sessionRepository, bookingRepository, modelMapper);
    }

    @Test
    void testGetAllUpcomingSessions() {
        List<Session> sessions = new ArrayList<>();
        when(sessionRepository.findUpcomingSessions(any(Date.class))).thenReturn(sessions);
        List<SessionDTO> result = sessionService.getAllUpcomingSessions();
        assertNotNull(result);
    }

    @Test
    void testGetSessionById() {
        Long sessionId = 1L;

        Film mockFilm = new Film();
        mockFilm.setTitle("Example Film Title");

        Genre genre1 = new Genre();
        genre1.setName("Genre1");
        Genre genre2 = new Genre();
        genre2.setName("Genre2");
        Set<Genre> genres = new HashSet<>(Arrays.asList(genre1, genre2));
        mockFilm.setGenres(genres);

        Theater mockTheater = new Theater();
        mockTheater.setName("Example Theater");
        mockTheater.setCapacity(100);
        mockTheater.setNumberOfRows(10);
        mockTheater.setSeatsPerRow(10);

        Language mockLanguage = new Language();
        mockLanguage.setName("Example Language");

        Session mockSession = new Session();
        mockSession.setId(sessionId);
        mockSession.setFilm(mockFilm);
        mockSession.setTheater(mockTheater);
        mockSession.setLanguage(mockLanguage);
        mockSession.setSubtitles(new HashSet<>(List.of(mockLanguage)));
        mockSession.setStartTime(new Date());

        Optional<Session> sessionOptional = Optional.of(mockSession);
        when(sessionRepository.findById(sessionId)).thenReturn(sessionOptional);

        SessionDTO result = sessionService.getSessionById(sessionId);
        assertNotNull(result);
        assertEquals(mockFilm.getTitle(), result.getFilmTitle());
        assertTrue(result.getGenres().containsAll(Arrays.asList("Genre1", "Genre2")));
        assertEquals(mockTheater.getName(), result.getTheaterName());
        assertEquals(100, result.getAvailableSeats().intValue());
    }

    @Test
    void testConvertToDTO() {
        Film film = new Film();
        film.setTitle("Test Film Title");
        Set<Genre> genres = new HashSet<>();
        genres.add(new Genre());
        film.setGenres(genres);
        film.setAgeRestriction(new AgeRestriction());
        film.setPosterUrl("");

        Theater theater = new Theater();
        theater.setName("Test Theater");
        theater.setCapacity(100);
        theater.setNumberOfRows(10);
        theater.setSeatsPerRow(10);

        Language language = new Language();
        language.setName("English");

        Set<Language> subtitles = new HashSet<>();
        subtitles.add(language);

        Session session = new Session();
        session.setFilm(film);
        session.setTheater(theater);
        session.setLanguage(language);
        session.setStartTime(new Date());
        session.setSubtitles(subtitles);
        SessionDTO dto = sessionService.convertToDTO(session);
        assertNotNull(dto);
    }
}
