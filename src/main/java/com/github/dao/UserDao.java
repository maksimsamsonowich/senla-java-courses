package com.github.dao;

import com.github.entity.User;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UserDao extends Dao<User> {

    public UserDao(Set<User> users) {
        super(users);
    }

    @Override
    public User update(User user) {
        User someone = super.update(user);
        someone.setEmail(user.getEmail());
        someone.setPassword(user.getPassword());
        someone.setLogin(user.getLogin());
        return someone;
    }
}
