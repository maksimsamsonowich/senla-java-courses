package com.github.dao;

import com.github.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@AllArgsConstructor
public class UserDao implements IAbstractDao<User> {

    @Override
    public void create(User object) {

    }

    @Override
    public User read(User object) {
        return null;
    }

    @Override
    public User update(User object) {
        return null;
    }

    @Override
    public void delete(User object) {

    }
}
