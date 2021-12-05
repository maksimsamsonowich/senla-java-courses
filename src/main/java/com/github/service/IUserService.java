package com.github.service;

import com.github.dto.UserDto;

public interface IUserService {

    UserDto createUser(UserDto user);

    UserDto readUser(Integer id);

    UserDto update(Integer id, UserDto user);

    void deleteUser(Integer id);

}
