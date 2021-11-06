package com.github.dao;

import com.github.entity.User;

public interface IUserDao {

    void createUser(User user);
    User readUser(User user);
    User updateUserEmail(User user, String email);
    void deleteUser(User user);

}
