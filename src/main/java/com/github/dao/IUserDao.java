package com.github.dao;

import com.github.entity.User;

import java.util.List;

public interface IUserDao {

    String getSomething();
    List<User> getUsers();

    void deleteUser(String login, String password);

    void updateLogin(String login, String newLogin);
    void updatePassword(String login, String password);
    void updateEmail(String login, String email);

    String getEmail(String login);
    void createUser(User user);
}
