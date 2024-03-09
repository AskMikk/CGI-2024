package com.cinema.recommendations.repository;

import com.cinema.recommendations.entity.AgeRestriction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgeRestrictionRepository extends JpaRepository<AgeRestriction, Long> {
}