package com.github.controller;

import com.github.dto.UserDto;
import com.github.mappers.JsonMapper;
import com.github.service.IUserService;
import org.springframework.stereotype.Component;

@Component
public class UserController implements IUserController {

    private final IUserService iUserService;

    public UserController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @Override
    public void createUser(UserDto userDto) {
        iUserService.createUser(userDto);
    }

    @Override
    public UserDto readUser(UserDto userDto) {
        return iUserService.readUser(userDto);
    }

    @Override
    public UserDto updateUserEmail(UserDto userDto, String email) {
        return iUserService.updateUserEmail(userDto, email);
    }

    @Override
    public void deleteUser(UserDto userDto) {
        iUserService.deleteUser(userDto);
    }
}
