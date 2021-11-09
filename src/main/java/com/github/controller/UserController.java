package com.github.controller;

import com.github.dto.UserDto;
import com.github.mappers.JsonMapper;
import com.github.service.IUserService;
import org.springframework.stereotype.Component;

@Component
public class UserController {

    private final IUserService iUserService;

    public UserController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    public void createUser(UserDto userDto) {
        iUserService.createUser(userDto);
    }

    public UserDto readUser(UserDto userDto) {
        return iUserService.readUser(userDto);
    }

    public UserDto updateUserEmail(UserDto userDto, String email) {
        return iUserService.updateUserEmail(userDto, email);
    }

    public void deleteUser(UserDto userDto) {
        iUserService.deleteUser(userDto);
    }
}
