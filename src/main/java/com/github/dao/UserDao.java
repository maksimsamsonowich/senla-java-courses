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
    public User update(User user, String email) {
        User someone = super.update(user);
        someone.setEmail(email);
        return someone;
    }

}
