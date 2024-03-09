package com.cinema.recommendations.repository;

import com.cinema.recommendations.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Long> {
}
