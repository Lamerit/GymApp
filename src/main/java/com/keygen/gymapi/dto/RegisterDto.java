package com.keygen.gymapi.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class RegisterDto {
    @NotBlank @Email
    private String email;

    @NotBlank @Size(min=6, message="Şifre en az 6 karakter olmalı")
    private String password;

    @NotBlank
    private String fullName;

    @NotBlank(message="Telefon numarası boş olamaz")
    @Pattern(regexp="05\\d{9}", message="Telefon 05XXXXXXXXX formatında olmalı")
    private String phoneNumber;

    @NotNull(message="Doğum tarihi girilmeli")
    @Past(message="Geçmiş bir tarih olmalı")
    private LocalDate birthDate;

    @NotBlank(message="Gender girilmeli")
    private String gender;

    // Opsiyonel: eğer roleId, gym ve coach DTO’da isterseniz:
    private Integer roleId;
    private Long gymId;
    private Long coachId;

    // ─── Getter & Setter ───────────────────────────────────────────
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public Integer getRoleId() { return roleId; }
    public void setRoleId(Integer roleId) { this.roleId = roleId; }

    public Long getGymId() { return gymId; }
    public void setGymId(Long gymId) { this.gymId = gymId; }

    public Long getCoachId() { return coachId; }
    public void setCoachId(Long coachId) { this.coachId = coachId; }
}
