package com.cinema.recommendations.repository;

import com.cinema.recommendations.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheaterRepository extends JpaRepository<Theater, Long> {
}