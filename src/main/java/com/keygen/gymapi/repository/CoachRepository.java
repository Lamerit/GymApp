package com.keygen.gymapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.keygen.gymapi.entity.Coach;

public interface CoachRepository extends JpaRepository<Coach, Long> {
}
