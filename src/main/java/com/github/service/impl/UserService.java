package com.github.service.impl;

import com.github.dto.UserDto;
import com.github.entity.User;
import com.github.mapper.IMapper;
import com.github.repository.impl.UserRepository;
import com.github.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;

    private final IMapper<UserDto, User> userMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User currentUser = userMapper.toEntity(userDto, User.class);

        userRepository.create(currentUser);

        return userMapper.toDto(currentUser, UserDto.class);
    }

    @Override
    public UserDto readUser(Long userId) {
        User currentUser = userRepository.readById(userId);

        return userMapper.toDto(currentUser, UserDto.class);
    }

    @Override
    public UserDto update(Long userId, UserDto userDto) {
        userDto.setId(userId);
        User currentUser = userMapper.toEntity(userDto, User.class);

        userRepository.update(currentUser);

        return userMapper.toDto(currentUser, UserDto.class);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
