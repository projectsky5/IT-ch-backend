package com.projectsky.IT_ch_backend.mapper;

import com.projectsky.IT_ch_backend.dto.UserDto;
import com.projectsky.IT_ch_backend.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(UserDto userDto);
}
