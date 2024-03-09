package com.cinema.recommendations.repository;

import com.cinema.recommendations.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
