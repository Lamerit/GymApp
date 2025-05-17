package com.keygen.gymapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.keygen.gymapi.entity.Gym;

public interface GymRepository extends JpaRepository<Gym, Long> {
}
