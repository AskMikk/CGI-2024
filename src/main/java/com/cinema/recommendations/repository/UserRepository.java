package com.cinema.recommendations.repository;

import com.cinema.recommendations.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
