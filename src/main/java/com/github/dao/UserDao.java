package com.github.dao;

import com.github.entity.User;
import com.github.exceptions.user.NoSuchUserException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserDao implements IUserDao {

    private final Set<User> users;

    public UserDao() {
        this.users = new HashSet<>();
    }

    @Override
    public void createUser(User user) {
        users.add(user);
    }

    @Override
    public User readUser(User user) {
        return getEntity(user);
    }

    @Override
    public User updateUserEmail(User user, String email) {
        User someone = getEntity(user);
        someone.setEmail(email);
        return someone;
    }

    @Override
    public void deleteUser(User user) {
        users.remove(getEntity(user));
    }

    private User getEntity(User usr) {
        return users.stream()
                .filter(user -> user.equals(usr))
                .findFirst()
                .orElseThrow(() -> new NoSuchUserException("There is no such user;"));
    }
}
