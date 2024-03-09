package com.cinema.recommendations.repository;

import com.cinema.recommendations.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Long> {
}
