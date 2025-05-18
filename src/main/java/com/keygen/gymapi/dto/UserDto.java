package com.keygen.gymapi.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserDto {
    private Long id;

    @NotBlank @Size(min = 2, max = 100)
    private String fullName;

    @NotBlank @Email
    private String email;

    @NotBlank
    private String passwordHash;

    @NotBlank @Pattern(regexp = "05\\d{9}")
    private String phoneNumber;

    @NotNull @Past
    private LocalDate birthDate;

    @NotBlank
    private String gender;

    private Boolean isCheckedIn;
    private LocalDateTime lastCheckinTime;

    @NotNull
    private Integer coachId;
    @NotNull
    private Integer roleId;
    @NotNull
    private Integer gymId;

    // getters & setters (IntelliJ generate ile ekle)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
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
        return isCheckedIn;
    }

    public void setCheckedIn(Boolean checkedIn) {
        isCheckedIn = checkedIn;
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

    public Integer getGymId() {
        return gymId;
    }

    public void setGymId(Integer gymId) {
        this.gymId = gymId;
    }

    public Integer getCoachId() {
        return coachId;
    }

    public void setCoachId(Integer coachId) {
        this.coachId = coachId;
    }

}
