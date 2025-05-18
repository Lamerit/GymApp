package com.keygen.gymapi.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserRequestDto {
    @NotBlank @Size(min = 2, max = 100)
    private String fullName;

    @NotBlank @Email
    private String email;

    @NotBlank @Size(min = 6, message = "Şifre en az 6 karakter olmalı")
    private String password;

    @NotBlank @Pattern(regexp = "05\\d{9}", message = "Telefon 05XXXXXXXXX formatında olmalı")
    private String phoneNumber;

    @NotNull @Past
    private LocalDate birthDate;

    @NotBlank
    private String gender;

    private Boolean checkedIn;
    private LocalDateTime lastCheckinTime;

    @NotNull
    private Integer roleId;
    @NotNull
    private Long gymId;
    @NotNull
    private Long coachId;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Boolean getCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(Boolean checkedIn) {
        this.checkedIn = checkedIn;
    }

    public LocalDateTime getLastCheckinTime() {
        return lastCheckinTime;
    }

    public void setLastCheckinTime(LocalDateTime lastCheckinTime) {
        this.lastCheckinTime = lastCheckinTime;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Long getGymId() {
        return gymId;
    }

    public void setGymId(Long gymId) {
        this.gymId = gymId;
    }

    public Long getCoachId() {
        return coachId;
    }

    public void setCoachId(Long coachId) {
        this.coachId = coachId;
    }
// getter & setter’lar…

}
