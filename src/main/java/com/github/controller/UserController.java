package com.github.controller;

import com.github.dto.UserDto;
import com.github.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserController {

    private final IUserService iUserService;

    public void createUser(UserDto userDto) {
        iUserService.createUser(userDto);
    }

    public UserDto readUser(UserDto userDto) {
        return iUserService.readUser(userDto);
    }

    public UserDto update(UserDto userDto) {
        return iUserService.update(userDto);
    }

    public void deleteUser(UserDto userDto) {
        iUserService.deleteUser(userDto);
    }
}
