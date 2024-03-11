package com.cinema.recommendations.init;

import com.cinema.recommendations.entity.*;
import com.cinema.recommendations.model.SeatDTO;
import com.cinema.recommendations.repository.*;
import com.cinema.recommendations.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

// Algselt ei tahtnud ma kahte lähtestamisfunktsiooni ühte faili ühendada. Kuid initsialiseerimisetapis ilmnes tõrkeid isegi @Order kasutamisel
@Component
@RequiredArgsConstructor
public class SessionAndBookingInitializer implements ApplicationRunner {

    private final SessionRepository sessionRepository;

    private final FilmRepository filmRepository;

    private final TheaterRepository theaterRepository;

    private final LanguageRepository languageRepository;

    private final UserRepository userRepository;

    private final BookingService bookingService;

    private final BookingRepository bookingRepository;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        initializeSessions();
        initializeBookings();
    }

    private void initializeSessions() {
        if (sessionRepository.count() == 0) {
            List<Film> films = filmRepository.findAll();
            List<Theater> theaters = theaterRepository.findAll();
            List<Language> languages = languageRepository.findAll();

            LocalDate startDate = LocalDate.now();
            Random random = new Random();

            // Aja säästmiseks kirjutatud tehisintellektiga, siin pole keerulist loogikat
            for (int day = 0; day < 7; day++) {
                for (Theater theater : theaters) {
                    LocalTime timeSlot = LocalTime.of(8, 0);

                    while (timeSlot.isBefore(LocalTime.of(21, 0))) {
                        Film film = films.get(random.nextInt(films.size()));
                        Language language = languages.get(random.nextInt(languages.size()));
                        LocalDateTime sessionDateTime = LocalDateTime.of(startDate.plusDays(day), timeSlot);
                        Date startTimeDate = Date.from(sessionDateTime.atZone(ZoneId.systemDefault()).toInstant());

                        Session session = new Session();
                        session.setFilm(film);
                        session.setTheater(theater);
                        session.setStartTime(startTimeDate);
                        session.setLanguage(language);

                        Set<Language> subtitles = new HashSet<>(languages);
                        subtitles.remove(language);
                        session.setSubtitles(subtitles);
                        sessionRepository.save(session);

                        int increment = 120 + 30 * random.nextInt(3);
                        timeSlot = timeSlot.plusMinutes(increment);
                    }
                }
            }
        }
    }

    private void initializeBookings() {
        if (bookingRepository.count() == 0) {
            List<Session> sessions = sessionRepository.findAll();
            User user = userRepository.findById(1L).orElse(null);
            if (user == null) return;

            Random random = new Random();

            for (Session session : sessions) {
                int numberOfBookings = 1 + random.nextInt(10);

                for (int i = 0; i < numberOfBookings; i++) {
                    int numberOfTickets = 1 + random.nextInt(5);

                    List<SeatDTO> recommendedSeats = bookingService.recommendSeats(session, numberOfTickets);
                    if (!recommendedSeats.isEmpty()) {
                        int row = recommendedSeats.getFirst().getRow();
                        List<Integer> seatsToBook = recommendedSeats.stream().map(SeatDTO::getSeat).collect(Collectors.toList());
                        bookingService.bookSeats(session, row, seatsToBook, user);
                    }
                }
            }

            // Kood oli vajalik filmisoovitusalgoritmi testimiseks
            User user2 = userRepository.findById(2L).orElse(null);
            List<Session> pastSessions = sessionRepository.findAll().stream()
                    .filter(session -> session.getStartTime().before(new Date()))
                    .collect(Collectors.toList());
            if (user2 != null && !pastSessions.isEmpty()) {

                Collections.shuffle(pastSessions);
                for (int i = 0; i < 3; i++) {
                    Session session = pastSessions.get(i);
                    int numberOfTickets = 1 + random.nextInt(5);
                    List<SeatDTO> recommendedSeats = bookingService.recommendSeats(session, numberOfTickets);
                    if (!recommendedSeats.isEmpty()) {
                        int row = recommendedSeats.getFirst().getRow();
                        List<Integer> seatsToBook = recommendedSeats.stream().map(SeatDTO::getSeat).collect(Collectors.toList());
                        bookingService.bookSeats(session, row, seatsToBook, user2);
                    }
                }
            }
        }
    }
}
