package com.cinema.recommendations.repository;

import com.cinema.recommendations.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long> {
}