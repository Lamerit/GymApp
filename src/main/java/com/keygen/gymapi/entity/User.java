package com.keygen.gymapi.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.validation.constraints.*;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;


@Entity
@Table(name = "users")
public class User {

    public void setId(Long id) {
        this.id = id;
    }
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Full name boş olamaz")
    @Size(min = 2, max = 100, message = "İsim 2–100 karakter arası olmalı")
    private String fullName;


    @NotBlank(message = "Email boş olamaz")
    @Email(message = "Geçerli bir email girin")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Password boş olamaz")
    private String passwordHash;

    @NotBlank(message = "Telefon numarası boş olamaz")
    @Pattern(regexp = "05\\d{9}", message = "Telefon 05XXXXXXXXX formatında olmalı")
    private String phoneNumber;

    @NotNull(message = "Doğum tarihi girilmeli")
    @Past(message = "Doğum tarihi geçmiş bir tarih olmalı")
    private LocalDate birthDate;

    @NotBlank(message = "Gender girilmeli")
    private String gender;

    private Boolean isCheckedIn;

    private LocalDateTime lastCheckinTime;

    @NotNull(message = "Role girilmeli")
    private Integer roleId;

    @ManyToOne
    @JoinColumn(name = "gym_id", nullable = false)
    private Gym gym;

    @ManyToOne
    @JoinColumn(name = "coach_id", nullable = false)
    private Coach coach;

    // ─── Getters & Setters ──────────────────────────────────────────────────────
    public Long getId() { return id; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public Boolean getIsCheckedIn() { return isCheckedIn; }
    public void setIsCheckedIn(Boolean isCheckedIn) { this.isCheckedIn = isCheckedIn; }
    public LocalDateTime getLastCheckinTime() { return lastCheckinTime; }
    public void setLastCheckinTime(LocalDateTime lastCheckinTime) { this.lastCheckinTime = lastCheckinTime; }
    public Gym getGym() {return gym;}
    public void setGym(Gym gym) {this.gym = gym;}
    public Coach getCoach() {return coach;}
    public void setCoach(Coach coach) {this.coach = coach;}
}
