package com.github.service.impl;

import com.github.dto.RoleDto;
import com.github.dto.UserDto;
import com.github.entity.Role;
import com.github.entity.User;
import com.github.exception.role.RoleNotFoundException;
import com.github.exception.role.RoleRepetitionException;
import com.github.exception.user.NoSuchUserException;
import com.github.mapper.IMapper;
import com.github.repository.impl.RoleRepository;
import com.github.repository.impl.UserRepository;
import com.github.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
@AllArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

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

    @Override
    public void grantRole(Long userId, RoleDto roleDto) {

        User currentUser = userRepository.readById(userId);

        if (Objects.isNull(currentUser))
            throw new NoSuchUserException("There is no such user.");

        Role roleToGrant = roleRepository.findByName(roleDto.getRole());

        if (Objects.isNull(roleToGrant))
            throw new RoleNotFoundException("There is role mismatch :(");

        if (currentUser.getCredential().getRoles().stream()
                .anyMatch(role -> role.getRole().equals(roleToGrant.getRole())))
            throw new RoleRepetitionException("The user already has such role.");

        currentUser.getCredential().getRoles().add(roleToGrant);

        userRepository.update(currentUser);

    }
}
