package com.cinema.recommendations.repository;

import com.cinema.recommendations.entity.Booking;
import com.cinema.recommendations.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository  extends JpaRepository<Booking, Long> {
    int countBySession(Session session);
    List<Booking> findBySessionId(Long sessionId);
    List<Booking> findByUserId(Long userId);
}

