package com.github.service.api;

import com.github.dto.UserDto;

public interface IUserService {

    UserDto createUser(Integer id, UserDto user);

    UserDto readUser(Integer id);

    UserDto update(Integer id, UserDto user);

    void deleteUser(Integer id);

}
