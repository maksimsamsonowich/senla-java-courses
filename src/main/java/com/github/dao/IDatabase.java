package com.github.dao;

import com.github.entity.User;

import java.util.List;

public interface IDatabase {

    String getSomething();
    List<User> getUsers();
}
