package com.keygen.gymapi.controller;
import com.keygen.gymapi.entity.Role;
import com.keygen.gymapi.repository.RoleRepository;
import com.keygen.gymapi.config.JwtUtill;
import com.keygen.gymapi.dto.RegisterDto;
import com.keygen.gymapi.entity.Coach;
import com.keygen.gymapi.entity.Gym;
import com.keygen.gymapi.entity.User;
import com.keygen.gymapi.repository.CoachRepository;
import com.keygen.gymapi.repository.GymRepository;
import com.keygen.gymapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private GymRepository gymRepo;        // ← ekle
    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private CoachRepository coachRepo;    // ← ekle

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtill jwtUtill;


    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> login(@RequestBody Map<String,String> creds) {
        try {
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.get("email"), creds.get("password")
                    )
            );
            UserDetails user = userDetailsService.loadUserByUsername(creds.get("email"));
            String token = jwtUtill.generateToken(user);
            return ResponseEntity.ok(Map.of("token", token));
        } catch (AuthenticationException ex) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error","Unauthorized","message","Bad credentials"));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String,String>> register(@Valid @RequestBody RegisterDto dto) {
        if (userRepo.findByEmail(dto.getEmail()).isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(Map.of("error","Conflict","message","Bu e-posta zaten kayıtlı"));
        }

        // Default ROLE_USER rolünü çekiyoruz
        Role userRole = roleRepo.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("ROLE_USER bulunamadı"));

        User user = new User();
        user.setEmail(dto.getEmail());
        user.setFullName(dto.getFullName());
        user.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setBirthDate(dto.getBirthDate());
        user.setGender(dto.getGender());
        user.setRole(userRole);  // eski setRoleId yerine bunu kullan

        // Gym ilişkilendirmesi:
        if (dto.getGymId() != null) {
            Gym gym = gymRepo.findById(dto.getGymId()).orElse(null);
            user.setGym(gym);
        }

        // Coach ilişkilendirmesi:
        if (dto.getCoachId() != null) {
            Coach coach = coachRepo.findById(dto.getCoachId()).orElse(null);
            user.setCoach(coach);
        }

        userRepo.save(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of("message","Kayıt başarılı"));
    }

}
