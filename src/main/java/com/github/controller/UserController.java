package com.github.controller;

import com.github.dto.UserDto;
import com.github.mappers.JsonMapper;
import com.github.service.IUserService;
import org.springframework.stereotype.Component;

@Component
public class UserController implements IUserController {

    private final IUserService iUserService;
    private final JsonMapper jsonMapper;

    public UserController(IUserService iUserService, JsonMapper jsonMapper) {
        this.iUserService = iUserService;
        this.jsonMapper = jsonMapper;
    }

    @Override
    public void createUser(String jsonObject) {
        iUserService.createUser(jsonMapper.toEntity(jsonObject, UserDto.class));
    }

    @Override
    public UserDto readUser(String jsonObject) {
        return iUserService.readUser(jsonMapper.toEntity(jsonObject, UserDto.class));
    }

    @Override
    public UserDto updateUserEmail(String jsonObject, String email) {
        return iUserService.updateUserEmail(jsonMapper.toEntity(jsonObject, UserDto.class), email);
    }

    @Override
    public void deleteUser(String jsonObject) {
        iUserService.deleteUser(jsonMapper.toEntity(jsonObject, UserDto.class));
    }
}
