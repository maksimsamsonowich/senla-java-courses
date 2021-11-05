package com.github.dao;

import com.github.entity.User;
import com.github.exceptions.NoSuchUserException;
import com.github.exceptions.WrongPasswordException;
import com.github.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@Repository
public class UserDao implements IUserDao {

    @Value("param.value")
    private String something;

    private List<User> users;

    public UserDao() {
        users = new ArrayList<>();
    }

    @Override
    public String getSomething(){
        return something;
    }

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public void deleteUser(String login, String password) {
        if (getAccess(login, password))
            users.remove(getEntity(login));
        else
            throw new WrongPasswordException("Wrong password");

    }

    @Override
    public void updateLogin(String login, String newLogin) {
        getEntity(login).setLogin(newLogin);
    }

    @Override
    public void updatePassword(String login, String password) {
        getEntity(login).setPassword(password);
    }

    @Override
    public void updateEmail(String login, String email) {
        getEntity(login).setEmail(email);
    }

    @Override
    public String getEmail(String login) {
        return getEntity(login).getEmail();
    }

    @Override
    public void createUser(User user) {
        users.add(user);
    }

    private User getEntity(String login) {
        return users.stream()
                .filter(user -> Objects.equals(user.getLogin(), login))
                .findFirst()
                .orElseThrow(() -> new NoSuchUserException("There is no users with " + login + " login."));
    }

    private boolean getAccess(String login, String password) {
        return Objects.equals(getEntity(login).getPassword(), password);
    }
}
