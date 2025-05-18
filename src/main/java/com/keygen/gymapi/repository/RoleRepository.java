package com.keygen.gymapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.keygen.gymapi.entity.Role;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(String name);
}
