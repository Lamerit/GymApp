package com.keygen.gymapi.controller;

import com.keygen.gymapi.dto.UserRequestDto;
import com.keygen.gymapi.dto.UserResponseDto;
import com.keygen.gymapi.entity.User;
import com.keygen.gymapi.entity.Gym;
import com.keygen.gymapi.entity.Coach;
import com.keygen.gymapi.entity.Role;
import com.keygen.gymapi.mapper.UserMapper;
import com.keygen.gymapi.repository.UserRepository;
import com.keygen.gymapi.repository.GymRepository;
import com.keygen.gymapi.repository.CoachRepository;
import com.keygen.gymapi.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import jakarta.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepo;
    private final GymRepository gymRepo;
    private final CoachRepository coachRepo;
    private final RoleRepository roleRepo;
    private final UserMapper mapper;

    @Autowired
    public UserController(UserRepository userRepo,
                          GymRepository gymRepo,
                          CoachRepository coachRepo,
                          RoleRepository roleRepo,
                          UserMapper mapper) {
        this.userRepo  = userRepo;
        this.gymRepo   = gymRepo;
        this.coachRepo = coachRepo;
        this.roleRepo  = roleRepo;
        this.mapper    = mapper;
    }

    // ─── Create ────────────────────────────────────────────────────────────────
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(
            @Valid @RequestBody UserRequestDto dto) {

        // Map DTO → Entity
        User user = mapper.toEntity(dto);

        // Role ilişkilendirmesi
        Role role = roleRepo.findById(dto.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found: " + dto.getRoleId()));
        user.setRole(role);

        // Gym ilişkilendirmesi
        Gym gym = gymRepo.findById(dto.getGymId())
                .orElseThrow(() -> new RuntimeException("Gym not found: " + dto.getGymId()));
        user.setGym(gym);

        // Coach ilişkilendirmesi
        Coach coach = coachRepo.findById(dto.getCoachId())
                .orElseThrow(() -> new RuntimeException("Coach not found: " + dto.getCoachId()));
        user.setCoach(coach);

        // Kaydet ve Response DTO’ya çevir
        User saved = userRepo.save(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.toResponse(saved));
    }

    // ─── List All ──────────────────────────────────────────────────────────────
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> list = userRepo.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
        return ResponseEntity.ok(list);
    }

    // ─── Get By ID ─────────────────────────────────────────────────────────────
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        return userRepo.findById(id)
                .map(mapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ─── Update ─────────────────────────────────────────────────────────────────
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserRequestDto dto) {

        return userRepo.findById(id)
                .map(existing -> {
                    // Map DTO → yeni User entity (ID’yi set edeceğiz)
                    User user = mapper.toEntity(dto);
                    user.setId(id);

                    // Role
                    Role role = roleRepo.findById(dto.getRoleId())
                            .orElseThrow(() -> new RuntimeException("Role not found: " + dto.getRoleId()));
                    user.setRole(role);

                    // Gym
                    Gym gym = gymRepo.findById(dto.getGymId())
                            .orElseThrow(() -> new RuntimeException("Gym not found: " + dto.getGymId()));
                    user.setGym(gym);

                    // Coach
                    Coach coach = coachRepo.findById(dto.getCoachId())
                            .orElseThrow(() -> new RuntimeException("Coach not found: " + dto.getCoachId()));
                    user.setCoach(coach);

                    // Kaydet
                    User updated = userRepo.save(user);
                    return mapper.toResponse(updated);
                })
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ─── Delete ─────────────────────────────────────────────────────────────────
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
