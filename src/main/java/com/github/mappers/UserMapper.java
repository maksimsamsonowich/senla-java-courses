package com.github.mappers;

import com.github.dto.UserDto;
import com.github.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserMapper {

    @Autowired
    private ModelMapper mapper;

    public User toEntity(UserDto userDTO) {
        return Objects.isNull(userDTO) ? null : mapper.map(userDTO, User.class);
    }

    public UserDto toDto(User entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, UserDto.class);
    }
}
