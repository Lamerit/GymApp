package com.keygen.gymapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import com.keygen.gymapi.entity.User;
import com.keygen.gymapi.dto.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({
            @Mapping(source = "gym.id",   target = "gymId"),
            @Mapping(source = "coach.id", target = "coachId"),
            @Mapping(source = "isCheckedIn", target = "checkedIn")
    })
    UserDto toDto(User user);

    @Mappings({
            @Mapping(source = "gymId",   target = "gym.id"),
            @Mapping(source = "coachId", target = "coach.id"),
            @Mapping(source = "checkedIn", target = "isCheckedIn")
    })
    User toEntity(UserDto dto);
}