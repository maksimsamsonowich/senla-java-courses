package com.github.service;

import com.github.dto.UserDto;

public interface IUserService {

    void createUser(UserDto user);
    UserDto readUser(UserDto user);
    UserDto updateUserEmail(UserDto user, String email);
    void deleteUser(UserDto user);

}
