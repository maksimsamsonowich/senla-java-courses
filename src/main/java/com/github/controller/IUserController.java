package com.github.controller;

import com.github.dto.UserDto;

public interface IUserController {

    void createUser(String jsonObject);
    UserDto readUser(String jsonObject);
    UserDto updateUserEmail(String jsonObjectr, String email);
    void deleteUser(String jsonObject);

}
