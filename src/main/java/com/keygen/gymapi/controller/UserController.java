package com.keygen.gymapi.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import com.keygen.gymapi.entity.User;
import com.keygen.gymapi.entity.Gym;
import com.keygen.gymapi.entity.Coach;
import com.keygen.gymapi.dto.UserDto;
import com.keygen.gymapi.mapper.UserMapper;
import com.keygen.gymapi.repository.UserRepository;
import com.keygen.gymapi.repository.GymRepository;
import com.keygen.gymapi.repository.CoachRepository;


@Validated
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepo;
    private final GymRepository gymRepo;
    private final CoachRepository coachRepo;
    private final UserMapper mapper;

    @Autowired
    public UserController(UserRepository userRepo,
                          GymRepository gymRepo,
                          CoachRepository coachRepo,
                          UserMapper mapper) {
        this.userRepo = userRepo;
        this.gymRepo = gymRepo;
        this.coachRepo = coachRepo;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto dto) {
        User user = mapper.toEntity(dto);

        // Convert Integer gymId/coachId to Long for findById
        Long gymId = dto.getGymId().longValue();
        Long coachId = dto.getCoachId().longValue();
        user.setRoleId(       dto.getRoleId());
        user.setIsCheckedIn(  dto.getCheckedIn());
        user.setLastCheckinTime(dto.getLastCheckinTime());

        Gym gym = gymRepo.findById(gymId)
                .orElseThrow(() -> new RuntimeException("Gym not found: " + gymId));
        Coach coach = coachRepo.findById(coachId)
                .orElseThrow(() -> new RuntimeException("Coach not found: " + coachId));

        user.setGym(gym);
        user.setCoach(coach);

        User saved = userRepo.save(user);
        return ResponseEntity.ok(mapper.toDto(saved));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> list = userRepo.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return userRepo.findById(id)
                .map(mapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id,
                                              @Valid @RequestBody UserDto dto) {
        // Convert gymId/coachId
        Long gymId = dto.getGymId().longValue();
        Long coachId = dto.getCoachId().longValue();

        return userRepo.findById(id)
                .map(existing -> {
                    User user = mapper.toEntity(dto);
                    user.setId(id);

                    Gym gym = gymRepo.findById(gymId)
                            .orElseThrow(() -> new RuntimeException("Gym not found: " + gymId));
                    Coach coach = coachRepo.findById(coachId)
                            .orElseThrow(() -> new RuntimeException("Coach not found: " + coachId));

                    user.setGym(gym);
                    user.setCoach(coach);

                    User updated = userRepo.save(user);
                    return mapper.toDto(updated);
                })
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
