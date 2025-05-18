package com.keygen.gymapi.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserResponseDto {
    private Long id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private LocalDate birthDate;
    private String gender;
    private Boolean checkedIn;
    private LocalDateTime lastCheckinTime;
    private Integer roleId;
    private Long gymId;
    private Long coachId;

    // getter & setter’lar…
}
