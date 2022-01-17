package com.github.service;

import com.github.dto.RoleDto;
import com.github.dto.UserDto;

public interface IUserService {

    UserDto createUser(UserDto user);

    UserDto readUser(Long id);

    UserDto update(Long id, UserDto user);

    void deleteUser(Long id);

    void grantRole(Long userId, RoleDto roleDto);

}
