package com.github.service.api;

import com.github.dto.UserDto;

public interface IUserService {

    UserDto createUser(UserDto user);

    UserDto readUser(Integer id);

    UserDto update(UserDto user);

    void deleteUser(UserDto user);

}
