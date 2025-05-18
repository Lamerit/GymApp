package com.keygen.gymapi.mapper;

import com.keygen.gymapi.dto.UserRequestDto;
import com.keygen.gymapi.dto.UserResponseDto;
import com.keygen.gymapi.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // ─── Entity → Response DTO ────────────────────────────────
    @Mapping(source = "gym.id",       target = "gymId")
    @Mapping(source = "coach.id",     target = "coachId")
    @Mapping(source = "role.id",      target = "roleId")
    @Mapping(source = "isCheckedIn",  target = "checkedIn")
    UserResponseDto toResponse(User user);

    // ─── Request DTO → Entity ────────────────────────────────
    @Mapping(source = "gymId",        target = "gym.id")
    @Mapping(source = "coachId",      target = "coach.id")
    @Mapping(source = "roleId",       target = "role.id")
    @Mapping(source = "checkedIn",    target = "isCheckedIn")
    User toEntity(UserRequestDto dto);
}
