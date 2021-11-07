package com.github.controller;

import com.github.dto.UserDto;

public interface IUserController {

    void createUser(UserDto userDto);
    UserDto readUser(UserDto userDto);
    UserDto updateUserEmail(UserDto userDto, String email);
    void deleteUser(UserDto userDto);

}
