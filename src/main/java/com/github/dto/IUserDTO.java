package com.github.dto;

import com.github.entity.User;

public interface IUserDTO {

    void deleteUser(String login, String password);

    void updateLogin(String login, String newLogin);
    void updatePassword(String login, String password);
    void updateEmail(String login, String email);

    String getEmail(String login);
    void createUser(String login, String password, String email);

}
