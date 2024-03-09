package com.cinema.recommendations.init;

import com.cinema.recommendations.entity.*;
import com.cinema.recommendations.repository.*;
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

@Component
@RequiredArgsConstructor
public class SessionInitializer implements ApplicationRunner {

    private final SessionRepository sessionRepository;

    private final FilmRepository filmRepository;

    private final TheaterRepository theaterRepository;

    private final LanguageRepository languageRepository;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        initializeSessions();
    }

    private void initializeSessions() {
        if (sessionRepository.count() == 0) {
            List<Film> films = filmRepository.findAll();
            List<Theater> theaters = theaterRepository.findAll();
            List<Language> languages = languageRepository.findAll();

            LocalDate startDate = LocalDate.now();
            Random random = new Random();

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
}
