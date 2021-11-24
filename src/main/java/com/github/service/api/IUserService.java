package com.github.service.api;

import com.github.dto.UserDto;

public interface IUserService {

    void createUser(UserDto user);

    UserDto readUser(UserDto user);

    UserDto update(UserDto user);

    void deleteUser(UserDto user);

}
